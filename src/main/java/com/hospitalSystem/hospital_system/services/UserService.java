package com.hospitalSystem.hospital_system.services;

import com.hospitalSystem.hospital_system.api.model.Token;
import com.hospitalSystem.hospital_system.api.model.User;
import com.hospitalSystem.hospital_system.repository.UserRepository;
import com.hospitalSystem.hospital_system.services.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final EmailService emailService;



    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void registerUser(User user) {
        //check the database if the user with the same email address
        //or username already exist
        userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("User already exists");
                });
        // if user with the username or email address does not exist
        //the save the user
        // first encypt the password
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);

        // send an email with validation link
        // user click the link to confirm
        // after confirmation the user becomes enabled
        Token confirmationToken = new Token(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        tokenService.save(confirmationToken);
        // send email
        emailService.send(user.getEmail(), confirmationToken.getToken());

    }

    public void confirmToken(String token) {
        //1.check if the token exist
        Token confirmedToken = tokenService.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        //2. check if user already verified
        if(confirmedToken.getConfirmedAt() != null){
            throw new IllegalArgumentException("Token already confirmed");
        }

        //3. check if token expired
        LocalDateTime expiresAt = confirmedToken.getExpiresAt();
        if(expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Token expired");
        }

        // if all is ok, then update confirmation time
        confirmedToken.setConfirmedAt(LocalDateTime.now());
        tokenService.save(confirmedToken);

        // enable user
        enableUser(confirmedToken.getUser());
    }

    private void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
