<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/pages/admin/index.jsp">Панель управления</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <c:out value="${sessionScope.user.name} ${sessionScope.user.surname}"/>  <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="/pages/Logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <li>
                <a href="/pages/admin/index.jsp"><i class="fa fa-fw fa-dashboard"></i> Быстрые действия</a>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#clients">Управление клиентами <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="clients" class="collapse">
                    <li>
                        <a href="/pages/admin/client/FindClient">Поиск клиентов</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#contracts">Управление контрактами <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="contracts" class="collapse">
                    <li>
                        <a href="/pages/admin/contract/NewContract">Новый контракт</a>
                    </li>
                    <li>
                        <a href="/pages/admin/contract/FindContract">Просмотр контрактов</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#tariffs">Управление тарифами <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="tariffs" class="collapse">
                    <li>
                        <a href="/pages/admin/tariff/NewTariff">Новый тариф</a>
                    </li>
                    <li>
                        <a href="/pages/admin/tariff/FindTariff">Просмотр тарифов</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#options">Управление опциями <i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="options" class="collapse">
                    <li>
                        <a href="/pages/admin/option/NewOption">Добавить опцию</a>
                    </li>
                    <li>
                        <a href="/pages/admin/option/FindOption">Просмотр опций</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>
