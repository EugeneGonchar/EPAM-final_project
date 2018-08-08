<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Admin menu</title>

</head>
<body>
<div class="left-container float-left col-2 px-0 left-admin-menu">
    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <div id="MainMenu">
            <div class="list-group panel ">
                <a href="#demo1" class="list-group-item font-weight-bold text-muted" data-toggle="collapse" data-parent="#MainMenu">
                    <i class="fa fa-area-chart"></i> Statistics <i class="fa fa-caret-down"></i>
                </a>
                <div class="collapse list-group-submenu" id="demo1">
                    <a href="${pageContext.request.contextPath}/admin/statistics/income_expenses" class="list-group-item text-muted">Income and expenses</a>
                    <a href="${pageContext.request.contextPath}/admin/statistics/users_visits" class="list-group-item text-muted">Users visits</a>
                </div>
                <a href="#demo2" class="list-group-item font-weight-bold text-muted" data-toggle="collapse" data-parent="#MainMenu">
                    <i class="fa fa-table"></i> Tables <i class="fa fa-caret-down"></i>
                </a>
                <div class="collapse list-group-submenu" id="demo2">
                    <a href="${pageContext.request.contextPath}/admin/tables/accidents" class="list-group-item text-muted">Accidents</a>
                    <a href="${pageContext.request.contextPath}/admin/tables/cars" class="list-group-item text-muted">Cars</a>
                    <a href="${pageContext.request.contextPath}/admin/tables/orders" class="list-group-item text-muted">Orders</a>
                    <a href="${pageContext.request.contextPath}/controller?command=users_table" class="list-group-item text-muted">Users</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>










