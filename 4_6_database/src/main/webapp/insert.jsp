<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="jp.co.web.RoleDto" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ユーザー登録システム</title>
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
        input[type="text"], 
        input[type="password"], 
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        input[type="text"]:focus, 
        input[type="password"]:focus, 
        select:focus {
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
        .menu-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .menu-link:hover {
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
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>会員登録入力画面</h1>
        
        <!-- エラーメッセージの表示 -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        
        <form action="InsertConfirmServlet" method="post">
            <div class="form-group">
                <label for="loginId">ID <span class="required">*</span></label>
                <input type="text" id="loginId" name="loginId" 
                       value="<%= request.getParameter("loginId") != null ? request.getParameter("loginId") : "" %>" 
                       required>
            </div>
            
            <div class="form-group">
                <label for="userName">名前 <span class="required">*</span></label>
                <input type="text" id="userName" name="userName" 
                       value="<%= request.getParameter("userName") != null ? request.getParameter("userName") : "" %>" 
                       required>
            </div>
            
            <div class="form-group">
                <label for="telephone">TEL <span class="required">*</span></label>
                <input type="text" id="telephone" name="telephone" 
                       value="<%= request.getParameter("telephone") != null ? request.getParameter("telephone") : "" %>" 
                       required>
            </div>
            
            <div class="form-group">
                <label for="roleId">権限 <span class="required">*</span></label>
                <select id="roleId" name="roleId" required>
                    <option value="">選択してください</option>
                    <% 
                        List<RoleDto> roles = (List<RoleDto>) request.getAttribute("roles");
                        String selectedRoleId = request.getParameter("roleId");
                        if (roles != null) {
                            for (RoleDto role : roles) {
                                String selected = (selectedRoleId != null && selectedRoleId.equals(String.valueOf(role.getRoleId()))) ? "selected" : "";
                    %>
                                <option value="<%= role.getRoleId() %>" <%= selected %>><%= role.getRoleName() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            
            <div class="form-group">
                <label for="password">PASS（入力） <span class="required">*</span></label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <div class="button-group">
                <button type="submit" class="btn btn-primary">登録</button>
                <button type="button" class="btn btn-secondary" onclick="goToMenu()">戻る</button>
            </div>
        </form>
        
        <a href="menu.jsp" class="menu-link">メニュー画面</a>
    </div>

    <script>
        function goToMenu() {
            window.location.href = 'menu.jsp';
        }
    </script>
</body>
</html>