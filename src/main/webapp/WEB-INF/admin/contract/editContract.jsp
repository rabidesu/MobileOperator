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

    <link href="/css/bselect.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/css/sb-admin.css" rel="stylesheet">


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
                        Контракт: <c:out value="${contract.number} "/>
                        <c:if test="${contract.blockedByAdmin}">
                            <span class="contract-blocked pull-right">Заблокирован </span>
                        </c:if>
                        <c:if test="${contract.blockedByClient && !contract.blockedByAdmin}">
                            <span class="contract-inactive pull-right" >Неактивен </span>
                        </c:if>
                        <c:if test="${!contract.blockedByAdmin && !contract.blockedByClient}">
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
                        <c:if test="${!contract.blockedByAdmin}">
                            <button type="submit" form="form-block-contract" class="btn btn-danger" id="btn-block">Заблокировать</button>
                        </c:if>
                        <c:if test="${contract.blockedByAdmin}">
                            <button type="submit" form="form-unblock-contract" class="btn btn-warning" id="btn-unblock">Разблокировать</button>
                        </c:if>
                        <button type="submit" form="form-client-profile" class="btn btn-info" id="btn-client-profile">Профиль клиента</button>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-lg-4 top-buffer">
                <form:form action="/pages/changeContract" id="form-save-contract" method="post" modelAttribute="contract">

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">
                            <div class="form-group">
                                <label>E-mail</label>
                                <p class="form-control"><c:out value="${contract.user.email}"/></p>
                                    <%--<input type="email" class="form-control" id="email" placeholder="Введите email" name="email">--%>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <p class="form-control"><c:out value="${contract.user.name}"/></p>
                                    <%--<input type="text" class="form-control" id="name" name="name" placeholder="Введите имя">--%>
                            </div>
                            <div class="form-group">
                                <label>Фамилия</label>
                                <p class="form-control"><c:out value="${contract.user.surname}"/></p>
                                    <%--<input type="text" class="form-control" id="surname" name="surname" placeholder="Введите фамилию">--%>
                            </div>
                            <div class="form-group">
                                <label>Дата рождения</label>
                                    <%--<input type="date" class="form-control" id="user.birthday" name="user.birthday">--%>
                                <p class="form-control"><c:out value="${contract.user.birthday}"/></p>
                                    <%--<input type="date" class="form-control" id="date" name="date">--%>
                            </div>
                            <div class="form-group">
                                <label>Паспортные данные</label>
                                <p class="form-control"><c:out value="${contract.user.passport}"/></p>
                                    <%--<input type="text" class="form-control" id="passport" name="passport" >--%>
                            </div>
                            <div class="form-group">
                                <label>Адрес</label>
                                <p class="form-control"><c:out value="${contract.user.address}"/></p>
                                    <%--<input type="text" class="form-control" id="address" name="address">--%>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="phone">
                            <div class="form-group">
                                <label>Номер телефона</label>
                                <label>Адрес</label>
                                <p class="form-control"><c:out value="${contract.number}"/></p>
                                <%--<label for="phone_number">Номер телефона</label>--%>
                                <%--<input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Введите номер" disabled value="${requestScope.contract.number}">--%>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="tariff">
                            <div class="form-group">
                                <label for="tariff">Выберите тариф:</label>
                                <form:select path="tariff" items="${tariffs}" itemValue="id" itemLabel="name" class="form-control" />
                                <c:if test="${!contract.tariff.available}">
                                    <br>
                                    <div class="alert alert-danger" id="info-tariff">
                                        Данный тариф больше недоступен для подключения. Если вы измените его, то не сможете снова подключить.
                                    </div>
                                </c:if>

                            </div>
                        </div>
                        <div class="tab-pane fade" id="options_tab">
                            <div class="form-group">
                                    <label>Выберите опции:</label>
                                <div id="option_chb">
                                <c:forEach items="${contract.options}" var="option">
                                    <div class="checkbox <c:if test='${!option.available}'> text-danger</c:if>"
                                         <c:if test='${!option.available}'>data-toggle="tooltip"
                                         title="Если вы отключите эту опцию, то больше не сможете ее подключить" </c:if>>
                                        <label><input type="checkbox" name="options" value="${option.id}" checked>${option.name}</label>
                                    </div>
                                </c:forEach>
                                    <c:forEach items="${notSelectedOptions}" var="option">
                                    <div class="checkbox"><label><input type="checkbox" name="options" value="${option.id}">${option.name}</label></div>
                                    </c:forEach>
                                </div>
                                <div id="text-empty-option" class="hidden">
                                Для данного тарифа нет доступных опций
                            </div>
                            </div>
                        </div>
                        <input type="hidden" id="contract_id" name="contract_id" value="${contract.id}">
                        </div>

                </form:form>
                </div>
                <form action="/pages/blockContractByAdmin" id="form-block-contract" method="post">
                    <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                    <%--<input type="hidden" name="tariff_id" value="${requestScope.contract.tariff.id}">--%>
                    <%--<input type="hidden" name="block" value="block">--%>
                </form>
                <form action="/pages/unblockContractByAdmin" id="form-unblock-contract" method="post">
                    <input type="hidden" name="contract_id" value="${requestScope.contract.id}">
                    <%--<input type="hidden" name="tariff_id" value="${requestScope.contract.tariff.id}">--%>
                    <%--<input type="hidden" name="block" value="unblock">--%>
                </form>
                <form action="/pages/findClientProfile" id="form-client-profile">
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
<%--<c:if test="${!requestScope.contract.blockedByAdmin && !requestScope.contract.blockedByClient}">--%>
    <%--<script src="/js/edit-contract.js"></script>--%>
<%--</c:if>--%>
<script>
    $(document).ready(function f() {
        $('#tariff').on('change', doAjaxGetOption);

        function doAjaxGetOption() {

            var selected_tariff = $('#tariff option:selected').val();
            var contract_id = $('#contract_id').val();
            $('#option_chb').empty();
            $('#info-tariff').hide();
            $.ajax({
                url: '/pages/getOptionsForTariff',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    tariffId: selected_tariff,
                    contractId: contract_id
                }),
                success: function (data) {

                    if (data.length == 0) {
                        $('#text-empty-option').show();
                    } else {
                        $('#text-empty-option').hide();
                    }
                    data.forEach(function (elem, index, array) {
                        var result = "<div class='checkbox'><label><input type='checkbox' name='options' id='option'" + elem.id +
                        " value=" + elem.id + ">" + elem.name + "</label></div>";
                        $('#option_chb').append(result);
                        <%--$('#option_chb').append("<div class='checkbox'><label>" +--%>
                                <%--"<input type='checkbox' name='options' id='option'" + elem.id +--%>
                        <%--"<c:if test='${!elem.available}'>checked class='text-danger' data-toggle='tooltip' title='Если вы отключите эту опцию, она больше не будет доступна для подключения'</c:if>" +--%>
                                <%--+ " value=" + elem.id + ">"--%>
                                <%--+ elem.name + "</label></div>")--%>

                    });

                }
            });
        }
    });
</script>


<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>


</body>

</html>