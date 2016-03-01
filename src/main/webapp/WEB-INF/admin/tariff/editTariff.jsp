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
                        Тариф: <c:out value="${requestScope.tariff.name}"/>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">

                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#possible" data-toggle="tab">Доступные опции</a></li>
                </ul>

            </div>

            <div class="col-lg-4 top-buffer" >
                <form role="form" action="/pages/admin/tariff/ChangeTariff" method="post">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <input type="text" class="form-control" id="name" name="name" value="${requestScope.tariff.name}">
                            </div>
                            <div class="form-group">
                                <label for="price">Цена</label>
                                <input type="number" class="form-control" id="price" name="price" value="${requestScope.tariff.price}">
                            </div>
                        </div>

                        <div class="tab-pane fade" id="possible">
                            <div class="form-group">
                                <label>Список доступных опций:</label>
                                <c:if test="${not empty requestScope.options}">
                                    <c:forEach var="option" items="${requestScope.options}">
                                        <div class="checkbox">
                                            <label><input type="checkbox"
                                                <c:forEach var="possible_option" items="${requestScope.tariff.options}">
                                                          <c:if test="${option.id eq possible_option.id}">checked</c:if>
                                                </c:forEach>
                                                          name="options" value="${option.id}">${option.name}</label>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty requestScope.options}">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body"><c:out value="Нет опций, доступных для выбора. Добавьте сначала новые опции." /></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="top-buffer">
                            <button type="submit" class="btn btn-success" name="tariff_id" value="${requestScope.tariff.id}">Изменить</button>
                        </div>
                    </div>
                </form>
                <form action="/pages/admin/tariff/RemoveTariff">
                    <div class="form-group">
                        <div class="top-buffer">
                            <button type="submit" class="btn btn-danger" name="tariff_id" value="${requestScope.tariff.id}">Удалить</button>
                        </div>
                    </div>
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

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>