<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

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
        <a class="nav-item nav-link col border-right" href="/rent">Choice of date and place</a>
        <a class="nav-item nav-link col border-right" href="/controller?command=get_cars">Car's search results</a>
        <a class="nav-item nav-link col border-right" href="/driverdetails">Driver</a>
        <a class="nav-item nav-link col border-right" href="/payment">Payment</a>
        <a class="nav-item nav-link col <%--disabled--%>" href="/confirmation">Confirmation</a>
    </nav>
</div>

<div class="padding-top-registration">
    <div class="card container col-lg-4">
        <article class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">Let’s find your ideal car</h4>
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

            <form id="signUpForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="choose_date_loc_of_order"/>

                <p class="text-left">Pick-up location :</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-map-marker">

                                </i>
                            </span>
                        </div>
                        <select class="form-control" name="pickUpLocation">
                            <c:forEach var="address" items="${addressList}" varStatus="status">
                                <option><c:out value="${address.street} ${address.building}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div> <!-- form-group// -->

                <p class="text-left">Drop-off location :</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-map-marker">

                                </i>
                            </span>
                        </div>
                        <select class="form-control" name="dropOffLocation">
                            <c:forEach var="address" items="${addressList}" varStatus="status">
                                <option><c:out value="${address.street} ${address.building}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="row justify-content-between">
                    <div class="col-6">
                        <p class="text-left">Pick-up date and time :</p>
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
                        <p class="text-left">Drop-off date and time :</p>
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
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div> <!-- form-group// -->
            </form>
        </article>
    </div> <!-- card.// -->
</div>

<script src="../plugin/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../js/script.js"></script>


</body>
</html>