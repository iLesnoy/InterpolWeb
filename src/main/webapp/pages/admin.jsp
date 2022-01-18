<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<html>


<title>AdminPage</title>

<body>
<div class="container mt-3">
    <div class="col">
        <div class="button">
            <form action="controller" method="GET">
                <input type="text" type="hidden" name="name" value="" width="13%"
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
                <input type="text" type="hidden" name="name" value="" width="13%"
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

    <%--<div class="row">
        <div class="even-row-color">
            <div>
                <form action="controller" method="GET">
                    <button type="submit" class="btn btn-primary btn-block">
                        <fmt:message key="label.find_all_users" />
                    </button>
                    <input type="hidden" name="command" value="find_all_users">
                </form>
            </div>
        </div>
    </div>--%>


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
                                    <button type="submit" class="dropdown-item" name="command"
                                            value="change_user_role_to_user">
                                        <fmt:message key="label.change_role_to_user"/>
                                    </button>

                                    <button type="submit" class="dropdown-item" name="command"
                                            value="change_user_role_to_agent">
                                        <fmt:message key="label.change_role_to_agent"/>
                                    </button>

                                    <button type="submit" class="dropdown-item" name="command"
                                            value="change_user_role_to_admin">
                                        <fmt:message key="label.change_role_to_admin"/>
                                    </button>
                                    <input type="hidden" name="userId" value="${elem.userId}">
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


            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:forEach begin="1" end="${number_of_pages }" var="i">
                        <li class="page-item">
                            <form action="controller" method="POST">
                                <div>
                                <button  type="submit" class="page-item disabled" value="${i - 1}">
                                    <fmt:message key="label.Previous"/>
                                </button>

                                <button type="submit" class="page-item disabled" value="${i }">
                                    <c:out value="${i }"/>
                                </button>

                                <button  type="submit" class="page-item disabled" value="${i + 1}">
                                    <fmt:message key="label.Next"/>
                                </button>
                                </div>
                                <input type="hidden" name="start_from" value="${i}"> <input
                                    type="hidden" name="command" value="find_users_by_name_pagination">
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </nav>
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
            <td>
                <div class="dropdown">
                    <form action="controller" method="post">
                        <button class="btn btn-danger btn-sm dropdown-toggle" type="button"
                                id="dropdownMenuButton2" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            <fmt:message key="label.update_status"/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button type="submit" class="dropdown-item" name="command"
                                    value="update_application_status_to_active">
                                <fmt:message key="label.update_application_status_to_active"/>
                            </button>

                            <button type="submit" class="dropdown-item" name="command"
                                    value="update_application_status_to_rejected">
                                <fmt:message key="label.update_application_status_to_rejected"/>
                            </button>
                            <button type="submit" class="dropdown-item" name="command"
                                    value="update_application_status_to_expired">
                                <fmt:message key="label.update_application_status_to_expired"/>
                            </button>
                            <button type="submit" class="dropdown-item" name="command"
                                    value="update_application_status_to_closed">
                                <fmt:message key="label.update_application_status_to_closed"/>
                            </button>
                            <input type="hidden" name="searchApplicationId" value="${elem.searchApplicationId}">
                        </div>
                    </form>
                </div>
            </td>
        </tr>
        </tbody>
        </c:forEach>
        </c:if>
    </table>


</body>
</html>
