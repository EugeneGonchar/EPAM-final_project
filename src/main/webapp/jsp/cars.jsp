<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.order.menu.date_and_place" var="date_and_place" />
<fmt:message bundle="${loc}" key="local.order.menu.cars_search" var="cars_search" />
<fmt:message bundle="${loc}" key="local.order.menu.driver" var="driver" />
<fmt:message bundle="${loc}" key="local.order.menu.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.order.menu.confirmation" var="confirmation" />
<fmt:message bundle="${loc}" key="local.car.seats_message" var="seats_message" />
<fmt:message bundle="${loc}" key="local.car.doors_message" var="doors_message" />
<fmt:message bundle="${loc}" key="local.car.air_conditioning_message" var="air_conditioning_message" />
<fmt:message bundle="${loc}" key="local.car.automatic_gearbox_message" var="automatic_gearbox_message" />
<fmt:message bundle="${loc}" key="local.car.fuel_consumption_message" var="fuel_consumption_message" />
<fmt:message bundle="${loc}" key="local.car.engine_type_message" var="engine_type_message" />
<fmt:message bundle="${loc}" key="local.car.year_of_issue_message" var="year_of_issue_message" />
<fmt:message bundle="${loc}" key="local.order.cars.price_for" var="price_for" />
<fmt:message bundle="${loc}" key="local.order.cars.days" var="days" />
<fmt:message bundle="${loc}" key="local.order.cars.book_now_button" var="book_now_button" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Cars</title>

</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-3 px-0">
    <nav class="nav row border rounded text-center">
        <a class="nav-item nav-link col border-right " href="${pageContext.request.contextPath}/controller?command=get_locations">${date_and_place}<i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right bg-light" href="${pageContext.request.contextPath}/controller?command=get_cars&elementsOnPage=10&page=1">${cars_search}</a>
        <a class="nav-item nav-link col border-right disabled" href="#">${driver}</a>
        <a class="nav-item nav-link col border-right disabled" href="#">${payment}</a>
        <a class="nav-item nav-link col disabled" href="#">${confirmation}</a>
    </nav>
</div>

<div class="container my-3">
    <div class="col-3 float-left pr-3" role="navigation">

        <div class="border rounded mb-3">
            <nav class="menu">
                <ul class="navbar-nav">
                        <span class="border-bottom bg-light">
                            <li class="pl-3 nav-item text-dark">
                                <a class="nav-link disabled text-secondary" href="#">Header</a>
                            </li>
                        </span>
                    <span class="border-bottom active">
                            <li class="pl-3 nav-item active">
                                <a class="nav-link text-dark" href="#">Text</a>
                            </li>
                        </span>
                    <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="#">Text</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="#">Text</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>

        <div id="accordion" class="border rounded">
            <div class="card border-0 border-bottom">
                <div class="card-header py-1" id="headingOne">
                    <h6 class="mb-0">
                        <div class="collapsed dropdown-toggle" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            Разворачиваемая панель #1
                        </div>
                    </h6>
                </div>

                <div id="collapseOne" class="collapse <%--show--%>" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>

            <div class="card border-0 border-bottom">
                <div class="card-header py-1" id="headingTwo">
                    <h6 class="mb-0">
                        <div class="collapsed dropdown-toggle" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            Разворачиваемая панель #2
                        </div>
                    </h6>
                </div>

                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>

            <div class="card border-0">
                <div class="card-header py-1" id="headingThree">
                    <h6 class="mb-0">
                        <div class="collapsed dropdown-toggle" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            Разворачиваемая панель #3
                        </div>
                    </h6>
                </div>

                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:set var="command" scope="request" value="get_cars"/>
    <%@ include file = "/jsp/pagination/items_on_page.jsp" %>

    <div class="col-9 float-left">

        <c:forEach var="car" items="${carList}" varStatus="status">
            <div class="card col-auto my-3">
                <div class="row border-bottom">
                    <div class="col-4 align-self-center">
                        <div class="text-center">
                            <c:choose>
                                <c:when test="${empty car.image}">
                                    <img width="190" height="130" class="rounded d-block" alt="" src="../img/uploads/car/default.png">
                                </c:when>
                                <c:otherwise>
                                    <img width="190" height="130" class="rounded d-block" alt="" src="../img/uploads/car/${car.image}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>

                    <div class="p-0 col-5">
                        <div class="mb-0 p-0 col">
                            <div class="my-1 border-bottom">
                                <h4>${car.brand} ${car.model}</h4>
                            </div>
                            <div class="container mx-0 my-3">
                                <div class="row text-left">
                                    <div class="col-6 px-0 pl-3">
                                        <h6 class="car_element" class="mb-1">${seats_message}: ${car.seats} </h6>
                                    </div>
                                    <div class="col-6 px-0 pl-3">
                                        <h6 class="car_element" class="mb-1">${doors_message}: ${car.doors}</h6>
                                    </div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-6 px-0 pl-3">
                                        <h6 class="car_element" class="mb-1">
                                                ${air_conditioning_message}:
                                                <c:choose>
                                                    <c:when test="${car.airConditioning eq true}">
                                                        <i class="fa fa-check" style="color: #34ce57"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fa fa-minus" style="color: red"></i>
                                                    </c:otherwise>
                                                </c:choose>
                                        </h6>
                                    </div>
                                    <div class="col-6 px-0 pl-3">
                                        <h6 class="car_element" class="mb-1">
                                                ${automatic_gearbox_message}:
                                                <c:choose>
                                                    <c:when test="${car.automaticGearbox eq true}">
                                                        <i class="fa fa-check" style="color: #34ce57"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fa fa-minus" style="color: red"></i>
                                                    </c:otherwise>
                                                </c:choose>
                                        </h6>
                                    </div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-6 px-0 pl-3">
                                        <h6 class="car_element" class="mb-1">${fuel_consumption_message}: ${car.fuelConsumption} L/100</h6>
                                    </div>
                                    <div class="col-6 px-0 pl-3">
                                        <h6 class="car_element" class="mb-1">${engine_type_message}: ${car.engineType}</h6>
                                    </div>
                                </div>
                                <div class="my-2">
                                    <h5 class="font-weight-bold">${car.carClass}</h5>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-3 float-right pl-0">
                        <div class="text-right m-2">
                            <h6 class="car_element" class="mb-1">${price_for}${rentDays} ${days}:</h6>
                            <h3>${car.rental4Day * rentDays} $</h3>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-end">
                    <div class="col-3 m-3">
                        <a href="${pageContext.request.contextPath}/controller?command=go_to_driver_details&carId=${car.id}" type="button" class="btn btn-block btn-success">
                            ${book_now_button}
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>

        <%@ include file = "/jsp/pagination/pagination.jsp" %>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>
