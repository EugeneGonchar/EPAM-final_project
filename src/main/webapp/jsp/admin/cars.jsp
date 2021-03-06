<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.admin.tables.cars.car_message" var="car_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.car_column" var="car_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.seats_column" var="seats_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.doors_column" var="doors_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.air_cond_column" var="air_cond_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.auto_gearbox_column" var="auto_gearbox_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.rental_for_a_day" var="rental_for_a_day" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.color_column" var="color_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.fuel_consumption_column" var="fuel_consumption_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.engine_type_column" var="engine_type_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.year_of_issue_column" var="year_of_issue_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.car_class" var="car_class" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.items_on_page_message" var="items_on_page_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.actions_column" var="actions_column" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Cars</title>
</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header mb-2">${car_message}</h5>

            <c:set var="command" scope="session" value="cars_table"/>


            <div class="container">
                <div class="row justify-content-between">
                    <%@ include file = "/jsp/pagination/items_on_page.jsp" %>
                    <div class="col-6">
                        <div class="row justify-content-end">
                            <div class="col-3">
                                <a class="btn btn-block btn-success text-white" href="${pageContext.request.contextPath}/admin/new_car">
                                    Add new
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <table class="table col-8">
                <thead class="thead-light">
                <tr>
                    <th scope="col" class="align-middle text-center">#</th>
                    <th scope="col" class="align-middle">${car_column}</th>
                    <th scope="col" class="align-middle text-center">${engine_type_column}</th>
                    <th scope="col" class="align-middle text-right">${fuel_consumption_column}</th>
                    <th scope="col" class="align-middle text-right">${year_of_issue_column}</th>
                    <th scope="col" class="align-middle text-right">${rental_for_a_day}</th>
                    <th scope="col" class="align-middle text-center">${actions_column}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${carList}" varStatus="status">
                    <tr>
                        <th scope="row" class="align-middle text-center">${(pageDTO.currentPage-1) * pageDTO.elementsOnPage + status.count}</th>
                        <td class="align-middle">${car.brand} ${car.model}</td>
                        <td class="align-middle text-center">${car.engineType}</td>
                        <td class="align-middle text-right">${car.fuelConsumption}</td>
                        <td class="align-middle text-right">${car.yearOfIssue}</td>
                        <td class="align-middle text-right">${car.rental4Day}</td>
                        <td class="align-middle text-center">
                            <button class="btn btn-success btn-sm" data-toggle="modal" data-target="#car${status.count}">
                                <i class="fa fa-edit"></i>
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="car${status.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">Description</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row col-7 justify-content-center">
                                                <c:choose>
                                                    <c:when test="${empty car.image}">
                                                        <img width="380" height="260" class="rounded d-block" alt="" src="../../img/uploads/car/default.png">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img width="380" height="260" class="rounded d-block" alt="" src="../../img/uploads/car/${car.image}">
                                                    </c:otherwise>
                                                </c:choose>
                                                <div class="col-8">
                                                    <div class="my-2">
                                                        message
                                                    </div>
                                                    <form action="${pageContext.request.contextPath}/controller" method="POST" enctype="multipart/form-data">
                                                        <input type="hidden" name="command" value="upload_car_img">
                                                        <input type="hidden" name="carId" value="${car.id}"/>
                                                        <label class="custom-file btn btn-block btn-info">
                                                            Upload
                                                            <input type="file" name="carImg" class="custom-file-input" style="display: none">
                                                        </label>
                                                        <button type="submit" class="btn btn-block btn-success">
                                                            Save
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <button class="btn btn-danger btn-sm">
                                <i class="fa fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav class="card-footer">

                <%@ include file = "/jsp/pagination/pagination.jsp" %>

            </nav>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>


</body>
</html>