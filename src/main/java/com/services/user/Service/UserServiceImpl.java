package com.services.user.Service;

import com.services.user.Entity.User;
import com.services.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
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
    public User loginUser(User user) {
        return null;
    }
}
