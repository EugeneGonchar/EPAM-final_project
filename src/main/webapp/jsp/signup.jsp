<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %><html>

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <title>Login</title>
</head>

<body>

<div class="padding-top-registraition">
    <div  class="card container col-lg-3">
        <article class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">Registration</h4>
            <hr>

            <div>
                <c:choose>
                    <c:when test="${empty errorLoginPassMessage && empty wrongAction && empty nullPage}">
                        <p>
                            <br/>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="text-danger text-center">
                                ${errorLoginPassMessage}
                                ${wrongAction}
                                ${nullPage}
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>

            <form id="signUpForm" method="POST" action="controller">
                <input type="hidden" name="command" value="signup"/>
                <p class="text-left">Think of your login :</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user">

                                </i>
                            </span>
                        </div>
                        <input name="login" class="form-control" placeholder="login" type="text">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">Please, enter your passport name and surname :</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Name *
                            </span>
                        </div>
                        <input name="first_name" class="form-control" placeholder="IVAN" type="text">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Surname *
                            </span>
                        </div>
                        <input name="last_name" class="form-control" placeholder="IVANOV" type="text">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">Please, enter contact information :</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                Email *
                            </span>
                        </div>
                        <input name="email" class="form-control" placeholder="email@example.com" type="email">
                    </div> <!-- input-group.// -->

                </div> <!-- form-group// -->
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-mobile-phone">

                                </i>
                            </span>
                        </div>
                        <input name="phone" class="form-control" placeholder="+375(29)111-11-11" type="phone">
                    </div> <!-- input-group.// -->

                </div> <!-- form-group// -->
                <p class="text-left">Enter password *</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-lock">
                                    1
                                </i>
                            </span>
                        </div>
                        <input name="password" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">Confirm password *</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-lock">
                                    2
                                </i>
                            </span>
                        </div>
                        <input name="password2" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <br/>
                <div class="form-group d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div> <!-- form-group// -->
            </form>
        </article>
    </div> <!-- card.// -->
</div>

   <%-- <form id="signUpForm" method="POST" action="controller">
        <input type="hidden" name="command" value="signup"/>
        Login:<br/>
        <input type="text" name="login" value=""/><br/>
        ${emptyField}
        <br/>
        ${loginExist}
        <br/>
        Email:<br/>
        <input type="text" name="email" value=""><br/>
        ${emptyField}
        <br/>
        Phone:<br/>
        <input type="text" name="phone" value=""><br/>
        ${emptyField}
        <br/>
        Name:<br/>
        <input type="text" name="first_name" value=""><br/>
        ${emptyField}
        <br/>
        Surname:<br/>
        <input type="text" name="last_name" value=""><br/>
        ${emptyField}
        <br/>
        Password:<br/>
        <input type="password" name="password" value=""/>
        ${emptyField}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="submit" value="Sign up"/>
    </form>--%>

</body>
</html>