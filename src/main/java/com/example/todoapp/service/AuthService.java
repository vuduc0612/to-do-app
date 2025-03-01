package com.example.todoapp.service;

import com.example.todoapp.dto.AuthDto;
import com.example.todoapp.dto.LoginDto;
import org.springframework.stereotype.Service;


public interface AuthService {
    String signup(AuthDto authDto);
    String signin(LoginDto authDto);

}
