package com.sparta.todolist.controller;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.sparta.todolist.dto.ScheduleRequestDto;
import com.sparta.todolist.dto.ScheduleResponseDto;
import com.sparta.todolist.entity.Schedule;
import com.sparta.todolist.tablerowmapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScheduleContoller {

    private final JdbcTemplate jdbctemplate;


    private final Map<Long, Schedule> schedList = new HashMap<>();

    public ScheduleContoller(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    @PostMapping("/todo")
    public ScheduleResponseDto createTodo(@RequestBody ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO Schedule (manager_name, todo, password, created_at, updated_at) \n" +
                "VALUES (?, ?, ?, ?, ?)";
        jdbctemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getManagerName());
                    preparedStatement.setString(2, schedule.getTodo());
                    preparedStatement.setString(3, schedule.getPassword());
                    preparedStatement.setObject(4, schedule.getCreatedAt());
                    preparedStatement.setObject(5, schedule.getUpdatedAt());
                    return preparedStatement;
                },
                keyHolder);
        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    @GetMapping("/todo")
    public List<ScheduleResponseDto> getTodo(){
        // DB 조회
        String sql = "SELECT * FROM schedule";

        return jdbctemplate.query(sql, new tablerowmapper());
    }

    @GetMapping("/todo/find")
    public List<ScheduleResponseDto> getTodoName(@RequestParam String manager_name
            , @RequestParam String updated_at){

        //
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm aaa");
//        format.format(updated_at);

        //DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate updated = LocalDate.parse(updated_at,format);
        System.out.println(updated);
        String sql = "SELECT * FROM schedule WHERE manager_name = ? OR updated_at = ?" +
                "ORDER BY updated_at DESC";

        return jdbctemplate.query(sql, new tablerowmapper(), manager_name, updated
                );
    }
}
