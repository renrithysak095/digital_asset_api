package com.example.digital_asset_project.mail;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    int verifyCode(String account) throws MessagingException;
    int resetPassword(String account) throws MessagingException;
}
