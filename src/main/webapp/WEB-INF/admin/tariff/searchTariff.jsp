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
                        Список тарифов
                    </h1>
                    <div class="row">
                    <div class="col-lg-6">
                            <form role="form" action="/pages/searchTariff" >
                                <div class="form-group input-group">
                                    <input type="text" class="form-control" placeholder="Поиск по названию..." name="search_text">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit" id="search"><i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Нажмите на тариф для его редактирования или удаления
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty requestScope.listTariffs}">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Название</th>
                            <th>Стоимость</th>
                            <th>Возможные опции</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.listTariffs}" var="tariff">
                            <tr class="click-row <c:if test="${!tariff.available}">text-muted</c:if>" data-value="${tariff.id}">
                                <td><c:out value="${tariff.name}"/></td>
                                <td><c:out value="${tariff.price}"/>&#8381</td>
                                <td>
                                <c:forEach items="${tariff.options}" var="option">
                                    <c:out value="${option.name}"/>,
                                </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                        </c:if>
                        <c:if test="${empty requestScope.listTariffs}">
                            <div class="col-lg-9">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Ошибка!</div>
                                    <div class="panel-body">Нет ни одного тарифа, удовлетворяющего условиям поиска</div>
                                </div>
                            </div>
                        </c:if>
                    <form role="form" id="send" action="/pages/editTariff" method="post">
                    <input type="text" id="entity_id" name="entity_id" hidden>
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