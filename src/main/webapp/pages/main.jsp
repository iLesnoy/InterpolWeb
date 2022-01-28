<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/header.css">
    <title>Interpol</title>
</head>
<body>
    <header>
        <c:import url="header.jsp"/>
    </header>
    <p>${message}</p>
    <div style="width: 400px; height: 400px; margin-left: 50px;"><a class="twitter-timeline" data-lang="ru"
     data-width="400" data-height="520" data-theme="light"
     href="https://twitter.com/INTERPOL_HQ?ref_src=twsrc%5Etfw">Tweets by INTERPOL_HQ</a></div>
    <script src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
</body>
</html>




