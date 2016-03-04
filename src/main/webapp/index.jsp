<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Главная страница</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/carousel.css" rel="stylesheet">
</head>
<!-- NAVBAR
================================================== -->
<body>
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/index.jsp">eCare Project</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav pull-right">
                        <li><a href="/pages/client/index.jsp">Личный кабинет</a></li>
                        <li><a href="/pages/admin/index.jsp">Управление</a></li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
</div>


<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>

    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="/images/purple.jpg" alt="First slide">
        </div>
    </div>
</div><!-- /.carousel -->


<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->

<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="col-lg-4">
            <img class="img-circle" src="/images/conract.jpg" width="140" height="140">
            <h2>Контракт</h2>
            <p>Быстрое заключение контракта любым менеджером. Вы можете иметь много контрактов!</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="img-circle" src="/images/tariff.png" width="140" height="140">
            <h2>Тарифы</h2>
            <p>Выберите один из множества выгодных доступных тарифов!</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
            <img class="img-circle" src="/images/option.png" width="140" height="140">
            <h2>Опции</h2>
            <p>Подключите множество доступных и выгодных опций</p>
        </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading">Функция SIM-карты <span class="text-muted">— хранение информации.</span></h2>
            <p class="lead">  об аккаунте, что позволяет абоненту легко и быстро менять сотовые аппараты,
                не меняя при этом свой аккаунт, а просто переставив свою SIM-карту в другой телефон.
                Для этого SIM-карта включает в себя микропроцессор с ПО и данные с ключами идентификации карты (IMSI, Ki и т. д.),
                записываемые в карту на этапе её производства, используемые на этапе идентификации карты (и абонента) сетью GSM.
            </p>
        </div>
        <div class="col-md-5">
            <img class="featurette-image img-responsive center-block" src="/images/simcard.png" alt="Generic placeholder image">
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
            <h2 class="featurette-heading">Сотовая сеть <span class="text-muted">— это сотовые телефоны</span></h2>
            <p class="lead">  которые обычно располагают на крышах зданий и вышках. Будучи включённым,
                сотовый телефон прослушивает эфир, находя сигнал базовой станции.
                После этого телефон посылает станции свой уникальный идентификационный код.
            </p>
        </div>
        <div class="col-md-5 col-md-pull-7">
            <img class="featurette-image img-responsive center-block" src="/images/sotovaja-svjaz1.jpg" alt="Generic placeholder image">
        </div>
    </div>

    <hr class="featurette-divider">


    <!-- /END THE FEATURETTES -->


    <!-- FOOTER -->
    <footer>
        <p>&copy; 2015 Company eCare, Inc. &middot;
    </footer>

</div><!-- /.container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/js/jquery-2.2.0.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="/js/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
</body>
