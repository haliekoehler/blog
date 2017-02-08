package com.codeup.controllers;

import com.codeup.models.Post;
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

    @GetMapping("/posts")
    public String allPosts (Model model){

        // array List with several posts
        List<Post> posts = new ArrayList<>();

        // add posts too list
        posts.add(new Post("First Post", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mi diam, iaculis blandit sapien ut, placerat vestibulum urna. Praesent imperdiet magna mauris, porttitor dictum purus volutpat suscipit. Praesent vulputate lacinia elementum. Mauris congue risus eget blandit venenatis. Suspendisse bibendum, justo non dictum ultricies, nisl tellus rhoncus dui, nec laoreet diam."));
        posts.add(new Post("Second Post", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eu lectus tincidunt, condimentum ante laoreet, bibendum libero. Mauris venenatis nibh et nunc condimentum, sit amet facilisis sapien dignissim. Pellentesque feugiat."));
        model.addAttribute("posts", posts);

       return "/posts/index"; // <-- index.html
    }

    @GetMapping("/posts/{id}")
    public String postsById (@PathVariable long id, Model model){
        Post post = new Post("First Post", "Text body");
        model.addAttribute("post", post);
        return "/posts/show";
    }



// -------- Ryan's Code -------------
//    @GetMapping("/posts")
//    public String viewAllPosts(Model model) {
//        // array list with several post objects
//        List<Post> posts = new ArrayList<>();
//        // pass the list to the view (through a view model)
//        posts.add(new Post("My first post", "body of first post"));
//        posts.add(new Post("this is also a post", "this is the body"));
//        model.addAttribute("ListOfPosts", posts);
//        return "/posts/index";
//    } // index.html
//    @GetMapping("/posts/{id}")
//    public String viewSinglePost(@PathVariable long id, Model model) {
////        Inside the method that shows an individual post, create a new post object and pass it to the view.
//        Post post = new Post("Hello World", "World body");
//        model.addAttribute("post", post);
//        return "/posts/show"; // show.html
//    }


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
