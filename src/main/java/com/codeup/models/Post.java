package com.codeup.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by HKoehler on 2/8/17.
 */

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String body;

    // will define your foreign key
    @ManyToOne
    @JoinColumn (name = "post_id") // defined at the table level
    private User user; // owner, author, etc.



    // CONSTRUCTORS
    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(){

    }


    // GETTERS / SETTERS

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
