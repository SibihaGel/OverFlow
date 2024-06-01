package com.javamentor.qa.platform.service.abstracts.model;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage mailMessage);
}
