package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String getCreatePosts(){
        return "<h1>view the form for creating a post</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postCreatePosts(){
        return "<h3>create a new post</h3>";
    }
}
