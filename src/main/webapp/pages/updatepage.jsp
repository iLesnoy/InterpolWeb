<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/missing.css">
    <title>AdminAccount</title>
</head>

<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<body>
<ul>
    <c:if test="${missing !=null }">
        <form action="controller" method="POST" class="form-container" enctype='multipart/form-data'>
            <c:forEach var="elem" items="${missing}" varStatus="status">
                <div class="col-md-1">
                    <input type="text" class="form-control" name="missingId" value="${elem.missingPeopleId }"
                           pattern=".*[^<>]" required>
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="first_name" value="${elem.name }" pattern=".*[^<>]"
                           required>
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="last_name" value="${elem.surname }" pattern=".*[^<>]"
                           required>
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="disappearance_date" value="${elem.disappearanceDate }"
                           pattern=".*[^<>]" required>
                </div>
                <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                <input type="file" name="photo" value="${elem.photo}" pattern=".*[^<>]"  class="form-control"
                       placeholder=<fmt:message key="label.photo"/>>
            </c:forEach>
            <input type="hidden" name="command" value="update_missing">
            <button type="submit" class="btn-brown">
                <fmt:message key="label.submit"/>
            </button>
        </form>
    </c:if>
</ul>

<ul>
    <c:if test="${wantedCriminals !=null }">
        <form action="controller" method="POST" class="form-container" enctype='multipart/form-data'>
            <c:forEach var="wanted" items="${wantedCriminals}" varStatus="status">
                <div class="col-md-1">
                    <input type="text" class="form-control" name="name" value="${wanted.guiltyId }"
                           pattern=".*[^<>]" required>
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="surname" value="${wanted.firstName }"
                           pattern=".*[^<>]">
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="name" value="${wanted.lastName }"
                           pattern=".*[^<>]" required>
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="surname" value="${wanted.crimeCity }"
                           pattern=".*[^<>]">
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="name" value="${wanted.crimeAddress }"
                           pattern=".*[^<>]" required>
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="surname" value="${wanted.crimeDOB }"
                           pattern=".*[^<>]">
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="surname" value="${wanted.reward }"
                           pattern=".*[^<>]">
                </div>
                <div class="col-md-1">
                    <input type="text" class="form-control" name="surname" value="${wanted.crimeType }"
                           pattern=".*[^<>]">
                </div>
                <img alt="img" src="data:image/jpeg;base64,${wanted.photo}"/>
                <input type="file" name="photo" value="${wanted.photo}" pattern=".*[^<>]"  class="form-control"
                       placeholder=<fmt:message key="label.photo"/>>
            </c:forEach>
            <input type="hidden" name="command" value="change_personal_info">
            <button type="submit" class="btn-brown">
                <fmt:message key="label.submit"/>
            </button>
        </form>
    </c:if>
</ul>


<c:if test="${news_article !=null }">
    <form action="controller" method="POST" class="form-container" enctype='multipart/form-data'>
        <c:forEach var="elem2" items="${news_article}" varStatus="status">
            <div class="col-md-1">
                <input type="text" class="form-control" name="article_id" value="${elem2.articleId}" pattern=".*[^<>]">
            </div>
            <div class="col-md-1">
                <input type="text" class="form-control" name="title" value="${elem2.title}" pattern=".*[^<>]">
            </div>
            <div class="col-md-1">
                <input type="text" class="form-control" name="news_article" value="${elem2.newsArticle}"
                       pattern=".*[^<>]">
            </div>
            <img alt="img" src="data:image/jpeg;base64,${elem2.image}"/>
            <input type="file" name="image" value="${elem2.image}" pattern=".*[^<>]"  class="form-control"
                   placeholder=<fmt:message key="label.photo"/>>
        </c:forEach>
        <input type="hidden" name="command" value="update_article">
        <button type="submit" class="btn-brown">
            <fmt:message key="label.submit"/>
        </button>
    </form>
</c:if>


</body>