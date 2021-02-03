<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
    <body>
        <h1>Update User Form</h1>
        <form action="/update/${user.user_id}" method="POST">
            UserID: ${user.user_id}<br />
            Username: <input type="text" name="username" value="${user.username}"/><br />
            Password: <input type="text" name="password" value="${user.password}" /><br />
            <input type="submit" value="Update" />
        </form>
    </body>
</html>