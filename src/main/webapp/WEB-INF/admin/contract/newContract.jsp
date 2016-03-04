<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Панель администратора</title>

    <link href="/css/ecare-style.css" rel="stylesheet">
    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bselect.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/css/sb-admin.css" rel="stylesheet">


</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/jspf/adminNavigation.jsp" />
    <!-- /Navigation -->

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Новый контракт
                    </h1>
                </div>
            </div>

            <!-- Main -->
            <div class="row">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#phone" data-toggle="tab">Номер</a></li>
                    <li class=""><a href="#tariff" data-toggle="tab">Тариф</a></li>
                    <li class=""><a href="#options_tab" data-toggle="tab">Опции</a></li>
                    <li class="pull-right">
                        <button type="submit" form="form-new-contract" class="btn btn-success">Добавить</button>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-lg-4 top-buffer">
                <form role="form" id="form-new-contract" action="/pages/admin/contract/SaveContract">

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                                <div class="form-group">
                                    <label for="name">Имя</label>
                                    <input type="text" class="form-control" id="name" name="name" placeholder="Введите имя">
                                </div>
                                <div class="form-group">
                                    <label for="surname">Фамилия</label>
                                    <input type="text" class="form-control" id="surname" name="surname" placeholder="Введите фамилию">
                                </div>
                                <div class="form-group">
                                    <label for="date">Дата рождения</label>
                                    <input type="date" class="form-control" id="date" name="date">
                                </div>
                                <div class="form-group">
                                    <label for="passport">Паспортные данные</label>
                                    <input type="text" class="form-control" id="passport" name="passport" >
                                </div>
                                <div class="form-group">
                                    <label for="address">Адрес</label>
                                    <input type="text" class="form-control" id="address" name="address">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="Введите email" name="email">
                                </div>
                                <div class="form-group">
                                    <label for="password">Пароль</label>
                                    <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password">
                                </div>
                        </div>

                        <div class="tab-pane fade" id="phone">
                            <div class="form-group">
                                <label for="phone_number">Номер телефона</label>
                                <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Введите номер">
                            </div>
                        </div>

                        <jsp:include page="/WEB-INF/jspf/tariffOptionForContract.jsp" />

                    </div>
                </form>
                </div>
            </div>
            <!-- /Main -->

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/js/jquery-2.2.0.min.js"></script>
<script src="/js/new-contract.js"></script>


<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>


</body>

</html>