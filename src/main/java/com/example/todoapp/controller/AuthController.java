package com.example.todoapp.controller;

import com.example.todoapp.dto.AuthDto;
import com.example.todoapp.dto.LoginDto;
import com.example.todoapp.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody AuthDto authDto) {
        System.out.println(authDto.getEmail() + " " + authDto.getPassword());
        return authService.signup(authDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginDto loginDto) {
        String token = authService.signin(loginDto);
        System.out.println(token);
        return ResponseEntity.ok(token);
    }
}
