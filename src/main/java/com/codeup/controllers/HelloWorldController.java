package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
/**
 * Created by HKoehler on 2/7/17.
 */

@Controller
public class HelloWorldController {

    @GetMapping("/home")
    public String homePage(Model model){

        ArrayList<String> names = new ArrayList<>();
        names.add("Cartman");
        names.add("Kyle");
        names.add("Stan");
        names.add("Kenny");

        model.addAttribute("date", "Feb 7th");
        model.addAttribute("names", names);
        return "home"; // < --- home.html
    }

    @GetMapping("/contact")
    public String contactPage(){
        return "contact/form";
    }

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
