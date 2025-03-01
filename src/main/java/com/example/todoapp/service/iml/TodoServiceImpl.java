package com.example.todoapp.service.iml;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.entity.Todo;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    @Override
    public List<TodoDto> getAllTodos(Long userId) {
        List<Todo> todoList = todoRepository.findAllByUserId(userId);
        return todoList.stream()
                .map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto saveTodo(TodoDto todoDto, Long userId) {
        Todo todo = modelMapper.map(todoDto, Todo.class);

        //Save database
        Todo savedTodo = todoRepository.save(todo);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        savedTodo.setUser(user);

        return modelMapper.map(savedTodo, TodoDto.class);
    }


}
