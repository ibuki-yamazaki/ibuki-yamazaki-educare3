package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/dbconnection";
    private static final String DB_USER = "hogeuser";
    private static final String DB_PASSWORD = "hoge";
    
    // データベース接続を取得
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBCドライバーが見つかりません", e);
        }
    }
    
    // 全商品を取得
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price FROM products ORDER BY id";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    // 条件に基づいて商品を検索（AND検索）
    public List<Product> search(ProductForm form) {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, name, price FROM products WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        // nameの条件を追加
        if (form.getName() != null && !form.getName().trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + form.getName().trim() + "%");
        }
        
        // priceの条件を追加
        if (form.getPrice() != null && !form.getPrice().trim().isEmpty()) {
            Integer price = form.getPriceAsInteger();
            if (price != null) {
                sql.append(" AND price = ?");
                params.add(price);
            }
        }
        
        sql.append(" ORDER BY id");
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            
            // パラメータを設定
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    // 新しい商品を登録
    public boolean insert(ProductForm form) {
        String sql = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection()) {
            // 次のIDを取得
            int nextId = getNextId(conn);
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, nextId);
                pstmt.setString(2, form.getName().trim());
                pstmt.setInt(3, form.getPriceAsInteger());
                
                int result = pstmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // 次のIDを取得（現在の最大ID + 1）
    private int getNextId(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 FROM products";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1; // デフォルト値
        }
    }
}