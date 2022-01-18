<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>User Account</title>
<html>

<body>
<div class="col">
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
        <input type="hidden" name="userId" value="${user.userId}">
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
        <th><fmt:message key="label.application_number"/></th>
        <th><fmt:message key="label.searchApplicationId"/></th>
        <th><fmt:message key="label.lead_time"/></th>
        <th><fmt:message key="label.status"/></th>
    </tr>
    <tbody>
    <c:forEach var="elem" items="${applications}" varStatus="status">
        <tr>
            <td><c:out value="${status.count }"/></td>
            <td><c:out value="${elem.searchApplicationId }"/></td>
            <td><c:out value="${elem.leadTime }"/></td>
            <td><c:out value="${elem.status }"/></td>
            <td><form action="controller" method="POST">
                <button type="submit" class="btn btn-danger btn-sm">
                    <fmt:message key="label.moreInformation"/>
                </button>
                <input type="hidden" name="searchApplicationId" value="${elem.searchApplicationId}">
                <input type="hidden" name="command" value="find_application_information_by_id">
            </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    </c:if>
</body>
</html>

<table class="table">
    <thead class="thead-dark">
    <c:if test="${wanted_criminal ne null}">
    <tr>
        <th><fmt:message key="label.number"/></th>
        <th><fmt:message key="label.guiltyId"/></th>
        <th><fmt:message key="label.firstName"/></th>
        <th><fmt:message key="label.lastName"/></th>
        <th><fmt:message key="label.crimeCity"/></th>
        <th><fmt:message key="label.crimeAddress"/></th>
        <th><fmt:message key="label.crimeDOB"/></th>
        <th><fmt:message key="label.reward"/></th>
        <th><fmt:message key="label.crimeType"/></th>
    </tr>
    <tbody>
    <c:forEach var="elem" items="${wanted_criminal}" varStatus="status">
        <tr>
            <td><c:out value="${status.count }"/></td>
            <td><c:out value="${elem.guiltyId }"/></td>
            <td><c:out value="${elem.firstName }"/></td>
            <td><c:out value="${elem.lastName }"/></td>
            <td><c:out value="${elem.crimeCity }"/></td>
            <td><c:out value="${elem.crimeAddress }"/></td>
            <td><c:out value="${elem.crimeDOB }"/></td>
            <td><c:out value="${elem.reward }"/></td>
            <td><c:out value="${elem.crimeType }"/></td>
        </tr>
    </c:forEach>
    </tbody>
    </c:if>
</table>
<table class="table">
    <thead class="thead-dark">
    <c:if test="${missing ne null}">
    <tr>
        <th><fmt:message key="label.number"/></th>
        <th><fmt:message key="label.user_id"/></th>
        <th><fmt:message key="label.name"/></th>
        <th><fmt:message key="label.surname"/></th>
        <th><fmt:message key="label.disappearanceDate"/></th>

    </tr>
    <tbody>
    <c:forEach var="elem" items="${missing}" varStatus="status">
        <tr>
            <td><c:out value="${status.count }"/></td>
            <td><c:out value="${elem.missingPeopleId }"/></td>
            <td><c:out value="${elem.name }"/></td>
            <td><c:out value="${elem.surname }"/></td>
            <td><c:out value="${elem.disappearanceDate }"/></td>
        </tr>
    </c:forEach>
    </tbody>
    </c:if>
</table>

</html>


