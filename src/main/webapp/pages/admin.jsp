<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--<fmt:setBundle basename="src/main/resources/pagecontent_eng_US.properties" />
<fmt:setLocale value="${locale}" scope="session" />--%>



<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<title>AdminPage</title>

<form action="${pageContext.request.contextPath}/controller" method="post" class="navbar-form navbar-right form-inline">
    <input type="submit" name="command" value="log_out">
</form>

<body>
<div class="col">
    <div class="button">
        <form action="controller" method="GET">
            <input type="text" type="hidden" name="name" value="" class="form-control"
                   placeholder=<fmt:message key="label.name"/>> <input
                type="hidden" name="command" value="find_users_by_name">
            <button type="submit" class="btn btn-success">
                <fmt:message key="label.find" />
            </button>
        </form>
    </div>
    <br/>
</div>



<table class="table table-striped table-dark">
    <c:if test="${list ne null}">
    <tr>
        <th><fmt:message key="label.number" /></th>
        <th><fmt:message key="label.id" /></th>
        <th><fmt:message key="label.email" /></th>
        <th><fmt:message key="label.password" /></th>
        <th><fmt:message key="label.name" /></th>
        <th><fmt:message key="label.surname" /></th>
        <th><fmt:message key="label.status" /></th>
        <th><fmt:message key="label.role" /></th>
    </tr>

    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="elem" items="${list}" varStatus="status">
    <tr>
        <td><c:out value="${status.count }" /></td>
        <td><c:out value="${elem.userId }" /></td>
        <td><c:out value="${elem.email }" /></td>
        <td><c:out value="${elem.password }" /></td>
        <td><c:out value="${elem.name }" /></td>
        <td><c:out value="${elem.surname }" /></td>
        <td><c:out value="${elem.status }" /></td>
        <td><c:out value="${elem.role }" /></td>

        <td>
            <div class="dropdown">
                <form action="controller" method="post">
                    <button class="button" type="button"
                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                        <fmt:message key="label.change_role" />
                    </button>



                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <button type="submit" class="dropdown-item" name="command" value="change_user_role_doctor">
                            <fmt:message key="label.change_role_to_doctor" />
                        </button>

                        <button type="submit" class="dropdown-item" name="command" value="change_user_role_admin">
                            <fmt:message key="label.change_role_to_admin" />
                        </button>

                        <button type="submit" class="dropdown-item" name="command" value="change_user_role_user">
                            <fmt:message key="label.change_role_to_user" />
                        </button>
                        <input type="hidden" name="id" value="${elem.userId}">
                    </div>
                </form>
            </div>
        </td>
    </tr>
    </c:forEach>
    </c:if>
</table>
</body>
</html>