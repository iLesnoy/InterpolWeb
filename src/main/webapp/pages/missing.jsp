<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>




<link href="css/style.css" rel="stylesheet"/>
<link href="css/missing.css" rel="stylesheet"/>
<link href="css/datePicker.css" rel="stylesheet"/>
<link href="css/header.css" rel="stylesheet"/>





<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>

<header><c:import url="header.jsp"/></header>
<p>${message}</p>
<body>
<div class="wrapper">
    <div class="container">
        <div class="masonry-container">
            <c:forEach items="${missing}" var="elem" varStatus="status">
                <div class="card">
                    <div class="post-slide">
                        <div class="post-img">
                            <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                            <%--<div class="category">Reward:</div>--%>
                        </div>

                        <div class="content">
                            <h3 class="post-title"><a href="#"> ${elem.name} ${elem.surname}</a></h3>
                            <p class="post-description">
                            <h4 class="name"><fmt:message key="label.name"/>${elem.name}</h4>
                            <h4 class="surname"><fmt:message key="label.surname"/> ${elem.surname}</h4>
                            <h4><fmt:message key="label.disappearanceDate"/>${elem.disappearanceDate}</h4>
                            </p>

                            <form action="controller" method="post">
                                <button class="btn" type="submit">
                                    <fmt:message key="label.takeApplication"/>
                                    <input type="hidden" name="command" value="accept_missing_application">
                                </button>

                                <div class="row">
                                    <p><fmt:message key="label.dateChose"/></p>
                                    <br/>
                                    <div class="controls">
                                        Date: <input class="datepicker form-control" type="date"
                                                     name="lead_time" required/>
                                    </div>
                                </div>

                                <input type="hidden" name="lead_time" value="selectedDtaeVal">
                                <input type="hidden" name="missingId" value="${elem.missingPeopleId}">
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>



