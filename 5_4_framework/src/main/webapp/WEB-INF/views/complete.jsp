<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.title.complete" /></title>
    
</head>
<body>
    <div class="container">
        <h1><spring:message code="page.title.complete" /></h1>
        
        <div class="success-message">
            <div class="check-icon">✓</div>
            <div class="success-text"><spring:message code="message.register.complete" /></div>
            <p style="color: #666;"><spring:message code="message.register.success" /></p>
        </div>
        
        <div>
            <a href="/" class="back-btn"><spring:message code="button.back" /></a>
        </div>
    </div>
</body>
</html>