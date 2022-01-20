<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link href="css/style.css" rel="stylesheet"/>
<link href="css/missing.css" rel="stylesheet"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.js"></script>

<body>
<div class="wrapper">
    <div class="container">
        <div class="masonry-container">
            <c:forEach items="${missing}" var="elem" varStatus="status">
                <div class="card">
                    <div class="post-slide">
                        <div class="post-img">
                            <img alt="img" src="data:image/jpeg;base64,${elem.photo}"/>
                            <div class="category">Reward:</div>
                        </div>

                        <div class="content">
                            <h3 class="post-title"><a href="#"> ${elem.name} ${elem.surname}</a></h3>
                            <p class="post-description">
                            <h4 class="name"><fmt:message key="label.name"/>${elem.name}</h4>
                            <h4 class="surname"><fmt:message key="label.surname"/> ${elem.surname}</h4>
                            <h4><fmt:message key="label.disappearanceDate"/>${elem.disappearanceDate}</h4>
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



