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

    <title>Личный кабинет</title>


    <link href="/css/ecare-style.css" rel="stylesheet">
    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

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
                        Список контрактов
                    </h1>

                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Нажмите на контракт для подробной информации и редактирования
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty sessionScope.user.contracts}">
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
                        <c:forEach items="${sessionScope.user.contracts}" var="contract">
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
                                    <c:if test="${contract.blockedByClient && !contract.blockedByAdmin}">
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
                        <c:if test="${empty sessionScope.user.contracts}">
                            <div class="col-lg-9">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Ошибка!</div>
                                    <div class="panel-body">У Вас еще нет контрактов. Обратитесь к администратору.</div>
                                </div>
                            </div>
                        </c:if>
                    <form role="form" id="send" action="/pages/client/contract/EditContract" method="post">
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
<script src="/js/sb-admin-2.js"></script>

</body>

</html>