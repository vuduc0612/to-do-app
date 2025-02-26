package com.example.todoapp.controller;

import com.example.todoapp.dto.AuthDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody AuthDto authDto) {
        return authDto.toString();
    }

}
