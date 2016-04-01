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
                        Новый тариф
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
                        <button type="submit" form="form-new-tariff" class="btn btn-success">Добавить</button>
                    </li>
                </ul>

            </div>

            <div class="col-lg-4 top-buffer" >
                <form:form id="form-new-tariff" action="/pages/saveTariff" method="post" modelAttribute="tariff" >
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                            <div class="form-group">
                                <label for="name">Название</label>
                                <form:input class="form-control" path="name"/>
                                <form:errors path="name" cssClass="red"/>
                                <%--<input type="text" class="form-control" id="name" name="name" placeholder="Введите название">--%>
                            </div>
                            <div class="form-group">
                                <label for="price">Цена</label>
                                    <%--<input type="number" class="form-control" id="price" name="price">--%>
                                <form:input type="number" class="form-control" path="price"/>
                                <form:errors path="price" cssClass="red"/>

                            </div>
                            <div class="form-group">
                                <label for="price">Описание</label>
                                    <%--<input type="number" class="form-control" id="price" name="price">--%>
                                <form:textarea class="form-control" rows="4" path="description"/>
                                <form:errors path="description" cssClass="red"/>

                            </div>
                        </div>

                        <div class="tab-pane fade" id="possible">
                            <div class="form-group">
                                <label>Список доступных опций:</label>
                                <form:checkboxes path="options" items="${options}" itemValue="id" itemLabel="name" element="div"/>
                                <%--<c:if test="${not empty requestScope.options}">--%>
                                <%--<div class="option_chb">--%>
                                    <%--<c:forEach var="option" items="${requestScope.options}">--%>
                                        <%--<div class="checkbox">--%>
                                            <%--<label><input type="checkbox" name="options"--%>
                                                <%--<c:set var="req_options" value=" " scope="page"/>--%>

                                            <%--<c:forEach var="req_option" items="${option.optionsRequired}">--%>
                                                    <%--<c:set var="req_options" value="${req_options} ${req_option.id}"/>--%>
                                            <%--</c:forEach>--%>
                                                          <%--data-req="${req_options}"--%>
                                                          <%--class="depended_option" value="${option.id}">${option.name}--%>
                                                <%--<input name="options" type="hidden"  disabled id="hidden_${option.id}" value="${option.id}"/>--%>
                                            <%--</label>--%>
                                        <%--</div>--%>
                                    <%--</c:forEach>--%>
                                    <%--</div>--%>
                                <%--</c:if>--%>
                                <c:if test="${empty requestScope.options}">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body"><c:out value="Нет опций, доступных для выбора. Добавьте сначала новые опции." /></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <form:errors path="options" cssClass="red"/>
                    </div>
                </form:form>
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
<script src="/js/new-tariff.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>