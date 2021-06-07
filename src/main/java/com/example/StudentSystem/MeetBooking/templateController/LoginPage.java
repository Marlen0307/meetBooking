package com.example.StudentSystem.MeetBooking.templateController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/")
public class LoginPage {

    @GetMapping("login")
    public String showLoginForm(){
        return "login";
    }
}
