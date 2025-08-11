package com.example.four;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 全件取得
    public List<Product> findAll() {
        String sql = "SELECT id, name, price FROM products ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class));
    }

    // 商品登録
    public void insert(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice());
    }

    // 名前での検索
    public List<Product> findByName(String name) {
        String sql = "SELECT id, name, price FROM products WHERE name LIKE ? ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), "%" + name + "%");
    }
}