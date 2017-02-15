package com.codeup.services;

import com.codeup.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by HKoehler on 2/15/17.
 */

@Service("usersSvc")
public class UserSvc {

    public boolean isLoggedIn(){
        boolean isAnonymouseUser = SecurityContextHolder.getContext().getAuthentication()
                instanceof AnonymousAuthenticationToken;
        return ! isAnonymouseUser;
    }

    public User loggedInUser() {
        if (! isLoggedIn()) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
