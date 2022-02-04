<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

    <!-- Second navbar for sign in -->
    <nav class="navbar navbar-default">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#navbar-collapse-2">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand"
                   href="${pageContext.request.contextPath}/controller?command=to_main">Interpol</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="navbar-collapse-2">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/controller?command=to_who_we_are_page">
                        <fmt:message key="label.who_we_are"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=to_news_feed"
                           class="button"><fmt:message key="label.news_feed"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=to_missing_people"
                           class="button"><fmt:message key="label.missing_people"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=to_wanted"><fmt:message
                            key="label.wanted_criminals"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/controller?command=to_account"
                           class="button"><fmt:message key="label.account"/></a></li>
                    <li class="lang-btn">
                        <form action="controller" method="post">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                                        aria-haspopup="true" aria-expanded="false">
                                    <fmt:message key="label.language"/>
                                </button>
                                <div class="dropdown-menu lang-dd" aria-labelledby="dropdownMenuButton">
                                    <button class="dropdown-item" type="submit" name="language"
                                            value="en_US">English
                                    </button>
                                    <button class="dropdown-item" type="submit" name="language"
                                            value="ru_RU">Russian
                                    </button>
                                    <input type="hidden" name="command" value="change_locale">
                                </div>
                            </div>
                        </form>
                    </li>
                    <c:choose>
                        <c:when test="${user == null}">
                            <li>
                                <a class="btn sign-in-btn btn-default btn-outline btn-circle" data-toggle="collapse"
                                   href="#nav-collapse2" aria-expanded="false"
                                   aria-controls="nav-collapse2"><fmt:message key="label.sign_in"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a class="btn sign-in-btn btn-default btn-outline btn-circle" data-toggle="collapse"
                                   href="#nav-collapse2" aria-expanded="false"
                                   aria-controls="nav-collapse2"><fmt:message key="label.logOut"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <c:choose>
                    <c:when test="${user == null}">
                        <div class="collapse nav navbar-nav nav-collapse" id="nav-collapse2">
                            <form action="${pageContext.request.contextPath}/controller" method="post"
                                  class="navbar-form navbar-right form-inline">
                                <input type="hidden" name="command" value="log_in">
                                <div class="form-group">
                                    <label for="email"></label>
                                    <input type="email" name="email" class="form-control" id="email" pattern=".*[^<>]"
                                           placeholder="Email" autofocus required/>
                                </div>
                                <div class="form-group">
                                    <label for="password"></label>
                                    <input type="text" name="password" class="form-control" id="password"
                                           pattern=".*[^<>]"
                                           placeholder="Password" required/>
                                </div>
                                <input type="submit" class="btn btn-success" value="Sign"/>
                                <a href="pages/signup.jsp" class="button"><fmt:message key="label.create_account"/></a>
                            </form>
                        </div>
                    </c:when>
                    <c:when test="${user ne null && password ne null}">
                        <div class="collapse nav navbar-nav nav-collapse" id="nav-collapse2">
                            <form action="controller" method="post" class="navbar-form navbar-right form-inline">
                                <input type="hidden" name="command" value="log_out">
                                <div class="form-group">
                                    <label for="email"></label>
                                    <input type="email" name="email" value="${user.email}" class="form-control"
                                           id="providedEmail" pattern=".*[^<>]"
                                           placeholder="<fmt:message key="label.email"/>" autofocus required/>
                                </div>
                                <div class="form-group">
                                    <label for="password"></label>
                                    <input type="text" name="password" value="${password}" class="form-control"
                                           id="providedPassword" pattern=".*[^<>]"
                                           placeholder="<fmt:message key="label.password"/>" required/>
                                </div>
                                <input type="submit" class="btn btn-success" value="Logout"/>
                            </form>
                        </div>
                    </c:when>
                </c:choose>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->
<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>





