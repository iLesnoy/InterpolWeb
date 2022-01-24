<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>


<link href="https://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
<link href="css/style.css" rel="stylesheet"/>
<link href="css/header.css" rel="stylesheet"/>


<c:import url="header.jsp"/>
<title>NesFeed</title>
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
                    <div class="content">
                        <h6 class="category">News</h6>
                        <h4 class="title"><a href="#">${elem.title}</a></h4>
                        <h6>${elem.title}</h6>
                        <p class="description">${elem.newsArticle}</p>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
</div>
</body>


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
