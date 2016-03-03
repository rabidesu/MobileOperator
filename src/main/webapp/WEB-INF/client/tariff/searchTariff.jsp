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
                        Список тарифов
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty requestScope.listTariffs}">

                        <c:forEach items="${requestScope.listTariffs}" var="tariff" varStatus="count">
                            <c:if test="${count.index % 3 == 0}"><div class="row"></c:if>
                        <div class="col-lg-4">
                            <div class="panel panel-default tariff-panel">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-xs-12 text-center">
                                            <span class="huge">${tariff.name}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <c:if test="${not empty tariff.options}">
                                    <p>Доступные опции:</p>
                                        <ul>
                                            <c:forEach items="${tariff.options}" var="option">
                                                <li>${option.name}</li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>
                                    <c:if test="${empty tariff.options}">
                                        <p>Для данного тарифа нет доступных опций</p>
                                    </c:if>

                                </div>
                                <div class="panel-footer">
                                    <span class="pull-left">Стоимость</span>
                                    <span class="pull-right">${tariff.price}</span>
                                    <div class="clearfix"></div>
                                </div>
                                <form action="/pages/client/tariff/AddTariffToContract" id="tariff-${tariff.id}">
                                    <div class="panel-footer">
                                        <a>
                                        <span class="pull-left">Перейти на тариф</span>
                                        <button class="pull-right" type="submit" name="tariff_id" value="${tariff.id}">
                                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                        </button>
                                        <div class="clearfix"></div>
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <c:if test="${count.index % 3 == 2}"></div></c:if>
                        </c:forEach>
                        </c:if>
                        <c:if test="${empty requestScope.listTariffs}">
                            <div class="col-lg-9">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Ошибка!</div>
                                    <div class="panel-body">Не найдено ни одного тарифа/></div>
                                </div>
                            </div>
                        </c:if>
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
        $(".tariff-panel").each(function() {
            var $rndColor = getRandomInt(1, 4);
            switch ($rndColor){
                case 1: $(this).attr("class", "panel panel-primary"); break;
                case 2: $(this).attr("class", "panel panel-green"); break;
                case 3: $(this).attr("class", "panel panel-yellow"); break;
                case 4: $(this).attr("class", "panel panel-red"); break;
            }
        });
    });

    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>