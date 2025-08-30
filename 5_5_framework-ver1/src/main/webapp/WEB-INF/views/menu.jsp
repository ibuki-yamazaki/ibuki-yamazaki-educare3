<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー画面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
        }
        .welcome {
            background-color: #e7f3ff;
            border: 1px solid #b8daff;
            padding: 15px;
            border-radius: 5px;
            margin: 20px 0;
        }
        .welcome p {
            margin: 0;
            font-size: 18px;
            color: #004085;
        }
        .menu-section {
            margin: 30px 0;
        }
        .menu-title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 15px;
            color: #495057;
        }
        .menu-links {
            display: flex;
            flex-direction: column;
            gap: 10px;
        }
        .menu-links a {
            display: inline-block;
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
            transition: background-color 0.3s;
            max-width: 200px;
        }
        .menu-links a:hover {
            background-color: #0056b3;
        }
        .menu-links a.register-link {
            background-color: #28a745;
        }
        .menu-links a.register-link:hover {
            background-color: #1e7e34;
        }
        .logout-section {
            margin-top: 40px;
            padding-top: 20px;
            border-top: 1px solid #dee2e6;
            text-align: right;
        }
        .logout-link {
            color: #dc3545;
            text-decoration: none;
            font-weight: bold;
        }
        .logout-link:hover {
            text-decoration: underline;
        }
        .role-info {
            background-color: #fff3cd;
            border: 1px solid #ffeaa7;
            padding: 10px;
            border-radius: 3px;
            margin: 10px 0;
            font-size: 14px;
        }
        .debug-info {
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            padding: 10px;
            border-radius: 3px;
            margin: 10px 0;
            font-size: 12px;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>メニュー画面</h1>

        <div class="welcome">
            <p>${userName}さん、こんにちは</p>
        </div>

        <!-- デバッグ用：現在のユーザー情報表示 -->
        <div class="debug-info">
            <strong>デバッグ情報:</strong><br>
            ログインユーザー: ${sessionScope.loginUser.loginId} (Role ID: ${sessionScope.loginUser.roleId})<br>
            <c:if test="${not empty sessionScope.roles}">
                利用可能なロール: 
                <c:forEach var="role" items="${sessionScope.roles}" varStatus="status">
                    ${role.roleName}(ID:${role.roleId})<c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </c:if>
        </div>

        <!-- 各機能に対してのリンクを表示 -->
        <div class="menu-section">
            <div class="menu-title">利用可能な機能:</div>
            <div class="menu-links">
                <!-- 検索機能（全ユーザー共通） -->
                <a href="/search">🔍 検索</a>

                <!-- 管理者権限チェック（修正版） -->
                <c:set var="isAdmin" value="false" />
                <c:if test="${not empty sessionScope.loginUser and not empty sessionScope.roles}">
                    <c:forEach var="role" items="${sessionScope.roles}">
                        <c:if test="${role.roleId == sessionScope.loginUser.roleId and (role.roleName == 'ADMIN' or role.roleName == '管理者')}">
                            <c:set var="isAdmin" value="true" />
                        </c:if>
                    </c:forEach>
                </c:if>

                <!-- 管理者権限がある場合のみ登録機能を表示 -->
                <c:if test="${isAdmin}">
                    <a href="/insert" class="register-link">📝 登録</a>
                    <div class="role-info">
                        ⚡ 管理者権限により登録機能が利用できます
                    </div>
                </c:if>

                <!-- 一般ユーザーへのメッセージ -->
                <c:if test="${!isAdmin}">
                    <div class="role-info">
                        ℹ️ 一般ユーザーのため、検索機能のみ利用可能です
                    </div>
                </c:if>
            </div>
        </div>

        <div class="logout-section">
            <a href="/logout" class="logout-link">🚪 ログアウト</a>
        </div>
    </div>

    <script>
        // ページロード時の処理
        document.addEventListener('DOMContentLoaded', function() {
            console.log('メニュー画面が読み込まれました');
            
            // 管理者権限の確認（デバッグ用）
            const isAdmin = ${isAdmin};
            console.log('管理者権限:', isAdmin);
            
            <c:if test="${not empty sessionScope.loginUser}">
                console.log('ログインユーザー:', '${sessionScope.loginUser.loginId}');
                console.log('ユーザーRole ID:', ${sessionScope.loginUser.roleId});
            </c:if>
        });
    </script>
</body>
</html>