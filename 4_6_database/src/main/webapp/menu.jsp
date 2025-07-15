<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.web.UserInfoDto" %>
<%@ page import="jp.co.web.RoleDto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー画面</title>
    <style>
        body {
            font-family: 'Yu Gothic', 'Meiryo', sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
            line-height: 1.6;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
        }
        .header {
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .header h1 {
            margin: 0;
            display: inline-block;
            font-size: 28px;
            font-weight: bold;
        }
        .user-info {
            float: right;
            font-size: 16px;
            background: rgba(255,255,255,0.1);
            padding: 10px 15px;
            border-radius: 5px;
            margin-top: 5px;
        }
        .user-info .username {
            font-weight: bold;
            color: #fff3cd;
        }
        .role-badge {
            display: inline-block;
            padding: 3px 8px;
            border-radius: 12px;
            font-size: 12px;
            font-weight: bold;
            margin-left: 8px;
        }
        .role-admin {
            background-color: #dc3545;
            color: white;
        }
        .role-user {
            background-color: #28a745;
            color: white;
        }
        .menu-container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 25px rgba(0,0,0,0.1);
        }
        .menu-title {
            text-align: center;
            margin-bottom: 40px;
            color: #333;
            font-size: 24px;
            font-weight: bold;
            position: relative;
        }
        .menu-title::after {
            content: '';
            position: absolute;
            bottom: -10px;
            left: 50%;
            transform: translateX(-50%);
            width: 80px;
            height: 3px;
            background: linear-gradient(90deg, #007bff, #0056b3);
            border-radius: 2px;
        }
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }
        .menu-item {
            display: block;
            padding: 20px;
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            border: 2px solid transparent;
            border-radius: 10px;
            text-decoration: none;
            color: #333;
            text-align: center;
            font-size: 18px;
            font-weight: 500;
            transition: all 0.3s ease;
            position: relative;
            overflow: hidden;
        }
        .menu-item:hover {
            background: linear-gradient(135deg, #007bff, #0056b3);
            color: white;
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(0,123,255,0.3);
            text-decoration: none;
        }
        .menu-item.admin-only {
            background: linear-gradient(135deg, #fff3cd, #ffeaa7);
            border: 2px solid #ffc107;
        }
        .menu-item.admin-only:hover {
            background: linear-gradient(135deg, #ffc107, #e0a800);
            color: #212529;
            border-color: #e0a800;
        }
        .menu-item.admin-only::after {
            content: '管理者専用';
            position: absolute;
            top: 5px;
            right: 10px;
            font-size: 10px;
            background: #dc3545;
            color: white;
            padding: 2px 6px;
            border-radius: 8px;
            font-weight: bold;
        }
        .menu-item::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(255,255,255,0.2), transparent);
            transition: left 0.5s;
        }
        .menu-item:hover::before {
            left: 100%;
        }
        .menu-item i {
            font-size: 24px;
            margin-bottom: 10px;
            display: block;
        }
        .menu-item .icon {
            font-size: 24px;
            margin-bottom: 8px;
            display: block;
        }
        .logout-section {
            text-align: center;
            margin-top: 40px;
            padding-top: 30px;
            border-top: 2px solid #dee2e6;
        }
        .logout-link {
            display: inline-block;
            padding: 12px 30px;
            background-color: #dc3545;
            color: white;
            text-decoration: none;
            border-radius: 25px;
            font-size: 16px;
            font-weight: 500;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(220,53,69,0.2);
        }
        .logout-link:hover {
            background-color: #c82333;
            transform: translateY(-1px);
            box-shadow: 0 6px 12px rgba(220,53,69,0.3);
            text-decoration: none;
        }
        .clearfix::after {
            content: "";
            display: table;
            clear: both;
        }
        .welcome-message {
            background: linear-gradient(135deg, #e3f2fd, #bbdefb);
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 30px;
            text-align: center;
            border-left: 5px solid #2196f3;
        }
        .welcome-message h2 {
            margin: 0 0 10px 0;
            color: #1976d2;
            font-size: 20px;
        }
        .welcome-message p {
            margin: 0;
            color: #424242;
            font-size: 14px;
        }
        .access-info {
            background: linear-gradient(135deg, #fff3cd, #ffeaa7);
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            border-left: 4px solid #ffc107;
        }
        .access-info.admin {
            background: linear-gradient(135deg, #f8d7da, #f1b0b7);
            border-left-color: #dc3545;
        }
        .access-info h3 {
            margin: 0 0 8px 0;
            font-size: 16px;
            color: #856404;
        }
        .access-info.admin h3 {
            color: #721c24;
        }
        .access-info p {
            margin: 0;
            font-size: 14px;
            color: #533f03;
        }
        .access-info.admin p {
            color: #491217;
        }
        
        /* ログアウト中の表示 */
        .logout-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.8);
            z-index: 1000;
        }
        
        .logout-message {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: white;
            padding: 40px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            min-width: 300px;
        }
        
        .logout-message h2 {
            color: #007bff;
            margin-bottom: 20px;
            font-size: 24px;
        }
        
        .logout-message p {
            color: #666;
            font-size: 16px;
            margin-bottom: 20px;
        }
        
        .countdown {
            font-size: 48px;
            font-weight: bold;
            color: #007bff;
            margin: 20px 0;
        }
        
        .spinner {
            display: inline-block;
            width: 40px;
            height: 40px;
            border: 4px solid #f3f3f3;
            border-top: 4px solid #007bff;
            border-radius: 50%;
            animation: spin 1s linear infinite;
            margin: 10px auto;
        }
        
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
        
        /* レスポンシブデザイン */
        @media (max-width: 768px) {
            .header h1 {
                font-size: 22px;
            }
            .user-info {
                float: none;
                margin-top: 15px;
                text-align: center;
                font-size: 14px;
            }
            .menu-container {
                padding: 20px;
            }
            .menu-grid {
                grid-template-columns: 1fr;
            }
            .menu-item {
                font-size: 16px;
                padding: 15px;
            }
        }
    </style>
</head>
<body>
    <%
        // セッションからユーザー情報を取得
        UserInfoDto loginUser = (UserInfoDto) session.getAttribute("loginUser");
        RoleDto userRole = (RoleDto) session.getAttribute("userRole");
        
        // ログインしていない場合はログイン画面にリダイレクト
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // 管理者権限の判定（例：role_id = 1 が管理者、role_name = "管理者" または "ADMIN"）
        boolean isAdmin = false;
        if (userRole != null) {
            // roleIdまたはroleNameで管理者を判定
            isAdmin = (userRole.getRoleId() == 1) || 
                     "管理者".equals(userRole.getRoleName()) || 
                     "ADMIN".equalsIgnoreCase(userRole.getRoleName());
        }
        
        // 現在時刻による挨拶文を生成
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
        String greeting = "";
        if (hour >= 5 && hour < 12) {
            greeting = "おはようございます";
        } else if (hour >= 12 && hour < 17) {
            greeting = "こんにちは";
        } else {
            greeting = "こんばんは";
        }
    %>
    
    <div class="container">
        <div class="header clearfix">
            <h1>ユーザ管理システム</h1>
            <div class="user-info">
                <span class="username"><%= loginUser.getUserName() %></span>さん
                <% if (userRole != null) { %>
                    <span class="role-badge <%= isAdmin ? "role-admin" : "role-user" %>">
                        <%= userRole.getRoleName() %>
                    </span>
                <% } %>
            </div>
        </div>
        
        <div class="welcome-message">
            <h2><%= greeting %>、<%= loginUser.getUserName() %>さん</h2>
            <p>本日もユーザ管理システムをご利用いただきありがとうございます。</p>
        </div>
        
        <% if (isAdmin) { %>
            <div class="access-info admin">
                <h3>🔐 管理者権限</h3>
                <p>すべての機能にアクセスできます。ユーザー情報の閲覧・登録・編集・削除が可能です。</p>
            </div>
        <% } else { %>
            <div class="access-info">
                <h3>👤 一般ユーザー権限</h3>
                <p>ユーザー情報の閲覧・検索のみ可能です。登録・編集・削除はできません。</p>
            </div>
        <% } %>
        
        <div class="menu-container">
            <h2 class="menu-title">メニュー選択</h2>
            
            <div class="menu-grid">
                <!-- すべてのユーザーがアクセス可能 -->
                <a href="select.jsp" class="menu-item">
                    <span class="icon">👥</span>
                    ユーザー一覧・検索
                </a>
                
                <!-- 管理者のみアクセス可能 -->
                <% if (isAdmin) { %>
                    <a href="insert.jsp" class="menu-item admin-only">
                        <span class="icon">➕</span>
                        ユーザー登録
                    </a>
                <% } %>
            </div>
            
            <div class="logout-section">
                <a href="#" class="logout-link" onclick="handleLogout(event)">
                    🚪 ログアウト
                </a>
            </div>
        </div>
    </div>
    
    <!-- ログアウト中のオーバーレイ -->
    <div class="logout-overlay" id="logoutOverlay">
        <div class="logout-message">
            <h2>ログアウト中...</h2>
            <div class="spinner"></div>
            <p>お疲れ様でした</p>
            <div class="countdown" id="countdown">3</div>
            <p>秒後にトップページに移動します</p>
        </div>
    </div>
    
    <script>
        // ログアウト処理
        function handleLogout(event) {
            event.preventDefault();
            
            if (confirm('ログアウトしますか？')) {
                // オーバーレイを表示
                const overlay = document.getElementById('logoutOverlay');
                const countdownElement = document.getElementById('countdown');
                overlay.style.display = 'block';
                
                // LogoutServletを呼び出してセッションを無効化
                fetch('LogoutServlet', {
                    method: 'POST',
                    headers: {
                        'X-Requested-With': 'XMLHttpRequest',
                        'Accept': 'application/json'
                    }
                }).then(response => {
                    if (response.ok) {
                        // カウントダウンを開始
                        let countdown = 3;
                        const countdownInterval = setInterval(() => {
                            countdown--;
                            countdownElement.textContent = countdown;
                            
                            if (countdown <= 0) {
                                clearInterval(countdownInterval);
                                // index.jspに遷移
                                window.location.href = 'index.jsp';
                            }
                        }, 1000);
                    } else {
                        throw new Error('Logout failed');
                    }
                }).catch(error => {
                    console.error('Logout error:', error);
                    // エラーが発生した場合も3秒後にindex.jspに遷移
                    let countdown = 3;
                    const countdownInterval = setInterval(() => {
                        countdown--;
                        countdownElement.textContent = countdown;
                        
                        if (countdown <= 0) {
                            clearInterval(countdownInterval);
                            window.location.href = 'index.jsp';
                        }
                    }, 1000);
                });
            }
        }
        
        // セッションタイムアウト警告（25分後に警告）
        setTimeout(function() {
            if (confirm('セッションがまもなくタイムアウトします。継続しますか？')) {
                // 何らかのアクション（例：隠しフォームを送信してセッションを延長）
                fetch('SessionExtendServlet', {method: 'POST'})
                    .catch(error => console.log('Session extend failed:', error));
            }
        }, 25 * 60 * 1000); // 25分
        
        // メニューアイテムにキーボードショートカット対応
        document.addEventListener('keydown', function(e) {
            if (e.ctrlKey || e.metaKey) {
                switch(e.key) {
                    case '1':
                        e.preventDefault();
                        window.location.href = 'select.jsp';
                        break;
                    <% if (isAdmin) { %>
                    case '2':
                        e.preventDefault();
                        window.location.href = 'insert.jsp';
                        break;
                    <% } %>
                    case 'l':
                        e.preventDefault();
                        handleLogout(e);
                        break;
                }
            }
        });
        
        // メニューアイテムのアクセシビリティ向上
        document.querySelectorAll('.menu-item').forEach(item => {
            item.addEventListener('focus', function() {
                this.style.outline = '3px solid #007bff';
            });
            item.addEventListener('blur', function() {
                this.style.outline = 'none';
            });
        });
        
        // 権限情報の表示/非表示切り替え（オプション）
        <% if (isAdmin) { %>
        console.log('管理者としてログインしています');
        <% } else { %>
        console.log('一般ユーザーとしてログインしています');
        <% } %>
    </script>
</body>
</html>