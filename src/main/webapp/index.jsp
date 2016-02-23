<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel='stylesheet' href='css/bootstrap.min.css' type='text/css' media='all'>
    <script src="js/jquery-2.2.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<nav role="navigation" class="navbar navbar-default">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand"><img src="images/cellphone.png"></a>
    </div>
    <!-- Collection of nav links, forms, and other content for toggling -->
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Главная</a></li>
            <li><a href="#">Статьи</a></li>
            <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">Новости <b class="caret"></b></a>
                <ul role="menu" class="dropdown-menu">
                    <li><a href="#">Windows</a></li>
                    <li><a href="#">Mac OS</a></li>
                    <li><a href="#">Linux</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Другие системы</a></li>
                </ul>
            </li>
        </ul>
        <form role="search" class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" placeholder="Найти" class="form-control">
            </div>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/admin/index.jsp">Управление</a></li>
            <li><a href="/client/userAccount.jsp">Личный кабинет</a></li>
        </ul>
    </div>
</nav>
</body>
</html>