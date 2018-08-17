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
    <title>Accidents</title>
</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header mb-2">${orders_message}</h5>

            <c:set var="command" scope="request" value="accidents_table"/>
            <%@ include file = "/jsp/pagination/items_on_page.jsp" %>

            <table class="table col-8">
                <thead class="thead-light">
                <tr>
                    <th scope="col" class="align-middle">#</th>
                    <th scope="col" class="align-middle text-center">Order id</th>
                    <th scope="col" class="align-middle text-right">Date</th>
                    <th scope="col" class="align-middle text-center">Description</th>
                    <th scope="col" class="align-middle text-right">Material damage, $</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="accident" items="${accidentList}" varStatus="status">
                    <tr>
                        <th scope="row" class="align-middle">${(pageDTO.currentPage-1) * pageDTO.elementsOnPage + status.count}</th>
                        <td class="align-middle text-center">${accident.orderId}</td>
                        <td class="align-middle text-right"><fmt:formatDate value="${accident.date}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td class="align-middle text-center">
                            <button type="button" class="btn btn-link" data-toggle="modal" data-target="#accident${status.count}">
                                Description
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="accident${status.count}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">Description</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            ${accident.description}
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle text-right">${accident.materialDamage}</td>

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