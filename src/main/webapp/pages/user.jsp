<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<title>USer Success Page</title>
</head>
<body>
<a><b>Welcome User!!!!</b></a>
<% String username = request.getParameter("name");  %>

<form action="${pageContext.request.contextPath}/controller" method="post" class="navbar-form navbar-right form-inline">
    <input type="submit" name="command" value="log_out">
</form>


</body>
</html>
<body>

</body>
</html>

