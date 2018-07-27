<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="import" href="../header.jsp">
    <title>Contacts</title>

</head>
<body>


<div class="container my-3">
    <div class="col-3 float-left pr-4" role="navigation">

        <div class="border rounded">
            <nav class="menu">
                <ul class="navbar-nav">
                        <span class="border-bottom bg-light">
                            <li class="pl-3 nav-item text-dark">
                                <a class="nav-link disabled text-secondary" href="#">Personal settings</a>
                            </li>
                        </span>
                    <span class="border-bottom active">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="/user/profile">Profile</a>
                            </li>
                        </span>
                    <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="/user/account">Account</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link text-dark" href="/user/contacts">Email & phone</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>
    </div>

    <div class="col-9 float-left">

        <div class="card border-0 col-12">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">Change email</h3>
            </div>

            <form id="changeEmailForm" method="POST" action="/controller" class="col-8">
                <input type="hidden" name="command" value="change_email"/>
                <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                    <div>
                        <p class="note">
                            <span class="font-weight-bold">Email: </span>
                        <p><c:out value="${user.email}"></c:out> </p>
                        </p>
                    </div>
                </div>

                <div>
                    <c:choose>
                        <c:when test="${empty updateEmailError}">
                            <p>
                                <br/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-danger text-left">
                                    ${updateEmailError}
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group">
                    <label for="b" class="font-weight-bold">New email:</label>
                    <input name="email" placeholder="example@mail.com" type="text" class="form-control" id="b">
                </div>

                <div class="form-group mt-1">
                    <button class="btn btn-light border" type="submit">Change email</button>
                </div>

            </form>
        </div>

        <div class="card border-0 col-12 mt-3">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">Change phone</h3>
            </div>

            <form id="changePhoneForm" method="POST" action="/controller" class="col-8">
                <input type="hidden" name="command" value="change_phone"/>
                <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                    <div>
                        <p class="note">
                            <span class="font-weight-bold">Phone: </span>
                        <p><c:out value="${user.phone}"></c:out></p>
                        </p>
                    </div>
                </div>

                <div>
                    <c:choose>
                        <c:when test="${empty updatePhoneError}">
                            <p>
                                <br/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-danger text-left">
                                    ${updatePhoneError}
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group mt-1">
                    <label class="font-weight-bold">New phone:</label>
                    <input name="phone" placeholder="+375291000000" type="text" class="form-control">
                </div>

                <div class="form-group mt-1">
                    <button class="btn btn-light border" type="submit">Change phone</button>
                </div>

            </form>
        </div>


    </div>


</div>
</div>


</body>
</html>
