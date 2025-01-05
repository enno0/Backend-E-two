package com.exa.demotwo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/manage_users")
    public String manage_users() {
        return "manage_users";
    }

    @GetMapping("/user-list")
    public String userList() {
        return "user_list";
    }

    @GetMapping("/home")
    public String logout() {
        return "home";
    }

}
