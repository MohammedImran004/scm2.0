package com.scm.scm20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scm.scm20.service.UserService;
import com.scm.scm20.entities.User;
import com.scm.scm20.proforms.userForm;

@Controller
public class pageController {
    @Autowired
    private UserService userService;
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new userForm());  // FIXED
        return "register";
    }

    @PostMapping("/do-register")
    public String ProcessRegister(@ModelAttribute userForm UserForm) {
        System.out.println(UserForm);  
        User user = User.builder()
                .username(UserForm.getName())
                .email(UserForm.getEmail())
                .password(UserForm.getPassword())
                .about(UserForm.getAbout())
                .profileLink("default.png")
                .phoneNumber(UserForm.getPhoneNumber())
                .build();
        User saveduser = userService.saveUser(user);
        System.out.println(saveduser);
        System.out.println("Processing registration...");
        return "redirect:/register";
    }
}
