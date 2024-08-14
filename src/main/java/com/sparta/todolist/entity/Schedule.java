package com.sparta.todolist.entity;

import com.sparta.todolist.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    private Long id;
    private String managerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;
    private String todo;

    public Schedule(ScheduleRequestDto requestDto) {
        this.managerName = requestDto.getManagerName();
        this.createdAt = requestDto.getCreatedAt();
        this.updatedAt = requestDto.getUpdatedAt();
        this.password = requestDto.getPassword();
        this.todo = requestDto.getTodo();
    }
}
