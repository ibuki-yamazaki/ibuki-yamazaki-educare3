<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品管理システム</title>
    <style>
        body {
            font-family: "Hiragino Sans", "Yu Gothic", sans-serif;
            margin: 40px;
            background-color: #f5f5f5;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 80px;
            font-weight: bold;
        }
        input[type="text"] {
            padding: 8px 12px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            font-size: 14px;
            width: 300px;
        }
        button {
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
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
        .error {
            color: #dc3545;
            margin-top: 10px;
            font-weight: bold;
        }
        .form-container {
            border: 2px solid #dee2e6;
            padding: 20px;
            border-radius: 8px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>商品管理システム</h1>
        
        <div class="form-container">
            <p><strong>検索条件または登録情報を入力してください</strong></p>
            
            <form:form modelAttribute="productForm" method="post">
                <div class="form-group">
                    <label>name:</label>
                    <form:input path="name" />
                </div>
                
                <div class="form-group">
                    <label>price:</label>
                    <form:input path="price" />
                </div>
                
                <div class="form-group">
                    <button type="submit" formaction="/products/search" class="search-btn">検索</button>
                    <button type="submit" formaction="/products/register" class="register-btn">登録</button>
                </div>
            </form:form>
            
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
        </div>
    </div>
</body>
</html>