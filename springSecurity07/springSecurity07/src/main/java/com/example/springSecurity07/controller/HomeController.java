package com.example.springSecurity07.controller;

import com.example.springSecurity07.entity.User;
import com.example.springSecurity07.repo.UserRepo;
import com.example.springSecurity07.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @ModelAttribute
    public void customUser(Principal p,Model m){
        if (p!=null){
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user",user);
        }

    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/signin")
    public String login(){
        return "login";
    }
    @GetMapping("/user/profile")
    public String profile(Principal p, Model m){
        String email = p.getName();
        User user = userRepo.findByEmail(email);
         m.addAttribute("user",user);

        return "profile";
    }
    @GetMapping("/user/home")
    public String home(){
        return "home";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session){
       // System.out.println(user);
        User u = userService.save(user);
        if (u!=null){
            session.setAttribute("msg","register successfully");
        }
        else {
            session.setAttribute("msg","server down");
        }
        return "redirect:/register";
    }
}
