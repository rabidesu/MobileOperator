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
<%--<nav class="light-blue lighten-1" role="navigation">--%>
<%--<div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>--%>
<%--<ul class="right hide-on-med-and-down">--%>
<%--<li><a href="#">Navbar Link</a></li>--%>
<%--</ul>--%>

<%--<ul id="nav-mobile" class="side-nav">--%>
<%--<li><a href="#">Navbar Link</a></li>--%>
<%--</ul>--%>
<%--<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>--%>
<%--</div>--%>
<%--</nav>--%>
<jsp:include page="/WEB-INF/jspf/clientNavigation.jsp" />

<div class="container max">
    <h3 class="header center">Личные данные</h3>
    <c:if test="${not empty message}">
        <div class="card-panel orange lighten-5">
            <c:out value="${message}"/>
        </div>
    </c:if>



    <br><br>
</div>


<footer class="orange">
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

