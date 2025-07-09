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
            width: 60px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 200px;
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 8px 16px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            margin-right: 10px;
        }
        button:hover {
            background-color: #45a049;
        }
        .register-btn {
            background-color: #008CBA;
        }
        .register-btn:hover {
            background-color: #007aa3;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .message {
            margin: 10px 0;
            padding: 10px;
            border-radius: 3px;
        }
        .success {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
        }
        .error {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>商品管理システム</h1>
        
        <!-- メッセージ表示 -->
        <% 
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
            if (message != null) {
        %>
            <div class="message <%= messageType != null ? messageType : "success" %>">
                <%= message %>
            </div>
        <% } %>
        
        <!-- 検索・登録フォーム -->
        <div class="form-section">
            <p><strong>検索条件、または、登録情報（name,price）を入力してください</strong></p>
            <form action="DBConnection_JavaEE02" method="post">
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
                <div class="form-group">
                    <button type="submit" name="action" value="search">検索</button>
                    <button type="submit" name="action" value="register" class="register-btn">登録</button>
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
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getName() %></td>
                        <td><%= product.getPrice() %></td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3">データがありません</td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>