<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>USer Success Page</title>
<html>
<%--<head>
    <c:import url="/pages/main.jsp"/>
</head>--%>


<body>


<div class="col">
    <h4>userId: ${user.userId }</h4>
    <h4>Name: ${user.name }</h4>
    <h4>Surname: ${user.surname }</h4>
    <h4>Email: ${user.email }</h4>
    <h4>Password: ${password }</h4>
    <div>
        <form action="controller" method="GET">
            <button type="submit" class="btn btn-primary">
                <fmt:message key="label.change"/>
            </button>
            <input type="hidden" name="command"
                   value="to_personal_info_change">
        </form>
    </div>

    <form action="controller" method="GET">
        <button type="submit" class="btn btn-danger btn-sm">
            <fmt:message key="label.showUserApplications"/>
        </button>
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="command" value="find_search_applications_by_user_id">
    </form>


    <form action="controller" method="post">
        <input type="hidden" name="command" value="log_out">
        <button type="submit" class="btn btn-warning">
            <fmt:message key="label.logOut"/>
        </button>
    </form>
</div>


<table class="table">
<thead class="thead-dark">
<c:if test="${applications ne null}">
    <tr>
    <th><fmt:message key="label.number"/></th>
    <th><fmt:message key="label.searchApplicationId"/></th>
    <th><fmt:message key="label.lead_time"/></th>
    <th><fmt:message key="label.user_id"/></th>
    <th><fmt:message key="label.status"/></th>
    </tr>
    <tbody>
    <c:forEach var="elem" items="${applications}" varStatus="status">
        <tr>
        <td><c:out value="${status.count }"/></td>
        <td><c:out value="${elem.searchApplicationId }"/></td>
        <td><c:out value="${elem.leadTime }"/></td>
        <td><c:out value="${elem.userId }"/></td>
        <td><c:out value="${elem.status }"/></td>
        </tr>
    </c:forEach>
    </tbody>
    </c:if>
</body>
</html>


