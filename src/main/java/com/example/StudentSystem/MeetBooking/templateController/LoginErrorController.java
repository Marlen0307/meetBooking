package com.example.StudentSystem.MeetBooking.templateController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@Controller
public class LoginErrorController {

    @PostMapping("loginError")
    public String loginError(){
        System.out.println("failed login");
        return "loginError";
    }


}
