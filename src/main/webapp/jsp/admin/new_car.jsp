<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.signup.registration_message" var="registration_message" />

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">

    <title>New car</title>
</head>

<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="padding-top-registration">
    <div  class="card container col-lg-3">
        <article class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">New car</h4>
            <hr>

            <div>
                <c:choose>
                    <c:when test="${empty registrationError}">
                        <p>
                            <br/>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger text-center">
                                ${registrationError}
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>

            <form id="signUpForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="addcar"/>

                <div class="row">
                    <div class="col">
                        <div class="row">
                            <div class="form-group col">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        Brand
                                    </span>
                                    </div>
                                    <input name="login" class="form-control" placeholder="login" type="text">
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->
                        </div>
                    </div>

                    <div class="col">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">
                                        Model
                                    </span>
                                </div>
                                <input name="firstName" class="form-control" placeholder="IVAN" type="text">
                            </div> <!-- input-group.// -->
                        </div> <!-- form-group// -->
                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                            <span class="input-group-text">
                                Seats
                            </span>
                                </div>
                                <select class="form-control" name="seats_select">
                                    <c:forEach var="i" begin="1" end="15">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div> <!-- input-group.// -->
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                            <span class="input-group-text">
                                Doors
                            </span>
                                </div>
                                <select class="form-control" name="doors_select">
                                    <c:forEach var="i" begin="1" end="6">
                                        <option>${i}</option>
                                    </c:forEach>
                                </select>
                            </div> <!-- input-group.// -->
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-check col">
                        <label class="form-check-label">
                            <input type="checkbox" class="form-check-input">
                            Air conditioning
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input">
                        Automatic gearbox
                    </label>
                </div> <!-- form-group// -->

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Rent cost
                            </span>
                        </div>
                        <input name="password2" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Color
                            </span>
                        </div>
                        <input name="password2" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Fuel consumption
                            </span>
                        </div>
                        <input name="password2" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Car class
                            </span>
                        </div>
                        <input name="password2" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Engine
                            </span>
                        </div>
                        <input name="password2" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div>

                <br/>
                <div class="form-group d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div> <!-- form-group// -->
            </form>
        </article>
    </div> <!-- card.// -->
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>
