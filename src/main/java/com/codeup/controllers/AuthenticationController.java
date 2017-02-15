package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.models.UserRole;
import com.codeup.repositories.RolesRepository;
import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by HKoehler on 2/13/17.
 */

@Controller
public class AuthenticationController {

    // preparing class to use users repository
    private UsersRepository repository;
    // preparing to encode passwords
    private PasswordEncoder encoder;
    // preparing class to use roles repository
    private RolesRepository roles;

    // use constructor to inject above variables
    @Autowired
    public AuthenticationController(UsersRepository repository, PasswordEncoder encoder, RolesRepository roles){
        this.repository = repository;
        this.encoder = encoder;
        this.roles = roles;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        System.out.println(new BCryptPasswordEncoder().encode("password"));
        return "login";
    }

    @GetMapping("users/register")
    public String showFrom(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    // creating a new User object
    // @ModelAttribute will do this for you

    @PostMapping("users/register")
    public String registerUser(
            @Valid User user, // <- create user from input values and apply validations, replaced @ModelAttribute with @Valid
            Errors validation,
            Model viewModel,
            @RequestParam(name = "password_confirm") String passwordConfirmation){

        // checks password & password_confirm match
        if(!passwordConfirmation.equals(user.getPassword())){
            validation.rejectValue("password", "user.password", "Passwords do not match");
        }

        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/register";
        }

        String hashedPassword = encoder.encode(user.getPassword()); // <- use encoder to hash password & save in variable
        user.setPassword(hashedPassword); // <- save hashedPassword as users password in DB
        User newUser = repository.save(user); // <- use repository defined about to save user

        UserRole ur = new UserRole();
        ur.setRole("ROLE_USER");
        ur.setUserId(newUser.getId());
        roles.save(ur);

        return "redirect:/login"; // <-redirect user to login page
    }
}
