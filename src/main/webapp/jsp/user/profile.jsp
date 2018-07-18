<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Profile</title>

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

        <div class="col-9 float-left">
            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">Profile</h3>
            </div>

            <form id="changeNameOrSurnameForm" method="POST" action="/controller">
                <input type="hidden" name="command" value="change_name_surname"/>
                <dl class="form-group edit-profile-avatar mt-3 pl-1 float-right col-4">
                    <dt><label>Profile picture</label></dt>
                    <dd class="avatar-upload-container clearfix">
                        <img width="200" height="200" class="avatar rounded-2" alt="" src="https://avatars0.githubusercontent.com/u/32580446?s=400&amp;v=4">
                        <div class="custom-file mt-3">
                            <button class="btn btn-block btn-primary">
                                Upload the file
                            </button>
                        </div> <!-- /.avatar-upload -->
                    </dd>
                </dl>

                <div class="col-7 mr-1 float-left px-0 py-2">
                    <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                        <div>
                            <p>
                                <span class="font-weight-bold">Name: </span>
                                <p><c:out value="${user.firstName}"></c:out></p>
                            </p>
                        </div>
                        <div>
                            <p>
                                <span class="font-weight-bold">Surname: </span>
                                <p><c:out value="${user.lastName}"></c:out></p>
                            </p>
                        </div>
                    </div>

                    <div>
                        <c:choose>
                            <c:when test="${empty updateNameSurnameError}">
                                <p>
                                    <br/>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-danger text-left">
                                        ${updateNameSurnameError}
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="form-group mt-3">
                        <label class="font-weight-bold">New name:</label>
                        <input name="first_name" placeholder="Ivan" type="text" class="form-control">
                    </div>
                    <div class="form-group mt-3">
                        <label class="font-weight-bold">New surname:</label>
                        <input name="last_name" placeholder="Ivanov" type="text" class="form-control">
                    </div>
                    <div>
                        <p class="text-small">
                            Note that name and surname should be as in your passport.
                        </p>
                    </div>
                    <div class="form-group mt-3">
                        <button class="btn btn-success" type="submit">Update profile</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


</body>
</html>
