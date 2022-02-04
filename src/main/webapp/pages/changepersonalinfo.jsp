<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/missing.css">
    <title>Change personal Info</title>
</head>

<body>
<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<div class="row">
    <form action="controller" method="POST" class="form-container">
        <div class="col-md-1">
            <input type="text" class="form-control" name="name" placeholder="<fmt:message key="label.name"/>"
                   value="<c:out value="${user.name }"/>" pattern=".*[^<>]" required>
        </div>
        <div class="col-md-1">
            <input type="text" class="form-control" name="surname" placeholder="<fmt:message key="label.surname"/>"
                   value="<c:out value="${user.surname }"/>" pattern=".*[^<>]" required>
        </div>
            <input type="hidden" name="command" value="change_personal_info">
            <button type="submit" class="btn-brown" >
                <fmt:message key="label.change" />
            </button>
    </form>
</div>
</body>
</html>
