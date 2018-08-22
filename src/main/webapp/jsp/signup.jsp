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
<fmt:message bundle="${loc}" key="local.signup.not_empty_req" var="not_empty_req" />
<fmt:message bundle="${loc}" key="local.signup.length_3_50_req" var="length_3_50_req" />
<fmt:message bundle="${loc}" key="local.signup.length_1_100_req" var="length_1_100_req" />
<fmt:message bundle="${loc}" key="local.signup.lower_upper_req" var="lower_upper_req" />
<fmt:message bundle="${loc}" key="local.signup.length_100_req" var="length_100_req" />
<fmt:message bundle="${loc}" key="local.signup.email_pattern_req" var="email_pattern_req" />
<fmt:message bundle="${loc}" key="local.signup.length_6_50_req" var="length_6_50_req" />
<fmt:message bundle="${loc}" key="local.signup.one_number_req" var="one_number_req" />
<fmt:message bundle="${loc}" key="local.signup.one_lowercase_req" var="one_lowercase_req" />
<fmt:message bundle="${loc}" key="local.signup.one_uppercase_req" var="one_uppercase_req" />
<fmt:message bundle="${loc}" key="local.signup.one_special_symbol_req" var="one_special_symbol_req" />
<fmt:message bundle="${loc}" key="local.signup.passwords_equals_req" var="passwords_equals_req" />

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
                        <c:remove var="registrationError" scope="session"/>
                    </c:otherwise>
                </c:choose>
            </div>

            <form id="signUpForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="signup"/>
                <p class="text-left">${login_message}:</p>
                <div class="form-group">
                    <div id="input-login" class="input-group input-validation">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user">

                                </i>
                            </span>
                        </div>
                        <input id="loginInput" name="login" minlength="3" maxlength="50" class="form-control" placeholder="login" type="text" aria-describedby="loginMessage" required>
                        <div class="w-100"></div>
                        <div id="loginMessage" class="input-requirements text-muted">
                            <small class="form-text">${not_empty_req}</small>
                            <small class="form-text">${length_3_50_req}</small>
                        </div>
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">${passport_message}:</p>
                <div class="form-group">
                    <div id="input-name" class="input-group input-validation">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${name_message}*
                            </span>
                        </div>
                        <input id="nameInput" name="firstName" minlength="1" maxlength="100" class="form-control" placeholder="IVAN" type="text" aria-describedby="nameMessage" required>
                        <div class="w-100"></div>
                        <div id="nameMessage" class="input-requirements text-muted">
                            <small class="form-text">${not_empty_req}</small>
                            <small class="form-text">${length_1_100_req}</small>
                            <small class="form-text">${lower_upper_req}</small>
                        </div>
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <div class="form-group">
                    <div id="input-surname" class="input-group input-validation">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${surname_message}*
                            </span>
                        </div>
                        <input id="surnameInput" name="lastName" minlength="1" maxlength="100" class="form-control" placeholder="IVANOV" type="text" aria-describedby="surnameMessage" required>
                        <div class="w-100"></div>
                        <div id="surnameMessage" class="input-requirements text-muted">
                            <small class="form-text">${not_empty_req}</small>
                            <small class="form-text">${length_1_100_req}</small>
                            <small class="form-text">${lower_upper_req}</small>
                        </div>
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">${contact_info_message}:</p>
                <div class="form-group">
                    <div id="input-email" class="input-group input-validation">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${email_message}*
                            </span>
                        </div>
                        <input id="emailInput" name="email" maxlength="100" class="form-control" placeholder="email@example.com" type="email" aria-describedby="emailMessage" required>
                        <div class="w-100"></div>
                        <div id="emailMessage" class="input-requirements text-muted">
                            <small class="form-text">${not_empty_req}</small>
                            <small class="form-text">${length_100_req}</small>
                            <small class="form-text">${email_pattern_req}</small>
                        </div>
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <div class="form-group">
                    <div id="input-phone" class="input-group input-validation">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-mobile-phone">

                                </i>
                            </span>
                        </div>
                        <input id="phoneInput" name="phone" class="form-control" placeholder="+375(29)111-11-11" type="text" aria-describedby="phoneMessage" required>
                        <div class="w-100"></div>
                        <div id="phoneMessage" class="input-requirements text-muted">
                            <small class="form-text">${not_empty_req}</small>
                        </div>
                    </div> <!-- input-group.// -->
                </div>

                <p class="text-left">${enter_password_message}*</p>
                <div class="form-group">
                    <div id="input-password" class="input-group input-validation">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-lock">
                                    1
                                </i>
                            </span>
                        </div>
                        <input id="passwordInput" name="password" minlength="6" maxlength="50" class="form-control" placeholder="******" type="password" aria-describedby="passwordMessage" required>
                        <div class="w-100"></div>
                        <div id="passwordMessage" class="input-requirements text-muted">
                            <small class="form-text">${not_empty_req}</small>
                            <small class="form-text">${length_6_50_req}</small>
                            <small class="form-text">${one_number_req}</small>
                            <small class="form-text">${one_lowercase_req}</small>
                            <small class="form-text">${one_uppercase_req}</small>
                            <small class="form-text">${one_special_symbol_req}</small>
                        </div>
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->

                <p class="text-left">${confirm_password_message}*</p>
                <div class="form-group">
                    <div id="input-password2" class="input-group input-validation">
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
<script src="../plugin/maskedinput/jquery.maskedinput.js"></script>
<script src="../js/script.js"></script>
<script src="../js/validation/common.js"></script>
<script src="../js/validation/page/signup.js"></script>

</body>
</html>
