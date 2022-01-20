<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<link href="css/style.css" rel="stylesheet"/>
<link href="css/missing.css" rel="stylesheet"/>

<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>

<body>
<div class="wrapper">
    <div class="container">
        <div class="masonry-container">
            <c:forEach items="${wantedCriminals}" var="elem">
                <div class="card">
                    <div class="post-slide">
                        <div class="post-img">
                            <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                            <div class="category"><fmt:message key="label.reward"/>${elem.reward} </div>
                        </div>

                        <div class="content">
                            <h3 class="post-title"><a href="#"> ${elem.firstName} ${elem.lastName}</a></h3>
                            <p class="post-description">
                            <h4 class="name"><fmt:message key="label.name"/> ${elem.firstName}</h4>
                            <h4 class="surname"><fmt:message key="label.surname"/>${elem.lastName}</h4>
                            <h4 class="text"><fmt:message key="label.crimeCity"/> ${elem.crimeCity}</h4>
                            <h4 class="text"><fmt:message key="label.crimeAddress"/> ${elem.crimeAddress}</h4>
                            <h4 class="text"><fmt:message key="label.crimeDOB"/> ${elem.crimeDOB}</h4>
                            <h4 class="text"><fmt:message key="label.crimeType"/> ${elem.crimeType}</h4>
                            <button class="btn"><fmt:message key="label.takeApplication"/></button>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>



