package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.repositories.Posts;
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
    Posts postsDao;

    public PostsController(Posts postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String allPosts (Model model){
        model.addAttribute("posts", postsDao.findAll());
        return "/posts/index"; // <-- index.html
    }

    @GetMapping("/posts/{id}")
    public String postsById (@PathVariable int id, Model model){
        model.addAttribute("post", postsDao.findOne(id)); // <--- using .findOne() method from PostService
        return "/posts/show";
    }


    @GetMapping("/posts/create")
    public String view(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post, Model model){
        postsDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable int id, @ModelAttribute Post post, Model model){
        model.addAttribute("post", postsDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post, Model model){
        postsDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }
}
