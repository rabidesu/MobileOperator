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
                        Новый контракт для <c:out value="${requestScope.user.name} ${requestScope.user.surname}"/>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <ul id="myTab" class="nav nav-tabs">
                    <li class=""><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class="active"><a href="#phone" data-toggle="tab">Номер</a></li>
                    <li class=""><a href="#tariff" data-toggle="tab">Тариф</a></li>
                    <li class=""><a href="#options_tab" data-toggle="tab">Опции</a></li>
                    <li class="pull-right">
                        <button type="submit"  class="btn btn-success" id="btn-save-contract">Добавить</button>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-lg-4 top-buffer">
                <form role="form" action="/pages/admin/contract/SaveContractForUser" id="form-save-contract" method="post">

                    <div class="tab-content">
                        <div class="tab-pane fade" id="general">
                                <div class="form-group">
                                    <label for="name">Имя</label>
                                    <input type="text" class="form-control" disabled id="name" name="name" value="${requestScope.user.name}">
                                </div>
                                <div class="form-group">
                                    <label for="surname">Фамилия</label>
                                    <input type="text" class="form-control" id="surname" disabled name="surname" value="${requestScope.user.surname}">
                                </div>
                                <div class="form-group">
                                    <label for="date">Дата рождения</label>
                                    <input type="date" class="form-control" id="date" name="date" disabled value="${requestScope.user.birthday}">
                                </div>
                                <div class="form-group">
                                    <label for="passport">Паспортные данные</label>
                                    <input type="text" class="form-control" id="passport" name="passport" disabled value="${requestScope.user.passport}" >
                                </div>
                                <div class="form-group">
                                    <label for="address">Адрес</label>
                                    <input type="text" class="form-control" id="address" name="address" disabled value="${requestScope.user.address}">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="Введите email" name="email" disabled value="${requestScope.user.email}">
                                </div>
                                <div class="form-group">
                                    <label for="password">Пароль</label>
                                    <input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password" disabled value="${requestScope.user.password}">
                                </div>
                        </div>

                        <div class="tab-pane fade in active" id="phone">
                            <div class="form-group">
                                <label for="phone_number">Номер телефона</label>
                                <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Введите номер" >
                            </div>
                        </div>

                        <div class="tab-pane fade" id="tariff">
                            <div class="form-group">
                                <label for="tariff_select">Выберите тариф:</label>
                                <select class="form-control"  id="tariff_select" name="tariff_id">
                                    <c:forEach items="${requestScope.tariffs}" var="tariff">
                                        <option value="${tariff.id}"><c:out value="${tariff.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="options_tab">
                            <div class="form-group">
                                <c:if test="${not empty requestScope.options}">
                                    <label>Выберите опции:</label>
                                    <c:forEach var="option" items="${requestScope.options}">
                                        <div class="checkbox">
                                            <label><input type="checkbox"  name="options" value="${option.id}">${option.name}</label>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty requestScope.options}">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body"><c:out value="Нет опций, доступных для данного тарифа"/></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" name="user_id" value="${requestScope.user.id}">
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
<script>
    $(document).ready(function() {
        $("#btn-save-contract").click(function() {
            $("#form-save-contract").submit();
        });
    });

</script>


<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>