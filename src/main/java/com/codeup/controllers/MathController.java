package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HKoehler on 2/7/17.
 */

@Controller
public class MathController {

    @GetMapping("/add/{a}/and/{b}")
    @ResponseBody
    public String addSum (@PathVariable int a, @PathVariable int b){
        return "<h1>" + (a + b) + "</h1>";
    }

    @GetMapping("/subtract/{a}/from/{b}")
    @ResponseBody
    public String subSum (@PathVariable int a, @PathVariable int b){
        return "<h1>" + (b - a) + "</h1>";
    }

    @GetMapping("/multiply/{a}/and/{b}")
    @ResponseBody
    public String multSum (@PathVariable int a, @PathVariable int b){
        return "<h1>" + (a * b) + "</h1>";
    }

    @GetMapping("/divide/6/by/3")
    @ResponseBody
    public String divSum(@PathVariable int a, @PathVariable int b){
        return "<h1>" + (a / b) + "</h1>";
    }
}
