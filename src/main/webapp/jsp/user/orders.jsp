<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.orders.orders_message" var="orders_message" />
<fmt:message bundle="${loc}" key="local.orders.car_column" var="car_column" />
<fmt:message bundle="${loc}" key="local.orders.start_date_column" var="start_date_column" />
<fmt:message bundle="${loc}" key="local.orders.end_date_column" var="end_date_column" />
<fmt:message bundle="${loc}" key="local.orders.status_column" var="status_column" />
<fmt:message bundle="${loc}" key="local.orders.action_column" var="action_column" />
<fmt:message bundle="${loc}" key="local.orders.common_information_message" var="common_information_message" />
<fmt:message bundle="${loc}" key="local.car.seats_message" var="seats_message" />
<fmt:message bundle="${loc}" key="local.car.doors_message" var="doors_message" />
<fmt:message bundle="${loc}" key="local.car.air_conditioning_message" var="air_conditioning_message" />
<fmt:message bundle="${loc}" key="local.car.automatic_gearbox_message" var="automatic_gearbox_message" />
<fmt:message bundle="${loc}" key="local.car.fuel_consumption_message" var="fuel_consumption_message" />
<fmt:message bundle="${loc}" key="local.car.engine_type_message" var="engine_type_message" />
<fmt:message bundle="${loc}" key="local.car.year_of_issue_message" var="year_of_issue_message" />
<fmt:message bundle="${loc}" key="local.orders.close_button" var="close_button" />
<fmt:message bundle="${loc}" key="local.orders.details_button" var="details_button" />
<fmt:message bundle="${loc}" key="local.orders.order_info_message" var="order_info_message" />
<fmt:message bundle="${loc}" key="local.orders.date_message" var="date_message" />
<fmt:message bundle="${loc}" key="local.orders.start_message" var="start_message" />
<fmt:message bundle="${loc}" key="local.orders.end_message" var="end_message" />
<fmt:message bundle="${loc}" key="local.orders.place_message" var="place_message" />
<fmt:message bundle="${loc}" key="local.orders.pickup_message" var="pickup_message" />
<fmt:message bundle="${loc}" key="local.orders.dropoff_message" var="dropoff_message" />
<fmt:message bundle="${loc}" key="local.orders.rent_cost_message" var="rent_cost_message" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Orders</title>

</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">

    <div class="col-12 mt-0 mb-2 border-bottom border-muted float-right">
        <h3 class="text-dark">${orders_message}</h3>
    </div>

    <div class="col-12 float-left">
        <c:set var="command" scope="session" value="get_orders"/>
        <%@ include file = "/jsp/pagination/items_on_page.jsp" %>

        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">${car_column}</th>
                    <th scope="col">${start_date_column}</th>
                    <th scope="col">${end_date_column}</th>
                    <th scope="col" class="text-center">${status_column}</th>
                    <th scope="col" class="text-center">${action_column}</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="orderElement" items="${orderList}" varStatus="status">
                    <tr>
                        <th scope="row" class="align-middle">${(pageDTO.currentPage-1) * pageDTO.elementsOnPage + status.count}</th>
                        <td class="align-middle">
                            <button type="button" class="btn btn-link m-0 p-0" data-toggle="modal" data-target="#car${status.count}">${orderElement.car.brand} ${orderElement.car.model}</button>

                            <div class="modal fade" id="car${status.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title">${orderElement.car.brand} ${orderElement.car.model}</h3>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="container">
                                                <div class="row justify-content-between">
                                                    <div class="col-5 align-self-center">
                                                        <div class="text-center">
                                                            <img width="285" height="195" class="rounded d-block" alt="" src="../img/sport_lrg.jpg">
                                                        </div>
                                                    </div>
                                                    <div class="col-7">
                                                        <div class="row mb-1 border-bottom border-muted ">
                                                            <h5 class="">${common_information_message}</h5>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">${seats_message}: ${orderElement.car.seats} </h6>
                                                            </div>
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">${doors_message}: ${orderElement.car.doors}</h6>
                                                            </div>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">
                                                                    ${air_conditioning_message}:
                                                                    <c:choose>
                                                                        <c:when test="${orderElement.car.airConditioning eq true}">
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
                                                                        <c:when test="${orderElement.car.automaticGearbox eq true}">
                                                                            <i class="fa fa-check" style="color: #34ce57"></i>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <i class="fa fa-minus" style="color: red"></i>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </h6>
                                                            </div>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">${fuel_consumption_message}: ${orderElement.car.fuelConsumption} L/100</h6>
                                                            </div>
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">${engine_type_message}: ${orderElement.car.engineType}</h6>
                                                            </div>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">${year_of_issue_message}: ${orderElement.car.yearOfIssue}</h6>
                                                            </div>
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1"></h6>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">${close_button}</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle"><fmt:formatDate value="${orderElement.order.dateReceived}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td class="align-middle"><fmt:formatDate value="${orderElement.order.returnDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td class="text-success text-center align-middle">
                            <c:choose>
                                <c:when test="${orderElement.orderStatus.status eq 'is processed'}">
                                    <span class="badge badge-pill badge-warning">${orderElement.orderStatus.status}</span>
                                </c:when>
                                <c:when test="${(orderElement.orderStatus.status eq 'confirmed') or (orderElement.orderStatus.status eq 'completed')}">
                                    <span class="badge badge-pill badge-success">${orderElement.orderStatus.status}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-pill badge-danger">${orderElement.orderStatus.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <!-- Button trigger modal -->
                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#order${status.count}">
                                    ${details_button}
                                </button>
                            </div>

                            <!-- Modal -->
                            <div class="modal fade" id="order${status.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title" id="exampleModalLongTitle">${order_info_message}</h3>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body justify-content-center">
                                            <div class="container-fluid">
                                                <div class="row justify-content-between">
                                                    <div class="col-5">
                                                        <div class="mb-1 border-bottom border-muted ">
                                                            <div class="h5 text-dark text-muted">${date_message}</div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                ${start_message}:
                                                            </div>
                                                            <div>
                                                                <fmt:formatDate value="${ orderElement.order.dateReceived}" pattern="yyyy-MM-dd HH:mm" />
                                                            </div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                ${end_message}:
                                                            </div>
                                                            <div>
                                                                <fmt:formatDate value="${ orderElement.order.returnDate}" pattern="yyyy-MM-dd HH:mm" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-5">
                                                        <div class="mb-1 border-bottom border-muted ">
                                                            <div class="h5 text-dark text-muted">${place_message}</div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                ${pickup_message}:
                                                            </div>
                                                            <div>
                                                                ${ orderElement.pickupAddress.street} ${ orderElement.pickupAddress.building}
                                                            </div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                ${dropoff_message}:
                                                            </div>
                                                            <div>
                                                                    ${ orderElement.dropoffAddress.street} ${ orderElement.dropoffAddress.building}
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="container my-3">
                                                        <div class="row justify-content-end">
                                                            <div class="col col-4 text-right">
                                                                <span class="h5">${rent_cost_message}:</span>
                                                                <span class="h4">${ orderElement.order.totalCost} $</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">${close_button}</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <%@ include file = "/jsp/pagination/pagination.jsp" %>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>
