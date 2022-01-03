<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
<link rel="stylesheet" href="https://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
<link rel="stylesheet" href="https://bootstraptema.ru/snippets/font/2016/pe-icon-stroke/Pe-icon-7-stroke.css">
<link href="https://bootstraptema.ru/_sf/3/391.css" rel="stylesheet"/>
<script src="https://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="https://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/masonry/3.3.1/masonry.pkgd.min.js"></script>


<div class="wrapper">

    <div class="container">

        <div class="masonry-container">

            <head>
                <title>NesFeed</title>
            </head>

            <div>
                <form action="controller" method="GET">
                    <button type="submit" class="btn btn-primary btn-block">
                        <fmt:message key="label.add_article"/>
                    </button>
                    <input type="hidden" name="command" value="add_article">
                </form>
            </div>


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


                <%--<div class="news_buttons">
                    <a href="<spring:url value="/edit/"/>${elem.id}" class="button">Редактировать новость</a>
                    <a href="<spring:url value="/delete/"/>${elem.id}" class="button">Удалить новость</a>
                </div>--%>
            </c:forEach>

        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        window_width = $(window).width();

        // Make the images from the card fill the hole space
        hipster_cards.fitBackgroundForCards();

    });

    hipster_cards = {
        misc:{
            navbar_menu_visible: 0
        },

        fitBackgroundForCards: function(){
            $('[data-background="image"]').each(function(){
                $this = $(this);

                background_src = $this.data("src");

                if(background_src != "undefined"){
                    new_css = {
                        "background-image": "url('" + background_src + "')",
                        "background-position": "center center",
                        "background-size": "cover"
                    };

                    $this.css(new_css);
                }
            });

            $('.card .header img').each(function(){
                $card = $(this).parent().parent();
                $header = $(this).parent();

                background_src = $(this).attr("src");

                if(background_src != "undefined"){
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
