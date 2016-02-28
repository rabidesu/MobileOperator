<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <!-- Custom CSS -->
    <link href="/css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="/css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
                        Клиент <c:out value="${requestScope.user.name} ${requestScope.user.surname}"/>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">

                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Профиль</a></li>
                    <li class=""><a href="#user_contracts" data-toggle="tab">Контракты</a></li>
                </ul>

            </div>

            <div class="tab-content">
                <div class="tab-pane fade in active" id="general">
                    <div class="col-lg-4 top-buffer">

                        <form role="form" action="">
                            <div class="form-group">
                                <label for="name">Имя</label>
                                <input type="text" class="form-control" disabled id="name" value="${requestScope.user.name}">
                            </div>
                            <div class="form-group">
                                <label for="surname">Фамилия</label>
                                <input type="text" class="form-control" id="surname" disabled  value="${requestScope.user.surname}">
                            </div>
                            <div class="form-group">
                                <label for="date">Дата рождения</label>
                                <input type="date" class="form-control" id="date"  disabled value="${requestScope.user.birthday}">
                            </div>
                            <div class="form-group">
                                <label for="passport">Паспортные данные</label>
                                <input type="text" class="form-control" id="passport"  disabled value="${requestScope.user.passport}" >
                            </div>
                            <div class="form-group">
                                <label for="address">Адрес</label>
                                <input type="text" class="form-control" id="address"  disabled value="${requestScope.user.address}">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email"   disabled value="${requestScope.user.email}">
                            </div>
                            <div class="form-group">
                                <label for="password">Пароль</label>
                                <input type="password" class="form-control" id="password"   disabled value="${requestScope.user.password}">
                            </div>
                        </form>
                    </div>
                </div>

                <div class="tab-pane fade" id="user_contracts">
                    <div class="col-lg-12 top-buffer">
                        <c:if test="${not empty requestScope.user.contracts}">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Номер</th>
                                        <th>Тариф</th>
                                        <th>Опции</th>
                                        <th>Цена в месяц</th>
                                        <th>Статус</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.user.contracts}" var="contract">
                                        <tr class="click-row" data-value="${contract.id}">
                                            <td><c:out value="${contract.id}"/></td>
                                            <td><c:out value="${contract.number}"/></td>
                                            <td><c:out value="${contract.tariff.name}"/></td>
                                            <c:set var="price" value="${contract.tariff.price}" scope="page"/>
                                            <td>
                                            <c:forEach items="${contract.options}" var="option">
                                                <c:set var="price" value="${price + option.price}"/>
                                                <c:out value="${option.name}"/>,
                                            </c:forEach>
                                            </td>
                                            <td><c:out value="${price}"/></td>
                                            <td>
                                                <c:if test="${contract.blockedByAdmin}">
                                                    <span class="contract-blocked">Заблокирован </span>
                                                </c:if>
                                                <c:if test="${contract.blockedByClient}">
                                                    <span class="contract-inactive" >Неактивен </span>
                                                </c:if>
                                                <c:if test="${!contract.blockedByAdmin && !contract.blockedByClient}">
                                                    <span class="contract-active">Активен </span>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                            <c:if test="${empty requestScope.user.contracts}">
                                <div class="col-lg-9">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body">У пользователя нет контрактов</div>
                                    </div>
                                </div>
                            </c:if>
                        <form role="form" id="send" action="/pages/admin/EditContract" method="post">
                            <input type="text" id="entity_id" name="entity_id" hidden>
                        </form>
                        </div>
                    </div>
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
<script>
    $(document).ready(function() {
        $(".click-row").click(function() {
            var $entity_id = $(this).attr("data-value");
            $("#entity_id").val($entity_id);
            $("#send").submit();
        });
    });
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>