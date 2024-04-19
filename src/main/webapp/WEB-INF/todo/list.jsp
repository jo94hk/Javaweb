<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 4. 18.
  Time: 오전 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>투두 리스트</title>
</head>
<body>

    <h1>Todo List</h1>
    <h2>${loginInfo}</h2>
    <h3>${loginInfo.mname}</h3>

<%--    컨트롤러에서 dtolist를 보내줌   변수는 $써야함--%>
    <ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "DONE" : "NOT YET" }</span>
        </li>
    </c:forEach>
</ul>
    <form action="/logout" method="post">
        <button type="submit">로그아웃</button>
    </form>
<
</body>
</html>
