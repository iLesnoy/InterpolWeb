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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/missing.css">
    <title>Missing People</title>
</head>
<body>


<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<div class="wrapper">
    <div class="container">
        <div class="masonry-container">
            <c:forEach items="${missing}" var="elem" varStatus="status">
                <div class="card">
                    <div class="post-slide">
                        <div class="post-img">
                            <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                        </div>
                        <div class="content">
                            <h3 class="post-title">${elem.name} ${elem.surname}</h3>
                            <p class="name"><fmt:message key="label.name"/> ${elem.name}</p>
                            <p class="surname"><fmt:message key="label.surname"/> ${elem.surname}</p>
                            <p><fmt:message key="label.disappearanceDate"/> ${elem.disappearanceDate}</p>
                            <form action="controller" method="post">
                                <button class="btn-primary" type="submit"
                                        onclick="return confirm('Are you sure that you want to accept an Search Application?')">
                                    <fmt:message key="label.takeApplication"/>
                                    <input type="hidden" name="command" value="accept_missing_application">
                                </button>
                                <br/>
                                <div class="controls">
                                    Date: <input class="datepicker form-control" id="datepicker form-control"
                                                 pattern="(0[1-9]|[12][0-9]|3[01])\.(0[1-9]|1[012])\.(19|20)\d\d" type="date"
                                                 name="lead_time" required/>
                                </div>
                                <input type="hidden" name="lead_time" value="selectedDtaeVal">
                                <input type="hidden" name="missingId" value="<c:out value="${elem.missingPeopleId }"/>">
                            </form>
                            <c:if test="${user_role == 'ADMIN'}">
                                <div style="display: flex; justify-content: space-around; align-items: center;">
                                    <form action="controller" method="get">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <fmt:message key="label.edit"/>
                                        </button>
                                        <input type="hidden" name="missingId" value="${elem.missingPeopleId}">
                                        <input type="hidden" name="command" value="to_update_missing">
                                    </form>
                                    <form action="controller" method="get">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <fmt:message key="label.delete"/>
                                        </button>
                                        <input type="hidden" name="missingId" value="${elem.missingPeopleId}">
                                        <input type="hidden" name="command" value="delete_application">
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
    jQuery(document).ready(function($) {
        $("#datepicker form-control").datepicker({ dateFormat: "yy.mm.dd" }).val()
    });
</script>
</html>

