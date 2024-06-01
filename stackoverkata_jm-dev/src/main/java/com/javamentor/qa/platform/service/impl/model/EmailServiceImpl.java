package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

  
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendEmail(SimpleMailMessage mailMessage) {

        mailSender.send(mailMessage);
        System.out.println("Mail Send...");


    }

}
