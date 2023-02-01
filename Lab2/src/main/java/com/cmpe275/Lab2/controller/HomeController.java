package com.cmpe275.Lab2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        String s = "Hi\n";
        s += "Visit https://docs.google.com/document/d/1rxf_uhg05AqdYxbFonSvmr4tD04yqbF4v5iqPr5nNbs/edit for instructions.";
        return s;
    }
}
