<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.admin.tables.cars.car_message" var="car_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.car_column" var="car_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.photo_column" var="photo_column" />
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
    <link rel="stylesheet" href="../css/style.css">
    <title>Cars</title>
</head>
<body>

<%@ include file = "/jsp/header.jsp" %>
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header">${car_message}</h5>
            <div class="row mt-2 mx-1 col-3 align-items-center">
                <label class="">
                    <select class="form-control rounded-0">
                        <option>10</option>
                        <option selected="selected">25</option>
                        <option>50</option>
                    </select>
                </label>
                <h6 class="mx-2">${ items_on_page_message}</h6>
            </div>
            <table class="table col-8">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">${photo_column}</th>
                    <th scope="col">${car_column}</th>
                    <th scope="col">${rental_for_a_day}</th>
                    <th scope="col">${fuel_consumption_column}</th>
                    <th scope="col">${engine_type_column}</th>
                    <th scope="col">${year_of_issue_column}</th>
                    <th scope="col">${actions_column}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${carList}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td></td>
                        <td>${car.brand} ${car.model}</td>
                        <td>${car.rental4Day}</td>
                        <td>${car.fuelConsumption}</td>
                        <td>${car.engineType}</td>
                        <td>${car.yearOfIssue}</td>
                        <td>
                            <button class="btn btn-success btn-sm">
                                <i class="fa fa-edit"></i>
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <i class="fa fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav class="card-footer">
                <div class="d-flex justify-content-end">
                    <ul class="pagination pagination-sm">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <i class="fa fa-arrow-left" aria-hidden="true"></i>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>


</body>
</html>