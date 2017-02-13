package com.codeup.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by HKoehler on 2/13/17.
 */

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm(){
        System.out.println(new BCryptPasswordEncoder().encode("password"));
        return "login";
    }

}
