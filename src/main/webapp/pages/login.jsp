<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logged</title>
</head>
<body>
<c:import url="header.jsp" />
<img alt="" src="images/extra.jpg">

<div class="form-container">
    <form action="controller" method="POST">
        <div class="mb-3">
            &lt;%&ndash;@declare id="email"&ndash;%&gt;<label for="email" class="form-label"><fmt:message
                    key="label.email" /></label>
            <input class="form-control" name="email" required pattern=".*[^<>]" placeholder=<fmt:message key="label.email"/>>
        </div>
        <div class="mb-3">
            &lt;%&ndash;@declare id="password"&ndash;%&gt;<label for="password" class="form-label"><fmt:message
                    key="label.password" /></label> <input type="password"
                                                           class="form-control" name="password" required
                                                           placeholder=<fmt:message key="label.password"/>>
        </div>
        <p>${message}</p>
        <input type="hidden" name="command" value="log_in">
        <button type="submit" class="btn btn-primary btn-block">
            <fmt:message key="label.submit" />
        </button>
    </form>
    <form action="controller" method="post">
        <input type="submit" value=<fmt:message key="label.signup"/>
                <input type="hidden" name="command"
                                              value="to_sign_up">
    </form>
</div>

<c:import url="pages/main.jsp" />
</body>
</html>--%>
