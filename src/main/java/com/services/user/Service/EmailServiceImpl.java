package com.services.user.Service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService{

    private JavaMailSender sender;
    @Override
    public void sendEmail(SimpleMailMessage email) {
        sender.send(email);
    }
}
