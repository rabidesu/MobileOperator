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
                        Список клиентов
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <div class="col-lg-9">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Телефон</th>
                            <th>Тариф</th>
                            <th>Опции</th>
                            <th>Дата рождения</th>
                            <th>Паспортные данные</th>
                            <th>Адрес</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.listContracts}" var="contract">
                            <tr>
                                <td><c:out value="${contract.id}"/></td>
                                <td><c:out value="${contract.number}"/></td>
                                <td><c:out value="${contract.tariff}"/></td>
                                <td><c:out value="${contract.id}"/></td>
                                <td><c:out value="${contract.id}"/></td>
                                <td><c:out value="${contract.id}"/></td>
                                <td><c:out value="${contract.id}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>