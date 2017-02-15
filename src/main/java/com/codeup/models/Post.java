package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(nullable = false, length = 5000)
    @NotBlank(message = "Description can not be empty")
    @Size(min = 5, message = "Description must have at least 5 characters")
    private String body;

    // will define your foreign key
    @ManyToOne
    @JoinColumn (name = "post_id") // defined at the table level
    @JsonManagedReference
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
