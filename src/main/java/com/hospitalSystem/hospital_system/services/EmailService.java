package com.hospitalSystem.hospital_system.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void send(String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom("richard.ndondo@gmail.com");
            message.setSubject("Confirm your email");
            String messageBody = """
                    Thank you for your registration. Please confirm your email.
                    http://localhost:8080/register/confirmToken?token=%s
                    """.formatted(token);
            message.setText(messageBody);
            mailSender.send(message);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
