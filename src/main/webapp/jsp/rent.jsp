<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.order.menu.date_and_place" var="date_and_place" />
<fmt:message bundle="${loc}" key="local.order.menu.cars_search" var="cars_search" />
<fmt:message bundle="${loc}" key="local.order.menu.driver" var="driver" />
<fmt:message bundle="${loc}" key="local.order.menu.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.order.menu.confirmation" var="confirmation" />
<fmt:message bundle="${loc}" key="local.order.rent.message" var="message" />
<fmt:message bundle="${loc}" key="local.order.rent.pickup_location_message" var="pickup_location_message" />
<fmt:message bundle="${loc}" key="local.order.rent.dropoff_location_messsage" var="dropoff_location_messsage" />
<fmt:message bundle="${loc}" key="local.order.rent.date_and_time_pickup" var="date_and_time_pickup" />
<fmt:message bundle="${loc}" key="local.order.rent.date_and_time_dropoff" var="date_and_time_dropoff" />
<fmt:message bundle="${loc}" key="local.order.rent.submit_button" var="submit_button" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="../plugin/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="../css/style.css">

</head>

<body>

<div class="container my-3 px-0">
    <nav class="nav row border rounded text-center">
        <a class="nav-item nav-link col border-right bg-light" href="${pageContext.request.contextPath}/controller?command=get_locations">${date_and_place}</a>
        <a class="nav-item nav-link col border-right disabled" href="#">${cars_search}</a>
        <a class="nav-item nav-link col border-right disabled" href="#">${driver}</a>
        <a class="nav-item nav-link col border-right disabled" href="#">${payment}</a>
        <a class="nav-item nav-link col disabled" href="#">${confirmation}</a>
    </nav>
</div>

<div class="padding-top-registration">
    <div class="card container col-lg-4">
        <article class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">${message}</h4>
            <hr>

            <div>
                <c:choose>
                    <c:when test="${empty datelocError}">
                        <p>
                            <br/>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger text-center">
                                ${datelocError}
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>

            <form id="signUpForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="choose_date_address_of_order"/>

                <p class="text-left">${pickup_location_message}:</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-map-marker">

                                </i>
                            </span>
                        </div>
                        <select class="form-control" name="pickupAddress">
                            <c:forEach var="address" items="${addressList}" varStatus="status">
                                <option>${address.street} ${address.building}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div> <!-- form-group// -->

                <p class="text-left">${dropoff_location_messsage}:</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-map-marker">

                                </i>
                            </span>
                        </div>
                        <select class="form-control" name="dropoffAddress">
                            <c:forEach var="address" items="${addressList}" varStatus="status">
                                <option>${address.street} ${address.building}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row justify-content-between">
                    <div class="col-6">
                        <p class="text-left">${date_and_time_pickup}:</p>
                        <div class="form-group">
                            <div class='input-group date' >
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa fa-calendar">

                                        </i>
                                    </span>
                                </div>
                                <input type='text' id='datetimepicker1' class="form-control" name="pickupDate"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <p class="text-left">${date_and_time_dropoff}:</p>
                        <div class="form-group">
                            <div class='input-group date' >
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        <i class="fa fa fa-calendar">

                                        </i>
                                    </span>
                                </div>
                                <input type='text' id='datetimepicker2' class="form-control" name="dropoffDate"/>
                            </div>
                        </div>
                    </div>
                    <!-- input-group.// -->
                </div> <!-- form-group// -->

                <br/>
                <div class="form-group d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">${submit_button}</button>
                </div> <!-- form-group// -->
            </form>
        </article>
    </div> <!-- card.// -->
</div>

<script src="../plugin/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../js/script.js"></script>


</body>
</html>