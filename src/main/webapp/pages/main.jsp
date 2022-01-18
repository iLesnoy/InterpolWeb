<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Interpol</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/main.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<!-- Second navbar for sign in -->
<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Interpol</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-2">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="pages/newsfeed.jsp">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=to_news_feed" class="button">News</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=to_missing" class="button">Missing People</a></li>
                <li><a href="pages/wantedcriminals.jsp">WantedCriminals</a></li>
                <li><a href="${pageContext.request.contextPath}/controller?command=to_account" class="button">Account</a></li>
                <c:if test="${user == null}">
                <li>
                    <a class="btn btn-default btn-outline btn-circle" data-toggle="collapse" href="#nav-collapse2" aria-expanded="false" aria-controls="nav-collapse2">Sign in</a>
                </li>
                </c:if>
                <c:if test="${user ne null}">
                <li>
                    <a class="btn btn-default btn-outline btn-circle" data-toggle="collapse" href="#nav-collapse2" aria-expanded="false" aria-controls="nav-collapse2">Logout</a>
                </li>
                </c:if>
            </ul>

            <c:if test="${user == null}">
            <div class="collapse nav navbar-nav nav-collapse" id="nav-collapse2">
                <form action="${pageContext.request.contextPath}/controller" method="post" class="navbar-form navbar-right form-inline">
                    <input type="hidden" name="command" value="log_in">
                    <div class="form-group">
                        <label for="email"></label>
                        <input type="email" name="email" class="form-control" id="email"  pattern=".*[^<>]"  placeholder="Email" autofocus required />
                    </div>
                    <div class="form-group">
                        <label for="password"></label>
                        <input type="text"  name="password" class="form-control" id="password" pattern=".*[^<>]"  placeholder="Password" required />
                    </div>
                    <input type="submit" class="btn btn-success" value="Sign"/>
                    <a href="pages/signup.jsp" class="button">Create new account</a>
                </form>
            </div>
            </c:if>

            <c:if test="${user ne null && password ne null}">
            <div class="collapse nav navbar-nav nav-collapse" id="nav-collapse2">
                <form action="controller" method="post" class="navbar-form navbar-right form-inline">
                    <input type="hidden" name="command" value="log_out">
                    <div class="form-group">
                        <label for="email"></label>
                        <input type="email" name="email" value="${user.email}" class="form-control" id="providedEmail"  pattern=".*[^<>]"  placeholder="Email" autofocus required />
                    </div>
                    <div class="form-group">
                        <label for="password"></label>
                        <input type="text"  name="password" value="${password}" class="form-control" id="providedPassword" pattern=".*[^<>]"  placeholder="Password" required />
                    </div>
                    <input type="submit" class="btn btn-success" value="Logout"/>
                </form>
            </div>
            </c:if>


        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->
</body>

<%--<script>
    $('body').on('click', '.password-checkbox', function(){
        if ($(this).is(':checked')){
            $('#password-input').attr('type', 'text');
        } else {
            $('#password-input').attr('type', 'password');
        }
    });
</script>--%>



