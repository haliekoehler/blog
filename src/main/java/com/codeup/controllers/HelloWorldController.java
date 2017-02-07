package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HKoehler on 2/7/17.
 */

@Controller
public class HelloWorldController {

    @GetMapping("/hello/{name}") // <--- Anything coming after second "/" is stored in a variable
    @ResponseBody

    public String hello(@PathVariable String name){
        return formatGreeting(name);
    }

    private String formatGreeting(String name){
        return "<h1>Hello " + name + " from Spring!</h1>";
    }

    @RequestMapping(path = "/bye/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String bye(@PathVariable String name){
        return "<h1>Goodbye " + name + "! from Spring</h1>";
    }

}
