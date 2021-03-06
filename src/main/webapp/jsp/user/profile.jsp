<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.account.personal_settings_message" var="personal_settings_message" />
<fmt:message bundle="${loc}" key="local.account.profile_button" var="profile_button" />
<fmt:message bundle="${loc}" key="local.account.account_button" var="account_button" />
<fmt:message bundle="${loc}" key="local.account.email_and_phone_button" var="email_and_phone_button" />
<fmt:message bundle="${loc}" key="local.profile.profile_message" var="profile_message" />
<fmt:message bundle="${loc}" key="local.profile.profile_picture_message" var="profile_picture_message" />
<fmt:message bundle="${loc}" key="local.profile.upload_picture_button" var="upload_picture_button" />
<fmt:message bundle="${loc}" key="local.profile.name_message" var="name_message" />
<fmt:message bundle="${loc}" key="local.profile.surname_message" var="surname_message" />
<fmt:message bundle="${loc}" key="local.profile.new_name_message" var="new_name_message" />
<fmt:message bundle="${loc}" key="local.profile.new_surname_message" var="new_surname_message" />
<fmt:message bundle="${loc}" key="local.profile.note_message" var="note_message" />
<fmt:message bundle="${loc}" key="local.profile.update_profile_button" var="update_profile_button" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Profile</title>

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
                            <li class="pl-3 nav-item active">
                                <a class="nav-link text-dark" href="${pageContext.request.contextPath}/user/profile">${profile_button}</a>
                            </li>
                        </span>
                        <span class="border-bottom">
                            <li class="pl-3 nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/user/account">${account_button}</a>
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
            <div class="mt-0 mb-0 border-bottom border-muted">
                <h3 class="text-dark">${profile_message}</h3>
            </div>

            <dl class="form-group edit-profile-avatar mt-3 pl-1 float-right col-4 text-center">
                <dt><label>${profile_picture_message}</label></dt>
                <dd class="avatar-upload-container clearfix">

                    <c:choose>
                        <c:when test="${empty user.profileImage}">
                            <img width="200" height="200" class="avatar rounded-2" alt="" src="../../img/uploads/user/profiledefault.png">
                        </c:when>
                        <c:otherwise>
                            <img width="200" height="200" class="avatar rounded-2" alt="" src="../../img/uploads/user/${user.profileImage}">
                        </c:otherwise>
                    </c:choose>
                    <div class="custom-file mt-3 col-12">
                        <form action="${pageContext.request.contextPath}/controller" method="POST" enctype="multipart/form-data">
                            <input type="hidden" name="command" value="upload_userImg">
                            <label class="custom-file btn btn-block btn-info">
                                ${upload_picture_button}
                                <input type="file" name="userImg" class="custom-file-input" style="display: none">
                            </label>
                            <button type="submit" class="btn btn-block btn-success">
                                Save
                            </button>
                        </form>
                    </div> <!-- /.avatar-upload -->
                </dd>
            </dl>

            <form id="changeNameOrSurnameForm" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="change_name_surname"/>
                <div class="col-7 mr-1 float-left px-0 py-2">
                    <div class="clearfix form-group mt-3 mx-3 px-3 py-0 border rounded">
                        <div>
                            <p>
                                <span class="font-weight-bold">${name_message}: </span>
                                <p>${user.firstName}</p>
                            </p>
                        </div>
                        <div>
                            <p>
                                <span class="font-weight-bold">${surname_message}: </span>
                                <p>${user.lastName}</p>
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
                                        <fmt:message bundle = "${loc}" key = "${updateNameSurnameError}" />
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="form-group mt-3">
                        <label class="font-weight-bold">${new_name_message}:</label>
                        <input name="firstName" placeholder="Ivan" type="text" class="form-control">
                    </div>
                    <div class="form-group mt-3">
                        <label class="font-weight-bold">${new_surname_message}:</label>
                        <input name="lastName" placeholder="Ivanov" type="text" class="form-control">
                    </div>
                    <div>
                        <p class="text-small">
                            ${note_message}
                        </p>
                    </div>
                    <div class="form-group mt-3">
                        <button class="btn btn-success" type="submit">${update_profile_button}</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>
