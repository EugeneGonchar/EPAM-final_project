<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">

    <title>Header</title>

</head>
<body>

<div class="container">
    <nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top">
        <ul class="navbar-nav d-flex ml-auto col-lg-5">
            <li class="nav-item active">
                <a class="nav-link" href="main.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Rent</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Cars</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Help</a>
            </li>
        </ul>

        <ul class="navbar-nav d-flex ml-auto col-lg-3">
            <%--добавить иконку языка сюда и другие ниже--%>
            <li class="nav-item">
                <a class="nav-link" href="signup.jsp">
                <span class="glyphicon glyphicon-user">

                </span>
                    Sign Up
                </a>
            </li>
            <li class="dropdown" class="nav-item">
                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" >
                    <span class="glyphicon glyphicon-log-in">

                    </span>
                    Log in
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <form id="loginForm" class="form-horizontal"  method="post" accept-charset="UTF-8" action="controller">
                        <input type="hidden" name="command" value="login"/>
                        <h6 class="dropdown-header">Login</h6>
                        <input class="form-control login" type="text" name="login"/>
                        <h6 class="dropdown-header">Password</h6>
                        <input class="form-control login" type="password" name="password"/>
                        <br/>
                        ${errorLoginPassMessage}
                        ${wrongAction}
                        ${nullPage}
                        <br/>
                        <input class="btn btn-primary" type="submit" name="submit" value="Submit" />

                    </form>
                </div>
            </li>
        </ul>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>
