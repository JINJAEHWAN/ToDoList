package com.sparta.todolist.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {
    private String managerName;
    private LocalDateTime createdAt =LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private String password;
    private String todo;
}
