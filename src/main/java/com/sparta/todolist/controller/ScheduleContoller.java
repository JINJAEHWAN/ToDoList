package com.sparta.todolist.controller;

import com.sparta.todolist.dto.ScheduleRequestDto;
import com.sparta.todolist.dto.ScheduleResponseDto;
import com.sparta.todolist.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.SimpleDateFormat;
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
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
        String sql = "SELECT * FROM memo";

        return jdbctemplate.query(sql, new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온
                Long id = rs.getLong("id");
                String managerName = rs.getString("managerName");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updatedAt").toLocalDateTime();
                String password = rs.getString("password");
                String todo = rs.getString("todo");
                return new ScheduleResponseDto(id, managerName, createdAt, updatedAt, password, todo);
            }
        });
    }
//    @GetMapping("/todo/{id}")
//    public ScheduleResponseDto getTodoId(@PathVariable Long id){
//        Schedule sch = schedList.get(id);
//
//        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(sch);
//        return  scheduleResponseDto;
//    }
//    @GetMapping("/todo/star")
//    public List<ScheduleResponseDto> getTodoStar(@PathVariable String managerName
//            ,@PathVariable LocalDateTime updateAt){
//        updateAt.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
//
//        return null;
//    }

    //managerName과 updateAt을 동시에 처리하기 위해서 어떻게 해야 할까?
    //둘 다 받을 수 있고, 둘 중 하나만 받을 수 있음.
}
