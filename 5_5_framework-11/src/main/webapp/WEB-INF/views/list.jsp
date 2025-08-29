<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>検索結果画面</title>
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
        .search-info {
            background-color: #fff3cd;
            color: #856404;
            padding: 15px;
            border: 1px solid #ffeaa7;
            border-radius: 5px;
            margin-bottom: 25px;
            border-left: 4px solid #ffd93d;
        }
        .result-table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: white;
        }
        .result-table th {
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        .result-table td {
            padding: 10px 12px;
            border: 1px solid #ddd;
        }
        .result-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .result-table tr:hover {
            background-color: #f5f5f5;
        }
        .result-count {
            text-align: right;
            margin: 10px 0;
            font-weight: bold;
            color: #666;
        }
        .menu-link {
            text-align: center;
            margin-top: 30px;
        }
        .menu-link a {
            display: inline-block;
            color: #007bff;
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid #007bff;
            border-radius: 5px;
            transition: all 0.3s;
            margin: 0 10px;
        }
        .menu-link a:hover {
            background-color: #007bff;
            color: white;
        }
        .no-data {
            text-align: center;
            padding: 40px;
            color: #666;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>検索結果画面</h1>
        
        <div class="search-info">
            <strong>検索結果</strong><br>
            tableタグを使用し、検索結果を表示する<br>
            表示項目：ID、名前、TEL、権限<br>
            ※権限はroleテーブルのrole_nameの値を表示<br>
            ※IDは、login_idの値を表示<br>
            出力順：user_idの昇順
        </div>
        
        <!-- 検索結果がある場合 -->
        <c:if test="${not empty userList}">
            <div class="result-count">
                検索結果：${userList.size()}件
            </div>
            
            <table class="result-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>名前</th>
                        <th>TEL</th>
                        <th>権限</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.loginId}</td>
                            <td>${user.userName}</td>
                            <td>${user.tel != null ? user.tel : '-'}</td>
                            <td>${user.roleName != null ? user.roleName : '未設定'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <!-- 検索結果がない場合 -->
        <c:if test="${empty userList}">
            <div class="no-data">
                検索条件に一致するデータが見つかりませんでした
            </div>
        </c:if>
        
        <div class="menu-link">
            <a href="/search">検索画面に戻る</a>
            <a href="/menu">メニューに戻る</a>
            <span style="color: #666; margin-left: 20px;">メニュー画面へのリンクを表示</span>
        </div>
    </div>
</body>
</html>