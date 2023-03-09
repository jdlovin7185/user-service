package com.services.user.Service;

import com.services.user.Entity.ConfirmationToken;
import com.services.user.Entity.User;
import com.services.user.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        final String encryptedPassword = BCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        final User createdUser = repository.save(user);

        final ConfirmationToken confirmationToken = new ConfirmationToken(user);

        service.saveConfirmationToken(confirmationToken);
    }

    @Override
    public User loginUser(User user) {
        return null;
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
