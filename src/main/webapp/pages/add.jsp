<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>


<%--<meta name="viewport"
      content="width=device-width, initial-scale=1,  shrink-to-fit=no charset=utf-8" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>
<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
      integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
      crossorigin="anonymous">
<link rel="stylesheet" href="https://bootstraptema.ru/assets/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/fine-uploader/fine-uploader-new.css" />
<script type="text/javascript" src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://bootstraptema.ru/plugins/2015/fine-uploader/jquery.fine-uploader.js"></script>--%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add News</title>
<body>
<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<div class="form-inline">
    <form action="controller" enctype='multipart/form-data' method="POST">
        <input type="text" name="title" value="" pattern=".*[^<>]" class="form-control"
               placeholder=<fmt:message key="label.title"/>>
        <input type="text" name="news_article" value="" required pattern=".*[^<>]" class="form-control"
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
        DisappearanceDate: <input class="datepicker form-control" type="date" value=""
                                  name="disappearance_date" required
                                  placeholder=<fmt:message key="label.disappearanceDate"/>/>
        <input type="file" name="photo" value="" pattern=".*[^<>]" required class="form-control"
               placeholder=<fmt:message key="label.photo"/>>
        <input type="hidden" name="command" value="add_missing">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="label.add_news"/>
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
        Date of birth: <input class="datepicker form-control" type="date" value=""
                              name="disappearance_date" required
                              placeholder=<fmt:message key="label.disappearanceDate"/>/>
        <input type="text" name="reward" value="" pattern="\d+[.]\d{8,2}" class="form-control"
               placeholder=<fmt:message key="label.reward"/>>
        <div class="dropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                    data-bs-toggle="dropdown" aria-expanded="false">
                <fmt:message key="label.crimeType"/>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                <c:forEach var="type" items="${crimeType}">
                    <button type="submit" class="dropdown-item"
                            name="application_status" value="${type}">
                        <c:out value="${type}"/>
                        <input type="hidden" name="command" value="">
                    </button>
                </c:forEach>
            </ul>
        </div>
        <input type="file" name="photo" value="" pattern=".*[^<>]" required class="form-control"
               placeholder=<fmt:message key="label.photo"/>>
        <input type="hidden" name="command" value="add_news">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="label.add_news"/>
        </button>
    </form>
</div>
</body>
</html>
