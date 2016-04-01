<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="firstUrl" value="/pages/contract/page/1/${searchText}" />
<c:url var="lastUrl" value="/pages/contract/page/${totalPage}/${searchText}" />
<c:url var="prevUrl" value="/pages/contract/page/${currentIndex - 1}/${searchText}" />
<c:url var="nextUrl" value="/pages/contract/page/${currentIndex + 1}/${searchText}" />
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
                        Список контрактов
                    </h1>
                    <div class="row">
                    <div class="col-lg-6">
                            <form role="form" action="/pages/contract/page/1" >
                                <div class="form-group input-group">
                                    <input type="text" class="form-control" placeholder="Поиск по номеру..." name="search_text">

                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit" id="search"><i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Нажмите на контракт для подробной информации и редактирования>
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div>
                <c:if test="${totalPage != 0}">
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${currentIndex == 1}">
                            <li class="disabled"><a href="#">&lt;&lt;</a></li>
                            <li class="disabled"><a href="#">&lt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${firstUrl}">&lt;&lt;</a></li>
                            <li><a href="${prevUrl}">&lt;</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                        <c:url var="pageUrl" value="/pages/contract/page/${i}/${searchText}" />
                        <c:choose>
                            <c:when test="${i == currentIndex}">
                                <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${currentIndex == totalPage}">
                            <li class="disabled"><a href="#">&gt;</a></li>
                            <li class="disabled"><a href="#">&gt;&gt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${nextUrl}">&gt;</a></li>
                            <li><a href="${lastUrl}">&gt;&gt;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                </c:if>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty requestScope.listContracts}">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                        <tr>
                            <th>Номер</th>
                            <th>E-mail</th>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Тариф</th>
                            <th>Опции</th>
                            <th>Статус</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.listContracts}" var="contract">
                            <tr class="click-row" data-value="${contract.id}">
                                <td><c:out value="${contract.number}"/></td>
                                <td><c:out value="${contract.user.email}"/></td>
                                <td><c:out value="${contract.user.name}"/></td>
                                <td><c:out value="${contract.user.surname}"/></td>
                                <td><c:out value="${contract.tariff.name}"/></td>
                                <td>
                                <c:forEach items="${contract.options}" var="option">
                                    <c:out value="${option.name}"/>,
                                </c:forEach>
                                </td>
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
                        <c:if test="${empty requestScope.listContracts}">
                            <div class="col-lg-9">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Ошибка!</div>
                                    <div class="panel-body">Нет ни одного контракта, удовлетворяющего условиям поиска</div>
                                </div>
                            </div>
                        </c:if>
                    <form role="form" id="send" action="/pages/editContractByAdmin" method="post">
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