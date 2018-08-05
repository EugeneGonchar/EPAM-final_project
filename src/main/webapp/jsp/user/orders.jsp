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
                            <button type="button" class="btn btn-link m-0 p-0" data-toggle="modal" data-target=".bd-example-modal-lg">${orderElement.car.brand} ${orderElement.car.model}</button>

                            <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Car info</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            ...
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
                            <span class="badge badge-pill badge-success">Confirmed</span>
                        </td>
                        <td>
                            <!-- Button trigger modal -->
                            <div class="d-flex justify-content-center">
                                <button type="button" class="btn btn-info" data-toggle="modal" data-target=".bd-example-modal-lg">
                                    Details
                                </button>
                            </div>

                            <!-- Modal -->
                            <div class="modal fade bd-example-modal-lg" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">Order info</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            ...
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
