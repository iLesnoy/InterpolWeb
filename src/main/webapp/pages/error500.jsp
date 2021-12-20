<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
request from ${pageContext.errorDate.requestURI} failed


<hr />
status = ${pageContext.errorDate.statusCode}
<hr />
servlet name: ${pageContext.errorData.servletName}
<hr />
message from exception: ${error_message }

<a href="index.jsp">ToIndex</a>
</body>
</html>