<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー登録完了画面</title>
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
            padding: 50px 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
        }
        h1 {
            color: #28a745;
            margin-bottom: 30px;
            font-size: 28px;
        }
        .success-message {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 20px;
            border-radius: 4px;
            margin-bottom: 30px;
            font-size: 18px;
            font-weight: bold;
        }
        .user-info {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            padding: 20px;
            border-radius: 4px;
            margin-bottom: 30px;
            text-align: left;
        }
        .user-info h3 {
            margin-top: 0;
            color: #495057;
            text-align: center;
        }
        .info-row {
            display: flex;
            margin-bottom: 10px;
            padding: 8px 0;
            border-bottom: 1px solid #dee2e6;
        }
        .info-row:last-child {
            border-bottom: none;
        }
        .info-label {
            font-weight: bold;
            color: #495057;
            width: 80px;
            margin-right: 20px;
        }
        .info-value {
            flex: 1;
            color: #333;
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
        .menu-link {
            display: block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .menu-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>登録完了</h1>
        
        <div class="success-message">
            正常に登録されました
        </div>
        
        <div class="user-info">
            <h3>登録者：${userName}</h3>
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
        
        <button class="btn btn-primary" onclick="goToMenu()">メニューに戻る</button>
        
        <a href="menu.jsp" class="menu-link">メニュー</a>
    </div>
    
    <script>
        function goToMenu() {
            window.location.href = 'menu.jsp';
        }
    </script>
</body>
</html>