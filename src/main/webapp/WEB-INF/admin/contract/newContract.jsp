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
    <link href="/css/bootstrap-formhelpers.min.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- Custom CSS -->
    <link href="/css/sb-admin.css" rel="stylesheet">


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
                        Новый контракт
                    </h1>
                </div>
            </div>

            <!-- Main -->
            <div class="row">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#general" data-toggle="tab">Основные</a></li>
                    <li class=""><a href="#phone" data-toggle="tab">Номер</a></li>
                    <li class=""><a href="#tariff" data-toggle="tab">Тариф</a></li>
                    <li class=""><a href="#options_tab" data-toggle="tab">Опции</a></li>
                    <li class="pull-right">
                        <button type="submit" form="form-new-contract" class="btn btn-success">Добавить</button>
                    </li>
                </ul>
            </div>

            <div class="row">

                <div class="col-lg-4 top-buffer">

                <form:form id="form-new-contract" action="/pages/saveContract" modelAttribute="contract">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="general">

                            <div class="form-group">
                                <label for="user.email">Email</label>
                                <form:input class="form-control" path="user.email"/>
                                <form:errors path="user.email" cssClass="red"/>
                                    <%--<input type="email" class="form-control" id="email" placeholder="Введите email" name="email">--%>
                            </div>
                                <div class="form-group">
                                    <label for="user.name">Имя</label>
                                    <form:input class="form-control" path="user.name"/>
                                    <form:errors path="user.name" cssClass="help-inline"/>
                                    <%--<input type="text" class="form-control" id="name" name="name" placeholder="Введите имя">--%>
                                </div>
                                <div class="form-group">
                                    <label for="user.surname">Фамилия</label>
                                    <form:input class="form-control" path="user.surname"/>
                                    <form:errors path="user.surname" cssClass="red"/>
                                    <%--<input type="text" class="form-control" id="surname" name="surname" placeholder="Введите фамилию">--%>
                                </div>
                                <div class="form-group">
                                    <label for="user.birthday">Дата рождения</label>
                                    <%--<input type="date" class="form-control" id="user.birthday" name="user.birthday">--%>
                                    <form:input type="date" class="form-control" path="user.birthday"/>
                                    <form:errors path="user.birthday" cssClass="red"/>
                                    <%--<input type="date" class="form-control" id="date" name="date">--%>
                                </div>
                                <div class="form-group">
                                    <label for="user.passport">Паспортные данные</label>
                                    <form:input class="form-control" path="user.passport"/>
                                    <form:errors path="user.passport" cssClass="red"/>
                                    <%--<input type="text" class="form-control" id="passport" name="passport" >--%>
                                </div>
                                <div class="form-group">
                                    <label for="user.address">Адрес</label>
                                    <form:input class="form-control" path="user.address"/>
                                    <form:errors path="user.address" cssClass="red"/>
                                    <%--<input type="text" class="form-control" id="address" name="address">--%>
                                </div>
                                <div class="form-group">
                                    <label for="user.password">Пароль</label>
                                    <form:password class="form-control" path="user.password"/>
                                    <form:errors path="user.password" cssClass="red"/>
                                    <%--<input type="password" class="form-control" id="password" placeholder="Введите пароль" name="password">--%>
                                </div>

                        </div>

                        <div class="tab-pane fade" id="phone">
                            <div class="form-group">
                                <label for="number">Номер телефона</label>
                                <%--<input type="text" class="input-medium bfh-phone form-control" data-format="+7 (ddd) ddd-dd-dd" name="number" id="number">--%>
                                <form:input class="input-medium bfh-phone form-control" data-format="+7 (ddd) ddd-dd-dd" path="number"/>
                                <form:errors path="number" cssClass="red"/>
                                <%--<input type="text" class="form-control" id="phone_number" name="phone_number" placeholder="Введите номер">--%>
                            </div>

                        </div>

                        <div class="tab-pane fade" id="tariff">
                            <div class="form-group">
                                <label for="tariff">Выберите тариф:</label>
                                <form:select path="tariff" items="${tariffs}" itemValue="id" itemLabel="name" class="form-control" />
                            </div>
                        </div>
                        <div class="tab-pane fade" id="options_tab">
                            <div class="form-group">
                                <%--<c:if test="${not empty requestScope.options}">--%>
                                <label>Выберите опции:</label>
                                <div id="text-empty-option">
                                    Для данного тарифа нет доступных опций
                                </div>
                                    <div id="option_chb">
                                        <%--<form:checkboxes path="options" items="${options}" itemValue="id" itemLabel="name" element="div"/>--%>
                                    </div>
                                <%--</c:if>--%>
                                <%--<c:if test="${empty requestScope.options}">--%>
                                    <%--<div class="panel panel-warning">--%>
                                        <%--<div class="panel-heading">Информация</div>--%>
                                        <%--<div class="panel-body"><c:out value="Нет опций, доступных для данного тарифа"/></div>--%>
                                    <%--</div>--%>
                                <%--</c:if>--%>
                            </div>
                        </div>
                        <form:errors path="options" cssClass="red"/>
                        <%--<jsp:include page="/WEB-INF/jspf/tariffOptionForContract.jsp" />--%>
                    </div>
                </form:form>

                </div>
                <div id="result-user-exist" class="col-lg-6 text-center top-buffer" hidden>
                    <div class="alert alert-info">
                        Такой пользователь уже существует. Вы хотите добавить для него новый контракт?
                        <br><br>
                        <form id="form-contract-for-user" action="/pages/newContractForUser" method="post">
                        <input type="hidden" id="user_id" name="user_id">
                        </form>
                        <i id="btn-get-user" class='fa fa-check fa-3x fa-btn'></i>
                        <i id="btn-dismiss" class="fa fa-remove fa-3x fa-btn"></i>
                    </div>
                </div>
                <div id="result-user-not-exists" class="col-lg-6 text-center top-buffer" hidden>
                    <div class="alert alert-info" >
                        E-mail свободен
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
<script src="/js/bootstrap-formhelpers-phone.js"></script>
<script src="/js/new-contract.js"></script>
<script type="text/javascript">

    $(document).ready(function f() {
        $('#tariff').on('change', doAjaxGetOption).change();
        $('#user\\.email').on('blur', doAjaxExistsUser);
        $('#btn-get-user').on('click', getUser);
        $('#btn-dismiss').on('click', resultDismiss);


    function doAjaxGetOption() {

        var selected_tariff = $('#tariff option:selected').val();
        $('#option_chb').empty();
        $.ajax({
            url: '/pages/getOptionsForTariff',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: ({
                tariffId: selected_tariff
            }),
            success: function (data) {

                if (data.length == 0){
                    $('#text-empty-option').show();
                } else {
                    $('#text-empty-option').hide();
                }
                data.forEach(function(elem, index, array){
                    $('#option_chb').append("<div class='checkbox'><label><input type='checkbox' name='options' id='option'" + elem.id
                            + " value=" + elem.id + ">" + elem.name + "</label></div>")
                });

            }
        });
    }

    function doAjaxExistsUser() {
        var email = $('#user\\.email').val();
        $.ajax({
            url: '/pages/checkEmailExists',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data: ({
                email: email
            }),
            success: function (data) {
                var result;
                if (data) {
                    $('#result-user-not-exists').hide();
                    $('#result-user-exist').show();
//                    result = "Такой пользователь уже существует. Вы хотите добавить для него новый контракт? " +
//                                    "<br><br><i id='btn-get-user' class='fa fa-check fa-3x'></i>  " +
//                            "<i id='btn-dismiss' class='fa fa-remove fa-3x'></i>";
//                    $('#user-exists-result').html(result).show();
                } else {
                    $('#result-user-exist').hide();
                    $('#result-user-not-exists').show();
//                    result = "E-mail свободен";
//                    $('#user-exists-result').text(result).show();
                }
            }
        });
    }
        function getUser() {
            resultDismiss();
            var email = $('#user\\.email').val();
            $.ajax({
                url: '/pages/getUser',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    email: email
                }),
                success: function (data) {
                    $('#user_id').val(data);
                    $('#form-contract-for-user').submit();
                }
            });
        }

        function resultDismiss() {
            $('#result-user-exist').hide();
            var email = $('#user\\.email').empty();
        }

    });


</script>


<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>


</body>

</html>