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

@WebServlet("/DBConnection_JavaEE05")
public class DBConnection_JavaEE05 extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new ProductDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        try {
            switch (action) {
                case "search":
                    handleSearch(request, idStr, name, priceStr);
                    break;
                case "register":
                    handleRegisterOrUpdate(request, idStr, name, priceStr);
                    break;
                case "delete":
                    handleDelete(request, idStr);
                    break;
            }
        } catch (Exception e) {
            request.setAttribute("message", "エラー: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        try {
            List<Product> products = dao.findAll();
            request.setAttribute("products", products);
        } catch (SQLException e) {
            request.setAttribute("message", "一覧取得失敗: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        RequestDispatcher rd = request.getRequestDispatcher("dbconnection_javaee05.jsp");
        rd.forward(request, response);
    }

    private void handleSearch(HttpServletRequest request, String idStr, String name, String priceStr) throws SQLException {
        Integer id = parseIntOrNull(idStr);
        Integer price = parseIntOrNull(priceStr);
        List<Product> products = dao.findByConditions(id, name, price);
        request.setAttribute("products", products);
        request.setAttribute("message", products.size() + "件ヒットしました。");
        request.setAttribute("messageType", "success");
    }

    private void handleRegisterOrUpdate(HttpServletRequest request, String idStr, String name, String priceStr) throws SQLException {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("商品名は必須です。");
        if (priceStr == null || priceStr.trim().isEmpty()) throw new IllegalArgumentException("価格は必須です。");

        int price = Integer.parseInt(priceStr);

        if (idStr != null && !idStr.trim().isEmpty()) {
            int id = Integer.parseInt(idStr);
            Product existing = dao.findById(id);
            if (existing != null) {
                dao.update(new Product(id, name.trim(), price));
                request.setAttribute("message", "ID " + id + " を更新しました。");
            } else {
                dao.register(new Product(id, name.trim(), price));
                request.setAttribute("message", "ID " + id + " を新規登録しました。");
            }
        } else {
            Product newProduct = new Product();
            newProduct.setName(name.trim());
            newProduct.setPrice(price);
            dao.register(newProduct);
            request.setAttribute("message", "新規商品を登録しました。");
        }
        request.setAttribute("messageType", "success");
    }

    private void handleDelete(HttpServletRequest request, String idStr) throws SQLException {
        if (idStr == null || idStr.trim().isEmpty()) {
            throw new IllegalArgumentException("削除にはIDが必要です。");
        }
        int id = Integer.parseInt(idStr);
        int result = dao.delete(id);
        if (result > 0) {
            request.setAttribute("message", "ID " + id + " を削除しました。");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "ID " + id + " は存在しません。");
            request.setAttribute("messageType", "error");
        }
    }

    private Integer parseIntOrNull(String str) {
        try {
            return (str != null && !str.trim().isEmpty()) ? Integer.parseInt(str.trim()) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
