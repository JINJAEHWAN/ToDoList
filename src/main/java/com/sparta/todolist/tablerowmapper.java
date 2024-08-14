package com.sparta.todolist;

import com.sparta.todolist.dto.ScheduleResponseDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class tablerowmapper implements RowMapper<ScheduleResponseDto> {
    @Override
    public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        // SQL 의 결과로 받아온
        Long id = rs.getLong("id");
        String managerName = rs.getString("manager_name");
        String todo = rs.getString("todo");
        String password = rs.getString("password");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        return new ScheduleResponseDto(id, managerName, createdAt, updatedAt, password, todo);
    }

}
