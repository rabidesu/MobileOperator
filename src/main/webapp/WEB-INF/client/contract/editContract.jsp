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

    <title>Личный кабинет</title>

    <link href="/css/ecare-style.css" rel="stylesheet">
    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/bselect.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/jspf/clientNavigation.jsp" />
    <!-- /Navigation -->

    <div id="page-wrapper">

        <div class="container-fluid">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Контракт: <c:out value="${requestScope.contract.number} "/>
                        <c:if test="${requestScope.contract.blockedByAdmin}">
                            <span class="contract-blocked pull-right">Заблокирован </span>
                        </c:if>
                        <c:if test="${requestScope.contract.blockedByClient}">
                            <span class="contract-inactive pull-right" >Неактивен </span>
                        </c:if>
                        <c:if test="${!requestScope.contract.blockedByAdmin && !requestScope.contract.blockedByClient}">
                            <span class="contract-active pull-right">Активен </span>
                        </c:if>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#phone" data-toggle="tab">Номер</a></li>
                    <li class=""><a href="#tariff" data-toggle="tab">Тариф</a></li>
                    <li class=""><a href="#options_tab" data-toggle="tab">Опции</a></li>
                    <li class="pull-right">
                        <c:if test="${!requestScope.contract.blockedByAdmin && !requestScope.contract.blockedByClient}">
                            <button type="submit" class="btn btn-success" form="form-save-contract" id="btn-save-contract">Сохранить</button>
                            <button type="submit" class="btn btn-danger" form="form-block-contract" id="btn-block">Заблокировать</button>
                        </c:if>
                        <c:if test="${!requestScope.contract.blockedByAdmin && requestScope.contract.blockedByClient}">
                            <button type="submit" class="btn btn-warning" form="form-unblock-contract" id="btn-unblock">Активировать</button>
                        </c:if>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-lg-4 top-buffer">
                <form role="form" action="/pages/client/contract/ChangeContractByClient" id="form-save-contract" method="post">

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                                <div class="form-group">
                                    <label for="name">Имя</label>
                                    <input type="text" class="form-control" disabled id="name" name="name" value="${requestScope.contract.user.name}">
                                </div>
                                <div class="form-group">
                                    <label for="surname">Фамилия</label>
                                    <input type="text" class="form-control" id="surname" disabled name="surname" value="${requestScope.contract.user.surname}">
                                </div>
                                <div class="form-group">
                                    <label for="date">Дата рождения</label>
                                    <input type="date" class="form-control" id="date" name="date" disabled value="${requestScope.contract.user.birthday}">
                                </div>
                                <div class="form-group">
                                    <label for="passport">Паспортные данные</label>
                                    <input type="text" class="form-control" id="passport" name="passport" disabled value="${requestScope.contract.user.passport}" >
                                </div>
                                <div class="form-group">
                                    <label for="address">Адрес</label>
                                    <input type="text" class="form-control" id="address" name="address" disabled value="${requestScope.contract.user.address}">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="Введите email" name="email" disabled value="${requestScope.contract.user.email}">
                                </div>
                        </div>

                        <div class="tab-pane fade" id="phone">
                            <div class="form-group">
                                <label for="phone_number">Номер телефона</label>
                                <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Введите номер" disabled value="${requestScope.contract.number}">
                            </div>
                        </div>



                        <jsp:include page="/WEB-INF/jspf/tariffOptionForContract.jsp" />

                        <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                        </div>

                </form>
                </div>
                <form action="/pages/client/contract/ChangeContractByClient" id="form-block-contract" method="post">
                    <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                    <input type="hidden" name="block" value="blockByClient">
                </form>
                <form action="/pages/client/contract/ChangeContractByClient" id="form-unblock-contract" method="post">
                    <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                    <input type="hidden" name="block" value="unblockByClient">
                </form>
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
<c:if test="${!requestScope.contract.blockedByAdmin && !requestScope.contract.blockedByClient}">
    <script src="/js/edit-contract.js"></script>
</c:if>



<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>
<script src="/js/sb-admin-2.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>