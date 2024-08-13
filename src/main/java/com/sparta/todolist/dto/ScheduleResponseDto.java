package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String managerName;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private int pwd;
    private String todo;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.managerName = schedule.getManagerName();
        this.createdAt = schedule.getCreatedAt();
        this.updateAt = schedule.getUpdateAt();
        this.pwd = schedule.getPwd();
        this.todo = schedule.getTodo();
    }
}
