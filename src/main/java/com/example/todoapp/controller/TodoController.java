package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.security.UserPrincipal;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/hello")
    public Map<String, String> hello(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        String email = userPrincipal.getEmail();
        Long id = userPrincipal.getId();
        System.out.println(id + " " + email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World2323");
        return response;
    }
    @GetMapping()
    public List<TodoDto> getAllTodos(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return todoService.getAllTodos(userPrincipal.getId());
    }

    @PostMapping()
    public TodoDto saveTodo(@RequestBody TodoDto todoDto, @AuthenticationPrincipal UserPrincipal userPrincipal){
        return todoService.saveTodo(todoDto, userPrincipal.getId());
    }

}
