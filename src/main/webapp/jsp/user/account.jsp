<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.account.personal_settings_message" var="personal_settings_message" />
<fmt:message bundle="${loc}" key="local.account.profile_button" var="profile_button" />
<fmt:message bundle="${loc}" key="local.account.account_button" var="account_button" />
<fmt:message bundle="${loc}" key="local.account.email_and_phone_button" var="email_and_phone_button" />
<fmt:message bundle="${loc}" key="local.account.change_login_message" var="change_login_message" />
<fmt:message bundle="${loc}" key="local.account.login_message" var="login_message" />
<fmt:message bundle="${loc}" key="local.account.new_login_message" var="new_login_message" />
<fmt:message bundle="${loc}" key="local.account.change_login_button" var="change_login_button" />
<fmt:message bundle="${loc}" key="local.account.change_password_message" var="change_password_message" />
<fmt:message bundle="${loc}" key="local.account.old_password_message" var="old_password_message" />
<fmt:message bundle="${loc}" key="local.account.new_password_message" var="new_password_message" />
<fmt:message bundle="${loc}" key="local.account.confirm_password_message" var="confirm_password_message" />
<fmt:message bundle="${loc}" key="local.account.change_password_button" var="change_password_button" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Account</title>
</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-3">
    <div class="col-3 float-left pr-4" role="navigation">

        <div class="border rounded">
            <nav class="menu">
                <ul class="navbar-nav">
                        <span class="border-bottom bg-light">
                            <li class="pl-3 nav-item text-dark">
                                <a class="nav-link disabled text-secondary" href="#">${personal_settings_message}</a>
                            </li>
                        </span>
                    <span class="border-bottom active">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/profile">${profile_button}</a>
                            </li>
                        </span>
                    <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link text-dark" href="${pageContext.request.contextPath}/user/account">${account_button}</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/contacts">${email_and_phone_button}</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>
    </div>

    <div class="col-9 float-left">

        <%--<div class="card border-0 col-12">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">${change_login_message}</h3>
            </div>

            <form id="changeLoginForm" method="POST" action="${pageContext.request.contextPath}/controller" class="col-8">
                <input type="hidden" name="command" value="change_login"/>
                    <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                        <div>
                            <p class="note">
                                <span class="font-weight-bold">${login_message}: </span>
                                <p>${user.login}</p>
                            </p>
                        </div>
                    </div>

                    <div>
                        <c:choose>
                            <c:when test="${empty updateLoginError}">
                                <p>
                                    <br/>
                                </p>
                            </c:when>
                            <c:otherwise>
                                <p class="text-danger text-left">
                                        ${updateLoginError}
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="form-group">
                        <label for="b" class="font-weight-bold">${new_login_message}:</label>
                        <input name="login" placeholder="login" type="text" class="form-control" id="b">
                    </div>

                    <div class="form-group mt-1">
                        <button class="btn btn-light border" type="submit">${change_login_button}</button>
                    </div>

            </form>
        </div>--%>

        <div class="card border-0 col-12 mt-3">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">${change_password_message}</h3>
            </div>

            <form id="changePasswordForm" method="POST" action="${pageContext.request.contextPath}/controller" class="col-8">
                <input type="hidden" name="command" value="change_password"/>
                <div>
                    <c:choose>
                        <c:when test="${empty updatePasswordError}">
                            <p>
                                <br/>
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="text-danger text-left">
                                    ${updatePasswordError}
                            </p>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="form-group mt-1">
                    <label for="pass" class="font-weight-bold">${old_password_message}:</label>
                    <input name="oldPassword" placeholder="******" type="password" class="form-control" id="pass">
                </div>

                <div class="form-group mt-1">
                    <label for="pass1" class="font-weight-bold">${new_password_message}:</label>
                    <input name="newPassword" placeholder="******" type="password" class="form-control" id="pass1">
                </div>

                <div class="form-group mt-1">
                    <label for="pass2" class="font-weight-bold">${confirm_password_message}:</label>
                    <input name="newPassword2" placeholder="******" type="password" class="form-control" id="pass2">
                </div>

                <div class="form-group mt-1">
                    <button class="btn btn-light border" type="submit">${change_password_button}</button>
                </div>

            </form>
        </div>


    </div>


</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>
