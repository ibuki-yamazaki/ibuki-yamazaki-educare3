<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理システム</title>
<style>
    .error { color: red; }
    table { border-collapse: collapse; width: 100%; margin-top: 20px; }
    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
    th { background-color: #f2f2f2; }
    .form-section { border: 1px solid #ccc; padding: 15px; margin: 20px 0; }
</style>
</head>
<body>

<h1>検索条件または登録情報を入力してください</h1>

<!-- 検索フォーム -->
<div class="form-section">
    <h3>商品検索</h3>
    <form action="/" method="get">
        <label>商品名: <input type="text" name="searchName" value="${param.searchName}" /></label>
        <button type="submit">検索</button>
    </form>
</div>

<!-- 登録フォーム -->
<div class="form-section">
    <h3>商品登録</h3>
    <form:form action="register" modelAttribute="productForm" method="post">
        <div>
            <label>name: <form:input path="name" /></label>
            <form:errors path="name" cssClass="error" />
        </div>
        <div>
            <label>price: <form:input path="price" /></label>
            <form:errors path="price" cssClass="error" />
        </div>
        <div>
            <button type="submit">登録</button>
        </div>
    </form:form>
</div>

<!-- 商品一覧 -->
<h3>商品一覧</h3>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>商品名</th>
            <th>価格</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${fn:escapeXml(product.id)}</td>
                <td>${fn:escapeXml(product.name)}</td>
                <td>${fn:escapeXml(product.price)}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>