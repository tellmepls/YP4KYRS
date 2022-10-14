package com.example.Vlarionov.controlers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mainControls {

    @GetMapping("/")
    public String index(){
        return ("index");
    }
    @GetMapping("/main/")
    public String home(Model model,
                       @RequestParam(name = "firstNumber", defaultValue = "1") int firstNumber,
                       @RequestParam(name = "secondNumber", defaultValue = "1") int secondNumber,
                       @RequestParam(name="sss")  String name){
        model.addAttribute("results", method(firstNumber,secondNumber,name));
        return ("home");
    }
    @PostMapping("/main/")
    public String homePost(Model model,
                       @RequestParam(name = "firstNumber", defaultValue = "1") int firstNumber,
                       @RequestParam(name = "secondNumber", defaultValue = "1") int secondNumber,
                       @RequestParam(name="sss")  String name){
        model.addAttribute("results", method(firstNumber,secondNumber,name));
        return ("home");
    }

    private int method(int firstNumber, int secondNumber, String sss){
        int results;
        switch (sss){
            case  "+" -> results = firstNumber + secondNumber;
            case "-" -> results = firstNumber - secondNumber;
            case "*" -> results = firstNumber * secondNumber;
            default -> results = firstNumber / secondNumber;

        }
        return results;
    }
}
