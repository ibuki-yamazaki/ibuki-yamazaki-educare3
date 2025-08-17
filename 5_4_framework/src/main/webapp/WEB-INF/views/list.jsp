<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.title.list" /></title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 20px; 
            background-color: #f5f5f5;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        .result-section {
            border: 2px solid #007bff;
            border-radius: 8px;
            padding: 20px;
            background-color: #f8f9fa;
        }
        table { 
            width: 100%;
            border-collapse: collapse; 
            margin-bottom: 20px;
        }
        th, td { 
            border: 2px solid #007bff; 
            padding: 12px; 
            text-align: center;
        }
        th { 
            background-color: #007bff;
            color: white;
            font-weight: bold;
        }
        td {
            background-color: white;
        }
        .back-btn {
            background-color: #6c757d;
            color: white;
            padding: 10px 20px;
            border: 2px solid #6c757d;
            border-radius: 4px;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
        }
        .back-btn:hover {
            background-color: #545b62;
            border-color: #545b62;
            color: white;
            text-decoration: none;
        }
        .no-results {
            text-align: center;
            color: #666;
            font-size: 16px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1><spring:message code="page.title.list" /></h1>
        
        <div class="result-section">
            <h2><spring:message code="title.search.result" /></h2>
            
            <!-- 検索条件の表示 -->
            <c:if test="${not empty searchName or not empty searchPrice}">
                <div style="margin-bottom: 15px; padding: 10px; background-color: #e7f3ff; border: 1px solid #b3d9ff; border-radius: 4px;">
                    <strong>検索条件：</strong>
                    <c:if test="${not empty searchName}">
                        <spring:message code="label.name" />: ${searchName}
                    </c:if>
                    <c:if test="${not empty searchName and not empty searchPrice}"> / </c:if>
                    <c:if test="${not empty searchPrice}">
                        <spring:message code="label.price" />: ${searchPrice}
                    </c:if>
                </div>
            </c:if>
            
            <c:if test="${empty products}">
                <div class="no-results">
                    <p><spring:message code="message.no.products" /></p>
                </div>
            </c:if>
            
            <c:if test="${not empty products}">
                <table>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th><spring:message code="label.name" /></th>
                            <th><spring:message code="label.price" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <div>
                <a href="/" class="back-btn"><spring:message code="button.back" /></a>
            </div>
        </div>
    </div>
</body>
</html>