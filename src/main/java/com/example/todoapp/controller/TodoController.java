package com.example.todoapp.controller;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World2323");
        return response;
    }
    @GetMapping()
    public List<TodoDto> getAllTodos(){
        return todoService.getAllTodos();
    }

    @PostMapping()
    public TodoDto saveTodo(@RequestBody TodoDto todoDto){
        return todoService.saveTodo(todoDto);
    }

}
