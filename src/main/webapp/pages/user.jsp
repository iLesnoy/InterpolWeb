
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>USer Success Page</title>
</head>
<body>
<a><b>Welcome User!!!!</b></a>
<% String username = request.getParameter("username");  %>

<form action="${pageContext.request.contextPath}/controller" method="post" class="navbar-form navbar-right form-inline">
    <input type="submit" name="command" value="log_out">
    <a href="pages/main.jsp" class="button">LogOut</a>
</form>


</body>
</html>
<body>

</body>
</html>

