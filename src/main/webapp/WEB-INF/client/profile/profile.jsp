<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<div class="container">
    <h2 class="header center orange-text">Личные данные</h2>

    <div class="row">

        <div class="col s8 offset-s2">
            <div class="card-panel">
                <div class="row">
                    <form class="col s12">
                        <div class="row">
                            <div class="input-field col s6">
                                <label for="loggedUser.name">First Name</label>
                                <form:input path="loggedUser.name"/>
                                <form:errors path="loggedUser.name"/>
                                <%--<input placeholder="Placeholder" id="first_name" type="text" class="validate">--%>
                            </div>
                            <div class="input-field col s6">
                                <label for="loggedUser.surname">Last Name</label>
                                <form:input  path="loggedUser.surname"/>
                                <form:errors path="loggedUser.surname"/>
                                <%--<input id="last_name" type="text" class="validate">--%>
                                <%--<label for="last_name">Last Name</label>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6">
                                <label for="loggedUser.email">Email</label>
                                <form:input  path="loggedUser.email" disabled="true"/>
                                <form:errors path="loggedUser.email"/>
                                <%--<input id="email" type="email" class="validate">--%>
                                <%--<label for="email">Email</label>--%>
                            </div>
                            <div class="input-field col s6">
                                <label for="loggedUser.birthday">Birthday</label>
                                <form:input  path="loggedUser.birthday" disabled="true"/>
                                <form:errors path="loggedUser.birthday"/>
                                <%--<input id="birthday" type="date">--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <label for="loggedUser.passport">Passport</label>
                                <form:input  path="loggedUser.passport" disabled="true"/>
                                <form:errors path="loggedUser.passport"/>
                                <%--<input id="passport" type="text">--%>
                                <%--<label for="passport">Passport</label>--%>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <label for="loggedUser.address">Address</label>
                                <form:input  path="loggedUser.address"/>
                                <form:errors path="loggedUser.address"/>
                                <%--<input id="address" type="text">--%>
                                <%--<label for="address">Address</label>--%>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row center">
        <a href="http://materializecss.com/getting-started.html" id="download-button" class="btn-large waves-effect waves-light orange">Изменить</a>
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

