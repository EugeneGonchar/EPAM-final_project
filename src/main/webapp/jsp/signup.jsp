<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.signup.registration_message" var="registration_message" />
<fmt:message bundle="${loc}" key="local.signup.login_message" var="login_message" />
<fmt:message bundle="${loc}" key="local.signup.passport_message" var="passport_message" />
<fmt:message bundle="${loc}" key="local.signup.name_message" var="name_message" />
<fmt:message bundle="${loc}" key="local.signup.surname_message" var="surname_message" />
<fmt:message bundle="${loc}" key="local.signup.contact_info_message" var="contact_info_message" />
<fmt:message bundle="${loc}" key="local.signup.email_message" var="email_message" />
<fmt:message bundle="${loc}" key="local.signup.enter_password_message" var="enter_password_message" />
<fmt:message bundle="${loc}" key="local.signup.confirm_password_message" var="confirm_password_message" />
<fmt:message bundle="${loc}" key="local.signup.submit_button" var="submit_button" />

<!doctype html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">

    <title>Sign up</title>
</head>

<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="padding-top-registration">
    <div  class="card container col-lg-3">
        <article class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">${registration_message}</h4>
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
                <input type="hidden" name="command" value="signup"/>
                <p class="text-left">${login_message}:</p>
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

                <p class="text-left">${passport_message}:</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${name_message}*
                            </span>
                        </div>
                        <input name="firstName" class="form-control" placeholder="IVAN" type="text">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${surname_message}*
                            </span>
                        </div>
                        <input name="lastName" class="form-control" placeholder="IVANOV" type="text">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">${contact_info_message}:</p>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${email_message}*
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
                        <input name="phone" class="form-control" placeholder="+375(29)111-11-11" type="tel">
                    </div> <!-- input-group.// -->

                </div> <!-- form-group// -->
                <p class="text-left">${enter_password_message}*</p>
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

                <p class="text-left">${confirm_password_message}*</p>
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
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div> <!-- form-group// -->

                <br/>
                <div class="form-group d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">${submit_button}</button>
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
