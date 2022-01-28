<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="css/main.css">
<html>

<head>
    <title>Error 404</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed<br/>
Status code : ${pageContext.errorData.statusCode}<br/>
Servlet name : ${pageContext.errorData.servletName}<br/>
Exception: ${pageContext.exception}<br/>
<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
    ${trace}<br/>
</c:forEach>
Message from exception: ${pageContext.exception.message}<br/>
<a href="${pageContext.request.contextPath}/index.jsp">Back to main</a>
</body>
</html>