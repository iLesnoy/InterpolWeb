<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>


<link href="css/style.css" rel="stylesheet"/>
<link href="css/missing.css" rel="stylesheet"/>
<link href="css/datePicker.css" rel="stylesheet"/>
<link href="css/header.css" rel="stylesheet"/>

<c:import url="header.jsp"/>

<header><p>${message}</p></header>
<body>
<div class="wrapper">
    <div class="container">
        <div class="masonry-container">
            <c:forEach items="${wantedCriminals}" var="elem">
                <div class="card">
                    <div class="post-slide">
                        <div class="post-img">
                            <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                            <div class="category"><fmt:message key="label.reward"/>: ${elem.reward} </div>
                        </div>

                        <div class="content">
                            <h3 class="post-title"><a href="#"> ${elem.firstName} ${elem.lastName}</a></h3>
                            <p class="post-description">
                            <h4 class="name"><fmt:message key="label.name"/>: ${elem.firstName}</h4>
                            <h4 class="surname"><fmt:message key="label.surname"/>: ${elem.lastName}</h4>
                            <h4 class="text"><fmt:message key="label.crimeCity"/>: ${elem.crimeCity}</h4>
                            <h4 class="text"><fmt:message key="label.crimeAddress"/>: ${elem.crimeAddress}</h4>
                            <h4 class="text"><fmt:message key="label.crimeDOB"/>: ${elem.crimeDOB}</h4>
                            <h4 class="text"><fmt:message key="label.crimeType"/>: ${elem.crimeType}</h4>
                            </p>

                            <form action="controller" method="post">
                                <button class="btn" type="submit">
                                    <fmt:message key="label.takeApplication"/>
                                    <input type="hidden" name="command" value="accept_wanted_application">
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
                                <input type="hidden" name="guiltyId" value="${elem.guiltyId}">
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>

<%--<script>
    $('.datepicker').datepicker({
        weekStart:1,
        color: 'red'
    });
</script>--%>
<script>
    $(function () {
        $('.datepicker').setDefaults({
            weekStart: 1,
            color: 'red',
            onClose: function (date, inst) {
                $("#selectedDtaeVal").html(date);
            }
        });

        $("#datepicker").datepicker();
    });
</script>



