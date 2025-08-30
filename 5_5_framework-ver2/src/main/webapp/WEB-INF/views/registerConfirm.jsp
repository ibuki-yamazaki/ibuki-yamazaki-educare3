<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録確認画面</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 100px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 200px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #f8f9fa;
        }
        .readonly {
            background-color: #e9ecef;
            color: #495057;
        }
        .button-group {
            margin-top: 20px;
        }
        .btn {
            padding: 8px 16px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #f8f9fa;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #e2e6ea;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .error {
            color: red;
            margin-bottom: 15px;
            font-weight: bold;
        }
        .header {
            background-color: #f8f9fa;
            padding: 10px;
            border: 1px solid #dee2e6;
            margin-bottom: 20px;
        }
        .nav-link {
            color: #007bff;
            text-decoration: none;
            margin-left: 10px;
        }
        .nav-link:hover {
            text-decoration: underline;
        }
        .confirmation-text {
            margin-bottom: 20px;
            font-weight: bold;
        }
        .note {
            background-color: #fff3cd;
            border: 1px solid #ffeaa7;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 3px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>登録確認画面</h2>
            <p>これでよろしいですか？</p>
            <a href="/menu" class="nav-link">メニュー画面へのリンクを表示</a>
        </div>

        <!-- エラーメッセージ表示 -->
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <div class="confirmation-text">
            登録内容を確認してください
        </div>

        <div class="note">
            ID、名前、TEL、権限はテキストボックスで表示し、それぞれ編集不可とする。<br>
            権限もテキストボックスで表示する。（プルダウンはない）
        </div>

        <form action="/insert" method="post">
            <div class="form-group">
                <label for="id">ID</label>
                <input type="text" id="id" name="id" value="${id}" readonly class="readonly" />
            </div>

            <div class="form-group">
                <label for="name">名前</label>
                <input type="text" id="name" name="name" value="${name}" readonly class="readonly" />
            </div>

            <div class="form-group">
                <label for="tel">TEL</label>
                <input type="text" id="tel" name="tel" value="${tel}" readonly class="readonly" />
            </div>

            <div class="form-group">
                <label for="role">権限</label>
                <input type="text" id="role" name="role" value="${roleName}" readonly class="readonly" />
            </div>

            <div class="form-group">
                <label for="passConfirm">PASS（再入力）</label>
                <input type="password" id="passConfirm" name="passConfirm" placeholder="パスワードを再入力してください" />
            </div>

            <div class="button-group">
                <input type="submit" value="登録" class="btn btn-primary" />
                <input type="button" value="戻る" class="btn" onclick="history.back()" />
            </div>
        </form>
    </div>

    <script>
        // フォーム送信前のバリデーション
        document.querySelector('form').addEventListener('submit', function(e) {
            const passConfirm = document.getElementById('passConfirm').value.trim();
            
            if (!passConfirm) {
                alert('パスワード（再入力）を入力してください');
                e.preventDefault();
                return false;
            }
        });

        // 戻るボタンの処理（登録画面へ遷移）
        function goBack() {
            // 確認：戻るボタン押下時、登録画面へ遷移する
            if (confirm('入力内容が失われますが、登録画面に戻りますか？')) {
                window.location.href = '/insert';
            }
        }
        
        // 戻るボタンにイベントを設定
        document.addEventListener('DOMContentLoaded', function() {
            const backButton = document.querySelector('input[value="戻る"]');
            if (backButton) {
                backButton.onclick = goBack;
            }
        });
    </script>
</body>
</html>