<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 全セッションをクリア
    session.invalidate();
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>石取りゲーム</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .container {
            display: inline-block;
            border: 1px solid #ddd;
            padding: 20px 300px 20px 300px;
            border-color: blue;
            border-radius: 8px;
            
           
            background-color: white;
        }
        h1 {
            margin-bottom: 20px;
        }
        button {
            font-size: 18px;
            padding: 10px 20px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>石取りゲーム</h1>
    <div class="container">
        <p><strong>条件</strong></p>
        <p>石の総数: <strong>25個</strong></p>
        <p>1度に取れる石の数: <strong>1～3個</strong></p>
        <p>プレイヤー: <strong>A</strong> vs <strong>B</strong></p>
        <form action="play.jsp" method="post">
            <button type="submit" action="play.jsp">始める</button>
        </form>
    </div>
</body>
</html>
