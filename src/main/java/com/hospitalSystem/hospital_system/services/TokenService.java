package com.hospitalSystem.hospital_system.services;

import com.hospitalSystem.hospital_system.api.model.Token;
import com.hospitalSystem.hospital_system.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Token save(Token confirmationToken) {
        return tokenRepository.save(confirmationToken);
    }
}
