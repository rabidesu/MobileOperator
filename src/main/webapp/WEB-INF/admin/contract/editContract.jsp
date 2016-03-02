<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link href="/css/bselect.min.css" rel="stylesheet">

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
                        Контракт: <c:out value="${requestScope.contract.number} "/>
                        <c:if test="${requestScope.contract.blockedByAdmin}">
                            <span class="contract-blocked pull-right">Заблокирован </span>
                        </c:if>
                        <c:if test="${requestScope.contract.blockedByClient}">
                            <span class="contract-inactive pull-right" >Неактивен </span>
                        </c:if>
                        <c:if test="${!requestScope.contract.blockedByAdmin && !requestScope.contract.blockedByClient}">
                            <span class="contract-active pull-right">Активен </span>
                        </c:if>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#phone" data-toggle="tab">Номер</a></li>
                    <li class=""><a href="#tariff" data-toggle="tab">Тариф</a></li>
                    <li class=""><a href="#options_tab" data-toggle="tab">Опции</a></li>
                    <li class="pull-right">
                        <button type="submit"  form="form-save-contract" class="btn btn-success" id="btn-save-contract">Сохранить</button>
                        <c:if test="${!requestScope.contract.blockedByAdmin}">
                            <button type="submit" form="form-block-contract" class="btn btn-danger" id="btn-block">Заблокировать</button>
                        </c:if>
                        <c:if test="${requestScope.contract.blockedByAdmin}">
                            <button type="submit" form="form-unblock-contract" class="btn btn-warning" id="btn-unblock">Разблокировать</button>
                        </c:if>
                        <button type="submit" form="form-client-profile" class="btn btn-info" id="btn-client-profile">Профиль клиента</button>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-lg-4 top-buffer">
                <form role="form" action="/pages/admin/contract/ChangeContract" id="form-save-contract" method="post">

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                                <div class="form-group">
                                    <label for="name">Имя</label>
                                    <input type="text" class="form-control" disabled id="name" name="name" value="${requestScope.contract.user.name}">
                                </div>
                                <div class="form-group">
                                    <label for="surname">Фамилия</label>
                                    <input type="text" class="form-control" id="surname" disabled name="surname" value="${requestScope.contract.user.surname}">
                                </div>
                                <div class="form-group">
                                    <label for="date">Дата рождения</label>
                                    <input type="date" class="form-control" id="date" name="date" disabled value="${requestScope.contract.user.birthday}">
                                </div>
                                <div class="form-group">
                                    <label for="passport">Паспортные данные</label>
                                    <input type="text" class="form-control" id="passport" name="passport" disabled value="${requestScope.contract.user.passport}" >
                                </div>
                                <div class="form-group">
                                    <label for="address">Адрес</label>
                                    <input type="text" class="form-control" id="address" name="address" disabled value="${requestScope.contract.user.address}">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" placeholder="Введите email" name="email" disabled value="${requestScope.contract.user.email}">
                                </div>
                        </div>

                        <div class="tab-pane fade" id="phone">
                            <div class="form-group">
                                <label for="phone_number">Номер телефона</label>
                                <input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Введите номер" disabled value="${requestScope.contract.number}">
                            </div>
                        </div>

                        <div class="tab-pane fade" id="tariff">
                            <div class="form-group">
                                <label for="tariff_select">Выберите тариф:</label>
                                <select class="form-control"  id="tariff_select"
                                        <c:if test="${requestScope.contract.blockedByAdmin ||
                                                          requestScope.contract.blockedByClient}">disabled</c:if>
                                        name="tariff_id">
                                    <c:forEach items="${requestScope.tariffs}" var="tariff">
                                        <option value="${tariff.id}" class="tariff_select_opt"
                                                <c:set var="pos_options" value="" scope="page"/>
                                        <c:forEach items="${tariff.options}" var="possible_option">
                                            <c:set var="pos_options" value="${pos_options} ${possible_option.id}"/>
                                        </c:forEach>
                                                data-options="${pos_options}"
                                                <c:if test="${tariff.id eq requestScope.contract.tariff.id}">
                                                    selected
                                                </c:if>
                                        >
                                            <c:out value="${tariff.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="options_tab">
                            <div class="form-group">
                                <c:if test="${not empty requestScope.options}">
                                    <label>Выберите опции:</label>
                                    <div id="text-empty-option" class="hidden">
                                        Для данного тарифа нет доступных опций
                                    </div>
                                    <div class="option_chb">
                                    <c:forEach var="option" items="${requestScope.options}">
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="checkbox"
                                            <c:forEach var="selected_option" items="${requestScope.contract.options}">
                                                          <c:if test="${option.id eq selected_option.id}">checked</c:if>
                                                          <c:if test="${requestScope.contract.blockedByAdmin ||
                                                          requestScope.contract.blockedByClient}">disabled</c:if>
                                            </c:forEach>
                                                          name="options" value="${option.id}">${option.name}</label>
                                        </div>
                                    </c:forEach>
                                    </div>
                                </c:if>
                                <c:if test="${empty requestScope.options}">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body"><c:out value="Нет опций, доступных для данного тарифа"/></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                        </div>

                </form>
                </div>
                <form action="/pages/admin/contract/ChangeContract" id="form-block-contract" method="post">
                    <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                    <input type="hidden" name="block" value="block">
                </form>
                <form action="/pages/admin/contract/ChangeContract" id="form-unblock-contract" method="post">
                    <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                    <input type="hidden" name="block" value="unblock">
                </form>
                <form action="/pages/admin/client/FindClientProfile" id="form-client-profile">
                    <input type="hidden" name="user_id" value="${requestScope.contract.user.id}">
                </form>
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
    $(document).ready(function () {
        $('#tariff_select').change(function () {
            $('.checkbox').prop('checked', false);
            var $pos_options = $("#tariff_select option:selected").data("options");
            var $options_arr = $pos_options.split(' ');
            if ($options_arr.length == 1){
                $('#text-empty-option').removeClass("hidden");
            } else {
                $('#text-empty-option').addClass("hidden");
            }
            $('.option_chb input:checkbox').each(function(i){
                if ($.inArray($(this).val(), $options_arr) !== -1){
                    $(this).parent().removeClass("hidden");
                } else {
                    $(this).parent().addClass("hidden");
                }
            })
        });
    });
</script>


<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>