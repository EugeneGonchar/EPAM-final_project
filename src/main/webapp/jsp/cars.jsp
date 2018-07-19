<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="import" href="../header.jsp">
    <title>Cars</title>

</head>
<body>


<div class="container my-3">
    <div class="col-3 float-left pr-4" role="navigation">

        <div class="border rounded mb-3">
            <nav class="menu">
                <ul class="navbar-nav">
                        <span class="border-bottom bg-light">
                            <li class="pl-3 nav-item text-dark">
                                <a class="nav-link disabled text-secondary" href="#">Personal settings</a>
                            </li>
                        </span>
                    <span class="border-bottom active">
                            <li class="pl-3 nav-item active">
                                <a class="nav-link text-dark" href="/user/profile">Profile</a>
                            </li>
                        </span>
                    <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="/user/account">Account</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="/user/contacts">Email & phone</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>

        <div class="border rounded">
            <nav class="menu">
                <ul class="navbar-nav">
                        <span class="border-bottom bg-light">
                            <li class="pl-3 nav-item text-dark">
                                <a class="nav-link disabled text-secondary" href="#">Personal settings</a>
                            </li>
                        </span>
                    <span class="border-bottom active">
                            <li class="pl-3 nav-item active">
                                <a class="nav-link text-dark" href="/user/profile">Profile</a>
                            </li>
                        </span>
                    <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="/user/account">Account</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="/user/contacts">Email & phone</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>
    </div>

    <div style="background-color: #28a745" class="col-9 float-left">

        <div style="background-color: red" class="card col-auto my-3">
            <div class="row">
                <div style="background-color: black" class="col-4 align-self-center">
                    <div class="text-center">
                        <img width="190" height="130" class="rounded d-block" alt="" src="../img/sunny_lrg.jpg">
                    </div>
                </div>

                <div style="background-color: #005cbf" class="col-5">

                </div>

                <div style="background-color: purple" class="col-3 float-right">
                    <img width="150" height="150" class="rounded text-center mx-auto d-block" alt="" src="https://avatars0.githubusercontent.com/u/32580446?s=400&amp;v=4">

                </div>
            </div>
        </div>

        <div style="background-color: red" class="card col-auto">
            <div class="row">
                <div style="background-color: black" class="col-4">
                    <div>
                        <img width="150" height="150" class="rounded text-center mx-auto d-block" alt="" src="https://avatars0.githubusercontent.com/u/32580446?s=400&amp;v=4">

                    </div>
                </div>

                <div style="background-color: #005cbf" class="col-5">
                    <img width="150" height="150" class="rounded text-center mx-auto d-block" alt="" src="https://avatars0.githubusercontent.com/u/32580446?s=400&amp;v=4">

                </div>

                <div style="background-color: purple" class="col-3 float-right">
                    <img width="150" height="150" class="rounded text-center mx-auto d-block" alt="" src="https://avatars0.githubusercontent.com/u/32580446?s=400&amp;v=4">

                </div>
            </div>
        </div>

    </div>
</div>


</body>
</html>
