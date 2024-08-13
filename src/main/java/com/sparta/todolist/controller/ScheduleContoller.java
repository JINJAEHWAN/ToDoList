package com.sparta.todolist.controller;

import com.sparta.todolist.dto.ScheduleRequestDto;
import com.sparta.todolist.dto.ScheduleResponseDto;
import com.sparta.todolist.entity.Schedule;
import com.sparta.todolist.request.requestSchedule;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleContoller {

    private final Map<Long, Schedule> schedList = new HashMap<>();




    @PostMapping("/todo")
    public ScheduleResponseDto createTodo(@ModelAttribute requestSchedule reqsch
            ,@RequestBody ScheduleRequestDto requestDto) {

        String managerName = reqsch.managerName;
        int pwd = reqsch.pwd;
        String todo= reqsch.todo;
        //RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        //Memo Max Id check
        Long maxId = schedList.size() > 0 ? Collections.max(schedList.keySet())+1 : 1;
        schedule.setId(maxId);
        schedule.setManagerName(managerName);
        schedule.setPwd(pwd);
        schedule.setTodo(todo);
        //db 저장
        schedList.put(schedule.getId(),schedule);

        //Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    @GetMapping("/todo")
    public List<ScheduleResponseDto> getTodo(){
        //Map To List
        List<ScheduleResponseDto> responselist = schedList.values().stream()
                .map(ScheduleResponseDto::new).toList();

        return responselist;
    }
    @GetMapping("/todo/{id}")
    public ScheduleResponseDto getTodoId(@PathVariable Long id){
        Schedule sch = schedList.get(id);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(sch);
        return  scheduleResponseDto;
    }
}
