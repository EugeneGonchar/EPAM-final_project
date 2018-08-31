<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.order.menu.date_and_place" var="date_and_place" />
<fmt:message bundle="${loc}" key="local.order.menu.cars_search" var="cars_search" />
<fmt:message bundle="${loc}" key="local.order.menu.driver" var="driver" />
<fmt:message bundle="${loc}" key="local.order.menu.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.order.menu.confirmation" var="confirmation" />
<fmt:message bundle="${loc}" key="local.order.payment.payment_details" var="payment_details" />
<fmt:message bundle="${loc}" key="local.order.payment.name_message" var="name_message" />
<fmt:message bundle="${loc}" key="local.order.payment.card_number_message" var="card_number_message" />
<fmt:message bundle="${loc}" key="local.order.order_details_message" var="order_details_message" />
<fmt:message bundle="${loc}" key="local.order.pickup_message" var="pickup_message" />
<fmt:message bundle="${loc}" key="local.order.dropoff_message" var="dropoff_message" />
<fmt:message bundle="${loc}" key="local.order.car_message" var="car_message" />
<fmt:message bundle="${loc}" key="local.order.confirmation.driver_details_message" var="driver_details_message" />
<fmt:message bundle="${loc}" key="local.order.driver.passport_name_surname_info" var="passport_name_surname_info" />
<fmt:message bundle="${loc}" key="local.order.driver.name_message" var="name_message" />
<fmt:message bundle="${loc}" key="local.order.driver.surname_message" var="surname_message" />
<fmt:message bundle="${loc}" key="local.order.driver.contact_info_message" var="contact_info_message" />
<fmt:message bundle="${loc}" key="local.order.driver.email_message" var="email_message" />
<fmt:message bundle="${loc}" key="local.order.driver.submit_button" var="submit_button" />
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
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>DriverDetails</title>

</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-3 px-0">
    <nav class="nav row border rounded text-center">
        <a class="nav-item nav-link col border-right" href="${pageContext.request.contextPath}/controller?command=get_locations">${date_and_place}<i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right" href="${pageContext.request.contextPath}/controller?command=get_cars&elementsOnPage=10&page=1">${cars_search}<i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right bg-light" href="${pageContext.request.contextPath}/driverdetails">${driver}</a>
        <a class="nav-item nav-link col border-right disabled" href="#">${payment}</a>
        <a class="nav-item nav-link col disabled" href="#">${confirmation}</a>
    </nav>
</div>

<div class="container my-3">
    <div class="col-3 float-left pr-3 invisible" role="navigation">

        <div class="border rounded mb-3">
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

        <div id="accordion" class="border rounded">
            <div class="card border-0 border-bottom">
                <div class="card-header py-1" id="headingOne">
                    <h6 class="mb-0">
                        <div class="collapsed dropdown-toggle" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                            Разворачиваемая панель #1
                        </div>
                    </h6>
                </div>

                <div id="collapseOne" class="collapse <%--show--%>" aria-labelledby="headingOne" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>

            <div class="card border-0 border-bottom">
                <div class="card-header py-1" id="headingTwo">
                    <h6 class="mb-0">
                        <div class="collapsed dropdown-toggle" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            Разворачиваемая панель #2
                        </div>
                    </h6>
                </div>

                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>

            <div class="card border-0">
                <div class="card-header py-1" id="headingThree">
                    <h6 class="mb-0">
                        <div class="collapsed dropdown-toggle" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            Разворачиваемая панель #3
                        </div>
                    </h6>
                </div>

                <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">
                    <div class="card-body">
                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-9 float-left">

        <div class="card col-auto my-3">
            <div class="row border-bottom">
                <div class="col-4 align-self-center">
                    <div>
                        <c:choose>
                            <c:when test="${empty car.image}">
                                <img width="190" height="130" class="rounded d-block" alt="" src="../img/uploads/car/default.png">
                            </c:when>
                            <c:otherwise>
                                <img width="190" height="130" class="rounded d-block" alt="" src="../img/uploads/car/${car.image}">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="p-0 col-8 pr-2">
                    <div class="mb-0 p-0 col">
                        <div class="my-1 border-bottom">
                            <h4>${order_details_message}</h4>
                        </div>
                        <div class="container mx-0 my-2">
                            <div class="py-2 border-bottom">
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1 font-weight-bold">${car_message}:</h6>
                                    </div>
                                    <div class="col-8">
                                        <h6 class="mb-1">${car.brand} ${car.model}</h6>
                                    </div>
                                </div>
                            </div>

                            <div class="py-2 border-bottom">
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1 font-weight-bold">${pickup_message}:</h6>
                                    </div>
                                    <div class="col-8">
                                        <h6 class="mb-1">${pickupAddressOfOrder.street} ${pickupAddressOfOrder.building}</h6>
                                    </div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1"></h6>
                                    </div>
                                    <div class="col-8">
                                        <h6 class="mb-1"><fmt:formatDate value="${order.dateReceived}" pattern="yyyy-MM-dd HH:mm" /></h6>
                                    </div>
                                </div>
                            </div>

                            <div class="py-2">
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1 font-weight-bold">${dropoff_message}:</h6>
                                    </div>
                                    <div class="col-8">
                                        <h6 class="mb-1">${dropoffAddressOfOrder.street} ${dropoffAddressOfOrder.building}</h6>
                                    </div>
                                </div>
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1"></h6>
                                    </div>
                                    <div class="col-8">
                                        <h6 class="mb-1"><fmt:formatDate value="${order.returnDate}" pattern="yyyy-MM-dd HH:mm" /></h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div class="card col-12 mt-3">

            <div class="mt-2 mb-0 border-bottom border-muted">
                <h3 class="text-dark">${driver_details_message}</h3>
            </div>

            <c:choose>
                <c:when test="${empty user}">
                    <div class="row justify-content-center">
                        <form id="enterDriverDetails" method="POST" action="${pageContext.request.contextPath}/controller" class="col-8">
                            <input type="hidden" name="command" value="driver_details"/>
                            <div>
                                <c:choose>
                                    <c:when test="${empty driverDetailsError}">
                                        <p>
                                            <br/>
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-danger text-left">
                                                ${driverDetailsError}
                                        </p>
                                        <c:remove var="driverDetailsError" scope="session"/>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <p class="text-left">${passport_name_surname_info}:</p>
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
                            </div>

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
                            </div>

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

                            <div class="form-group d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary">${submit_button}</button>
                            </div>

                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row justify-content-center">
                        <form method="POST" action="${pageContext.request.contextPath}/payment" class="col-8">
                            <p>
                                <br/>
                            </p>

                            <p class="text-left">${passport_name_surname_info}:</p>
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                            <span class="input-group-text">
                                ${name_message}*
                            </span>
                                    </div>
                                    <input name="firstName" class="form-control" placeholder="${user.firstName}" type="text" disabled>
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->

                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">
                                            ${surname_message}*
                                        </span>
                                    </div>
                                    <input name="lastName" class="form-control" placeholder="${user.lastName}" type="text" disabled>
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
                                    <input name="email" class="form-control" placeholder="${user.email}" type="email" disabled>
                                </div> <!-- input-group.// -->

                            </div> <!-- form-group// -->
                            <div class="form-group">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">
                                            <i class="fa fa-mobile-phone"></i>
                                        </span>
                                    </div>
                                    <input name="phone" class="form-control" placeholder="${user.phone}" type="phone" disabled>
                                </div> <!-- input-group.// -->
                            </div> <!-- form-group// -->

                            <div class="form-group d-flex justify-content-center">
                                <button type="submit" class="btn btn-primary">${submit_button}</button>
                            </div>

                        </form>
                    </div>
                </c:otherwise>
            </c:choose>



        </div>


    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>
<script src="../plugin/maskedinput/jquery.maskedinput.js"></script>
<script src="../js/script.js"></script>
<script src="../js/validation/common.js"></script>
<script src="../js/validation/page/driverdetails.js"></script>

</body>
</html>