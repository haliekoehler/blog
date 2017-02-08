package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by HKoehler on 2/8/17.
 */

@Controller
public class DiceRollingController {

    @GetMapping("/roll-dice")
    public String rollDicePage(){
        return "roll-dice";        // <--- roll-dice.html
    }

    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public String compareNumbers(@PathVariable int n){
        int userNumber = n;
        int randNumber = diceRoll();

        if (userNumber == randNumber){
            return match(userNumber, randNumber);
         } else {
            return noMatch(userNumber, randNumber);
        }
    }

    private int diceRoll (){
        Random rand = new Random();
        // 6 maximum, 1 minimum
        return rand.nextInt(6) + 1;
    }

    private String match(int userNumber, int randNumber){
        return "<h1>It was a match!</h1>" +
                "<h2>Your Guess: " + userNumber + "</h2>" +
                "<h2>Dice Roll: " + randNumber + "</h2>";
    }

    private String noMatch(int userNumber, int randNumber){
        return  "<h1>It was a match!</h1>" +
                "<h2>Your Guess: " + userNumber + "</h2>" +
                "<h2>Dice Roll: " + randNumber + "</h2>";
    }


}
