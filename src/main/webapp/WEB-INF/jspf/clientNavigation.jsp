<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/pages/client/index.jsp">Личный кабинет</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">


        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar">
                    <div class="row text-center">
                            <i class="fa fa-user fa-5x"></i>
                    </div>
                    <div class="row text-center">
                        <h3>${sessionScope.user.name} ${sessionScope.user.surname}</h3>
                    </div>

                </li>
                <li>
                    <a href="/pages/client/profile/profile.jsp"><i class="fa fa-list fa-fw"></i> Профиль</a>
                </li>
                <li>
                    <a href="/pages/client/contract/contracts.jsp"><i class="fa fa-file-o fa-fw"></i> Контракты</a>
                </li>
                <li>
                    <a href="/pages/client/tariff/ViewTariff"><i class="fa fa-th-large fa-fw"></i> Тарифы</a>
                </li>
                <li>
                    <a href="/pages/Logout"><i class="fa fa-sign-out fa-fw"></i> Выход</a>
                </li>

            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>