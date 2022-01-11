<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://bootstraptema.ru/plugins/bootstrap/v3/3-3-6/bootstrap.css"/>
<link rel="stylesheet" href="https://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.css"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.js"></script>

<script>
    $(document).ready(function () {
        $("#news-slider").owlCarousel({
            items: 3,
            itemsDesktop: [1199, 3],
            itemsDesktopSmall: [1000, 2],
            itemsMobile: [650, 1],
            pagination: false,
            navigationText: false,
            autoPlay: true
        });
    });
</script>

<br><br><br>

<style>
    .post-slide {
        margin: 0 15px;
        border-bottom: 1px solid #dadada;
        box-shadow: 0 0 5px rgba(167, 197, 167, 0.8);
        transition: all 0.4s ease-in-out 0s;
    }

    .post-slide .post-img {
        position: relative;
        overflow: hidden;
    }

    .post-slide .post-img:before {
        content: "";
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
        background: rgba(0, 0, 0, 0);
        transition: all 0.4s linear 0s;
    }

    .post-slide:hover .post-img:before {
        background: rgba(255, 7, 44, 0.6);
    }

    .post-slide .post-img img {
        width: 100%;
        height: auto;
    }

    .post-slide .category {
        width: 20%;
        font-size: 16px;
        color: #fff;
        line-height: 11px;
        text-align: center;
        text-transform: capitalize;
        padding: 11px 0;
        background: #9B7DF5;
        position: absolute;
        bottom: 0;
        left: -50%;
        transition: all 0.5s ease-in-out 0s;
    }

    .post-slide:hover .category {
        left: 0;
    }

    .post-slide .post-review {
        padding: 25px 20px;
        background: #fff;
        position: relative;
    }

    .post-slide .post-title {
        margin: 0;
    }

    .post-slide .post-title a {
        display: inline-block;
        font-size: 16px;
        color: #9B7DF5;
        font-weight: bold;
        letter-spacing: 2px;
        text-transform: uppercase;
        margin-bottom: 25px;
        transition: all 0.30s linear 0s;
    }

    .post-slide .post-title a:hover {
        text-decoration: none;
        color: #555;
    }

    .post-slide .post-description {
        font-size: 15px;
        color: #555;
        line-height: 26px;
    }

    .post-review .post-bar {
        margin-top: 20px;
    }

    .post-bar span {
        display: inline-block;
        font-size: 14px;
    }

    .post-bar span i {
        margin-right: 5px;
        color: #999;
    }

    .post-bar span a {
        color: #999;
        text-transform: uppercase;
    }

    .post-bar span a:hover {
        text-decoration: none;
        color: #4c17e7;
    }

    .post-bar span.comments {
        float: right;
    }

    @media only screen and (max-width: 359px) {
        .post-slide .category {
            font-size: 13px;
        }
    }
</style>

<c:forEach items="${missing}" var="elem" varStatus="status">
    <div class="container">
    <div class="row">
        <div class="col-md-12">


                    <div class="post-slide">
                        <div class="post-img">
                            <img src="https://bootstraptema.ru/img/md/1920x1080-jpg/material-design-jpeg-1920x1080-10-.jpg"
                                 alt="Bootstrap Blocks Owl Carousel 1"/>
                            <div class="category"></div>
                        </div>

                        <div class="content">
                            <h3 class="post-title"><a href="#"> ${elem.name} ${elem.surname}</a></h3>
                            <p class="post-description">
                            <h6 class="name">Name ${elem.name}</h6>
                            <h4 class="surname">Surname ${elem.surname}</h4>
                            </p>
                            <h6>Disappearance Date: ${elem.disappearanceDate}</h6>
                        </div>
                        <%--<div class="post-bar">
                                <span><i class="fa fa-user"></i> <a href="#">BootstrapTema</a></span>
                                <span class="comments"><i class="fa fa-comments"></i> <a
                                        href="#">5 Комментариев</a></span>
                            </div>--%>
                        </div>
                </div>
            </c:forEach>


        </div>
    </div>
</div>
