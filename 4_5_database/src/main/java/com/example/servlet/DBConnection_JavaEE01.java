package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品検索システムのサーブレットクラス
 * PostgreSQLデータベースから商品情報を検索する
 */
@WebServlet("/DBConnection_JavaEE01")
public class DBConnection_JavaEE01 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // データベース接続情報
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/dbconnection";
    private static final String DB_USER = "hogeuser";
    private static final String DB_PASSWORD = "hoge";
    
    /**
     * デフォルトコンストラクタ
     */
    public DBConnection_JavaEE01() {
        super();
    }
    
    /**
     * GETリクエストを処理（初期画面表示）
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 検索フォームのJSPページに転送
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dbconnection_javaee01.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * POSTリクエストを処理（検索処理）
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        // 検索条件の取得
        String searchId = request.getParameter("id");
        String searchName = request.getParameter("name");
        String searchPrice = request.getParameter("price");
        
        // 検索結果を格納するリスト
        List<Product> products = new ArrayList<>();
        String errorMessage = null;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // PostgreSQLドライバーのロード
            Class.forName("org.postgresql.Driver");
            
            // データベースに接続
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // SQL文の構築
            StringBuilder sql = new StringBuilder("SELECT id, name, price FROM products WHERE 1=1");
            List<Object> params = new ArrayList<>();
            
            // 検索条件の追加
            if (searchId != null && !searchId.trim().isEmpty()) {
                sql.append(" AND id = ?");
                params.add(Integer.parseInt(searchId.trim()));
            }
            
            if (searchName != null && !searchName.trim().isEmpty()) {
                sql.append(" AND name LIKE ?");
                params.add("%" + searchName.trim() + "%");
            }
            
            if (searchPrice != null && !searchPrice.trim().isEmpty()) {
                sql.append(" AND price = ?");
                params.add(Integer.parseInt(searchPrice.trim()));
            }
            
            sql.append(" ORDER BY id");
            
            // PreparedStatementの作成
            pstmt = conn.prepareStatement(sql.toString());
            
            // パラメータの設定
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            
            // クエリの実行
            rs = pstmt.executeQuery();
            
            // 結果をリストに格納
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                products.add(product);
            }
            
        } catch (ClassNotFoundException e) {
            errorMessage = "PostgreSQLドライバーが見つかりません: " + e.getMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            errorMessage = "データベースエラーが発生しました: " + e.getMessage();
            e.printStackTrace();
        } catch (NumberFormatException e) {
            errorMessage = "IDまたは価格には数値を入力してください";
        } catch (Exception e) {
            errorMessage = "予期しないエラーが発生しました: " + e.getMessage();
            e.printStackTrace();
        } finally {
            // リソースのクローズ
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // 結果をリクエストスコープに設定
        request.setAttribute("products", products);
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("searchId", searchId);
        request.setAttribute("searchName", searchName);
        request.setAttribute("searchPrice", searchPrice);
        
        // 結果表示用のJSPページに転送
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dbconnection_javaee01.jsp");
        dispatcher.forward(request, response);
    }
}

// Productクラスは別ファイル（Product.java）で定義