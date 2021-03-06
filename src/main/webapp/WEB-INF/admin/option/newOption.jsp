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
                        Новая опция
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">

                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#required" data-toggle="tab">Требуемые опции</a></li>
                    <li class=""><a href="#incompatible" data-toggle="tab">Несовместимые опции</a></li>
                    <li class="pull-right">
                        <button type="submit" form="form-new-option" class="btn btn-success">Добавить</button>
                    </li>
                </ul>

            </div>

                <div class="col-lg-4 top-buffer" >
                <form:form id="form-new-option" action="/pages/saveOption" method="post" modelAttribute="option">
                    <div class="tab-content">
                    <div class="tab-pane fade in active" id="general">
                    <div class="form-group">
                        <label for="name">Название</label>
                        <%--<input type="text" class="form-control" id="name" name="name" placeholder="Введите название"/>--%>
                        <form:input class="form-control" path="name"/>
                        <form:errors path="name" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label for="price">Цена</label>
                        <%--<input class="form-control" id="price" name="price"/>--%>
                        <form:input type="number" class="form-control" path="price"/>
                        <form:errors path="price" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                        <label for="connectPrice">Стоимость подключения</label>
                        <%--<input type="number" class="form-control" id="connectPrice" name="connect_price">--%>
                        <form:input type="number" class="form-control" path="connectPrice"/>
                        <form:errors path="connectPrice" cssClass="text-danger"/>
                    </div>
                    </div>

                    <div class="tab-pane fade" id="required">
                        <div class="form-group">
                            <label>Требуемые опции:</label>
                            <c:forEach items="${options}" var="req_option">
                            <div class="checkbox">
                                <label><form:checkbox path="optionsRequired" value="${req_option.id}" class="required_option"/>
                                    <c:out value="${req_option.name}" /></label>
                                </div>
                            </c:forEach>
                            <%--<form:checkboxes path="optionsRequired" items="${options}" itemLabel="name" itemValue="id" element="div"/>--%>
                            <%--<c:if test="${not empty requestScope.options}">--%>
                                <%--<label>Требуемые опции:</label>--%>
                                <%--<c:forEach var="option" items="${requestScope.options}">--%>
                                    <%--<div class="checkbox">--%>
                                        <%--<label><input type="checkbox" class="required_option" name="required_option" value="${option.id}">${option.name}</label>--%>
                                    <%--</div>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                            <c:if test="${empty requestScope.options}">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Информация</div>
                                    <div class="panel-body"><c:out value="Нет опций, доступных для выбора. Добавьте сначала новые опции." /></div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="incompatible">
                        <div class="form-group">
                            <label>Несовместима с опциями:</label>
                            <c:forEach items="${options}" var="inc_option">
                            <div class="checkbox">
                                <label><form:checkbox path="optionsIncompatible" value="${inc_option.id}" class="incompatible_option" />
                                <c:out value="${inc_option.name}" /></label>
                                </div>
                            </c:forEach>
                            <%--<c:if test="${not empty requestScope.options}">--%>
                                <%--<label>Несовместима с опциями:</label>--%>
                                <%--<c:forEach var="option" items="${requestScope.options}">--%>
                                    <%--<div class="checkbox">--%>
                                        <%--<label><input type="checkbox"  class="incompatible_option" name="incompatible_option" value="${option.id}">${option.name}</label>--%>
                                    <%--</div>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                            <c:if test="${empty requestScope.options}">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Информация</div>
                                    <div class="panel-body"><c:out value="Нет опций, доступных для выбора. Добавьте сначала новые опции." /></div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                        <form:errors path="optionsIncompatible" cssClass="bg-danger"/>
                        </div>

                </form:form>

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
<script language="JavaScript" type="text/javascript">


        $(document).ready(function () {
        $('input[name="required_option"]:checkbox').change(function () {
            var value = $(this).prop("value");
            if (this.checked){
                $('input[name="incompatible_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
            } else {
                $('input[name="incompatible_option"]:checkbox[value="' + value + '"]').prop("disabled", false);
            }
        });
            $('input[name="incompatible_option"]:checkbox').change(function () {
                var value = $(this).prop("value");
                if (this.checked){
                    $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
                } else {
                    $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", false);
                }
            });

    });

        $(document).ready(function () {
            $('input[name="incompatible_option"]:checkbox').change(function () {
                var value = $(this).prop("value");
                if (this.checked){
                    $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
                } else {
                    $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", false);
                }
            });
        });
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>