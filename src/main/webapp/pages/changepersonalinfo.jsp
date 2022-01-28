<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Interpol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>

<body>
<div class="row">
    <form action="controller" method="POST" class="form-container">
        <div class="col-md-1">
            <input type="text" class="form-control" name="name" value="${user.name }" pattern=".*[^<>]" required>
        </div>
        <div class="col-md-1">
            <input type="text" class="form-control" name="surname" value="${user.surname }" pattern=".*[^<>]">
        </div>
            <input type="hidden" name="command" value="change_personal_info">
            <button type="submit" class="btn-brown" >
                <fmt:message key="label.stream" />
            </button>
    </form>
</div>
</body>
</html>

