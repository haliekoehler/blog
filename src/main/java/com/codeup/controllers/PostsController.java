package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by HKoehler on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostsRepository postsRepositoryDao;

    public PostsController(PostsRepository postsRepositoryDao) {
        this.postsRepositoryDao = postsRepositoryDao;
    }

    @GetMapping("/posts")
    public String allPosts (Model model){
        model.addAttribute("posts", postsRepositoryDao.findAll());
        return "/posts/index"; // <-- index.html
    }

    @GetMapping("/posts/{id}")
    public String postsById (@PathVariable int id, Model model){
        model.addAttribute("post", postsRepositoryDao.findOne(id)); // <--- using .findOne() method from PostService
        return "/posts/show";
    }


    @GetMapping("/posts/create")
    public String view(@ModelAttribute Post post, Model model){
        model.addAttribute("post", post);
        return "/posts/create";
    }

    // @Valid call Model Attribute first
    @PostMapping("/posts/create")
    public String create(@Valid Post post, Errors validation, Model model){

        // if there are errors in the form
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(user);
        postsRepositoryDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable int id, @ModelAttribute Post post, Model model){
        model.addAttribute("post", postsRepositoryDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post, Model model){
        postsRepositoryDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }
}
