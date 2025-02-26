package com.example.todoapp.service;

import com.example.todoapp.dto.TodoDto;

import java.util.List;

public interface TodoService {
    public List<TodoDto> getAllTodos();
    public TodoDto saveTodo(TodoDto todoDto);


}
