package com.hospitalSystem.hospital_system.api.controller;

import com.hospitalSystem.hospital_system.api.model.User;
import com.hospitalSystem.hospital_system.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class AuthenticationController {


    public final UserService customUserDetailsService;

    public AuthenticationController(UserService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping
    public String postUser(@ModelAttribute("user") User user, Model model, RedirectAttributes redirectAttributes) {
        //save user to database
        //send the confirmation email
        customUserDetailsService.registerUser(user);
        redirectAttributes.addFlashAttribute("success", "Please confirm your email address");
        return "redirect:/register";
    }

    @GetMapping("/confirmToken")
    public String confirmToken(@RequestParam("token") String token, Model model) {
        customUserDetailsService.confirmToken(token);
        return "confirmToken";
    }
}
