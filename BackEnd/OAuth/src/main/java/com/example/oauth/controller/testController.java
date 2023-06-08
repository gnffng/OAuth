package com.example.oauth.controller;

import com.example.oauth.repository.AccessTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @GetMapping("/test")
    public String testGet(){
        return "hello";
    }

    @PostMapping("/test")
    public String testPost(){
        return "hello";
    }


}
