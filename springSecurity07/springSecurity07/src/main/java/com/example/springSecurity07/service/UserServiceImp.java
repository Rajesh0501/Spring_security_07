package com.example.springSecurity07.service;

import com.example.springSecurity07.entity.User;
import com.example.springSecurity07.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User save(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");
        return userRepo.save(user);
    }

    @Override
    public void removeSessionMsg() {
       HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
       session.removeAttribute("msg");
    }
}
