<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="light-blue lighten-1" role="navigation">
    <div class="nav-wrapper container">
        <a id="logo-container" href="#" class="brand-logo"><c:out value="${loggedUser.name} ${loggedUser.surname}"/> </a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/pages/clientProfile">Профиль</a></li>
            <li><a href="/pages/clientContracts">Контракты</a></li>
            <li><a href="/pages/searchTariffs">Тарифы</a></li>
            <li><a href="/logout">Выход</a></li>
        </ul>
    </div>
</nav>