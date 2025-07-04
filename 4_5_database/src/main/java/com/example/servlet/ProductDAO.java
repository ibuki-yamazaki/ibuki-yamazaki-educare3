package com.example.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品データアクセスオブジェクト（DAO）のサンプル実装
 * 実際のプロジェクトでは、このようなDAOパターンを使用することを推奨します
 * 
 * 【DAOパターンの利点】
 * 1. 責任の分離：データアクセスロジックを分離
 * 2. テスタビリティ：モックを使用した単体テストが容易
 * 3. 保守性：データベース変更時の影響範囲を限定
 * 4. 再利用性：複数のサーブレットで同じDAOを使用可能
 */
public class ProductDAO {
    
    // データベース接続情報
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/dbconnection";
    private static final String DB_USER = "hogeuser";
    private static final String DB_PASSWORD = "hoge";
    
    /**
     * データベース接続を取得
     * @return データベース接続
     * @throws SQLException データベース接続エラー
     */
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver not found", e);
        }
    }
    
    /**
     * 全商品を取得
     * @return 全商品のリスト
     * @throws SQLException データベースエラー
     */
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price FROM products ORDER BY id";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                products.add(product);
            }
        }
        
        return products;
    }
    
    /**
     * IDで商品を検索
     * @param id 商品ID
     * @return 商品情報（見つからない場合はnull）
     * @throws SQLException データベースエラー
     */
    public Product findById(int id) throws SQLException {
        String sql = "SELECT id, name, price FROM products WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    return product;
                }
            }
        }
        
        return null;
    }
    
    /**
     * 商品名で部分一致検索
     * @param name 商品名（部分一致）
     * @return 検索結果のリスト
     * @throws SQLException データベースエラー
     */
    public List<Product> findByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price FROM products WHERE name LIKE ? ORDER BY id";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    products.add(product);
                }
            }
        }
        
        return products;
    }
    
    /**
     * 価格で商品を検索
     * @param price 価格
     * @return 検索結果のリスト
     * @throws SQLException データベースエラー
     */
    public List<Product> findByPrice(int price) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price FROM products WHERE price = ? ORDER BY id";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, price);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    products.add(product);
                }
            }
        }
        
        return products;
    }
    
    /**
     * 複合条件で商品を検索
     * @param searchId 商品ID（nullの場合は条件に含めない）
     * @param searchName 商品名（nullまたは空文字の場合は条件に含めない）
     * @param searchPrice 価格（nullの場合は条件に含めない）
     * @return 検索結果のリスト
     * @throws SQLException データベースエラー
     */
    public List<Product> findByConditions(Integer searchId, String searchName, Integer searchPrice) 
            throws SQLException {
        List<Product> products = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT id, name, price FROM products WHERE 1=1");
        List<Object> params = new ArrayList<>();
        
        // 動的にWHERE句を構築
        if (searchId != null) {
            sql.append(" AND id = ?");
            params.add(searchId);
        }
        
        if (searchName != null && !searchName.trim().isEmpty()) {
            sql.append(" AND name LIKE ?");
            params.add("%" + searchName.trim() + "%");
        }
        
        if (searchPrice != null) {
            sql.append(" AND price = ?");
            params.add(searchPrice);
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
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getInt("price"));
                    products.add(product);
                }
            }
        }
        
        return products;
    }
    
    /**
     * 商品を追加
     * @param product 追加する商品情報
     * @return 追加された商品のID
     * @throws SQLException データベースエラー
     */
    public int insert(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?) RETURNING id";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        
        throw new SQLException("商品の追加に失敗しました");
    }
    
    /**
     * 商品を更新
     * @param product 更新する商品情報
     * @return 更新された行数
     * @throws SQLException データベースエラー
     */
    public int update(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getId());
            
            return pstmt.executeUpdate();
        }
    }
    
    /**
     * 商品を削除
     * @param id 削除する商品のID
     * @return 削除された行数
     * @throws SQLException データベースエラー
     */
    public int delete(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        }
    }
    
    /**
     * 商品数を取得
     * @return 商品の総数
     * @throws SQLException データベースエラー
     */
    public int getProductCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM products";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        
        return 0;
    }
}