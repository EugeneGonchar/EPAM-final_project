<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">

    <title>Header</title>

</head>
<body>

<div class="container" class="header-div">
    <nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top">
        <ul class="navbar-nav d-flex ml-auto col-lg-5">
            <li class="menu__item_left" class="nav-item <%--active--%>">
                <a class="nav-link" href="#">Logo</a>
            </li>
            <li class="menu__item_left" class="nav-item <%--active--%>">
                <a class="nav-link" href="main.jsp">
                    <i class="fa fa-home">

                    </i>
                    Home
                </a>
            </li>
            <li class="menu__item_left" class="nav-item">
                <a class="nav-link" href="#">Rent</a>
            </li>
            <li class="menu__item_left" class="nav-item">
                <a class="nav-link" href="#">Cars</a>
            </li>
            <li class="menu__item_left" class="nav-item">
                <a class="nav-link" href="#">Help</a>
            </li>
        </ul>

        <ul class="navbar-nav d-flex ml-auto col-lg-3">
            <li class="menu__item_right" class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fa fa-language">
                        <%--засунуть язык--%>
                    </i>
                    (en)
                </a>
            </li>
            <li class="menu__item_right" class="nav-item">
                <a class="nav-link" href="signup.jsp">
                    <i class="fa fa-user-plus">

                    </i>
                    Sign Up
                </a>
            </li>
                <li class="menu__item_right" class="nav-item">
                    <a class="nav-link" href="login.jsp">
                        <i class="fa fa-sign-in">

                        </i>
                        Log in
                    </a>
                </li>
        </ul>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>
