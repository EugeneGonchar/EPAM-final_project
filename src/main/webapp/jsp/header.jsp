<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.header.home_button" var="home_button" />
<fmt:message bundle="${loc}" key="local.header.rent_button" var="rent_button" />
<fmt:message bundle="${loc}" key="local.header.about_button" var="about_button" />
<fmt:message bundle="${loc}" key="local.header.hello_message" var="hello_message" />
<fmt:message bundle="${loc}" key="local.header.signin_as" var="signin_as" />
<fmt:message bundle="${loc}" key="local.header.your_profile_button" var="your_profile_button" />
<fmt:message bundle="${loc}" key="local.header.your_orders_button" var="your_orders_button" />
<fmt:message bundle="${loc}" key="local.header.help_button" var="help_button" />
<fmt:message bundle="${loc}" key="local.header.signout_button" var="signout_button" />
<fmt:message bundle="${loc}" key="local.header.sign_up_button" var="sign_up_button" />
<fmt:message bundle="${loc}" key="local.header.login_button" var="login_button" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">

    <title>Header</title>

</head>
<body>

    <div class="bg-dark container-fluid">
        <nav class="navbar navbar-expand-sm navbar-dark <%--fixed-top--%>">
            <div class="d-flex ml-auto justify-content-start col-lg-4">
                <ul class="navbar-nav">
                    <li class="nav-item px-2<%--active--%>">
                        <a class="nav-link" href="#">
                            <%--<img src="../img/Travel_3.svg" width="40px" height="40px">--%>
                            <i class="fa fa-car" style="font-size:20px;"></i>
                        </a>
                    </li>
                    <li class="nav-item px-2<%--active--%>">
                        <a class="nav-link" href="${pageContext.request.contextPath}/main">
                            <i class="fa fa-home">

                            </i>
                            <c:out value="${home_button}"/>

                        </a>
                    </li>
                    <li class="nav-item px-2">
                        <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=get_locations">${rent_button}</a>
                    </li>
                    <li class="nav-item px-2">
                        <a class="nav-link" href="#">${about_button}</a>
                    </li>
                </ul>
            </div>


            <div class="d-flex mr-sm-auto justify-content-end col-lg-4">
                <ul class="navbar-nav">

                    <li class="nav-item dropdown px-2">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-language">
                                <%--засунуть язык--%>
                            </i>
                            <c:choose>
                                <c:when test="${empty sessionScope.local}">
                                    (ru)
                                </c:when>
                                <c:otherwise>
                                    (${sessionScope.local})
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=language&lang=en">English</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=language&lang=ru">Русский</a>
                        </div>
                    </li>

                    <c:choose>
                        <c:when test="${not empty user}">
                            <li class="nav-item px-2">
                                <a class="nav-link invisible" href="${pageContext.request.contextPath}/signup">
                                    <i class="fa fa-user-plus">

                                    </i>
                                    ${sign_up_button}
                                </a>
                            </li>

                            <li class="nav-item dropdown px-2">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    ${hello_message},
                                    <c:out value="${user.login}">

                                    </c:out>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                                    <h6 class="dropdown-header">${signin_as}</h6>
                                    <span class="dropdown-item-text font-weight-bold" href="#">
                                        ${user.firstName }
                                        ${user.lastName}
                                    </span>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile">${your_profile_button}</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=get_orders">${your_orders_button}</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#">${help_button}</a>
                                    <form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/controller">
                                        <input type="hidden" name="command" value="logout"/>
                                        <button class="dropdown-item" type="submit">${signout_button}</button>
                                    </form>
                                </div>
                            </li>

                        </c:when>
                        <c:otherwise>
                            <li class="nav-item px-2">
                                <a class="nav-link" href="${pageContext.request.contextPath}/signup">
                                    <i class="fa fa-user-plus">

                                    </i>
                                    ${sign_up_button}
                                </a>
                            </li>
                            <li class="nav-item px-2">
                                <a class="nav-link" href="${pageContext.request.contextPath}/login">
                                    <i class="fa fa-sign-in">

                                    </i>
                                    ${login_button}
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </nav>
    </div>





<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>
