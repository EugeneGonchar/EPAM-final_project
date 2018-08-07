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
    <title>Orders</title>

</head>
<body>

<div class="container my-3">

    <div class="col-12 mt-0 mb-3 border-bottom border-muted float-right">
        <h3 class="text-dark">Orders</h3>
    </div>

    <div class="col-12 float-left">

        <table class="table table-hover">
            <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Car</th>
                    <th scope="col">Start date</th>
                    <th scope="col">End date</th>
                    <th scope="col" class="text-center">Status</th>
                    <th scope="col" class="text-center">Action</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean id="orderList" scope="session" type="java.util.List"/>
                <c:forEach var="orderElement" items="${orderList}" varStatus="status">
                    <tr>
                        <th scope="row" class="align-middle">${status.count}</th>
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
                                                            <h5 class="">Common information</h5>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">Seats: ${orderElement.car.seats} </h6>
                                                            </div>
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">Doors: ${orderElement.car.doors}</h6>
                                                            </div>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">
                                                                    Air conditioning:
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
                                                                    Automatic gearbox:
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
                                                                <h6 class="car_element" class="mb-1">Fuel consumption: ${orderElement.car.fuelConsumption} L/100</h6>
                                                            </div>
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">Engine power: ${orderElement.car.engineType}</h6>
                                                            </div>
                                                        </div>
                                                        <div class="row my-1 text-left">
                                                            <div class="col-6 px-0 pl-3">
                                                                <h6 class="car_element" class="mb-1">Year of issue: ${orderElement.car.yearOfIssue}</h6>
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
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
                                    Details
                                </button>
                            </div>

                            <!-- Modal -->
                            <div class="modal fade" id="order${status.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title" id="exampleModalLongTitle">Order info</h3>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body justify-content-center">
                                            <div class="container-fluid">
                                                <div class="row justify-content-between">
                                                    <div class="col-5">
                                                        <div class="mb-1 border-bottom border-muted ">
                                                            <div class="h5 text-dark text-muted">Date</div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                Start:
                                                            </div>
                                                            <div>
                                                                <fmt:formatDate value="${ orderElement.order.dateReceived}" pattern="yyyy-MM-dd HH:mm" />
                                                            </div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                End:
                                                            </div>
                                                            <div>
                                                                <fmt:formatDate value="${ orderElement.order.returnDate}" pattern="yyyy-MM-dd HH:mm" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-5">
                                                        <div class="mb-1 border-bottom border-muted ">
                                                            <div class="h5 text-dark text-muted">Place</div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                Pick-up:
                                                            </div>
                                                            <div>
                                                                ${ orderElement.pickupAddress.street} ${ orderElement.pickupAddress.building}
                                                            </div>
                                                        </div>
                                                        <div class="row px-3 justify-content-between">
                                                            <div class="h6">
                                                                Drop-off:
                                                            </div>
                                                            <div>
                                                                    ${ orderElement.dropoffAddress.street} ${ orderElement.dropoffAddress.building}
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="container my-3">
                                                        <div class="row justify-content-end">
                                                            <div class="col col-4 text-right">
                                                                <span class="h5">Rent cost:</span>
                                                                <span class="h4">${ orderElement.order.totalCost} $</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</div>


</body>
</html>
