package com.example.shardingsphere2.controller;

import com.example.shardingsphere2.entity.User;
import com.example.shardingsphere2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/select")
    public List<User> select(){
        return userService.getUserList();
    }

    @GetMapping("/insert")
    public Boolean insert(User user){
        return userService.save(user);
    }
}
