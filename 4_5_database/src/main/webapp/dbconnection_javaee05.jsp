<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.servlet.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品管理システム</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-section {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 80px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 200px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        button {
            padding: 8px 16px;
            font-size: 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .search-btn {
            background-color: #007bff;
            color: white;
        }
        .search-btn:hover {
            background-color: #0056b3;
        }
        .register-btn {
            background-color: #28a745;
            color: white;
        }
        .register-btn:hover {
            background-color: #218838;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f8f9fa;
        }
        .message {
            margin: 10px 0;
            padding: 10px;
            border-radius: 4px;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>商品管理システム</h1>

    <!-- メッセージ表示 -->
    <% String message = (String) request.getAttribute("message"); %>
    <% String messageType = (String) request.getAttribute("messageType"); %>
    <% if (message != null) { %>
    <div class="message <%= messageType %>"><%= message %></div>
    <% } %>

    <!-- 検索・登録・更新・削除フォーム -->
    <div class="form-section">
        <h3>検索条件、または、登録情報（name,price）を入力してください</h3>
        <form method="post" action="DBConnection_JavaEE05">
            <div class="form-group">
                <label for="id">id:</label>
                <input type="text" id="id" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>">
            </div>
            <div class="form-group">
                <label for="name">name:</label>
                <input type="text" id="name" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">
            </div>
            <div class="form-group">
                <label for="price">price:</label>
                <input type="text" id="price" name="price" value="<%= request.getParameter("price") != null ? request.getParameter("price") : "" %>">
            </div>
            <div class="form-group" style="display: flex; gap: 10px;">
                <button type="submit" name="action" value="search" class="search-btn">検索</button>
                <button type="submit" name="action" value="register" class="register-btn">登録または更新</button>
                <button type="submit" name="action" value="delete" class="delete-btn" onclick="return confirm('本当に削除しますか？');">削除</button>
            </div>
        </form>
    </div>

    <!-- 商品一覧テーブル -->
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>price</th>
        </tr>
        </thead>
        <tbody>
        <%
            @SuppressWarnings("unchecked")
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Product p : products) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %></td>
        </tr>
        <% 
                }
            } else {
        %>
        <tr>
            <td colspan="3" style="text-align: center;">商品データがありません</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
