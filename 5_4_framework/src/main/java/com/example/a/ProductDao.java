package com.example.a;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    // 価格での検索
    public List<Product> findByPrice(Integer price) {
        String sql = "SELECT id, name, price FROM products WHERE price = ? ORDER BY id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Product>(Product.class), price);
    }

    // 名前と価格での複合検索
    public List<Product> findByNameAndPrice(String name, Integer price) {
        StringBuilder sql = new StringBuilder("SELECT id, name, price FROM products WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        if (StringUtils.hasText(name)) {
            sql.append(" AND name LIKE ?");
            params.add("%" + name + "%");
        }
        
        if (price != null) {
            sql.append(" AND price = ?");
            params.add(price);
        }
        
        sql.append(" ORDER BY id");
        
        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Product>(Product.class), params.toArray());
    }
}