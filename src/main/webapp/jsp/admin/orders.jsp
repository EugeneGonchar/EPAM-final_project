<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.admin.tables.orders.orders_message" var="orders_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.login_column" var="login_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.name_column" var="name_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.surname_column" var="surname_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.date_received_column" var="date_received_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.date_return_column" var="date_return_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.status_column" var="status_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.description_column" var="description_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.orders.total_cost_column" var="total_cost_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.items_on_page_message" var="items_on_page_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.actions_column" var="actions_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.cars.car_column" var="car_column" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Orders</title>
</head>
<body>

<%@ include file = "/jsp/header.jsp" %>
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header">${orders_message}</h5>
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
                    <th scope="col">${login_column}</th>
                    <th scope="col">${name_column} ${surname_column}</th>
                    <th scope="col">${car_column}</th>
                    <th scope="col">${date_received_column}</th>
                    <th scope="col">${date_return_column}</th>
                    <th scope="col">${status_column}</th>
                    <th scope="col">${total_cost_column}</th>
                    <th scope="col">${actions_column}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fullUserOrderDTO" items="${fullUserOrderDTOList}" varStatus="status">
                    <tr>
                        <th scope="row">${status.count}</th>
                        <td>${fullUserOrderDTO.user.login}</td>
                        <td>${fullUserOrderDTO.user.firstName} ${fullUserOrderDTO.user.lastName}</td>
                        <td>${fullUserOrderDTO.car.brand} ${fullUserOrderDTO.car.model}</td>
                        <td><fmt:formatDate value="${fullUserOrderDTO.order.dateReceived}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td><fmt:formatDate value="${fullUserOrderDTO.order.returnDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${fullUserOrderDTO.orderStatus.status eq 'is processed'}">
                                    <span class="badge badge-pill badge-warning">${orderElement.orderStatus.status}</span>
                                </c:when>
                                <c:when test="${(fullUserOrderDTO.orderStatus.status eq 'confirmed') or (fullUserOrderDTO.orderStatus.status eq 'completed')}">
                                    <span class="badge badge-pill badge-success">${fullUserOrderDTO.orderStatus.status}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-pill badge-danger">${fullUserOrderDTO.orderStatus.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${fullUserOrderDTO.order.totalCost}</td>
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