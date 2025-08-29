<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録完了</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
        }
        .success-icon {
            font-size: 48px;
            color: #28a745;
            margin-bottom: 15px;
        }
        .success-message {
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            color: #155724;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-weight: bold;
            text-align: center;
        }
        .error-message {
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            color: #721c24;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-weight: bold;
            text-align: center;
        }
        .button-group {
            text-align: center;
            margin-top: 30px;
        }
        .btn {
            padding: 10px 20px;
            margin: 0 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #f8f9fa;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
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
        .btn-secondary {
            background-color: #6c757d;
            color: white;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #545b62;
            border-color: #545b62;
        }
        .nav-links {
            text-align: center;
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #dee2e6;
        }
        .nav-link {
            color: #007bff;
            text-decoration: none;
            margin: 0 15px;
        }
        .nav-link:hover {
            text-decoration: underline;
        }
        .info-box {
            background-color: #e7f3ff;
            border: 1px solid #b8daff;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div class="success-icon">✓</div>
            <h2>ユーザー登録</h2>
        </div>

        <!-- 成功メッセージ表示 -->
        <c:if test="${not empty successMessage}">
            <div class="success-message">
                <div class="success-icon">✓</div>
                ${successMessage}
            </div>
        </c:if>

        <!-- エラーメッセージ表示 -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ⚠ ${errorMessage}
            </div>
        </c:if>

        <!-- 登録完了時の表示 -->
        <c:if test="${not empty successMessage}">
            <div class="info-box">
                <p><strong>登録が正常に完了しました。</strong></p>
                <p>新しいユーザーがシステムに追加されました。</p>
            </div>
        </c:if>

        <!-- エラー時の表示 -->
        <c:if test="${not empty errorMessage}">
            <div class="info-box">
                <p><strong>登録処理中にエラーが発生しました。</strong></p>
                <p>もう一度お試しいただくか、システム管理者にお問い合わせください。</p>
            </div>
        </c:if>

        <div class="button-group">
            <c:choose>
                <c:when test="${not empty successMessage}">
                    <!-- 登録成功時のボタン -->
                    <a href="/insert" class="btn btn-primary">続けて登録</a>
                    <a href="/menu" class="btn btn-secondary">メニューに戻る</a>
                </c:when>
                <c:otherwise>
                    <!-- エラー時のボタン -->
                    <a href="/insert" class="btn btn-primary">再試行</a>
                    <a href="/menu" class="btn btn-secondary">メニューに戻る</a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="nav-links">
            <a href="/menu" class="nav-link">📋 メニュー</a>
            <a href="/search" class="nav-link">🔍 検索</a>
            <a href="/logout" class="nav-link">🚪 ログアウト</a>
        </div>
    </div>

    <script>
        // 成功時の自動リダイレクト（オプション）
        <c:if test="${not empty successMessage}">
        // 5秒後に自動でメニューに戻る（必要に応じてコメントアウト）
        /*
        setTimeout(function() {
            if (confirm('5秒経過しました。メニューに戻りますか？')) {
                window.location.href = '/menu';
            }
        }, 5000);
        */
        </c:if>

        // エラー時の処理
        <c:if test="${not empty errorMessage}">
        console.error('登録エラー: ${errorMessage}');
        </c:if>
    </script>
</body>
</html>