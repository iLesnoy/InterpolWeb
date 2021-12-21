<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>

<p>Email: <%= request.getParameter("email") %></p>
<p>Password: <%= request.getParameter("password") %></p>
<p>Name: <%= request.getParameter("name") %></p>

<form action="index.jsp" method="get">
    <input type="submit" value="Logout" /></form>



</form>
</body>
</html>
