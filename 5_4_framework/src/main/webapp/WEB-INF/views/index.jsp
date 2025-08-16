<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.title.top" /></title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px; 
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 10px;
        }
        .subtitle {
            text-align: center;
            color: #666;
            margin-bottom: 30px;
            font-size: 16px;
        }
        .form-section { 
            margin-bottom: 25px; 
            padding: 20px; 
            border: 2px solid #ddd;
            border-radius: 8px;
            background-color: #fafafa;
        }
        .input-row {
            display: flex;
            align-items: flex-start;
            margin-bottom: 15px;
        }
        .input-row label {
            width: 80px;
            font-weight: bold;
            color: #333;
            padding-top: 8px;
        }
        .input-column {
            flex: 1;
            margin-left: 10px;
        }
        .input-column input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        .input-column input.error-field {
            border-color: #dc3545;
        }
        .button-row {
            display: flex;
            gap: 10px;
            margin-top: 20px;
        }
        button {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
        }
        .search-btn {
            background-color: #007bff;
            color: white;
        }
        .register-btn {
            background-color: #28a745;
            color: white;
        }
        .search-btn:hover {
            background-color: #0056b3;
        }
        .register-btn:hover {
            background-color: #218838;
        }
        .error {
            color: #dc3545;
            font-size: 12px;
            margin-top: 5px;
            display: block;
        }
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><spring:message code="page.title.top" /></h1>
        <p class="subtitle"><spring:message code="title.main.subtitle" /></p>
        
        <!-- 検索フォーム -->
        <div class="form-section" id="searchForm">
            <form method="get" action="/search">
                <div class="input-row">
                    <label><spring:message code="label.name" /> :</label>
                    <div class="input-column">
                        <input type="text" name="searchName" />
                    </div>
                </div>
                <div class="input-row">
                    <label><spring:message code="label.price" /> :</label>
                    <div class="input-column">
                        <input type="text" disabled style="background-color: #e9ecef;" />
                    </div>
                </div>
                <div class="button-row">
                    <button type="submit" class="search-btn"><spring:message code="button.search" /></button>
                    <button type="button" class="register-btn" onclick="showRegisterForm()"><spring:message code="button.register" /></button>
                </div>
            </form>
        </div>

        <!-- 登録フォーム -->
        <div class="form-section hidden" id="registerForm">
            <form:form modelAttribute="productForm" action="/register" method="post">
                <div class="input-row">
                    <label><spring:message code="label.name" /> :</label>
                    <div class="input-column">
                        <form:input path="name" cssClass="${not empty bindingResult and bindingResult.hasFieldErrors('name') ? 'error-field' : ''}" />
                        <form:errors path="name" cssClass="error" />
                    </div>
                </div>
                
                <div class="input-row">
                    <label><spring:message code="label.price" /> :</label>
                    <div class="input-column">
                        <form:input path="price" type="number" cssClass="${not empty bindingResult and bindingResult.hasFieldErrors('price') ? 'error-field' : ''}" />
                        <form:errors path="price" cssClass="error" />
                    </div>
                </div>
                
                <div class="button-row">
                    <button type="button" class="search-btn" onclick="showSearchForm()"><spring:message code="button.search" /></button>
                    <button type="submit" class="register-btn"><spring:message code="button.register" /></button>
                </div>
            </form:form>
        </div>
    </div>

    <script>
        // バリデーションエラーがある場合は登録フォームを表示
        <c:if test="${hasValidationErrors}">
            showRegisterForm();
        </c:if>
        
        function showRegisterForm() {
            document.getElementById('searchForm').classList.add('hidden');
            document.getElementById('registerForm').classList.remove('hidden');
        }
        
        function showSearchForm() {
            document.getElementById('registerForm').classList.add('hidden');
            document.getElementById('searchForm').classList.remove('hidden');
        }
    </script>
</body>
</html>