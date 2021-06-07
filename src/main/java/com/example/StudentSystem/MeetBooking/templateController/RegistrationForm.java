package com.example.StudentSystem.MeetBooking.templateController;

import com.example.StudentSystem.MeetBooking.registration.RegistrationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegistrationForm {
        @GetMapping("welcome")
        public String signUpForm(){
            return "signUp";
        }

}
//@Controller
//@RequestMapping("/signUp")
//public class RegistrationController {
//
//    @GetMapping()
//    public String showForm(Model model){
//        UserDto userDto = new UserDto();
//        model.addAttribute("user", userDto);
//        return "signUp";
//    }
//
//    @PostMapping()
//    public String greetingSubmit(@ModelAttribute UserDto userDto, Model model) {
//        model.addAttribute("user", userDto);
//        System.out.println(model.getAttribute(userDto.getFirstName()));
//        return "result";
//    }
//}