package com.example.todoapp.repository;

import com.example.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUserId(Long UserId);
    Optional<Todo> findByIdAndUserId(Long id, Long userId);
    void deleteByIdAndUserId(Long id, Long userId);

}
