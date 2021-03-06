<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход в личный кабинет</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel='stylesheet' href='/css/bootstrap.min.css' type='text/css' media='all'>
    <link rel='stylesheet' href='/css/ecare-style.css' type='text/css' media='all'>
</head>
<body>



<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Вход в систему</h3>
                </div>
                <div class="panel-body">
                    <form role="form" action="/pages/client/LoginClient" method="post">
                        <div class="form-group">
                            <input class="form-control" placeholder="E-mail" name="email" type="email" autofocus>
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="Пароль" name="password" type="password" value="">
                        </div>
                        <button type="submit" class="btn btn-lg btn-success btn-block">Войти</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <c:if test="${not empty requestScope.errorMassage}">
                <div class="panel panel-danger">
                    <div class="panel-heading">Ошибка!</div>
                    <div class="panel-body"><c:out value="${requestScope.errorMassage}" /></div>
                </div>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
