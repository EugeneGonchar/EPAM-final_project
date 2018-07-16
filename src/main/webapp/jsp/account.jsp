<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="import" href="header.jsp">
    <title>Account</title>

</head>
<body>


<div class="container my-3">
    <div <%--style="background-color: #28a745" --%>class="col-3 float-left pr-4" role="navigation">

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
                                <a class="nav-link" href="profile.jsp">Profile</a>
                            </li>
                        </span>
                    <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link text-dark" href="account.jsp">Account</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="contacts.jsp">Email & phone</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>
    </div>

    <div class="col-9 float-left">

        <div class="card border-0 col-8">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">Change login</h3>
            </div>

            <form action="">

                    <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                        <div>
                            <p class="note">
                                <span class="font-weight-bold">Login: </span>
                            <p>User1</p>
                            </p>
                        </div>
                    </div>

                    <div>
                        <c:choose>
                            <c:when test="${empty loginError}">
                                <p>
                                    <br/>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-danger text-left">
                                        ${loginError}
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="form-group">
                        <label for="b" class="font-weight-bold">New name:</label>
                        <input type="text" class="form-control" id="b">
                    </div>

                    <div class="form-group mt-1">
                        <button class="btn btn-light border" type="submit">Change login</button>
                    </div>

            </form>
        </div>

        <div class="card border-0 col-8 mt-3">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">Change password</h3>
            </div>

            <form action="">

                <div>
                    <c:choose>
                        <c:when test="${empty loginError}">
                            <p>
                                <br/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-danger text-left">
                                    ${loginError}
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group mt-1">
                    <label class="font-weight-bold">Old password:</label>
                    <input type="text" class="form-control">
                </div>

                <div class="form-group mt-1">
                    <label class="font-weight-bold">New password:</label>
                    <input type="text" class="form-control">
                </div>

                <div class="form-group mt-1">
                    <label class="font-weight-bold">Confirm new password:</label>
                    <input type="text" class="form-control">
                </div>

                <div class="form-group mt-1">
                    <button class="btn btn-light border" type="submit">Change password</button>
                </div>

            </form>
        </div>


    </div>


</div>
</div>


</body>
</html>
