<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.servlet.Product" %>
<%@ page import="com.example.servlet.DBConnection_JavaEE01" %>
<%@ page import="com.example.servlet.ProductDAO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品検索システム</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .search-form {
            border: 2px solid #ccc;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 80px;
            font-weight: bold;
        }
        input[type="text"] {
            width: 200px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .search-btn {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 14px;
        }
        .search-btn:hover {
            background-color: #0056b3;
        }
        .results-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .results-table th, .results-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .results-table th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .results-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .error {
            color: red;
            font-weight: bold;
            padding: 10px;
            background-color: #ffe6e6;
            border: 1px solid #ff0000;
            border-radius: 5px;
            margin: 10px 0;
        }
        .info {
            color: #666;
            font-style: italic;
        }
        .success {
            color: green;
            font-weight: bold;
        }
        .no-results {
            text-align: center;
            color: #666;
            font-style: italic;
            padding: 20px;
        }
        .search-info {
            background-color: #e9ecef;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>商品検索システム</h1>
        
        <!-- 検索フォーム -->
        <form method="post" action="DBConnection_JavaEE01" class="search-form">
            <h3>検索条件を入力してください</h3>
            
            <div class="form-group">
                <label for="id">id:</label>
                <input type="text" id="id" name="id" value="${searchId != null ? searchId : ''}">
            </div>
            
            <div class="form-group">
                <label for="name">name:</label>
                <input type="text" id="name" name="name" value="${searchName != null ? searchName : ''}">
            </div>
            
            <div class="form-group">
                <label for="price">price:</label>
                <input type="text" id="price" name="price" value="${searchPrice != null ? searchPrice : ''}">
            </div>
            
            <button type="submit" class="search-btn">検索</button>
        </form>

        <!-- エラーメッセージ表示 -->
        <c:if test="${errorMessage != null}">
            <div class="error">
                <strong>エラー:</strong> ${errorMessage}
            </div>
        </c:if>

        <!-- 検索結果表示 -->
        <c:if test="${products != null}">
            <div class="results-section">
                <h3>検索結果</h3>
                
                <!-- 検索条件表示 -->
                <div class="search-info">
                    <strong>検索条件:</strong>
                    <c:choose>
                        <c:when test="${(searchId == null || searchId == '') && (searchName == null || searchName == '') && (searchPrice == null || searchPrice == '')}">
                            全件表示
                        </c:when>
                        <c:otherwise>
                            <c:if test="${searchId != null && searchId != ''}">ID: ${searchId} </c:if>
                            <c:if test="${searchName != null && searchName != ''}">商品名: ${searchName} </c:if>
                            <c:if test="${searchPrice != null && searchPrice != ''}">価格: ${searchPrice} </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
                
                <!-- 結果カウント -->
                <p class="success">検索結果: ${products.size()}件</p>
                
                <!-- 結果テーブル -->
                <c:choose>
                    <c:when test="${products.size() > 0}">
                        <table class="results-table">
                            <thead>
                                <tr>
                                    <th>id</th>
                                    <th>name</th>
                                    <th>price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>${product.name}</td>
                                        <td>${product.price}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="no-results">
                            検索条件に一致する商品が見つかりませんでした
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>
        
        <!-- 使用方法の説明 -->
        <div style="margin-top: 30px; padding: 15px; background-color: #e9ecef; border-radius: 5px;">
            <h4>使用方法</h4>
            <ul>
                <li><strong>全件表示:</strong> 検索条件を全て空にして「検索」ボタンを押してください</li>
                <li><strong>ID検索:</strong> 商品IDを入力（完全一致）</li>
                <li><strong>商品名検索:</strong> 商品名を入力（部分一致検索が可能）</li>
                <li><strong>価格検索:</strong> 価格を入力（完全一致）</li>
                <li><strong>複合検索:</strong> 複数の条件を組み合わせて検索可能</li>
            </ul>
        </div>
        
        <!-- システム情報 -->
        <div style="margin-top: 20px; padding: 10px; background-color: #f8f9fa; border-radius: 5px; font-size: 12px; color: #666;">
            <p><strong>システム情報:</strong></p>
            <p>サーブレット: DBConnection_JavaEE01</p>
            <p>データベース: PostgreSQL</p>
            <p>テーブル: products</p>
        </div>
    </div>
</body>
</html>