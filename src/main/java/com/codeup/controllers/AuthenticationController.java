package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by HKoehler on 2/13/17.
 */

@Controller
public class AuthenticationController {

    // preparing class to use repository
    private UsersRepository repository;
    // preparing to encode passwords
    private PasswordEncoder encoder;

    // use constructor to inject above variables
    @Autowired
    public AuthenticationController(UsersRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        System.out.println(new BCryptPasswordEncoder().encode("password"));
        return "login";
    }

    @GetMapping("/register")
    public String showFrom(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    // creating a new User object
    // @ModelAttribute will do this for you
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){ // <- create user from input values

        String hashedPassword = encoder.encode(user.getPassword()); // <- use encoder to hash password & save in variable
        user.setPassword(hashedPassword); // <- save hashedPassword as users password in DB

        repository.save(user); // <- use repository defined about to save user

        return "redirect:/login"; // <-redirect user to login page
    }
}
