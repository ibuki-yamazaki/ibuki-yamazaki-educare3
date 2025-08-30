package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Role> findAll() {
        String sql = "SELECT * FROM role";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Role.class));
    }
}
