<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>検索画面 (GET /select)</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            border-bottom: 2px solid #4CAF50;
            padding-bottom: 10px;
        }
        .description {
            background-color: #e8f5e8;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 25px;
            border-left: 4px solid #4CAF50;
        }
        .form-group {
            margin: 20px 0;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            box-sizing: border-box;
        }
        input[type="text"]:focus {
            outline: none;
            border-color: #4CAF50;
        }
        .button-group {
            margin: 30px 0 20px 0;
            text-align: center;
        }
        .search-btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin: 0 10px;
            transition: background-color 0.3s;
        }
        .search-btn:hover {
            background-color: #45a049;
        }
        .menu-link {
            text-align: center;
            margin-top: 20px;
        }
        .menu-link a {
            color: #007bff;
            text-decoration: none;
            padding: 8px 15px;
            border: 1px solid #007bff;
            border-radius: 4px;
            transition: all 0.3s;
        }
        .menu-link a:hover {
            background-color: #007bff;
            color: white;
        }
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            margin-bottom: 20px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>検索画面 (GET /select)</h1>
        
        <div class="description">
            <strong>検索したいデータ情報を入力してください</strong><br>
            ※全て空白の場合は全検索を行います
        </div>
        
        <!-- エラーメッセージ表示 -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage}
            </div>
        </c:if>
        
        <!-- 検索フォーム -->
        <form action="/list" method="get">
            <div class="form-group">
                <label for="name">名前</label>
                <input type="text" id="name" name="name" value="${param.name}" placeholder="名前を入力してください">
            </div>
            
            <div class="form-group">
                <label for="tel">TEL</label>
                <input type="text" id="tel" name="tel" value="${param.tel}" placeholder="電話番号を入力してください">
            </div>
            
            <div class="button-group">
                <button type="submit" class="search-btn">検索</button>
            </div>
        </form>
        
        <div class="menu-link">
            <a href="/menu">メニューに戻る</a>
            <span style="margin: 0 10px;">メニュー画面へのリンクを表示</span>
        </div>
    </div>
</body>
</html>