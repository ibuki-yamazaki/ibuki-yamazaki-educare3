<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品管理システム</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .form-section { margin-bottom: 20px; padding: 15px; border: 1px solid #ccc; }
        .error { color: red; font-size: 12px; }
        input[type="text"], input[type="number"] { padding: 5px; margin: 5px; }
        button { padding: 8px 16px; margin: 5px; }
    </style>
</head>
<body>
    <h1>商品管理システム</h1>
    
    <!-- 商品登録フォーム -->
    <div class="form-section">
        <h2>商品登録</h2>
        <form:form modelAttribute="productForm" action="/register" method="post">
            <div>
                <label for="name">商品名:</label>
                <form:input path="name" id="name" />
                <form:errors path="name" cssClass="error" />
            </div>
            <div>
                <label for="price">価格:</label>
                <form:input path="price" type="number" id="price" />
                <form:errors path="price" cssClass="error" />
            </div>
            <button type="submit">登録</button>
        </form:form>
    </div>
    
    <!-- 商品検索フォーム -->
    <div class="form-section">
        <h2>商品検索</h2>
        <form method="get" action="/">
            <label for="searchName">商品名で検索:</label>
            <input type="text" name="searchName" id="searchName" value="${param.searchName}" />
            <button type="submit">検索</button>
            <a href="/">全件表示</a>
        </form>
    </div>
    
    <!-- 商品一覧 -->
    <div>
        <h2>商品一覧</h2>
        <c:if test="${empty products}">
            <p>商品がありません。</p>
        </c:if>
        <c:if test="${not empty products}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>商品名</th>
                        <th>価格</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>¥${product.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>