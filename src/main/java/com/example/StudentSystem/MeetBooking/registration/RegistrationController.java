package com.example.StudentSystem.MeetBooking.registration;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @PostMapping
//    public String register(@ModelAttribute @Valid RegistrationRequest request, Model model,
//                         HttpServletResponse httpResponse , BindingResult bindingResult) throws IOException {
//        if (bindingResult.hasErrors()){
//            return "signUp";
//        }
//        model.addAttribute("request", request);
//        try{
//            registrationService.register(request);
//            httpResponse.sendRedirect("/welcome");
//        }catch (Exception e){
//            httpResponse.sendRedirect("/welcome");
//        }
//        return "";
//    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping()
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
