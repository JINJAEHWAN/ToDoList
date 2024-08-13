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
    private LocalDateTime updateAt;
    private int pwd;
    private String todo;

    public Schedule(ScheduleRequestDto requestDto) {
        this.managerName = requestDto.getManagerName();
        this.createdAt = requestDto.getCreatedAt();
        this.updateAt = requestDto.getUpdateAt();
        this.pwd = requestDto.getPwd();
        this.todo = requestDto.getTodo();
    }
}
