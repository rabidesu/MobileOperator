<%--
  Created by IntelliJ IDEA.
  User: Alexandra
  Date: 11.02.2016
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в личный кабинет</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel='stylesheet' href='/css/bootstrap.min.css' type='text/css' media='all'>
    </head>
</head>
<body>
<div class="col-sm-4"></div>
<div class="col-sm-4">
<form role="form" action="pages/client/LoginClient" >
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Введите email">
    </div>
    <div class="form-group">
        <label for="pass">Пароль</label>
        <input type="password" class="form-control" id="pass" name="pass" placeholder="Пароль">
    </div>
    <button type="submit" class="btn btn-success">Войти</button>
</form>
</div>
<div class="col-sm-4">
</div>
</body>
</html>
