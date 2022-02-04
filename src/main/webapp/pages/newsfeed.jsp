<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>


<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/missing.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>NesFeed</title>
</head>


<header><c:import url="header.jsp"/></header>
<p>${message}</p>

<body>
<div class="wrapper">
    <div class="container">
        <div class="masonry-container">
            <c:forEach items="${news}" var="elem" varStatus="status">
                <div class="card">
                    <div class="header">
                        <img alt="img" src="data:image/jpeg;base64,${elem.image}"/>
                        <div class="filter"></div>
                        <div class="actions">
                            <button class="btn btn-round btn-fill btn-neutral btn-modern">
                                Read Article
                            </button>
                        </div>
                    </div>
                    <div class="content" id="cont1">
                        <h6 class="category">News</h6>
                        <h4 class="title"><a href="#">${elem.title}</a></h4>
                        <h6>${elem.title}</h6>
                        <p class="description">${elem.newsArticle}</p>
                    </div>
                    <c:if test="${user_role == 'ADMIN'}">
                    <div style="display: flex; justify-content: space-around; align-items: center;">
                        <form action="controller" method="get">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <fmt:message key="label.edit"/>
                            </button>
                            <input type="hidden" name="article_id" value="${elem.articleId}">
                            <input type="hidden" name="command" value="to_update_article">
                        </form>
                        <form action="controller" method="get">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <fmt:message key="label.delete"/>
                            </button>
                            <input type="hidden" name="article_id" value="${elem.articleId}">
                            <input type="hidden" name="command" value="delete_application">
                        </form>
                    </div>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>


<script>
    $(document).ready(function () {
        window_width = $(window).width();

        // Make the images from the card fill the hole space
        hipster_cards.fitBackgroundForCards();
    });

    hipster_cards = {
        misc: {
            navbar_menu_visible: 0
        },

        fitBackgroundForCards: function () {
            $('[data-background="image"]').each(function () {
                $this = $(this);

                background_src = $this.data("src");

                if (background_src != "undefined") {
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };

                    $this.css(new_css);
                }
            });

            $('.card .header img').each(function () {
                $card = $(this).parent().parent();
                $header = $(this).parent();

                background_src = $(this).attr("src");

                if (background_src != "undefined") {
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };

                    $header.css(new_css);
                }
            });
        },
    }
</script>