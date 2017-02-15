package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by HKoehler on 2/13/17.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Must enter a username")
    @Size(min = 4, message = "username must be at least 4 characters long")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Must enter a password")
    @Size(min = 8, message="Must have at least 8 characters")
    @JsonIgnore // <- use when there is never a reason to show this in JS
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Enter an email address")
    @Email(message = "Enter a valid email address")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user") // defined at the object level
    @JsonBackReference
    private List<Post> posts;  // these are all the ads created by this user

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // pattern
    // COPY CONSTRUCTOR -> an alternative to "clone"
    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
        posts = user.posts;
    }

    public User() {

    }


    // GETTERS / SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
