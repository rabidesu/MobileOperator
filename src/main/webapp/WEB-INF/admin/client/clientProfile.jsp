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

        <div class="container-fluid fill">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Клиент: <c:out value="${requestScope.user.name} ${requestScope.user.surname}"/>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->

            <div class="row">

                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Профиль</a></li>
                    <li class=""><a href="#user_contracts" data-toggle="tab">Контракты</a></li>
                    <c:if test="${requestScope.user.role.roleName == 'CLIENT'}">
                    <li class="pull-right">
                        <button type="submit" form="form-new-contract-for-user" class="btn btn-success" id="btn-new-contract-for-user">Новый контракт</button>
                    </li>
                    </c:if>
                    <c:if test="${requestScope.user.role.roleName == 'ADMIN'}">
                        <li class="pull-right">
                            <h3>Администратор</h3>
                        </li>
                    </c:if>
                </ul>

            </div>

            <div class="tab-content">
                <div class="tab-pane fade in active" id="general">
                    <div class="col-lg-4 top-buffer">

                        <form role="form" action="">
                            <div class="form-group">
                                <label>E-mail</label>
                                <p class="form-control"><c:out value="${user.email}"/></p>
                                <%--<input type="email" class="form-control" id="email" placeholder="Введите email" name="email">--%>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <p class="form-control"><c:out value="${user.name}"/></p>
                                <%--<input type="text" class="form-control" id="name" name="name" placeholder="Введите имя">--%>
                            </div>
                            <div class="form-group">
                                <label>Фамилия</label>
                                <p class="form-control"><c:out value="${user.surname}"/></p>
                                <%--<input type="text" class="form-control" id="surname" name="surname" placeholder="Введите фамилию">--%>
                            </div>
                            <div class="form-group">
                                <label>Дата рождения</label>
                                <%--<input type="date" class="form-control" id="user.birthday" name="user.birthday">--%>
                                <p class="form-control"><c:out value="${user.birthday}"/></p>
                                <%--<input type="date" class="form-control" id="date" name="date">--%>
                            </div>
                            <div class="form-group">
                                <label>Паспортные данные</label>
                                <p class="form-control"><c:out value="${user.passport}"/></p>
                                <%--<input type="text" class="form-control" id="passport" name="passport" >--%>
                            </div>
                            <div class="form-group">
                                <label>Адрес</label>
                                <p class="form-control"><c:out value="${user.address}"/></p>
                                <%--<input type="text" class="form-control" id="address" name="address">--%>
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
                        <form role="form" id="send" action="/pages/editContractByAdmin" method="post">
                            <input type="text" id="entity_id" name="entity_id" hidden>
                        </form>
                        </div>
                    </div>
                </div>
            <form action="/pages/newContractForUser" method="post" id="form-new-contract-for-user">
                <input type="hidden" name="user_id" value="${requestScope.user.id}">
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


</body>

</html>