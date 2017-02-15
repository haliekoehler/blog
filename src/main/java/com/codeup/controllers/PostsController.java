package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by HKoehler on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    private PostsRepository postsRepositoryDao;

    public PostsController(PostsRepository postsRepositoryDao) {
        this.postsRepositoryDao = postsRepositoryDao;
    }

    @GetMapping("/posts")
    public String allPosts (
            Model model){
//        model.addAttribute("posts", postsRepositoryDao.findAll());
        model.addAttribute("posts", Collections.emptyList());
        return "/posts/index"; // <-- index.html
    }

    @GetMapping("/posts.json")
    public @ResponseBody List<Post> all(){
        return (List<Post>) postsRepositoryDao.findAll();
    }

    @GetMapping("/posts/{id}")
    public String postsById (
            @PathVariable int id,
            Model model){
        model.addAttribute("post", postsRepositoryDao.findOne(id)); // <--- using .findOne() method from PostService
        return "/posts/show";
    }


    @GetMapping("/posts/create")
    public String view(
            @ModelAttribute Post post,
            Model model){
        model.addAttribute("post", post);
        return "/posts/create";
    }

    @Value("${uploads}")
    private String uploadsPath;

    // @Valid call Model Attribute first
    @PostMapping("/posts/create")
    public String savePost(
            @Valid Post post,
            Errors validation,
            Model model,
            @RequestParam(name = "image_file")MultipartFile uploadedFile){

        // if there are errors in the form
        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

        String filename = uploadedFile.getOriginalFilename();
        System.out.println(filename);
        String filepath = Paths.get(uploadsPath, filename).toString();
        System.out.println(filepath);

        File destinationFile = new File(filepath);

        try {
            uploadedFile.transferTo(destinationFile); // <- will move the file in this step
        } catch (IOException e) {
            e.printStackTrace();
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(user);
        post.setImage(filename);
        postsRepositoryDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(
            @PathVariable int id,
            @ModelAttribute Post post,
            Model model){
        model.addAttribute("post", postsRepositoryDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(
            @ModelAttribute Post post,
            Model model){
        postsRepositoryDao.save(post);
        model.addAttribute("post", post);
        return "redirect:/posts";
    }
}
