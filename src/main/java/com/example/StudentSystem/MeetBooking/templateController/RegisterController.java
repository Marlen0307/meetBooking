package com.example.StudentSystem.MeetBooking.templateController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegisterController {

    @GetMapping("/signUp")
    public String registrationForm(){
        return "signUp";
    }
}
