package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.converters.UserConverter;
import com.javamentor.qa.platform.dao.abstracts.model.UserDao;
import com.javamentor.qa.platform.models.dto.UserRegistrationDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.RegistrationService;
import com.javamentor.qa.platform.service.abstracts.model.EmailService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.abstracts.model.RoleService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl extends ReadWriteServiceImpl<User, Long> implements RegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final EmailService emailService;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Value("${registration.code.lifetime}")
    private int EXPIRATION_TIME_IN_MINUTES;
    @Value("${sender.address}")
    private String fromAddress;
    @Value("${sender.name}")
    private String senderName;
    @Value("${app.host}")
    private String host;


    public RegistrationServiceImpl(UserDao userDao, EmailServiceImpl emailService, UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        super(userDao);
        this.userService = userService;
        this.roleService = roleService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerUser(UserRegistrationDto userRegistrationDto) {

        if (userService.getByEmail(userRegistrationDto.getEmail()).isPresent()) {
            LOGGER.info("Пользователь с email - {} уже существует", userRegistrationDto.getEmail());
            return false;
        }

        User user = UserConverter.INSTANCE.userRegistrationDtoToUser(userRegistrationDto);
        if (roleService.getByName("ROLE_USER").isPresent()) {
            user.setRole(roleService.getByName("ROLE_USER").get());
            user.setIsEnabled(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.persist(user);
        }
        String token = String.valueOf((userRegistrationDto.getEmail().hashCode() * 31L));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderName + " <" + fromAddress + ">");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : " + host + "api/user/registration/verify?token=" + token + "&email=" + user.getEmail());
        emailService.sendEmail(mailMessage);
        LOGGER.info("Регистрационная ссылка отправлена на email - {}", userRegistrationDto.getEmail());
        return true;
    }

    @Override
    public boolean confirmEmail(String confirmationToken, String email) {
        if (userService.getByEmail(email).isPresent()) {
            User user = userService.getByEmail(email).get();
            if (LocalDateTime.now().isBefore(user.getPersistDateTime().plusMinutes(EXPIRATION_TIME_IN_MINUTES)) &&
                    confirmationToken.equals(String.valueOf(user.getEmail().hashCode() * 31L))) {
                user.setIsEnabled(true);
                userService.update(user);
                return true;
            }
        }
        LOGGER.info("Произошла ошибка при верификации пользователя");
        return false;
    }

}
