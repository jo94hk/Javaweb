<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-15
  Time: 오전 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>넘1 : ${param.num1}</h1>
    <h1>넘2 : ${param.num2}</h1>
    <h1>더한값 : ${Integer.parseInt(param.num1)+Integer.parseInt(param.num2)}</h1>
</body>
</html>
