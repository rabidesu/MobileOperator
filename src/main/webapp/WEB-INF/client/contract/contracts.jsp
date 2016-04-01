<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Личный кабинет</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="/css/materialize.min.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <%--<link href="/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>--%>
</head>
<body>

<jsp:include page="/WEB-INF/jspf/clientNavigation.jsp" />

<div class="container">
    <h2 class="header center orange-text">Контракты</h2>
    <div class="row">
        <c:forEach items="${user.contracts}" var="contract">
        <div class="col s12 m4">
            <div class="card">
                <div class="card-content center">
                    <h5 class="card-title light-blue-text"><c:out value="${contract.number}"/></h5>
                    <p class="card-title grey-text text-darken-4"><b>Тариф: </b><c:out value="${contract.tariff.name}"/></p>
                    <p class="card-title grey-text text-darken-4"><b>Цена: </b><c:out value="${contract.tariff.price}"/>&#8381</p>
                </div>
                <div class="card-action center">
                    <a href="">Изменить</a>
                    <span class="activator orange-text"> MORE</span>
                </div>
                <div class="card-reveal">
                    <span class="card-title grey-text text-darken-4">Опции<i class="material-icons right">close</i></span>
                    <ul class="collection">
                        <c:forEach items="${contract.options}" var="option">
                            <li class="collection-item"><c:out value="${option.name}"/></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
            </c:forEach>
        </div>
    <br><br>
</div>


<footer class="page-footer orange">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Company Bio</h5>
                <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Settings</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Connect</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
        </div>
    </div>
</footer>


<!--  Scripts-->
<script src="/js/jquery-2.2.0.min.js"></script>
<script src="/js/materialize.min.js"></script>
<%--<script src="/js/init.js"></script>--%>

</body>
</html>

