package com.sparta.todolist.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {
    private String managerName;
    private LocalDateTime createdAt =LocalDateTime.now();
    private LocalDateTime updateAt= LocalDateTime.now();
    private int pwd;
    private String todo;
}
