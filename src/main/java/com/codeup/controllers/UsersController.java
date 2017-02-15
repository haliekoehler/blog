package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.models.UserRole;
import com.codeup.repositories.RolesRepository;
import com.codeup.repositories.UsersRepository;
import com.codeup.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by HKoehler on 2/13/17.
 */

@Controller
@RequestMapping("users")
public class UsersController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    UserSvc userSvc;

    @Autowired
    public UsersController (UsersRepository usersRepository, UserSvc userSvc, PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.userSvc = userSvc;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/{id}")
    public String showUserInfo(@PathVariable Integer id, Model model){
        User user = usersRepository.findById(id);
        model.addAttribute("user", user);
        return "users/show";
    }

}
