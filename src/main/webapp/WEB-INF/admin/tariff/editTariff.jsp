<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> При выборе зависимой опции необходимые опции будут добавлены автоматически
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">

                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#possible" data-toggle="tab">Доступные опции</a></li>
                    <li class="pull-right">
                        <c:if test="${tariff.available}">
                        <button type="submit" form="form-change-tariff" class="btn btn-success">Изменить</button>
                        </c:if>
                        <button type="submit" form="form-remove-tariff" class="btn btn-danger">Удалить</button>
                    </li>
                </ul>

            </div>
            <div class="row">

            <div class="col-lg-4 top-buffer" >
                <form:form id="form-change-tariff" action="/pages/changeTariff" method="post" modelAttribute="tariff">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <form:input class="form-control" path="name" readonly="${!tariff.available || used}"/>
                                <form:errors path="name" cssClass="red"/>
                                <%--<input type="text" class="form-control" id="name" name="name" value="${requestScope.tariff.name}">--%>
                            </div>
                            <div class="form-group">
                                <label for="price">Цена</label>
                                <form:input type="number" class="form-control" path="price" readonly="${!tariff.available || used}"/>
                                <form:errors path="price" cssClass="red"/>
                                <%--<input type="number" class="form-control" id="price" name="price" >--%>
                            </div>
                            <div class="form-group">
                                <label for="price">Описание</label>
                                    <%--<input type="number" class="form-control" id="price" name="price">--%>
                                <form:textarea class="form-control" rows="4" path="description" disabled="${!tariff.available}"/>
                                <form:errors path="description" cssClass="red"/>

                            </div>
                        </div>

                        <div class="tab-pane fade" id="possible">
                            <div class="form-group">
                                <label>Список доступных опций:</label>
                                <c:if test="${not empty allOptions}">
                                    <c:forEach items="${allOptions}" var="option">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="options"
                                                <c:if test="${tariff.options.contains(option)}"> checked </c:if>
                                                <c:if test="${tariff.options.contains(option) && !tariff.available}"> checked disabled </c:if>
                                                <c:if test="${!tariff.options.contains(option) && !tariff.available}"> disabled </c:if>
                                                <c:if test="${tariff.options.contains(option) && used}"> checked class="not-modified" </c:if>
                                                       value="${option.id}">${option.name}
                                            </label>
                                        </div>
                                    </c:forEach>

                                <%--<div class="option_chb">--%>
                                    <%--<form:checkboxes path="options" items="${options}" itemValue="id" itemLabel="name" element="div"--%>
                                                     <%--disabled="${!tariff.available}"/>--%>
                                    <%--</div>--%>
                                </c:if>
                                <c:if test="${empty allOptions}">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body"><c:out value="Нет опций, доступных для выбора. Добавьте сначала новые опции." /></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <form:errors path="options" cssClass="bg-danger"/>
                    </div>
                    <form:hidden path="id"/>
                    <%--<input type="hidden" name="tariff_id" value="${requestScope.tariff.id}" />--%>
                </form:form>
                <form id="form-remove-tariff" action="/pages/removeTariff">
                    <div class="form-group">
                        <div class="top-buffer">
                            <input type="hidden" name="tariff_id" value="${requestScope.tariff.id}" />
                        </div>
                    </div>
                </form>
                </div>

                <div class="row ">
                    <br><br>
                    <div class="col-lg-6 col-lg-offset-3">
                        <c:if test="${used}">
                        <p class="alert alert-info" id="check-tariff-result">Данный тариф используется в одном или нескольних контрактах.
                            При удалении он станет недоступным для подключения, но не будет удален.
                            У используемого тарифа вы не можете изменить название и стоимость, а также удалить доступные опции</p>
                        </c:if>
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

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>