package com.example.StudentSystem.MeetBooking.templateController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class LoginPageController {

    @GetMapping("login")
    public String loginPage(){
        return "login";
    }
}
