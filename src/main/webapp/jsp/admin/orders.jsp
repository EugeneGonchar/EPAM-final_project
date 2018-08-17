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
<fmt:message bundle="${loc}" key="local.orders.order_info_message" var="order_info_message" />
<fmt:message bundle="${loc}" key="local.orders.date_message" var="date_message" />
<fmt:message bundle="${loc}" key="local.orders.start_message" var="start_message" />
<fmt:message bundle="${loc}" key="local.orders.end_message" var="end_message" />
<fmt:message bundle="${loc}" key="local.orders.place_message" var="place_message" />
<fmt:message bundle="${loc}" key="local.orders.pickup_message" var="pickup_message" />
<fmt:message bundle="${loc}" key="local.orders.dropoff_message" var="dropoff_message" />
<fmt:message bundle="${loc}" key="local.orders.close_button" var="close_button" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link rel="stylesheet" href="../../plugin/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Orders</title>
</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header mb-2">${orders_message}</h5>

            <c:set var="command" scope="request" value="orders_table"/>
            <%@ include file = "/jsp/pagination/items_on_page.jsp" %>

            <table class="table col-8">
                <thead class="thead-light">
                <tr>
                    <th scope="col" class="align-middle">#</th>
                    <th scope="col" class="align-middle">${login_column}</th>
                    <th scope="col" class="align-middle">${name_column} ${surname_column}</th>
                    <th scope="col" class="align-middle">${car_column}</th>
                    <th scope="col" class="align-middle text-right">${date_received_column}</th>
                    <th scope="col" class="align-middle text-right">${date_return_column}</th>
                    <th scope="col" class="align-middle text-center">${status_column}</th>
                    <th scope="col" class="align-middle text-right">${total_cost_column}</th>
                    <th scope="col" class="align-middle text-center">${actions_column}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="fullUserOrderDTO" items="${fullUserOrderDTOList}" varStatus="status">
                    <tr>
                        <th scope="row" class="align-middle">${(pageDTO.currentPage-1) * pageDTO.elementsOnPage + status.count}</th>
                        <td class="align-middle">${fullUserOrderDTO.user.login}</td>
                        <td class="align-middle">${fullUserOrderDTO.user.firstName} ${fullUserOrderDTO.user.lastName}</td>
                        <td class="align-middle">${fullUserOrderDTO.car.brand} ${fullUserOrderDTO.car.model}</td>
                        <td class="align-middle text-right"><fmt:formatDate value="${fullUserOrderDTO.order.dateReceived}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td class="align-middle text-right"><fmt:formatDate value="${fullUserOrderDTO.order.returnDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td class="align-middle text-center">
                            <c:choose>
                                <c:when test="${fullUserOrderDTO.orderStatus.status eq 'is processed'}">
                                    <span class="badge badge-pill badge-warning">${fullUserOrderDTO.orderStatus.status}</span>
                                </c:when>
                                <c:when test="${(fullUserOrderDTO.orderStatus.status eq 'confirmed') or (fullUserOrderDTO.orderStatus.status eq 'completed')}">
                                    <span class="badge badge-pill badge-success">${fullUserOrderDTO.orderStatus.status}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-pill badge-danger">${fullUserOrderDTO.orderStatus.status}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle text-right">${fullUserOrderDTO.order.totalCost}</td>
                        <td class="align-middle text-center">
                            <button class="btn btn-success btn-sm" data-toggle="modal" data-target="#order${status.count}">
                                <i class="fa fa-edit"></i>
                            </button>
                            <div class="modal fade text-left" id="order${status.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title" id="exampleModalLongTitle">${order_info_message}</h3>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="update_status"/>
                                            <input type="hidden" name="orderId" value="${fullUserOrderDTO.order.id}"/>
                                            <div class="modal-body justify-content-center">
                                                <div class="container-fluid">
                                                    <div id="modal_order" class="row mx-0 justify-content-between">
                                                        <div class="col-5">
                                                            <div class="mb-1 border-bottom border-muted ">
                                                                <div class="h5 text-dark text-muted">${date_message}</div>
                                                            </div>
                                                            <div class="row px-3 justify-content-between">
                                                                <div class="h6">
                                                                        ${start_message}:
                                                                </div>
                                                                <div>
                                                                    <fmt:formatDate value="${ fullUserOrderDTO.order.dateReceived}" pattern="yyyy-MM-dd HH:mm" />
                                                                </div>
                                                            </div>
                                                            <div class="row px-3 justify-content-between">
                                                                <div class="h6">
                                                                        ${end_message}:
                                                                </div>
                                                                <div>
                                                                    <fmt:formatDate value="${ fullUserOrderDTO.order.returnDate}" pattern="yyyy-MM-dd HH:mm" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="col-5 mb-3">
                                                            <div class="mb-1 border-bottom border-muted ">
                                                                <div class="h5 text-dark text-muted">${place_message}</div>
                                                            </div>
                                                            <div class="row px-3 justify-content-between">
                                                                <div class="h6">
                                                                        ${pickup_message}:
                                                                </div>
                                                                <div>
                                                                        ${ fullUserOrderDTO.pickupAddress.street} ${ fullUserOrderDTO.pickupAddress.building}
                                                                </div>
                                                            </div>
                                                            <div class="row px-3 justify-content-between">
                                                                <div class="h6">
                                                                        ${dropoff_message}:
                                                                </div>
                                                                <div>
                                                                        ${ fullUserOrderDTO.dropoffAddress.street} ${ fullUserOrderDTO.dropoffAddress.building}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>


                                                    <div class="container my-2">
                                                        <div class="row">
                                                            <div class="col col-4">
                                                                <span class="h6 text-muted">Current status:</span>
                                                                <span class="h6">
                                                                    <c:choose>
                                                                        <c:when test="${fullUserOrderDTO.orderStatus.status eq 'is processed'}">
                                                                            <span class="badge badge-pill badge-warning">${fullUserOrderDTO.orderStatus.status}</span>
                                                                        </c:when>
                                                                        <c:when test="${(fullUserOrderDTO.orderStatus.status eq 'confirmed') or (fullUserOrderDTO.orderStatus.status eq 'completed')}">
                                                                            <span class="badge badge-pill badge-success">${fullUserOrderDTO.orderStatus.status}</span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span class="badge badge-pill badge-danger">${fullUserOrderDTO.orderStatus.status}</span>
                                                                        </c:otherwise>
                                                                    </c:choose></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="container col-12 my-3">
                                                        <div class="mb-1 border-bottom border-muted ">
                                                            <div class="h5 text-dark text-muted">Change order</div>
                                                        </div>
                                                        <div class="input-group my-2">
                                                            <div class="input-group-prepend">
                                                                <span class="input-group-text">
                                                                    Change status:
                                                                </span>
                                                            </div>
                                                            <select id="selectStatus" class="form-control" name="selectStatus">
                                                                <option>accident</option>
                                                                <option selected>confirmed</option>
                                                                <option>dismissed</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div id="place_for_details" class="col-12">

                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">${close_button}</button>
                                                <button type="submit" class="btn btn-success">Save changes</button>
                                            </div>
                                        </form>
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
<script src="../../plugin/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="../../js/script.js"></script>

</body>
</html>