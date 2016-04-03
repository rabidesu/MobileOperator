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
    <h3 class="header center"><spring:message code="lbl.tariff"/>: ${tariff.name}</h3>
    <br>

    <div class="row">

        <div class="col s8 offset-s2">
            <div class="card-panel">
                <div class="row">
                    <form:form class="col s12" action="/pages/saveTariffToContract" method="post" modelAttribute="contract" id="form-change-contract">
                        <div class="section">
                            <br>
                            <h5><spring:message code="lbl.tariff.description"/></h5>
                            <p id="tariff_description">${tariff.description}</p>
                            <br>
                        </div>
                        <div class="section">
                            <h5><spring:message code="lbl.select.contract"/></h5>
                            <div class="input-field col s12">
                                <select name="id" id="select-contract">
                                    <c:forEach items="${contracts}" var="contract">
                                        <c:if test="${!contract.blockedByAdmin && !contract.blockedByClient}">
                                            <option value="${contract.id}">${contract.number}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <label><spring:message code="lbl.contract"/></label>
                            </div>
                        </div>
                        <br>
                        <div class="section">
                            <h5><spring:message code="lbl.select.options"/></h5>
                            <br>
                            <div id="new-options">
                                <div class="col s4 offset-s3">
                                    Подключенные<br><br>
                                    <div id="selected-opt">

                                    </div>
                                </div>
                                <div class="col s4">
                                    Доступные<br><br>
                                    <div id="possible-opt">
                                    </div>
                                </div>
                            </div>
                        </div>
                    <input type="hidden" name="tariff" value="${tariff.id}">
                    </form:form>
                </div>
                <div class="row">
                </div>
            </div>
        </div>
    </div>
    <div class="row center">
        <button type="submit" form="form-change-contract" class="btn-large waves-effect waves-light orange">
            Изменить</button>
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
    var tariff_options_ids = [];
    var tariff_options_names = [];
    <c:forEach items="${tariff.options}" var="option">
    tariff_options_ids.push(${option.id});
    tariff_options_names.push("${option.name}");
    </c:forEach>

    $(document).ready(function() {
        $('select').material_select();
        $('#select-contract').on('change', doAjaxGetOption).change();

        function doAjaxGetOption() {

            var selected_contract = $('#select-contract option:selected').val();
            $.ajax({
                url: '/pages/getContractOptions',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data: ({
                    contractId: selected_contract
                }),
                success: function (data) {

                    var arrayOptionsIds = [];
                    data.forEach(function (elem, index, array) {
                        arrayOptionsIds.push(elem.id);
                    });

                    var resultSelected = "";
                    var resultPossible = "";
                    tariff_options_ids.forEach(function (elem, index, array) {
                        if ($.inArray(elem, arrayOptionsIds) !== -1){
                            resultSelected += tariff_options_names[index];
                            resultSelected += '<div class="switch"> <label> Off' +
                                    '<input value="' + elem + '" type="checkbox" checked name="options">' +
                                    '<span class="lever"></span>On</label> </div>';
                        } else {
                            resultPossible += tariff_options_names[index];
                            resultPossible += '<div class="switch"> <label> Off' +
                                    '<input value="' + elem + '" type="checkbox" name="options">' +
                                    '<span class="lever"></span>On</label> </div>';
                        }
                    });

                    $('#selected-opt').html(resultSelected);
                    $('#possible-opt').html(resultPossible);


                }
            });
        }

    });
</script>

</body>
</html>

