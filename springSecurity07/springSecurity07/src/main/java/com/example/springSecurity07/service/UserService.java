package com.example.springSecurity07.service;

import com.example.springSecurity07.entity.User;

public interface UserService {

    public User save(User user);
    public void removeSessionMsg();
}
