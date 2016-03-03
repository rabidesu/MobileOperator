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

    <title>Личный кабинет</title>

    <link href="/css/ecare-style.css" rel="stylesheet">
    <!-- Bootstrap Core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/bselect.min.css" rel="stylesheet">

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
                        Тариф: <c:out value="${requestScope.tariff.name} "/>
                    </h1>
                </div>
            </div>
            <!-- /.row -->

            <!-- Main -->
            <div class="row">
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#options" data-toggle="tab">Опции</a></li>
                    <li class=""><a href="#contracts" data-toggle="tab">Контракт</a></li>
                    <li class="pull-right">
                        <button type="submit"  class="btn btn-success" form="form-save-contract">Перейти</button>
                    </li>
                </ul>
            </div>

            <div class="row">
                <div class="col-lg-4 top-buffer">
                <form role="form" action="/pages/client/contract/ChangeContractByClient" id="form-save-contract" method="post">
                    <input type="hidden" name="tariff_id" value="${requestScope.tariff.id}">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="options">
                            <div class="form-group">
                                    <label>Выберите опции:</label>
                                        <div class="option_chb">
                                            <c:forEach var="option" items="${requestScope.tariff.options}">
                                                <div class="checkbox">
                                                    <label><input type="checkbox" name="options"
                                                        <c:set var="req_options" value="" scope="page"/>

                                                    <c:forEach var="req_option" items="${option.optionsRequired}">
                                                        <c:set var="req_options" value="${req_options} ${req_option.id}"/>
                                                    </c:forEach>
                                                                  data-req="${req_options}" value="${option.id}">${option.name}
                                                        <input name="options" type="hidden"  disabled id="hidden_${option.id}" value="${option.id}"/>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                <c:if test="${empty requestScope.tariff.options}">
                                    <div class="panel panel-warning">
                                        <div class="panel-heading">Информация</div>
                                        <div class="panel-body"><c:out value="Нет опций, доступных для данного тарифа"/></div>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                    <div class="tab-pane fade" id="contracts">
                        <div class="form-group">
                            <label>Выберите контракт для перехода:</label>
                            <c:forEach var="contract" items="${sessionScope.user.contracts}">
                                <select class="form-control" name="contract_id">
                                    <option value="${contract.id}"><c:out value="${contract.number}"/></option>
                                </select>
                            </c:forEach>
                        </div>
                    </div>
                </div>

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
    $(document).ready(function () {
        $('.option_chb input:checkbox').change(function () {
            var $req_options = $(this).data("req");
            var $options_arr = $req_options.split(' ');
            if (this.checked){
                $current_id = $(this).val();
                $('.option_chb input:checkbox').each(function(){
                    if ($.inArray($(this).val(), $options_arr) !== -1){
                        $(this).prop("checked", true);
                        $(this).prop("disabled", true);
                        $(this).addClass("by_"+$current_id);
                        $('#hidden_'+$(this).val()).prop("disabled", false);
                    }
                })
            } else {
                $current_id = $(this).val();
                $('.option_chb input:checkbox').each(function(){
                    if ($.inArray($(this).val(), $options_arr) !== -1){
                        $(this).removeClass("by_"+$current_id);
                        $className = $(this).attr('class').split(' ');
                        if ($className.length == 1) {
                            $(this).prop("disabled", false);
                            $('#hidden_'+$(this).val()).prop("disabled", true);
                        }
                    }
                })
            }
    });
    });
</script>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/bselect.min.js"></script>
<script src="/js/sb-admin-2.js"></script>

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script src="/js/plugins/morris/morris-data.js"></script>

</body>

</html>