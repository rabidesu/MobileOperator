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
                        Опция: <c:out value="${requestScope.option_name}"/>
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
                        <c:if test="${option.available}">
                        <button type="submit" form="form-change-option" class="btn btn-success">Изменить</button>
                        </c:if>
                        <button type="submit" id="check_option" class="btn btn-success">Проверить</button>
                        <button type="submit" form="form-remove-option" class="btn btn-danger" >Удалить</button>
                    </li>
                </ul>

            </div>
            <div class="row">

                <div class="col-lg-4 top-buffer" >
                <form:form role="form" id="form-change-option" action="/pages/changeOption" method="post" modelAttribute="option">
                    <div class="tab-content">
                    <div class="tab-pane fade in active" id="general">
                    <div class="form-group">
                        <label for="name">Название</label>
                        <form:input class="form-control" path="name" disabled="${!option.available}"/>
                        <form:errors path="name" cssClass="text-danger"/>
                        <%--<input type="text" class="form-control" id="name" name="name" value="${option.name}">--%>
                    </div>
                    <div class="form-group">
                        <label for="price">Цена</label>
                        <form:input type="number" class="form-control" path="price" disabled="${!option.available}"/>
                        <form:errors path="price" cssClass="text-danger"/>
                        <%--<input type="number" class="form-control" id="price" name="price" value="${option.price}">--%>
                    </div>
                    <div class="form-group">
                        <label for="connectPrice">Стоимость подключения</label>
                        <form:input type="number" class="form-control" path="connectPrice" disabled="${!option.available}"/>
                        <form:errors path="connectPrice" cssClass="text-danger"/>
                        <%--<input type="number" class="form-control" id="connect_price" name="connect_price" value="${option.connectPrice}">--%>
                    </div>
                    </div>

                    <div class="tab-pane fade" id="required">
                        <div class="form-group">
                            <label>Требуемые опции:</label>
                            <form:checkboxes path="optionsRequired" items="${anotherOptions}" itemValue="id" itemLabel="name" element="div" disabled="${!option.available}"/>
                            <%--<c:forEach items="${options}" var="req_option">--%>
                                <%--<div class="checkbox">--%>
                                    <%--<label><form:checkbox path="optionsRequired" value="${req_option.id}" class="required_option" --%>

                                    <%--/>--%>
                                        <%--<c:out value="${req_option.name}" /></label>--%>
                                <%--</div>--%>
                            <%--</c:forEach>--%>
                            <%--<c:if test="${not empty requestScope.options}">--%>
                                <%--<label>Требуемые опции:</label>--%>
                                <%--<c:forEach var="option" items="${requestScope.options}">--%>
                                    <%--<div class="checkbox">--%>
                                        <%--<label><input type="checkbox"--%>
                                            <%--<c:forEach var="req_option" items="${requestScope.option.optionsRequired}">--%>
                                                <%--<c:if test="${option.id eq req_option.id}">checked</c:if>--%>
                                            <%--</c:forEach>--%>
                                                      <%--class="required_option" name="required_option" value="${option.id}">${option.name}</label>--%>
                                    <%--</div>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                            <c:if test="${empty requestScope.anotherOptions}">
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
                            <form:checkboxes path="optionsIncompatible" items="${anotherOptions}" itemValue="id" itemLabel="name" element="div"
                                             disabled="${!option.available}"/>
                            <%--<c:forEach items="${anotherOptions}" var="inc_option">--%>
                                <%--<div class="checkbox">--%>
                                    <%--<label><form:checkbox path="optionsIncompatible" value="${inc_option.id}" class="incompatible_option" />--%>
                                        <%--<c:out value="${inc_option.name}" /></label>--%>
                                <%--</div>--%>
                            <%--</c:forEach>--%>
                            <%--<c:if test="${not empty requestScope.options}">--%>
                                <%--<label>Несовместима с опциями:</label>--%>
                                <%--<c:forEach var="option" items="${requestScope.options}">--%>
                                    <%--<div class="checkbox">--%>
                                        <%--<label><input type="checkbox"--%>
                                        <%--<c:forEach var="inc_option" items="${requestScope.option.optionsIncompatible}">--%>
                                                      <%--<c:if test="${option.id eq inc_option.id}">checked</c:if>--%>
                                        <%--</c:forEach>--%>
                                                      <%--class="incompatible_option" name="incompatible_option" value="${option.id}">${option.name}</label>--%>
                                    <%--</div>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                            <c:if test="${empty requestScope.anotherOptions}">
                                <div class="panel panel-warning">
                                    <div class="panel-heading">Информация</div>
                                    <div class="panel-body"><c:out value="Нет опций, доступных для выбора. Добавьте сначала новые опции." /></div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                        <form:errors path="optionsIncompatible" cssClass="bg-danger"/>
                        </div>
                    <form:hidden path="id"/>
                </form:form>
                    <form action="/pages/removeOption" id="form-remove-option">
                        <input type="hidden" name="option_id" value="${requestScope.option.id}"/>
                    </form>

                </div>
            </div>

            <div class="row ">
                <br><br>
                <div class="col-lg-6 col-lg-offset-3">
                <p class="alert alert-info" id="check-option-result" hidden></p>
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
<script language="JavaScript" type="text/javascript">

    $(document).ready(function f() {
        $('#check_option').on('click', doAjax);

    });
    function doAjax() {

        var optionId = ${option.id};
        $.ajax({
            url: '/pages/checkOptionUsed',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: ({
                optionId: optionId
            }),
            success: function (data) {
                var result;
                if (data) {
                    result = "Опция подключена к одному или нескольким контрактам. При удалении она станет недоступной для подключения, но не будет удалена.";
                } else {
                    result = "Опция не подключена ни к одному контракту и может быть удалена";
                }
                $('#check-option-result').text(result).show();
            }
        });
    }

//    $(document).ready(function () {
//        $('input[name="required_option"]:checkbox').each(function (i) {
//            if (this.checked) {
//                var value = $(this).prop("value");
//                $('input[name="incompatible_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
//            }
//        });
//    });
//
//    $(document).ready(function () {
//        $('input[name="incompatible_option"]:checkbox').each(function (i) {
//            if (this.checked) {
//                var value = $(this).prop("value");
//                $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
//            }
//        });
//    });
//
//        $(document).ready(function () {
//        $('input[name="required_option"]:checkbox').change(function () {
//            var value = $(this).prop("value");
//            if (this.checked){
//                $('input[name="incompatible_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
//            } else {
//                $('input[name="incompatible_option"]:checkbox[value="' + value + '"]').prop("disabled", false);
//            }
//        });
//    });
//
//        $(document).ready(function () {
//            $('input[name="incompatible_option"]:checkbox').change(function () {
//                var value = $(this).prop("value");
//                if (this.checked){
//                    $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", true);
//                } else {
//                    $('input[name="required_option"]:checkbox[value="' + value + '"]').prop("disabled", false);
//                }
//            });
//        });
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>