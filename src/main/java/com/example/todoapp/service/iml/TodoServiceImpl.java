package com.example.todoapp.service.iml;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;

    public TodoServiceImpl(ModelMapper modelMapper,
                           TodoRepository todoRepository) {
        this.modelMapper = modelMapper;
        this.todoRepository = todoRepository;
    }
    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream()
                .map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto saveTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        //Save database
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }


}
