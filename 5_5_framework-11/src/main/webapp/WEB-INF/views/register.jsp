<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ユーザー登録</title>
    
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
                       maxlength="20" required />
                <div id="id-error" class="field-error-message">IDは必須です</div>
            </div>

            <div class="form-group">
                <label for="name">名前 <span class="required">*</span></label>
                <input type="text" id="name" name="name" value="${name}" 
                       placeholder="ユーザー名を入力してください" 
                       maxlength="50" required />
                <div id="name-error" class="field-error-message">名前は必須です</div>
            </div>

            <div class="form-group">
                <label for="tel">TEL <span class="required">*</span></label>
                <input type="text" id="tel" name="tel" value="${tel}" 
                       placeholder="電話番号を入力してください" 
                       maxlength="15" required />
                <div id="tel-error" class="field-error-message">TELは必須です</div>
            </div>

            <div class="form-group">
                <label for="role">権限 <span class="required">*</span></label>
                <select id="role" name="role" required>
                    <option value="">-- 権限を選択してください --</option>
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.roleId}" 
                                <c:if test="${selectedRoleId == role.roleId}">selected</c:if>>
                            ${role.roleName}
                        </option>
                    </c:forEach>
                </select>
                <div id="role-error" class="field-error-message">権限は必須です</div>
            </div>

            <div class="form-group">
                <label for="pass">PASS <span class="required">*</span></label>
                <input type="password" id="pass" name="pass" 
                       placeholder="パスワードを入力してください" 
                       maxlength="20" required />
                <div id="pass-error" class="field-error-message">PASSは必須です</div>
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
            }
            
            if (!name) {
                showFieldError('name', '名前は必須です');
                hasError = true;
            }
            
            if (!tel) {
                showFieldError('tel', 'TELは必須です');
                hasError = true;
            }
            
            if (!role) {
                showFieldError('role', '権限は必須です');
                hasError = true;
            }
            
            if (!pass) {
                showFieldError('pass', 'PASSは必須です');
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

        // リアルタイムバリデーション（フォーカスが外れた時）
        document.addEventListener('DOMContentLoaded', function() {
            const textFields = ['id', 'name', 'tel', 'pass'];
            
            textFields.forEach(fieldId => {
                const field = document.getElementById(fieldId);
                field.addEventListener('blur', function() {
                    if (this.value.trim() === '') {
                        showFieldError(fieldId, this.getAttribute('placeholder').replace('を入力してください', '') + 'は必須です');
                    } else {
                        clearFieldError(fieldId);
                    }
                });

                // 入力時にエラーをクリア
                field.addEventListener('input', function() {
                    if (this.value.trim() !== '') {
                        clearFieldError(fieldId);
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
    </script>
</body>
</html>