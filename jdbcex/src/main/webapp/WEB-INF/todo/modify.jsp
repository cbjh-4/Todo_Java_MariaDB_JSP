<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To Do Modify</title>
    <h1>To Do Modify</h1>
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
<form action="/todo/modify" method="post">
    <label for="tno"></label>
    <input type="text" id="tno" name="tno" value="${todoDTO.tno}" readonly>
    <label for="title"></label>
    <input type="text" id="title" name="title" value="${todoDTO.title}">
    <label for="dueDate"></label>
    <input type="date" id="dueDate" name="dueDate" value="${todoDTO.dueDate}" readonly>
    <span>finished: </span><input type="checkbox" id="finished" name="finished" value="true" ${todoDTO.finished ? "checked" : ""}>
    <label for="finished"></label>
    <button type="submit" class="button">수정</button>
</form>
<form action="/todo/delete" method="post">
    <input type="hidden" name = "tno" value="${todoDTO.tno}">
    <button type="submit" class="button">삭제</button>
</form>
</body>
</html>
