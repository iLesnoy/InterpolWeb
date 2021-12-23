<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logged</title>
</head>
<body>

<h1>Logged</h1>
<h2>Email: <%= request.getParameter("email") %></h2>

<form action="pages/main.jsp" method="">
<input type="submit" value="Logout" /></form>



</form>
</body>
</html>