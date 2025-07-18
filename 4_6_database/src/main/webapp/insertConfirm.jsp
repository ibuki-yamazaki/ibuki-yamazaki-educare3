<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー登録確認画面</title>
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
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        .form-value {
            display: block;
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background-color: #f9f9f9;
            color: #333;
            font-size: 14px;
            box-sizing: border-box;
        }
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        input[type="password"]:focus {
            outline: none;
            border-color: #007bff;
            box-shadow: 0 0 0 2px rgba(0,123,255,0.25);
        }
        .required {
            color: red;
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
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        .btn-secondary:hover {
            background-color: #545b62;
        }
        .confirmation-message {
            background-color: #e7f3ff;
            border: 1px solid #b3d9ff;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
            color: #0066cc;
        }
        .menu-link {
            text-align: center;
            margin-top: 20px;
        }
        .menu-link a {
            color: #007bff;
            text-decoration: none;
        }
        .menu-link a:hover {
            text-decoration: underline;
        }
        .error-message {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>登録確認画面</h1>
        
        <div class="confirmation-message">
            これでよろしいですか？
        </div>
        
        <!-- エラーメッセージの表示 -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        
        <form action="InsertConfirmServlet" method="post">
            <div class="form-group">
                <label for="loginId">ID</label>
                <div class="form-value">${loginId}</div>
                <input type="hidden" name="loginId" value="${loginId}" />
            </div>
            
            <div class="form-group">
                <label for="userName">名前</label>
                <div class="form-value">${userName}</div>
                <input type="hidden" name="userName" value="${userName}" />
            </div>
            
            <div class="form-group">
                <label for="telephone">TEL</label>
                <div class="form-value">${telephone}</div>
                <input type="hidden" name="telephone" value="${telephone}" />
            </div>
            
            <div class="form-group">
                <label for="role">権限</label>
                <div class="form-value">${roleName}</div>
                <input type="hidden" name="roleId" value="${roleId}" />
            </div>
            
            <div class="form-group">
                <label for="passwordConfirm">PASS（再入力） <span class="required">*</span></label>
                <input type="password" id="passwordConfirm" name="passwordConfirm" required>
                <input type="hidden" name="password" value="${password}" />
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn btn-primary">登録</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">戻る</button>
            </div>
        </form>
        
        <div class="menu-link">
            <a href="MenuServlet">メニューに戻る</a>
        </div>
    </div>
</body>
</html>