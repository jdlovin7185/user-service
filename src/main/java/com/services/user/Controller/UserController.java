package com.services.user.Controller;

import com.services.user.Entity.User;
import com.services.user.Service.UserService;
import com.services.user.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }
    @PutMapping("/")
    public User updateUser(@RequestBody User user){
       return service.updateUser(user);
    }
    @DeleteMapping("/")
    public void deleteUser(@RequestBody User user){
        service.deleteUSer(user);
    }

    @GetMapping("/")
    public String basicTest(){
        return "Hey lil punk";
    }
}
