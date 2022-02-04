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
    <title>Admin Add Page</title>
</head>

<body>
<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<div class="form-inline">
    <form action="controller" enctype='multipart/form-data' method="POST">
        <input type="text" name="title" value=""  maxlength="45" pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.title"/>>
        <input type="text" name="news_article" value="" maxlength="400" required pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.news_article"/>>
        <input type="file" name="image" value="" pattern=".*[^<>]" required class="form-control"
               placeholder=<fmt:message key="label.image"/>>
        <input type="hidden" name="command" value="add_news">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="label.add_news"/>
        </button>
    </form>
</div>

<div class="form-inline">
    <form action="controller" enctype='multipart/form-data' method="POST">
        <input type="text" name="name" value="" pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.name"/>>
        <input type="text" name="surname" value="" required pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.surname"/>>
        DisappearanceDate: <input class="datepicker form-control" type="date"
                                  name="disappearance_date" required
                                  placeholder=<fmt:message key="label.disappearanceDate"/>/>
        <input type="file" name="photo" value="" pattern=".*[^<>]" required class="form-control"
               placeholder=<fmt:message key="label.photo"/>>
        <input type="hidden" name="command" value="add_missing">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="label.add_missing"/>
        </button>
    </form>
</div>

<div class="form-inline">
    <form action="controller" enctype='multipart/form-data' method="POST">
        <input type="text" name="name" value="" required pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.name"/>>
        <input type="text" name="surname" value="" required pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.surname"/>>
        <input type="text" name="crimeCity" value="" required pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.crimeCity"/>>
        Date of birth: <input class="datepicker form-control" type="date" id="dob"
                              name="DOB" required
                              placeholder=<fmt:message key="label.crimeDOB"/>/>
        <input type="text" name="reward" value="" pattern="\d+[.]\d{8,2}" class="form-control"
               placeholder=<fmt:message key="label.reward"/>>
        <select id="select" name="crimeType">
            <c:forEach var="type" items="${crimeType}">
                <option class="dropdown-item" value="<c:out value="${type}"/>">${type}</option>
            </c:forEach>
        </select>
<%--        <input type="hidden" name="command" value="">--%>
        <input type="file" name="photo" value="" pattern=".*[^<>]" required class="form-control"
               placeholder=<fmt:message key="label.photo"/>>
        <input type="hidden" name="command" value="add_criminal">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="label.criminal"/>
        </button>
    </form>
</div>
</body>
</html>
