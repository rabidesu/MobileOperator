<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title><spring:message code="lbl.user.account"/></title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="/css/materialize.min.css" type="text/css" rel="stylesheet"/>
    <%--<link href="/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>--%>
</head>
<body>
<%--<nav class="light-blue lighten-1" role="navigation">--%>
<%--<div class="nav-wrapper container"><a id="logo-container" href="#" class="brand-logo">Logo</a>--%>
<%--<ul class="right hide-on-med-and-down">--%>
<%--<li><a href="#">Navbar Link</a></li>--%>
<%--</ul>--%>

<%--<ul id="nav-mobile" class="side-nav">--%>
<%--<li><a href="#">Navbar Link</a></li>--%>
<%--</ul>--%>
<%--<a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>--%>
<%--</div>--%>
<%--</nav>--%>
<jsp:include page="/WEB-INF/jspf/clientNavigation.jsp" />

<div class="container">
    <h3 class="header center"><spring:message code="lbl.contract"/>: ${contract.number}</h3>
    <br>

    <div class="row">

        <div class="col s8 offset-s2">
            <div class="card-panel">
                <div class="row">
                    <form:form class="col s12" action="/pages/changeContractByClient" method="post" modelAttribute="contract" id="form-change-contract">
                        <c:if test="${contract.blockedByAdmin}">
                            <div id="tariff-not-available" class="card-panel red lighten-3">
                                Контракт заблокирован администратором
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${contract.blockedByClient && !contract.blockedByAdmin}">
                            <div id="tariff-not-available" class="card-panel red lighten-3">
                                Контракт неактивен и недоступен для изменений. Активируйте его, чтобы изменить тариф и опции.
                            </div>
                            <br>
                        </c:if>
                        <c:if test="${!contract.tariff.available}">
                            <div id="tariff-not-available" class="card-panel red lighten-3">
                                Ваш тариф "${contract.tariff.name}" больше недоступен для подключения.
                                Если вы измените его, то не сможете к нему вернуться.
                            </div>
                            <br>
                        </c:if>
                        <div class="section">
                                <h5><spring:message code="lbl.select.tariff"/></h5>
                                <div class="input-field col s12">
                                    <form:select path="tariff" items="${tariffs}" disabled="${contract.blockedByClient || contract.blockedByAdmin}"
                                                 itemLabel="name"
                                    itemValue="id" />
                                    <form:errors path="tariff" cssClass="card-panel red lighten-3"/>
                                </div>
                        </div>
                        <br>
                        <div class="section">
                            <br>
                            <h5><spring:message code="lbl.tariff.description"/></h5>
                            <p id="tariff_description">${contract.tariff.description}</p>
                        </div>
                        <div class="section">
                            <h5><spring:message code="lbl.select.options"/></h5>
                            <br>
                            <div id="old-options">
                                <div class="col s4 offset-s3">
                                    Подключенные<br><br>
                                    <c:forEach items="${contract.options}" var="option" varStatus="count">
                                        ${option.name}
                                        <div class="switch">
                                            <label>
                                                Off
                                                <input value="${option.id}"
                                                <c:if test="${contract.blockedByClient || contract.blockedByAdmin}">
                                                       disabled
                                                </c:if>
                                                       type="checkbox" name="options" checked>
                                                <span class="lever"></span>
                                                On
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col s4">
                                    Доступные<br><br>
                                    <c:forEach items="${notSelectedOptions}" var="option" varStatus="count">
                                        ${option.name}
                                        <div class="switch">
                                            <label>
                                                Off
                                                <input value="${option.id}" type="checkbox"
                                                <c:if test="${contract.blockedByClient || contract.blockedByAdmin}">
                                                       disabled
                                                </c:if>
                                                       name="options">
                                                <span class="lever"></span>
                                                On
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div id="new-options" hidden>
                                <div id="selected-opt" class="col s4 offset-s3">
                                    Подключенные<br><br>
                                    <c:forEach items="${contract.options}" var="option" varStatus="count">
                                        ${option.name}
                                        <div class="switch" id="tool_${option.id}">
                                            <label>
                                                Off
                                                <input value="${option.id}" type="checkbox" class="selected-options" name="options" checked id="selected${option.id}">
                                                <span class="lever"></span>
                                                On
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="col s4">
                                    Доступные<br><br>
                                    <div id="possible-opt">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="contract_id" value="${contract.id}">

                    </form:form>
                </div>
                <div class="row">
                    </div>
            </div>
        </div>
    </div>
    <form method="post" action="/pages/unblockContractByClient" id="form-unblock-contract">
        <input type="hidden" name="contract_id" value="${contract.id}">
        </form>
    <form method="post" action="/pages/blockContractByClient" id="form-block-contract">
        <input type="hidden" name="contract_id" value="${contract.id}">
    </form>
    <div class="row center">
        <c:if test="${!contract.blockedByAdmin && !contract.blockedByClient}">
            <button type="submit" form="form-change-contract" class="btn-large waves-effect waves-light orange">
            Изменить</button>
        </c:if>
        <c:if test="${!contract.blockedByAdmin && contract.blockedByClient}">
            <button type="submit" form="form-unblock-contract" class="btn-large waves-effect waves-light orange">
            Разблокировать</button>
        </c:if>
        <c:if test="${!contract.blockedByAdmin && !contract.blockedByClient}">
            <button type="submit" form="form-block-contract" class="btn-large waves-effect waves-light orange">
                Заблокировать</button>
        </c:if>
    </div>

    <br><br>
</div>


<footer class="page-footer orange">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Company Bio</h5>
                <p class="grey-text text-lighten-4">We are a team of college students working on this project like it's our full time job. Any amount would help support and continue development on this project and is greatly appreciated.</p>


            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Settings</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Connect</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by <a class="orange-text text-lighten-3" href="http://materializecss.com">Materialize</a>
        </div>
    </div>
</footer>


<!--  Scripts-->
<script src="/js/jquery-2.2.0.min.js"></script>
<script src="/js/materialize.min.js"></script>
<%--<script src="/js/init.js"></script>--%>
<script>
    $(document).ready(function() {
        $('select').material_select();
        $('#tariff').on('change', doAjaxGetOption);
        var contract_tariff_id = ${contract.tariff.id};

        function doAjaxGetOption() {

            var selected_tariff = $('#tariff option:selected').val();
            $('#tariff_description').empty();
            $.ajax({
                url: '/pages/getTariff',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    tariffId: selected_tariff
                }),
                success: function (data) {
                    $('#tariff_description').html(data.description);
                    var arrayOptionsIds = [];
                    var arraySelectedOptionsIds = [];
                    if (data.id == contract_tariff_id) {
                        $('#new-options').hide();
                        $('#old-options').show(100);
                    } else {
                        $('#old-options').hide();
                        $('#new-options').show(100);
                        data.options.forEach(function (elem, index, array) {
                            arrayOptionsIds.push("" + elem.id);
                        });
                            $('.selected-options').each(function(){
                                var selectedOptId = this.id.replace("selected", "");
                                arraySelectedOptionsIds.push(selectedOptId);
                                if ($.inArray(selectedOptId, arrayOptionsIds) !== -1){
                                    $(this).attr("disabled", false).prop("checked", true);
                                    $('#tool_'+selectedOptId).removeClass("tooltipped").removeAttr("data-tooltip")
                                            .removeAttr("data-position").removeAttr("data-delay").removeAttr("data-tooltip-id");
                                    $('#tool_'+selectedOptId).off('mouseenter.tooltip mouseleave.tooltip');
                                } else {
                                    $(this).attr("disabled", true).prop("checked", false);
                                    $('#tool_'+selectedOptId).attr("data-tooltip", "This option not possible in this tariff")
                                            .attr("data-position", "left").attr("data-delay","50").addClass("tooltipped");
                                }
                            });

                        var result = "";
                        data.options.forEach(function (elem, index, array) {
                            if ($.inArray("" + elem.id, arraySelectedOptionsIds) == -1){
                                result += elem.name;
                                result += '<div class="switch"> <label> Off' +
                                        '<input value="' + elem.id + '" type="checkbox" name="options">' +
                                        '<span class="lever"></span>On</label> </div>';
                            }
                        });
                        $('#possible-opt').html(result);
                        $('.tooltipped').tooltip({delay: 50});
                    }




                }
            });
        }

//        function doAjaxGetNotSelectedOptions() {
//
//            var selected_tariff = $('#tariff option:selected').val();
//            $('#tariff_description').empty();
//            $.ajax({
//                url: '/pages/getTariff',
//                type: 'GET',
//                dataType: 'json',
//                contentType: 'application/json',
//                mimeType: 'application/json',
//                data: ({
//                    tariffId: selected_tariff
//                }),
//                success: function (data) {
//                    if (data.id == contract_tariff_id) {
//                        $('#old-options').show();
//                        $('#show-options').empty();
//                    } else {
//                        $('#old-options').hide();
//                        $('#tariff_description').html(data.description);
//                        var result = '<div class="col s4 offset-s3">';
//                        data.options.forEach(function (elem, index, array) {
//                            if (index < array.length / 2) {
//                                result += elem.name;
//                                result += '<div class="switch"> <label> Off' +
//                                        '<input value="' + data.id + '" type="checkbox" name="options">' +
//                                        '<span class="lever"></span>On</label> </div>';
//                            }
//                        });
//                        result += '</div>';
//                        result += '<div class="col s4">';
//                        data.options.forEach(function (elem, index, array) {
//                            if (index >= array.length / 2) {
//                                result += elem.name;
//                                result += '<div class="switch"> <label> Off' +
//                                        '<input value="' + data.id + '" type="checkbox" name="options">' +
//                                        '<span class="lever"></span>On</label> </div>';
//                            }
//                        });
//                        result += '</div>';
//                        $('#show-options').html(result);
//                    }
//                }
//            });
//        }
    });
</script>

</body>
</html>

