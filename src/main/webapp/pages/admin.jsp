<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="pagecontent"/>


<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
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
            <input type="text" type="hidden" name="name" value="" width="13%"
                   placeholder=<fmt:message key="label.name"/>> <input
                type="hidden" name="command" value="find_users_by_name">
            <button type="submit" class="btn btn-success">
                <fmt:message key="label.findUser"/>
            </button>
        </form>
    </div>
    <br/>
</div>

<div class="col">
    <div class="button">
        <form action="controller" method="GET">
            <input type="text" type="hidden" name="name" value="" width="13%"
                   placeholder=<fmt:message key="label.firstName"/>> <input
                type="hidden" name="command" value="find_wanted_criminals">
            <button type="submit" class="btn btn-success">
                <fmt:message key="label.findWantedCriminal"/>
            </button>
        </form>
    </div>
    <br/>
</div>

<div class="col">
    <h4>Name: ${user.name }</h4>
    <h4>Surname: ${user.surname }</h4>
    <h4>Email: ${user.email }</h4>
    <h4>Password: ${user.password }</h4>
    <div>
        <form action="controller" method="GET">
            <button type="submit" class="btn btn-primary">
                <fmt:message key="label.change" />
            </button>
            <input type="hidden" name="command"
                   value="to_personal_info_change">
        </form>
    </div>
</div>
</div>



<table class="table table-striped">
    <c:if test="${list ne null}">
        <tr>
            <th><fmt:message key="label.number"/></th>
            <th><fmt:message key="label.id"/></th>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="label.password"/></th>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.surname"/></th>
            <th><fmt:message key="label.status"/></th>
            <th><fmt:message key="label.role"/></th>
            <th><fmt:message key="label.action"/></th>
        </tr>

        <c:forEach var="elem" items="${list}" varStatus="status">
            <tr>
                <td><c:out value="${status.count }"/></td>
                <td><c:out value="${elem.userId }"/></td>
                <td><c:out value="${elem.email }"/></td>
                <td><c:out value="${elem.password }"/></td>
                <td><c:out value="${elem.name }"/></td>
                <td><c:out value="${elem.surname }"/></td>
                <td><c:out value="${elem.status }"/></td>
                <td><c:out value="${elem.role }"/></td>
                <td>
                    <c:if test="${elem.status=='ACTIVE'}">
                        <form action="controller" method="POST">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <fmt:message key="label.block_user"/>
                            </button>
                            <input type="hidden" name="userId" value="${elem.userId}">
                            <input type="hidden" name="command" value="block_user">
                        </form>
                    </c:if>

                    <c:if test="${elem.status=='BLOCKED'}">
                        <form action="controller" method="POST">
                            <button type="submit" class="btn btn-success btn-sm">
                                <fmt:message key="label.unblock_user"/>
                            </button>
                            <input type="hidden" name="userId" value="${elem.userId}">
                            <input type="hidden" name="command" value="unblock_user">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </c:if>

        <table class="table table-striped">
            <c:if test="${criminals_list ne null}">
            <tr>
                <th><fmt:message key="label.guiltyId"/></th>
                <th><fmt:message key="label.firstName"/></th>
                <th><fmt:message key="label.lastName"/></th>
            </tr>

            <c:forEach var="elem" items="${criminals_list}" varStatus="status">
            <tr>
                <td><c:out value="${status.count }"/></td>
                <td><c:out value="${elem.guiltyId }"/></td>
                <td><c:out value="${elem.firstName }"/></td>
                <td><c:out value="${elem.lastName }"/></td>
            </tr>
            </c:forEach>
            </c:if>
        </table>
</table>
</body>
</html>




</table>