package com.example.todoapp.dto;

import com.example.todoapp.entity.ETaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private String description;
    private ETaskStatus status = ETaskStatus.PENDING;
}
