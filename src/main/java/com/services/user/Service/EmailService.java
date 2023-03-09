package com.services.user.Service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    public void sendEmail(SimpleMailMessage email);
}
