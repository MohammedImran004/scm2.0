package com.scm.scm20.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm20.entities.User;
import com.scm.scm20.helpers.Message;
import com.scm.scm20.helpers.MessageType;
import com.scm.scm20.proforms.userForm;
import com.scm.scm20.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
        model.addAttribute("userForm", new userForm()); // FIXED
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute userForm UserForm,
            BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");

        if (rBindingResult.hasErrors()) {
            return "register";
        }
        // if (userService.existsByPassword(UserForm.getPassword())) {
        // // Attach manual error to the field
        // rBindingResult.rejectValue("password", "error.password", "Password already
        // exists");
        // return "register";
        // }
        User user = User.builder()
                .name(UserForm.getName())
                .email(UserForm.getEmail())
                .password(UserForm.getPassword())
                .about(UserForm.getAbout())
                .profilePic("default.png")
                .phoneNumber(UserForm.getPhoneNumber())
                .build();

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);

        // ✅ Set success message in session
        session.setAttribute("message", new Message("Successfully registered!", MessageType.green));

        return "redirect:/register";
    }

}
