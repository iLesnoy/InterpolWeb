<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>ERROR</h1>
<h2>Something goes wrong.</h2>
request from ${pageContext.errorDate.requestURI} failed

<hr/> Status code: ${pageContext.errorData.statusCode}
<hr /> Servlet name: ${pageContext.errorData.servletName}
<hr />status = ${pageContext.errorDate.statusCode}
<hr />servlet name: ${pageContext.errorData.servletName}
<hr />
message from exception: ${error_message }

<a href="index.jsp">ToIndex</a>
</body>
</html>