<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="firstUrl" value="/pages/users/page/1/${searchText}/${searchField}" />
<c:url var="lastUrl" value="/pages/users/page/${totalPage}/${searchText}/${searchField}" />
<c:url var="prevUrl" value="/pages/users/page/${currentIndex - 1}/${searchText}/${searchField}" />
<c:url var="nextUrl" value="/pages/users/page/${currentIndex + 1}/${searchText}/${searchField}" />
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
                        Список клиентов
                    </h1>
                    <div class="row">
                        <div class="col-lg-2">
                            <select class="form-control" form="search-client-form" name="search_field">
                                <option value="phone">Телефон</option>
                                <option value="surname">Фамилия</option>
                                <option value="email">E-mail</option>
                            </select>
                        </div>
                        <div class="col-lg-6">
                            <form role="form" action="/pages/users/page/1" id="search-client-form" >
                                <div class="form-group input-group">
                                    <input type="text" class="form-control" placeholder="Поиск..." name="search_text">
                                    <span class="input-group-btn"><button class="btn btn-default" type="submit" id="search">
                                        <i class="fa fa-search"></i></button></span>
                                </div>
                            </form>

                        </div>
                    </div>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Нажмите на клиента для просмотра профиля
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
                            <c:url var="pageUrl" value="/pages/users/page/${i}/${searchText}/${searchField}" />
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
                    <c:if test="${not empty requestScope.listUsers}">
                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                            <tr>
                                <th>Имя</th>
                                <th>Фамилия</th>
                                <th>E-mail</th>
                                <th>Дата рождения</th>
                                <th>Контракты</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listUsers}" var="user">
                                <tr class="click-row" data-value="${user.id}">
                                    <td><c:out value="${user.name}"/></td>
                                    <td><c:out value="${user.surname}"/></td>
                                    <td><c:out value="${user.email}"/></td>
                                    <td><c:out value="${user.birthday}"/></td>
                                    <td>
                                    <c:forEach items="${user.contracts}" var="contract" varStatus="count">
                                    <c:out value="${contract.number}" />
                                        <c:if test="${!count.last}"><br></c:if>
                                    </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty requestScope.listUsers}">
                        <div class="col-lg-9">
                            <div class="panel panel-warning">
                                <div class="panel-heading">Ошибка!</div>
                                <div class="panel-body">Нет ни одного клиента, удовлетворяющего условиям поиска"</div>
                            </div>
                        </div>
                    </c:if>
                    <form role="form" id="send" action="/pages/findClientProfile" method="post">
                        <input type="text" id="entity_id" name="user_id" hidden>
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
            var $user_id = $(this).attr("data-value");
            $("#entity_id").val($user_id);
            $("#send").submit();
        });
    });
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>


</body>

</html>