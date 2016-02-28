<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alexandra
  Date: 18.02.2016
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в панель администратора</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel='stylesheet' href='/css/bootstrap.min.css' type='text/css' media='all'>
</head>
<body>
<div class="col-sm-4"></div>
<div class="col-sm-4">
    <form role="form" action="/pages/admin/LoginAdmin" method="post" >
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
    <c:if test="${not empty requestScope.errorMassage}">
        <div class="panel panel-danger">
            <div class="panel-heading">Ошибка!</div>
            <div class="panel-body"><c:out value="${requestScope.errorMassage}" /></div>
        </div>
    </c:if>

</div>
<div class="col-sm-4">
</div>
</body>
</html>
