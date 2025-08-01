<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録結果 - 商品管理システム</title>
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
            text-align: center;
        }
        h1 {
            color: #2c3e50;
            margin-bottom: 30px;
        }
        .message {
            font-size: 18px;
            margin-bottom: 30px;
            padding: 20px;
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
        .back-btn {
            background-color: #6c757d;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
        }
        .back-btn:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${not empty message}">
                <h1>登録完了</h1>
                <div class="message success">
                    ${message}
                </div>
            </c:when>
            <c:otherwise>
                <h1>登録エラー</h1>
                <div class="message error">
                    ${error}
                </div>
            </c:otherwise>
        </c:choose>
        
        <a href="/products/" class="back-btn">戻る</a>
    </div>
</body>
</html>