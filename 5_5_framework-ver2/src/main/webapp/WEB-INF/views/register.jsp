<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー登録</title>
    <style>
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            font-family: Arial, sans-serif;
        }
        
        .header {
            text-align: center;
            margin-bottom: 30px;
        }
        
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        
        .validation-note {
            background-color: #d1ecf1;
            color: #0c5460;
            padding: 15px;
            border: 1px solid #bee5eb;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        
        .form-group {
            margin-bottom: 20px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        .required {
            color: red;
        }
        
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        
        .input-error {
            border-color: #dc3545 !important;
            background-color: #f8f8f8;
        }
        
        .field-error-message {
            color: #dc3545;
            font-size: 12px;
            margin-top: 5px;
            display: none; /* デフォルトで非表示 */
        }
        
        .button-group {
            text-align: center;
            margin-top: 30px;
        }
        
        .btn {
            padding: 10px 20px;
            margin: 0 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        
        .btn:hover {
            opacity: 0.8;
        }
        
        .nav-links {
            text-align: center;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #eee;
        }
        
        .nav-link {
            display: inline-block;
            margin: 0 15px;
            text-decoration: none;
            color: #007bff;
        }
        
        .nav-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>ユーザー登録</h2>
        </div>

        <!-- エラーメッセージ表示 -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">
                ${errorMessage}
            </div>
        </c:if>

        <div class="validation-note">
            <strong>入力規則:</strong><br>
            • 全ての項目は必須入力です<br>
            • IDは重複できません<br>
            • パスワードは確認画面で再入力が必要です
        </div>

        <form action="/insertConfirm" method="post" id="registerForm">
            <div class="form-group">
                <label for="id">ID <span class="required">*</span></label>
                <input type="text" id="id" name="id" value="${id}" 
                       placeholder="ユーザーIDを入力してください" 
                       maxlength="20" />
                <div id="id-error" class="field-error-message"></div>
            </div>

            <div class="form-group">
                <label for="name">名前 <span class="required">*</span></label>
                <input type="text" id="name" name="name" value="${name}" 
                       placeholder="ユーザー名を入力してください" 
                       maxlength="50" />
                <div id="name-error" class="field-error-message"></div>
            </div>

            <div class="form-group">
                <label for="tel">TEL <span class="required">*</span></label>
                <input type="text" id="tel" name="tel" value="${tel}" 
                       placeholder="電話番号を入力してください" 
                       maxlength="15" />
                <div id="tel-error" class="field-error-message"></div>
            </div>

            <div class="form-group">
                <label for="role">権限 <span class="required">*</span></label>
                <select id="role" name="role">
                    <option value="">-- 権限を選択してください --</option>
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.roleId}" 
                                <c:if test="${selectedRoleId == role.roleId}">selected</c:if>>
                            ${role.roleName}
                        </option>
                    </c:forEach>
                </select>
                <div id="role-error" class="field-error-message"></div>
            </div>

            <div class="form-group">
                <label for="pass">PASS <span class="required">*</span></label>
                <input type="password" id="pass" name="pass" 
                       placeholder="パスワードを入力してください" 
                       maxlength="20" />
                <div id="pass-error" class="field-error-message"></div>
            </div>

            <div class="button-group">
                <input type="submit" value="確認" class="btn btn-primary" />
                <button type="button" onclick="goBack()" class="btn btn-secondary">戻る</button>
            </div>
        </form>

        <div class="nav-links">
            <a href="/menu" class="nav-link">📋 メニュー</a>
            <a href="/search" class="nav-link">🔍 検索</a>
            <a href="/logout" class="nav-link">🚪 ログアウト</a>
        </div>
    </div>

    <script>
        // フォーム送信前のバリデーション
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            const id = document.getElementById('id').value.trim();
            const name = document.getElementById('name').value.trim();
            const tel = document.getElementById('tel').value.trim();
            const role = document.getElementById('role').value;
            const pass = document.getElementById('pass').value.trim();
            
            let hasError = false;
            
            // エラー表示をクリア
            clearAllErrors();
            
            // 各フィールドのバリデーションチェック
            if (!id) {
                showFieldError('id', 'IDは必須です');
                hasError = true;
            } else if (id.length > 20) {
                showFieldError('id', 'IDは20文字以内で入力してください');
                hasError = true;
            }
            
            if (!name) {
                showFieldError('name', '名前は必須です');
                hasError = true;
            } else if (name.length > 50) {
                showFieldError('name', '名前は50文字以内で入力してください');
                hasError = true;
            }
            
            if (!tel) {
                showFieldError('tel', 'TELは必須です');
                hasError = true;
            } else if (tel.length > 15) {
                showFieldError('tel', 'TELは15文字以内で入力してください');
                hasError = true;
            } else if (!/^[0-9\-]+$/.test(tel)) {
                showFieldError('tel', 'TELは数字とハイフンのみ入力可能です');
                hasError = true;
            }
            
            if (!role) {
                showFieldError('role', '権限は必須です');
                hasError = true;
            }
            
            if (!pass) {
                showFieldError('pass', 'PASSは必須です');
                hasError = true;
            } else if (pass.length > 20) {
                showFieldError('pass', 'PASSは20文字以内で入力してください');
                hasError = true;
            }
            
            if (hasError) {
                e.preventDefault();
                return false;
            }
        });

        // フィールドエラー表示
        function showFieldError(fieldId, message) {
            const field = document.getElementById(fieldId);
            const errorElement = document.getElementById(fieldId + '-error');
            
            // フィールドにエラースタイルを適用
            field.classList.add('input-error');
            
            // エラーメッセージを表示
            errorElement.textContent = message;
            errorElement.style.display = 'block';
        }

        // エラー表示をクリア
        function clearFieldError(fieldId) {
            const field = document.getElementById(fieldId);
            const errorElement = document.getElementById(fieldId + '-error');
            
            field.classList.remove('input-error');
            errorElement.style.display = 'none';
            errorElement.textContent = '';
        }

        // 全てのエラー表示をクリア
        function clearAllErrors() {
            const fields = ['id', 'name', 'tel', 'role', 'pass'];
            fields.forEach(fieldId => {
                clearFieldError(fieldId);
            });
        }

        // 戻るボタンの処理
        function goBack() {
            if (confirm('入力内容が失われますが、メニューに戻りますか？')) {
                window.location.href = '/menu';
            }
        }

        // リアルタイムバリデーション（入力時とフォーカスが外れた時）
        document.addEventListener('DOMContentLoaded', function() {
            const textFields = ['id', 'name', 'tel', 'pass'];
            
            textFields.forEach(fieldId => {
                const field = document.getElementById(fieldId);
                
                // フォーカスが外れた時のバリデーション
                field.addEventListener('blur', function() {
                    validateField(fieldId, this.value.trim());
                });

                // 入力時にエラーをクリア（入力があった場合のみ）
                field.addEventListener('input', function() {
                    const value = this.value.trim();
                    if (value !== '') {
                        clearFieldError(fieldId);
                        // リアルタイムで形式チェック
                        if (fieldId === 'tel' && value && !/^[0-9\-]+$/.test(value)) {
                            showFieldError(fieldId, 'TELは数字とハイフンのみ入力可能です');
                        }
                    }
                });
            });

            // 権限セレクトボックスのバリデーション
            const roleField = document.getElementById('role');
            roleField.addEventListener('change', function() {
                if (this.value === '') {
                    showFieldError('role', '権限は必須です');
                } else {
                    clearFieldError('role');
                }
            });
        });

        // フィールド個別バリデーション関数
        function validateField(fieldId, value) {
            const field = document.getElementById(fieldId);
            const maxLengths = {
                'id': 20,
                'name': 50,
                'tel': 15,
                'pass': 20
            };

            if (!value) {
                const fieldNames = {
                    'id': 'ID',
                    'name': '名前',
                    'tel': 'TEL',
                    'pass': 'PASS'
                };
                showFieldError(fieldId, fieldNames[fieldId] + 'は必須です');
            } else if (value.length > maxLengths[fieldId]) {
                const fieldNames = {
                    'id': 'ID',
                    'name': '名前',
                    'tel': 'TEL',
                    'pass': 'PASS'
                };
                showFieldError(fieldId, fieldNames[fieldId] + 'は' + maxLengths[fieldId] + '文字以内で入力してください');
            } else if (fieldId === 'tel' && !/^[0-9\-]+$/.test(value)) {
                showFieldError(fieldId, 'TELは数字とハイフンのみ入力可能です');
            } else {
                clearFieldError(fieldId);
            }
        }
    </script>
</body>
</html>