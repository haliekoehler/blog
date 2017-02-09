package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HKoehler on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostSvc postSvc;


    @GetMapping("/posts")
    public String allPosts (Model model){

        List<Post> posts = postSvc.findAll(); // <--- Create List using .findAll() method from PostService
        model.addAttribute("posts", posts);

        return "/posts/index"; // <-- index.html
    }

    @GetMapping("/posts/{id}")
    public String postsById (@PathVariable int id, Model model){

        model.addAttribute("post", postSvc.findOne(id)); // <--- using .findOne() method from PostService

        return "/posts/show";
    }


    @GetMapping("/posts/create")
    public String getCreatePosts(Model model){
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String postCreatePosts(@ModelAttribute Post post){
        //  submit a post object and store that post with the posts service.


        return "/posts/show";
    }
}
