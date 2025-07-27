package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class PgProductDao implements productDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Products> findAll() {
        return jdbcTemplate.query("SELECT id, name, price FROM products ORDER BY id",
            new RowMapper<Products>() {
                @Override
                public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Products product = new Products();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    return product;
                }
            });
    }
}
