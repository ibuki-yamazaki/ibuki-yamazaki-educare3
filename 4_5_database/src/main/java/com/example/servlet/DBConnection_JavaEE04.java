package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品管理システムのメインサーブレット
 * 検索、登録、更新機能を提供
 */
@WebServlet("/DBConnection_JavaEE04")
public class DBConnection_JavaEE04 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ProductDAO productDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    
    /**
     * GET リクエストの処理（初期表示）
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 全商品を取得して表示
            List<Product> products = productDAO.findAll();
            request.setAttribute("products", products);
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "データベースエラーが発生しました: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }
        
        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dbconnection_javaee04.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * POST リクエストの処理（検索・登録・更新）
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        
        try {
            if ("search".equals(action)) {
                handleSearch(request, idStr, name, priceStr);
            } else if ("register".equals(action)) {
                handleRegisterOrUpdate(request, idStr, name, priceStr);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "エラーが発生しました: " + e.getMessage());
            request.setAttribute("messageType", "error");
            
            // エラー時は全商品を表示
            try {
                List<Product> products = productDAO.findAll();
                request.setAttribute("products", products);
            } catch (SQLException sqlE) {
                sqlE.printStackTrace();
            }
        }
        
        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dbconnection_javaee04.jsp");
        dispatcher.forward(request, response);
    }
    
    /**
     * 検索処理
     */
    private void handleSearch(HttpServletRequest request, String idStr, String name, String priceStr) 
            throws SQLException {
        
        Integer searchId = null;
        Integer searchPrice = null;
        
        // ID の変換
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                searchId = Integer.parseInt(idStr.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("IDは数値で入力してください");
            }
        }
        
        // 価格の変換
        if (priceStr != null && !priceStr.trim().isEmpty()) {
            try {
                searchPrice = Integer.parseInt(priceStr.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("価格は数値で入力してください");
            }
        }
        
        // 検索実行
        List<Product> products;
        if (searchId == null && (name == null || name.trim().isEmpty()) && searchPrice == null) {
            // 全条件が空の場合は全商品を取得
            products = productDAO.findAll();
        } else {
            // 複合条件で検索
            products = productDAO.findByConditions(searchId, name, searchPrice);
        }
        
        request.setAttribute("products", products);
        request.setAttribute("message", "検索が完了しました。" + products.size() + "件の商品が見つかりました。");
        request.setAttribute("messageType", "success");
    }
    
    /**
     * 登録または更新処理
     */
    private void handleRegisterOrUpdate(HttpServletRequest request, String idStr, String name, String priceStr) 
            throws SQLException {
        
        // 入力値検証
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("商品名は必須です");
        }
        
        if (priceStr == null || priceStr.trim().isEmpty()) {
            throw new IllegalArgumentException("価格は必須です");
        }
        
        int price;
        try {
            price = Integer.parseInt(priceStr.trim());
            if (price < 0) {
                throw new IllegalArgumentException("価格は0以上の数値で入力してください");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("価格は数値で入力してください");
        }
        
        // IDが入力されている場合は更新、そうでなければ登録
        if (idStr != null && !idStr.trim().isEmpty()) {
            // 更新処理
            int id;
            try {
                id = Integer.parseInt(idStr.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("IDは数値で入力してください");
            }
            
            // 既存商品の確認
            Product existingProduct = productDAO.findById(id);
            if (existingProduct != null) {
                // 更新処理
                Product updateProduct = new Product(id, name.trim(), price);
                productDAO.update(updateProduct);
                request.setAttribute("message", "商品ID " + id + " の情報を更新しました");
                request.setAttribute("messageType", "success");
            } else {
                // 新規登録処理（指定されたIDで登録）
                Product newProduct = new Product(id, name.trim(), price);
                productDAO.register(newProduct);
                request.setAttribute("message", "商品ID " + id + " で新規登録しました");
                request.setAttribute("messageType", "success");
            }
        } else {
            // 新規登録処理（IDは自動採番）
            Product newProduct = new Product();
            newProduct.setName(name.trim());
            newProduct.setPrice(price);
            productDAO.register(newProduct);
            request.setAttribute("message", "新しい商品を登録しました");
            request.setAttribute("messageType", "success");
        }
        
        // 登録・更新後は全商品を表示
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
    }
}