<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
</head>
<body>
<h2>${message}</h2>

<form action="/everythings/system/login" method="post">
    <label>用户名:</label>
    <input name="loginName" value="yarne">
    <label>密码:</label>
    <input name="password" value="123123123">
    <button type="submit">提交</button>
</form>


</body>
</html>
