package com.services.user.config;

import com.services.user.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfiguration {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers
    }
}
