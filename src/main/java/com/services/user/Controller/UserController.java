package com.services.user.Controller;

import com.services.user.Entity.ConfirmationToken;
import com.services.user.Entity.User;
import com.services.user.Service.ConfirmationTokenService;
import com.services.user.Service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserServiceImpl service;


    private final ConfirmationTokenService confirmationTokenService;

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

    @GetMapping("/sign-in")
    String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    String signUp(){
        return "sign-up";
    }

    @PostMapping("sign-up")
    String signUp(User user){
        service.signUpUser(user);

        return "redirect:/sign-in";
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {
        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(service::confirmUser);

        return "/sign-in";
    }
}
