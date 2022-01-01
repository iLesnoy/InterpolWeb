<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error 500</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed<br/>
Status code : ${pageContext.errorData.statusCode}<br/>
Servlet name : ${pageContext.errorData.servletName}<br/>
Exception: ${pageContext.exception}<br/>
<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
  ${trace}<br/>
</c:forEach>
<a href="${pageContext.request.contextPath}/index.jsp">Back to home page</a>
</body>
</html>