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
<style>
    .form-rows input, .form-rows textarea, .form-rows button{
        margin-bottom: 15px !important;
    }
</style>

<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<body>
    <c:if test="${missing !=null }">
        <form action="controller" method="POST" class="form-container" enctype='multipart/form-data'>
            <c:forEach var="elem" items="${missing}" varStatus="status">
                <div class="flex-form" style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="form-rows" style="flex-basis: 40%;">
                        <input type="text" class="form-control" name="missingId" value="${elem.missingPeopleId }"
                               pattern=".*[^<>]" required>
                        <input type="text" class="form-control" name="first_name" value="${elem.name }" pattern=".*[^<>]"
                               required>
                        <input type="text" class="form-control" name="last_name" value="${elem.surname }" pattern=".*[^<>]"
                               required>
                        <input type="text" class="form-control" name="disappearance_date" value="${elem.disappearanceDate }"
                               pattern=".*[^<>]" required>
                        <input type="file" name="photo" value="${elem.photo}" pattern=".*[^<>]" required  class="form-control"
                               placeholder=<fmt:message key="label.photo"/>>
                        <button type="submit" class="btn-brown">
                            <fmt:message key="label.submit"/>
                        </button>
                    </div>
                    <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                </div>
            </c:forEach>
            <input type="hidden" name="command" value="update_missing">
        </form>
    </c:if>

    <c:if test="${wantedCriminals !=null }">
        <form action="controller" method="POST" class="form-container" enctype='multipart/form-data'>
            <c:forEach var="wanted" items="${wantedCriminals}" varStatus="status">
                <div class="flex-form" style="display: flex; justify-content: space-around; align-items: center;">
                    <div class="form-rows" style="flex-basis: 40%;">
                        <input type="text" class="form-control" name="guiltyId" value="${wanted.guiltyId }"
                               pattern=".*[^<>]" required>
                        <input type="text" class="form-control" name="first_name" value="${wanted.firstName }"
                               pattern=".*[^<>]">
                        <input type="text" class="form-control" name="last_name" value="${wanted.lastName }"
                               pattern=".*[^<>]" required>
                        <input type="text" class="form-control" name="crimeCity" value="${wanted.crimeCity }"
                               pattern=".*[^<>]">
                        <input type="text" class="form-control" name="crimeAddress" value="${wanted.crimeAddress }"
                               pattern=".*[^<>]" required>
                        <input type="text" class="form-control" name="DOB" value="${wanted.crimeDOB }"
                               pattern=".*[^<>]">
                        <input type="text" class="form-control" name="reward" value="${wanted.reward }"
                               pattern=".*[^<>]">
                        <input type="text" class="form-control" name="crimeType" value="${wanted.crimeType }"
                               pattern=".*[^<>]">
                        <input type="file" name="photo" value="${wanted.photo}" pattern=".*[^<>]"  required class="form-control"
                               placeholder=<fmt:message key="label.photo"/>>
                        <button type="submit" class="btn-brown">
                            <fmt:message key="label.submit"/>
                        </button>
                    </div>
                    <img alt="img" src="data:image/jpeg;base64,${wanted.photo}"/>
                </div>
            </c:forEach>
            <input type="hidden" name="command" value="update_wanted">

        </form>
    </c:if>


<c:if test="${news_article !=null }">
    <form action="controller" method="POST" class="form-container" enctype='multipart/form-data'>
        <c:forEach var="elem2" items="${news_article}" varStatus="status">
            <div class="flex-form" style="display: flex; justify-content: space-around; align-items: center;">
                <div class="form-rows" style="flex-basis: 40%;">
                    <input type="text" class="form-control" name="article_id" value="${elem2.articleId}" pattern=".*[^<>]">
                    <input type="text" class="form-control" name="title" value="${elem2.title}"
                           maxlength="45" pattern=".*[^<>]">
                    <textarea type="text" class="form-control" name="news_article" maxlength="400" style="height: 200px !important;" pattern=".*[^<>]">${elem2.newsArticle}</textarea>
                    <input type="file" name="image" value="" pattern=".*[^<>]" required class="form-control"
                           placeholder=<fmt:message key="label.image"/>>
                    <button type="submit" class="btn-brown">
                        <fmt:message key="label.submit"/>
                    </button>
                </div>
                <img alt="img" src="data:image/jpeg;base64,${elem2.image}"/>
            </div>
        </c:forEach>
        <input type="hidden" name="command" value="update_article">
    </form>
</c:if>


</body>