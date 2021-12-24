<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <div class="title">
     <p><span class="pink">L</span>ist of <span class="pink">S</span>news</p>
 </div>
<table>
    <thead>
    <tr>
    <tr>articleId</tr>
    <tr>NewsArticle</tr>
    <tr>UserId</tr>
    <th class="but"></th>
    </tr>
    </thead>
    <c:forEach var="newsList" items="${list}">
        <td></td>
        <td><strong></strong>${list.article_id}</td>
        <td>${list.news_id}</td>
        <td>${user_id}</td>
        <td class ="but"><button>Delete</button></td>
    </c:forEach>
<c:foreach items="${list}" var="article.get">
    ${student.studName}
</c:foreach>

</table>

</body>
</html>
