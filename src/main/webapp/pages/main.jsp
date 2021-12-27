<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Interpol</title>
</head>
<body>
<style>
    label{
        background-color: darkgrey;
        width: 70px;
        display: inline-block;
    }

    h2{
        text-align: center;
    }

    .button{
        text-decoration: none;
        line-height: 40px;
        padding: 0 20px;
        width: 100px;
        text-align: center;
        display: block;
        height: 40px;
        border: 1px solid #000;
        border-radius: 6px;
        background-color: rgb(58, 106, 124);
        color: #000;
    }

    .main-section{
        display: flex;
        height: 100%;
        margin: 0 auto;
        justify-content: space-between;
        margin-top: 70px;
    }

    .nav{
        display: flex;
    }

    li{
        list-style-type: none;
        margin: 0 20px;
    }


    .left {
        margin-right: auto;
    }

    .right{
        margin-left: auto;
    }

    .center{
        margin: 0 auto;
    }

    td{
        padding-bottom: 20px;
    }


</style>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <table style="margin: 0 auto;">
        <tr>
            <td>
                <label for="article_id">ArticleId</label>
                <input type="number" id="article_id" name="article_id"/>
            </td>
        </tr>
        <div>
            <input type="submit" class="button center" value="search"/>
        </div>
    </table>
</form>

<div class="main-section">
    <div>
        <ul class="nav">
            <li>
                <a href="pages/newsfeed.jsp" class="button">NewsFeed</a>
            </li>
            <li>
                <a href="pages/missingpeople.jsp" class="button">MissingPeople</a>
            </li>
            <li>
                <a href="pages/wantedcriminals.jsp" class="button">WantedCriminals</a>
            </li>
            <li>
                <a href="pages/account.jsp" class="button">Account</a>
            </li>
            <li>
            </li>
            <li>
                <a href="pages/loggin.jsp" class="button">Login</a>
            </li>
            <li>
                <a href="" class="button">Кнопка1</a>
            </li>
        </ul>
    </div>
    <div style="margin: 0 20px;">
        <h2>Авторизация</h2>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="log_in">
            <table style="margin: 0 auto;">
                <tr>
                    <td>
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password">Password</label>
                        <input type="text" id="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <div>
                        <input type="submit" class="button center" value="Sign"/>
                    </div>
                    <td>
                        <a href="pages/signing.jsp" class="button">Login</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>

