<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<link href="css/style.css" rel="stylesheet"/>

<html>
<head>
    <title>Interpol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>

<body>
<div class="row">
    <form action="controller" method="POST" class="form-container">
        <div class="col-md-1">
            <input type="text" class="form-control" name="name" value="${user.name }" pattern=".*[^<>]" required>
        </div>
        <div class="col-md-1">
            <input type="text" class="form-control" name="surname" value="${user.surname }" pattern=".*[^<>]">
        </div>
            <input type="hidden" name="command" value="change_personal_info">
            <button type="submit" class="btn" >
                <fmt:message key="label.submit" />
            </button>
    </form>
</div>
</body>
</html>

</div>
</body>
