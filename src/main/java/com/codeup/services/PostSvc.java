package com.codeup.services;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HKoehler on 2/9/17.
 */

@Service("postSvc")
public class PostSvc {

    private List<Post> posts = new ArrayList<>();

    public PostSvc (){
        createPosts();
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post save (Post post){
        post.setId(posts.size()+1);
        posts.add(post);
        return post;
    }

    public Post findOne(int id){
        return posts.get(id-1);
    }

    private void createPosts() {
        for(int i = 0; i < 50; i++){
            save(new Post("title" + (i+1), "Body Content" + (i+2)));
        }
    }


}


// Create a PostSvc class and inject it into your posts controller.
// The service should keep an array list of posts internally, and have methods for
// --> finding a post (retrieving an individual post object)
// --> retrieving all the posts
//
// Create a few test posts so that you have some data that you can test.
// Use the posts service to pass all the posts and individual posts to the corresponding views.


// FORM MODEL BINDING EXERCISE
// Add a save method to your PostsSvc.
// It should take in a Post object, set it's id property, and add it to the list of posts.
//