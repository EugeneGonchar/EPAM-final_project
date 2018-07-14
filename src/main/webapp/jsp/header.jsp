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
    <nav class="navbar navbar-expand-xl bg-dark navbar-dark fixed-top">

        <div class="d-flex justify-content-lg-end col-lg-1">

        </div>
        <div class="d-flex justify-content-lg-end col-lg-4">
            <ul class="navbar-nav d-flex justify-content-lg-start">
                <li class="menu__item_left" class="nav-item <%--active--%>">
                    <a class="nav-link" href="#">
                        <%--<img src="../img/Travel_3.svg" width="40px" height="40px">--%>
                        <i class="fa fa-car" style="font-size:36px;"></i>
                    </a>
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
                    <a class="nav-link" href="#">About</a>
                </li>
            </ul>
        </div>


        <div class="d-flex justify-content-lg-end col-lg-5">
            <ul class="navbar-nav ">
                <li  class="menu__item_right" class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fa fa-language">
                            <%--засунуть язык--%>
                        </i>
                        (en)
                    </a>
                </li>

                <c:choose>
                    <c:when test="${not empty user}">
                        <li class="menu__item_right" class="nav-item">
                            <a class="nav-link invisible" href="signup.jsp">
                                <i class="fa fa-user-plus">

                                </i>
                                Sign Up
                            </a>
                        </li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Hello,
                                <c:out value="${user.login}">

                                </c:out>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                <h6 class="dropdown-header">Signed in as</h6>
                                <span class="dropdown-item-text font-weight-bold" href="#">
                                        <c:out value="${user.firstName }"></c:out>
                                        <c:out value="${user.lastName}"></c:out>
                                    </span>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Your profile</a>
                                <a class="dropdown-item" href="#">Your orders</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Help</a>
                                <form id="logoutForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="logout"/>
                                    <button class="dropdown-item" type="submit">Sign out</button>
                                </form>
                            </div>
                        </li>

                    </c:when>
                    <c:otherwise>
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
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

        <div class="d-flex justify-content-lg-end col-lg-1">

        </div>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
</body>
</html>
