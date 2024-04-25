<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo List</title>
    <style>
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>To Do List</h1>
    <ul>
        <c:forEach items="${dtoList}" var ='dto'>
            <li><a href="/todo/modify?tno=${dto.tno}">${dto}</a></li>
        </c:forEach>
    </ul>
    <a href="/todo/register" class="button">글 등록</a>
</body>
</html>
