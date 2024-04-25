<%--
  Created by IntelliJ IDEA.
  User: YJ
  Date: 2024-04-25
  Time: 오전 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To Do Register</title>
    <b1>To Do Register</b1>
</head>
<body>
<form action="/todo/register" method="post">
    <label for="title"></label>
    <input type="text" id ="title" name="title" placeholder="title">
    <label for="dueDate"></label>
    <input type="date" id = dueDate name="dueDate" placeholder="dueDate">
    <label for="finished"></label>
    <span>finished: </span><input type="checkbox" id="finished" name="finished" value="true">
    <button type="submit">SUBMIT</button>
</form>
</body>
</html>
