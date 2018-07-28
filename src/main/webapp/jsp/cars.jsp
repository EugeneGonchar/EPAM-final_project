<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Cars</title>

</head>
<body>

<div class="container my-3 px-0">
    <nav class="nav row border rounded text-center">
        <a class="nav-item nav-link col border-right " href="/controller?command=get_locations">Choice of date and place <i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right bg-light" href="/controller?command=get_cars">Car's search results</a>
        <a class="nav-item nav-link col border-right disabled" href="#">Driver</a>
        <a class="nav-item nav-link col border-right disabled" href="#">Payment</a>
        <a class="nav-item nav-link col disabled" href="#">Confirmation</a>
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

    <div class="col-9 float-left">

        <c:forEach var="car" items="${carList}" varStatus="status">
            <div class="card col-auto my-3">
                <div class="row border-bottom">
                    <div class="col-4 align-self-center">
                        <div class="text-center">
                            <img width="190" height="130" class="rounded d-block" alt="" src="../img/sport_lrg.jpg">
                        </div>
                    </div>

                    <div class="p-0 col-5">
                        <div class="mb-0 p-0 col">
                            <div class="my-1 border-bottom">
                                <h4><c:out value="${car.brand} ${car.model}"></c:out></h4>
                            </div>
                            <div class="container mx-0">
                                <div class="row text-left">
                                    <div class="col-6">
                                        <h6 class="car_element" class="mb-1"><c:out value="${car.seats}"></c:out> Seats</h6>
                                    </div>
                                    <div class="col-6">
                                        <h6 class="car_element" class="mb-1"><c:out value="${car.doors}"></c:out> Doors</h6>
                                    </div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-6">
                                        <h6 class="car_element" class="mb-1"><c:out value="${car.airConditioning}"></c:out> Air conditioning</h6>
                                    </div>
                                    <div class="col-6">
                                        <h6 class="car_element" class="mb-1"><c:out value="${car.automaticGearbox}"></c:out> Automatic gearbox</h6>
                                    </div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-6">
                                        <h6 class="car_element" class="mb-1"><c:out value="${car.fuelConsumption}"></c:out> Fuel consumption</h6>
                                    </div>
                                    <div class="col-6">
                                        <h6 class="car_element" class="mb-1"><c:out value="${car.enginePower}"></c:out> Engine power</h6>
                                    </div>
                                </div>
                                <div class="my-2">
                                    <h5 class="font-weight-bold"><c:out value="${car.carClass}"></c:out></h5>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-3 float-right">
                        <div class="text-right m-2">
                            <h6 class="car_element" class="mb-1">Price for <c:out value="${rentDays}"></c:out> days:</h6>
                            <h3><c:out value="${car.rental4Day * rentDays}"></c:out> $</h3>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-end">
                    <div class="col-3 m-3">
                        <a href="/controller?command=go_to_driver_details&id=${car.id}" type="button" class="btn btn-block btn-success">
                            Book now
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>


</body>
</html>
