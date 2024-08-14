package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String managerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String password;
    private String todo;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.managerName = schedule.getManagerName();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.password = schedule.getPassword();
        this.todo = schedule.getTodo();
    }

    public ScheduleResponseDto(Long id, String managerName, LocalDateTime createdAt, LocalDateTime updatedAt, String password, String todo) {
        this.id = id;
        this.managerName = managerName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.password = password;
        this.todo = todo;
    }
}
