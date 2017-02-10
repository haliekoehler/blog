package com.codeup.repositories;

import com.codeup.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by HKoehler on 2/10/17.
 */
@Repository
public interface Posts extends CrudRepository <Post, Integer> {

}
