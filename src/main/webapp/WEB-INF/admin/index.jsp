<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="lbl.dashboard"/> </title>

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

    <div id="page-wrapper">

        <div class="container-fluid fill">

            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                       <spring:message code="lbl.dashboard"/>
                    </h1>
                </div>
            </div>
            <!-- /.row -->


            <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-shopping-cart fa-5x"></i>
                                </div>
                            </div>
                        </div>
                        <a href="/pages/admin/option/NewOption">
                            <div class="panel-footer">
                                <span class="pull-left">Новая опция</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                            </div>
                        </div>
                        <a href="/pages/admin/tariff/NewTariff">
                            <div class="panel-footer">
                                <span class="pull-left">Новый тариф</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-user fa-5x"></i>
                                </div>
                            </div>
                        </div>
                        <a href="/pages/admin/contract/NewContract">
                            <div class="panel-footer">
                                <span class="pull-left">Новый контракт</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-search fa-5x"></i>
                                </div>
                            </div>
                        </div>
                        <a href="/pages/admin/client/FindClient">
                            <div class="panel-footer">
                                <span class="pull-left">Поиск клиентов</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /.row -->
            <div align="center">
                <h3>Enter text:</h3>
                <input id="input_str" type="text">
                <input type="button" value="OK" onclick="doAjax()">
                <p id="result_text"></p>
            </div>

        </div>
        <!-- /.container-fluid -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="/js/jquery-2.2.0.min.js"></script>

<script type="text/javascript">

//    $(document).ready(function f() {
//        $('#but').on('click', doAjax);
//
//    });
    function doAjax() {

        var inputText = $("#input_str").val();

        $.ajax({
            url : '/pages/getCharNum',
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            data : ({
                text: inputText
            }),
            success: function (data) {

                var result = data.name;
                $("#result_text").text(result);
            }
        });
    }
</script>

<%--<script type="text/javascript">--%>
    <%--var intervalId = 0;--%>
    <%--intervalId = setInterval(crunchifyAjax, 3000);--%>
<%--</script>--%>

<!-- Bootstrap Core JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>

</html>
