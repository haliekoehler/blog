package com.codeup.models;

import javax.persistence.*;

/**
 * Created by HKoehler on 2/13/17.
 */

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "role")
    private String role;

}
