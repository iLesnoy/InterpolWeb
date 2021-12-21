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

<div class="main-section">
    <div>
        <ul class="nav">
            <li>
                <a href="newsfeed.jsp" class="button">NewsFeed</a>
            </li>
            <li>
                <a href="missingpeople.jsp" class="button">MissingPeople</a>
            </li>
            <li>
                <a href="wantedcriminals.jsp" class="button">WantedCriminals</a>
            </li>
            <li>
                <a href="" class="button">Кнопка1</a>
            </li>
        </ul>
    </div>
    <div style="margin: 0 20px;">
        <h2>Регистрация</h2>
        <form action="controller" method="get">
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
                        <input type="password" id="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="surname">Surname</label>
                        <input type="text" id="surname" name="surname" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" class="button center" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>