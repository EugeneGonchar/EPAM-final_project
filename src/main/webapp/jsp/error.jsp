<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.404.message" var="message" />
<fmt:message bundle="${loc}" key="local.header.contact" var="contact" />
<fmt:message bundle="${loc}" key="local.header.home_button" var="home_button" />

<html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en"> <!--<![endif]-->

<head>
    <meta charset="utf-8" />
    <title>Light 404 Page Non Found | BootstrapTema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!-- Template CSS -->
    <link type="text/css" media="all" href="../css/error/style.css" rel="stylesheet" />
    <!-- Responsive CSS -->
    <link type="text/css" media="all" href="../css/error/respons.css" rel="stylesheet" />
    <link rel="shortcut icon" href="../img/favicon.ico" />
    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Raleway:300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>

</head>
<body>

    <!-- Load page -->
    <div class="animationload">
        <div class="loader">
        </div>
    </div>
    <!-- End load page -->

    <!-- Content Wrapper -->
    <div id="wrapper">
        <div class="container">
            <!-- Switcher -->
            <div class="switcher">
                <input id="sw" type="checkbox" class="switcher-value">
                <label for="sw" class="sw_btn"></label>
                <div class="bg"></div>
                <div class="text"><span class="text-l">On</span><span class="text-d">Off</span><br />light</div>
            </div>
            <!-- End Switcher -->

            <!-- Dark version -->
            <div id="dark" class="row text-center">
                <div class="info">
                    <img src="../img/404.png" alt="404 error" />
                </div>
            </div>
            <!-- End Dark version -->

            <!-- Light version -->
            <div id="light" class="row text-center">
                <!-- Info -->
                <div class="info">
                    <img src="../img/404.gif" alt="404 error" />
                    <h4>${message}</h4>
                    <a href="${pageContext.request.contextPath}/main" class="btn">${home_button}</a>
                    <a href="#" class="btn btn-brown">${contact}</a>
                </div>
                <!-- end Info -->
            </div>
            <!-- End Light version -->

        </div>
        <!-- end container -->
    </div>
    <!-- end Content Wrapper -->


    <!-- Scripts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/error/modernizr.custom.js" type="text/javascript"></script>
    <script src="../js/error/jquery.nicescroll.min.js" type="text/javascript"></script>
    <script src="../js/error/scripts.js" type="text/javascript"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</body>
</html>