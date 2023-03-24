package com.services.user.Service;

import com.services.user.Entity.ConfirmationToken;
import com.services.user.Entity.User;
import com.services.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository repository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private EmailService emailService;

    private ConfirmationTokenService service;
    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUSer(User user) {
        repository.delete(user);
    }

    @Override
    public void signUpUser(User user) {

        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        final User createdUser = repository.save(user);

        final ConfirmationToken confirmationToken = new ConfirmationToken(user);

        service.saveConfirmationToken(confirmationToken);
    }
    @Override
    public void confirmUser(ConfirmationToken confirmationToken) {
        final User user = confirmationToken.getUser();

        user.setEnabled(true);

        repository.save(user);

        service.deleteConfirmationToken(confirmationToken.getId());
    }

    @Override
    public void sendConfirmationMail(String userMail, String token) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation Link!");
        mailMessage.setFrom("emailnotalk@gmail.com");
        mailMessage.setText(
                "Thank you for registering. Please click on the below link to active your account."
                + "http://localhost:8080/sing-up/confirm?token=" + token
        );
        emailService.sendEmail(mailMessage);
    }

    //    Below is the implemented UserDetailService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional is a class where it can contain a non-null
//        (which it will be considered present or can contain
//        no value at all(it will be considered empty
        final Optional<User> optionalUser = repository.findByEmail(email);

        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
    }
}
