<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>
<%@ include file = "/jsp/admin/admin_menu.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="import" href="../header.jsp">
    <link rel="import" href="../admin_menu.jsp">
    <title>Cars</title>
</head>
<body>

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header">${users_message}</h5>
            <div class="row mt-2 mx-1 col-3 align-items-center">
                <label class="">
                    <select class="form-control rounded-0">
                        <option>10</option>
                        <option selected="selected">25</option>
                        <option>50</option>
                    </select>
                </label>
                <h6 class="mx-2">${ }</h6>
            </div>
            <table class="table col-8">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">${}</th>
                    <th scope="col">${}</th>
                    <th scope="col">${}</th>
                    <th scope="col">${}</th>
                    <th scope="col">${}</th>
                    <th scope="col">${}</th>
                    <th scope="col">${}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="" items="${}" varStatus="status">
                    <tr>
                        <th scope="row">${.}</th>
                        <td>${.}</td>
                        <td>${.}</td>
                        <td>${.}</td>
                        <td>${.}</td>
                        <td>${.}</td>
                        <td>${.}</td>
                        <td>
                            <button class="btn btn-success btn-sm">
                                <i class="fa fa-edit"></i>
                            </button>
                            <button class="btn btn-danger btn-sm">
                                block
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


</body>
</html>