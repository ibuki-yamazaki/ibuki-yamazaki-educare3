package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DBConnection_JavaEE03")
public class DBConnection_JavaEE03 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // データベース接続情報
    private static final String DB_URL = "jdbc:postgresql:dbconnection";
    private static final String DB_USER = "hogeuser";
    private static final String DB_PASSWORD = "hoge";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 商品一覧を取得してJSPに転送
        List<Product> products = getAllProducts();
        request.setAttribute("products", products);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("dbconnection_javaee03.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        String message = "";
        String messageType = "";
        
        if ("register".equals(action)) {
            // 登録処理
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            
            if (name != null && !name.trim().isEmpty() && 
                priceStr != null && !priceStr.trim().isEmpty()) {
                try {
                    int price = Integer.parseInt(priceStr);
                    Product product = new Product(0, name, price);
                    
                    int newId = register(product);
                    if (newId > 0) {
                        message = "登録完了: ID = " + newId;
                        messageType = "success";
                    } else {
                        message = "登録に失敗しました";
                        messageType = "error";
                    }
                } catch (NumberFormatException e) {
                    message = "価格は数値で入力してください";
                    messageType = "error";
                }
            } else {
                message = "商品名と価格を入力してください";
                messageType = "error";
            }
            
        } else if ("update".equals(action)) {
            // 更新処理
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            
            if (idStr != null && !idStr.trim().isEmpty() && 
                name != null && !name.trim().isEmpty() && 
                priceStr != null && !priceStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    int price = Integer.parseInt(priceStr);
                    Product product = new Product(id, name, price);
                    
                    boolean success = update(product);
                    if (success) {
                        message = "更新成功";
                        messageType = "success";
                    } else {
                        message = "更新対象なし";
                        messageType = "error";
                    }
                } catch (NumberFormatException e) {
                    message = "IDと価格は数値で入力してください";
                    messageType = "error";
                }
            } else {
                message = "ID、商品名、価格をすべて入力してください";
                messageType = "error";
            }
        }
        
        // 結果をリクエストに設定
        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        
        // 商品一覧を再取得
        List<Product> products = getAllProducts();
        request.setAttribute("products", products);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("dbconnection_javaee03.jsp");
        dispatcher.forward(request, response);
    }
    
    // 商品登録メソッドF
    public int register(Product product) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int newId = 0;
        
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            con.setAutoCommit(false);
            
            String sqlMax = "SELECT id FROM products ORDER BY id DESC LIMIT 1 FOR UPDATE";
            stmt = con.prepareStatement(sqlMax);
            rs = stmt.executeQuery();
            
            int nextId = 1;
            if (rs.next()) {
                nextId = rs.getInt("id") + 1;
            }
            
            rs.close();
            stmt.close();
            
            String sqlInsert = "INSERT INTO products (id, name, price) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sqlInsert);
            stmt.setInt(1, nextId);
            stmt.setString(2, product.getName());
            stmt.setInt(3, product.getPrice());
            stmt.executeUpdate();
            
            con.commit();
            newId = nextId;
            System.out.println("登録完了: ID = " + nextId);
            
        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        
        return newId;
    }
    
    // 商品更新メソッド
    public boolean update(Product product) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean success = false;
        
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setInt(3, product.getId());
            
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                success = true;
                System.out.println("更新成功: " + rows + "件");
            } else {
                System.out.println("更新対象なし");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        
        return success;
    }
    
    // 全商品取得メソッド
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            String sql = "SELECT id, name, price FROM products ORDER BY id";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                products.add(new Product(id, name, price));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        
        return products;
    }
}