<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <title>AdminAccount</title>
</head>

<header><c:import url="header.jsp"/></header>
<p>${message}</p>
<body>
<div class="container mt-3">
    <div class="col">
        <div class="button">
            <form action="controller" method="GET">
                <input type="text" type="hidden" name="name" value="" pattern=".*[^<>]"
                       placeholder=<fmt:message key="label.name"/>> <input
                    type="hidden" name="command" value="find_users_by_name">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="label.findUser"/>
                </button>
            </form>
        </div>
        <br/>
    </div>
    <div class="col">
        <div class="button">
            <form action="controller" method="GET">
                <input type="text" type="hidden" name="nameAndSurname" value="" pattern="^([a-zA-Z]{2,}\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)"
                       placeholder=<fmt:message key="label.name_and_surname"/>> <input
                    type="hidden" name="command" value="find_by_name_and_surname">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="label.findUser"/>
                </button>
            </form>
        </div>
        <br/>
    </div>
    <div class="col">
        <div class="button">
            <form action="controller" method="GET">
                <input type="text" type="hidden" name="name" value="" pattern=".*[^<>]"
                       placeholder=<fmt:message key="label.firstName"/>> <input
                    type="hidden" name="command" value="find_wanted_criminals">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="label.findWantedCriminal"/>
                </button>
            </form>
        </div>
        <br/>
    </div>
    <div class="col">
        <div type="button" class="btn btn-secondary">
            <form action="controller" method="GET">
                <input
                        type="hidden" name="command" value="find_all_applications">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="label.findAllApplications"/>
                </button>
            </form>
        </div>
        <br/>
    </div>
    <div class="col">
        <div type="button" class="btn btn-secondary">
            <form action="controller" method="GET">
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="label.find_all_users"/>
                </button>
                <input type="hidden" name="command" value="find_all_users">
            </form>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse-2">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=to_add"
                       class="button"><fmt:message key="label.add"/>
                    </a>
                </li>
        </div>
    </div>

    <div class="col">
        <h4>id: ${user.userId }</h4>
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

        <form action="controller" method="post">
            <input type="hidden" name="command" value="log_out">
            <button type="submit" class="btn btn-warning">
                <fmt:message key="label.logOut"/>
            </button>
        </form>
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
                        <div class="nav-item dropdown">
                            <form action="controller" method="post">
                                <button class="btn btn-danger btn-sm dropdown-toggle" type="button"
                                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                    <fmt:message key="label.change_role"/>
                                </button>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <c:forEach var="role" items="${user_role}">
                                        <button type="submit" class="dropdown-item"
                                                name="user_role" value="${role}">
                                            <c:out value="${role}"/>
                                            <input type="hidden" name="command" value="update_user_role">
                                        </button>
                                    </c:forEach>
                                    <input type="hidden" name="userId" value="${elem.userId }">
                                    <input type="hidden" name="user_role" value="${role}">
                                </div>
                            </form>
                        </div>
                    </td>

                    <c:if test="${elem.status=='ACTIVE'}">
                        <td>
                            <form action="controller" method="POST">
                                <button type="submit" class="btn btn-danger btn-sm">
                                    <fmt:message key="label.block_user"/>
                                </button>
                                <input type="hidden" name="userId" value="${elem.userId}">
                                <input type="hidden" name="command" value="block_user">
                            </form>
                        </td>
                    </c:if>

                    <c:if test="${elem.status=='BLOCKED'}">
                        <td>
                            <form action="controller" method="POST">
                                <button type="submit" class="btn btn-success btn-sm">
                                    <fmt:message key="label.unblock_user"/>
                                </button>
                                <input type="hidden" name="userId" value="${elem.userId}">
                                <input type="hidden" name="command" value="unblock_user">
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
    </table>


    <table class="table table-striped">
        <c:if test="${criminals_list ne null}">
            <tr>
                <th><fmt:message key="label.number"/></th>
                <th><fmt:message key="label.guiltyId"/></th>
                <th><fmt:message key="label.firstName"/></th>
                <th><fmt:message key="label.lastName"/></th>
                <th><fmt:message key="label.crimeCity"/></th>
                <th><fmt:message key="label.crimeAddress"/></th>
                <th><fmt:message key="label.reward"/></th>
                <th><fmt:message key="label.crimeDOB"/></th>
                <th><fmt:message key="label.crimeType"/></th>
            </tr>

            <c:forEach var="elem" items="${criminals_list}" varStatus="status">
                <tr>
                    <td><c:out value="${status.count }"/></td>
                    <td><c:out value="${elem.guiltyId }"/></td>
                    <td><c:out value="${elem.firstName }"/></td>
                    <td><c:out value="${elem.crimeCity }"/></td>
                    <td><c:out value="${elem.crimeAddress }"/></td>
                    <td><c:out value="${elem.reward }"/>$</td>
                    <td><c:out value="${elem.crimeDOB }"/></td>
                    <td><c:out value="${elem.crimeType }"/></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

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
            <td><form action="controller" method="POST">
                <button type="submit" class="btn btn-danger btn-sm">
                    <fmt:message key="label.applicationInformation"/>
                </button>
                <input type="hidden" name="searchApplicationId" value="${elem.searchApplicationId}">
                <input type="hidden" name="command" value="find_application_information_by_id">
            </form>
                <form action="controller" method="POST">
                    <button type="submit" class="btn btn-danger btn-sm">
                        <fmt:message key="label.userInformation"/>
                    </button>
                <input type="hidden" name="userId" value="${elem.userId}">
                <input type="hidden" name="command" value="find_user_by_id">
                </form>
            </td>
            <td>
                <div class="nav-item dropdown">
                    <form action="controller" method="post">
                        <button class="btn btn-danger btn-sm dropdown-toggle" type="button"
                                id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            <fmt:message key="label.update_status"/>
                        </button>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <c:forEach var="stat" items="${application_status}">
                                <button type="submit" class="dropdown-item"
                                        name="application_status" value="${stat}">
                                    <c:out value="${stat}"/>
                                    <input type="hidden" name="command" value="update_application_status">
                                </button>
                            </c:forEach>
                            <input type="hidden" name="application_status" value="${stat}">
                            <input type="hidden" name="searchApplicationId" value="${elem.searchApplicationId}">
                        </div>
                    </form>
                    <form action="controller" method="post">
                        <button type="submit" class="btn btn-danger btn-sm">
                            <fmt:message key="label.delete_application"/>
                        </button>
                        <input type="hidden" name="userId" value="${elem.userId }">
                        <input type="hidden" name="searchApplicationId" value="${elem.searchApplicationId }">
                        <input type="hidden" name="command" value="delete_application_by_user_id">
                    </form>

                </div>
            </td>
        </tr>
        </tbody>
        </c:forEach>
        </c:if>
    </table>
    <table class="table">
        <thead class="thead-dark">
        <c:if test="${wantedCriminals ne null}">
        <tr>
            <th><fmt:message key="label.guiltyId"/></th>
            <th><fmt:message key="label.firstName"/></th>
            <th><fmt:message key="label.lastName"/></th>
            <th><fmt:message key="label.crimeCity"/></th>
            <th><fmt:message key="label.crimeAddress"/></th>
            <th><fmt:message key="label.crimeDOB"/></th>
            <th><fmt:message key="label.reward"/></th>
            <th><fmt:message key="label.crimeType"/></th>
            <th><fmt:message key="label.photo"/></th>
        </tr>
        <tbody>
        <c:forEach var="elem" items="${wantedCriminals}" varStatus="status">
            <tr>
                <td><c:out value="${elem.guiltyId }"/></td>
                <td><c:out value="${elem.firstName }"/></td>
                <td><c:out value="${elem.lastName }"/></td>
                <td><c:out value="${elem.crimeCity }"/></td>
                <td><c:out value="${elem.crimeAddress }"/></td>
                <td><c:out value="${elem.crimeDOB }"/></td>
                <td><c:out value="${elem.reward }"/></td>
                <td><c:out value="${elem.crimeType }"/></td>
                <td><img alt="img" src="data:image/jpeg;base64,${elem.photo}"/></td>
            </tr>
        </c:forEach>
        </tbody>
        </c:if>
    </table>
    <table class="table">
        <thead class="thead-dark">
        <c:if test="${missing ne null}">
        <tr>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.surname"/></th>
            <th><fmt:message key="label.disappearanceDate"/></th>
            <th><fmt:message key="label.photo"/></th>

        </tr>
        <tbody>
        <c:forEach var="elem" items="${missing}" varStatus="status">
            <tr>
                <td><c:out value="${elem.name }"/></td>
                <td><c:out value="${elem.surname }"/></td>
                <td><c:out value="${elem.disappearanceDate}"/></td>
                <td><img alt="img" src="data:image/jpeg;base64,${elem.photo}"/></td>
            </tr>
        </c:forEach>
        </tbody>
        </c:if>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <c:forEach begin="1" end="${number_of_pages }" var="i">
                <li class="page-item">
                    <form action="controller" method="POST">
                        <button type="submit" class="page-link" value="${i}">
                            <c:out value="${i }"/>
                        </button>
                        <input type="hidden" name="start_from" value="${i}"> <input
                            type="hidden" name="command" value="find_users_by_name_pagination">
                    </form>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>


</body>
</html>
