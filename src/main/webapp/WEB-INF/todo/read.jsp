<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 4. 18.
  Time: 오전 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>할일 읽기</title>
</head>
<body>
<div>
  <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
  <input type="text" name="title" value="${dto.title}" readonly>
</div>
<div>
  <input type="date" name="dueDate" value="${dto.dueDate}" >

</div>
<div>
  <input type="checkbox" name="finished" ${dto.finished ? "checked" : ""} readonly >
</div>
<%-- href href 글자 틀림   herf 글자 틀려서 리스트버튼 없음--%>
<div>
  <button onclick="window.location.href='http://localhost:8080/todo/modify?tno=${dto.tno}'">수정/삭제</button>
  <br>
  <button onclick="window.location.href='http://localhost:8080//todo/list'">목록</button>
</div>
</body>
</html>
