package com.services.user.Service;

import com.services.user.Entity.ConfirmationToken;
import com.services.user.Entity.User;

public interface UserService {


    User addUser(User user);
    User updateUser(User user);
    void deleteUSer(User user);
    void signUpUser(User user);

    void confirmUser(ConfirmationToken confirmationToken);

    void sendConfirmationMail(String userMail, String token);
}
