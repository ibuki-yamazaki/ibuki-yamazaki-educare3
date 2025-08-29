<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラーページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            text-align: center;
        }
        .error-icon {
            font-size: 48px;
            color: #dc3545;
            margin-bottom: 20px;
        }
        .error-title {
            color: #dc3545;
            margin-bottom: 20px;
        }
        .error-message {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .button-group {
            margin-top: 30px;
        }
        .btn {
            padding: 10px 20px;
            margin: 0 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #f8f9fa;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
        }
        .btn:hover {
            background-color: #e2e6ea;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="error-icon">⚠️</div>
        <h2 class="error-title">エラーが発生しました</h2>
        
        <div class="error-message">
            ${errorMessage != null ? errorMessage : 'システムエラーが発生しました'}
        </div>
        
        <div class="button-group">
            <a href="/" class="btn btn-primary">トップページに戻る</a>
            <a href="/menu" class="btn">メニューに戻る</a>
            <a href="javascript:history.back()" class="btn">前のページに戻る</a>
        </div>
    </div>
</body>
</html>