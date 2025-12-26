package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User register(String username, String password);
}
