<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログアウト</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 80vh;
        }
        .logout-container {
            max-width: 500px;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            text-align: center;
        }
        .logout-icon {
            font-size: 48px;
            color: #dc3545;
            margin-bottom: 20px;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
        }
        .message {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin: 20px 0;
            font-size: 16px;
        }
        .countdown {
            font-size: 18px;
            font-weight: bold;
            color: #007bff;
            margin: 20px 0;
        }
        .progress-bar {
            width: 100%;
            height: 8px;
            background-color: #e9ecef;
            border-radius: 4px;
            overflow: hidden;
            margin: 20px 0;
        }
        .progress {
            height: 100%;
            background-color: #007bff;
            transition: width 0.1s ease;
        }
        .button-group {
            margin-top: 30px;
        }
        .btn {
            display: inline-block;
            padding: 12px 24px;
            margin: 0 10px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
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
    </style>
</head>
<body>
    <div class="logout-container">
        <div class="logout-icon">🚪</div>
        <h1>ログアウト確認</h1>
        
        <div class="message">
            ログアウトしますか？<br>
            セッションを破棄し、トップページに戻ります。
        </div>
        
        <div class="button-group">
            <form method="POST" action="/logout" style="display: inline;">
                <button type="submit" class="btn btn-primary" id="logoutBtn">ログアウト</button>
            </form>
            <a href="/menu" class="btn btn-secondary">キャンセル</a>
        </div>
    </div>

    <script>
        // ログアウトボタンクリック時の処理
        document.getElementById('logoutBtn').addEventListener('click', function(e) {
            e.preventDefault();
            
            // ログアウト処理実行
            fetch('/logout', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                }
            })
            .then(response => {
                if (response.ok) {
                    showLogoutSuccess();
                } else {
                    alert('ログアウト処理中にエラーが発生しました');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('ログアウト処理中にエラーが発生しました');
            });
        });

        function showLogoutSuccess() {
            // 画面の内容を変更
            document.querySelector('.logout-container').innerHTML = `
                <div class="logout-icon">✅</div>
                <h1>ログアウト完了</h1>
                <div class="message">
                    ログアウトが完了しました。<br>
                    セッションを破棄し、トップページに戻ります。
                </div>
                <div class="countdown" id="countdown">3秒後にトップページに戻ります...</div>
                <div class="progress-bar">
                    <div class="progress" id="progress"></div>
                </div>
                <div class="button-group">
                    <a href="/" class="btn btn-primary">今すぐトップページに戻る</a>
                </div>
            `;

            // カウントダウン開始
            let count = 3;
            const countdownElement = document.getElementById('countdown');
            const progressElement = document.getElementById('progress');
            
            const timer = setInterval(() => {
                count--;
                countdownElement.textContent = `${count}秒後にトップページに戻ります...`;
                progressElement.style.width = `${(3-count) * 33.33}%`;
                
                if (count <= 0) {
                    clearInterval(timer);
                    window.location.href = '/';
                }
            }, 1000);
        }
    </script>
</body>
</html>