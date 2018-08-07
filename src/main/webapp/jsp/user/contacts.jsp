<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.account.personal_settings_message" var="personal_settings_message" />
<fmt:message bundle="${loc}" key="local.account.profile_button" var="profile_button" />
<fmt:message bundle="${loc}" key="local.account.account_button" var="account_button" />
<fmt:message bundle="${loc}" key="local.account.email_and_phone_button" var="email_and_phone_button" />
<fmt:message bundle="${loc}" key="local.contacts.change_email_message" var="change_email_message" />
<fmt:message bundle="${loc}" key="local.contacts.email_message" var="email_message" />
<fmt:message bundle="${loc}" key="local.contacts.new_email_message" var="new_email_message" />
<fmt:message bundle="${loc}" key="local.contacts.change_email_button" var="change_email_button" />
<fmt:message bundle="${loc}" key="local.contacts.change_phone_message" var="change_phone_message" />
<fmt:message bundle="${loc}" key="local.contacts.phone_message" var="phone_message" />
<fmt:message bundle="${loc}" key="local.contacts.new_phone_message" var="new_phone_message" />
<fmt:message bundle="${loc}" key="local.contacts.change_phone_button" var="change_phone_button" />

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
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/account">${account_button}</a>
                            </li>
                        </span>
                    <span class="">
                            <li class="pl-3 nav-item">
                                <a class="nav-link text-dark" href="${pageContext.request.contextPath}/user/contacts">${email_and_phone_button}</a>
                            </li>
                        </span>
                </ul>
            </nav>
        </div>
    </div>

    <div class="col-9 float-left">

        <div class="card border-0 col-12">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">${change_email_message}</h3>
            </div>

            <form id="changeEmailForm" method="POST" action="${pageContext.request.contextPath}/controller" class="col-8">
                <input type="hidden" name="command" value="change_email"/>
                <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                    <div>
                        <p class="note">
                            <span class="font-weight-bold">${email_message}: </span>
                            <p>${user.email}</p>
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
                    <label for="b" class="font-weight-bold">${new_email_message}:</label>
                    <input name="email" placeholder="example@mail.com" type="text" class="form-control" id="b">
                </div>

                <div class="form-group mt-1">
                    <button class="btn btn-light border" type="submit">${change_email_button}</button>
                </div>

            </form>
        </div>

        <div class="card border-0 col-12 mt-3">

            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">${change_phone_message}</h3>
            </div>

            <form id="changePhoneForm" method="POST" action="/controller" class="col-8">
                <input type="hidden" name="command" value="change_phone"/>
                <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                    <div>
                        <p class="note">
                            <span class="font-weight-bold">${phone_message}: </span>
                        <p>${user.phone}</p>
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
                    <label class="font-weight-bold">${new_phone_message}:</label>
                    <input name="phone" placeholder="+375291000000" type="text" class="form-control">
                </div>

                <div class="form-group mt-1">
                    <button class="btn btn-light border" type="submit">${change_phone_button}</button>
                </div>

            </form>
        </div>


    </div>


</div>

</body>
</html>
