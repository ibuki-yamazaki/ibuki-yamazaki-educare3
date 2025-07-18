<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.web.UserInfoDto" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登録完了画面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            font-size: 24px;
        }
        .success-message {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
            text-align: center;
        }
        .user-info {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 30px;
        }
        .info-row {
            display: flex;
            margin-bottom: 10px;
            padding: 8px 0;
            border-bottom: 1px solid #e9ecef;
        }
        .info-row:last-child {
            border-bottom: none;
            margin-bottom: 0;
        }
        .info-label {
            font-weight: bold;
            color: #495057;
            width: 80px;
            flex-shrink: 0;
        }
        .info-value {
            color: #212529;
            flex: 1;
        }
        .button-group {
            text-align: center;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 30px;
            margin: 0 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
            transition: background-color 0.3s ease;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .completion-icon {
            text-align: center;
            margin-bottom: 20px;
        }
        .completion-icon::before {
            content: "✓";
            font-size: 48px;
            color: #28a745;
            display: block;
            margin-bottom: 10px;
        }
        .registered-user {
            font-size: 18px;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 10px;
        }
        .completion-text {
            font-size: 16px;
            color: #155724;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>登録完了画面</h1>
        
        <div class="success-message">
            <div class="completion-icon"></div>
            <div class="registered-user">
                実行者：
                <% 
                    // セッションからログインユーザー情報を取得
                    UserInfoDto loginUser = (UserInfoDto) session.getAttribute("loginUser");
                    if (loginUser != null) {
                        out.print(loginUser.getUserName());
                    } else {
                        out.print("不明");
                    }
                %>
            </div>
            <div class="completion-text">
                正常に登録されました
            </div>
        </div>
        
        <div class="user-info">
            <div class="info-row">
                <div class="info-label">ID：</div>
                <div class="info-value">${loginId}</div>
            </div>
            <div class="info-row">
                <div class="info-label">名前：</div>
                <div class="info-value">${userName}</div>
            </div>
            <div class="info-row">
                <div class="info-label">TEL：</div>
                <div class="info-value">${telephone}</div>
            </div>
            <div class="info-row">
                <div class="info-label">権限：</div>
                <div class="info-value">${roleName}</div>
            </div>
        </div>
        
        <div class="button-group">
            <a href="menu.jsp" class="btn btn-primary">メニューに戻る</a>
        </div>
    </div>
</body>
</html>