<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Payment</title>

</head>
<body>

<div class="container my-3 px-0">
    <nav class="nav row border rounded text-center">
        <a class="nav-item nav-link col border-right" href="/controller?command=get_locations">Choice of date and place <i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right" href="/controller?command=get_cars">Car's search results <i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right" href="/driverdetails">Driver <i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col border-right" href="/payment">Payment <i class="fa fa-check" style="color: #34ce57"></i></a>
        <a class="nav-item nav-link col bg-light" href="/confirmation">Confirmation</a>
    </nav>
</div>

<div class="container my-3">
    <div class="col-3 float-left pr-3" role="navigation">

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
                        <img width="190" height="130" class="rounded d-block" alt="" src="../img/sport_lrg.jpg">
                    </div>
                </div>

                <div class="p-0 col-8 pr-2">
                    <div class="mb-0 p-0 col">
                        <div class="my-1 border-bottom">
                            <h4>Order details</h4>
                        </div>
                        <div class="container mx-0 my-2">
                            <div class="py-2 border-bottom">
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1 font-weight-bold">Car:</h6>
                                    </div>
                                    <div class="col-8">
                                        <h6 class="mb-1">${car.brand} ${car.model}</h6>
                                    </div>
                                </div>
                            </div>

                            <div class="py-2 border-bottom">
                                <div class="row text-left">
                                    <div class="col-4">
                                        <h6 class="mb-1 font-weight-bold">Pick-up:</h6>
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
                                        <h6 class="mb-1 font-weight-bold">Drop-off:</h6>
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

        <c:choose>
            <c:when test="${not empty user}">
                <div class="card col-12 mt-3">

                    <div class="mt-2 mb-0 border-bottom border-muted">
                        <h4 class="text-dark">Driver details</h4>
                    </div>

                    <div class="row m-1 justify-content-between">
                        <div class="row m-1 align-items-end">
                            <h6>
                                <span class="font-weight-bold">Name:</span> ${user.firstName} ${user.lastName}
                            </h6>
                        </div>

                        <div class="row m-1 align-items-end">
                            <h6>
                                <span class="font-weight-bold">Email:</span> ${user.email}
                            </h6>
                        </div>

                        <div class="row m-1 align-items-end">
                            <h6>
                                <span class="font-weight-bold">Phone:</span> ${user.phone}
                            </h6>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="card col-12 mt-3">

                    <div class="mt-2 mb-0 border-bottom border-muted">
                        <h4 class="text-dark">Driver details</h4>
                    </div>

                    <div class="row m-1 justify-content-between">
                        <div class="row m-1 align-items-end">
                            <h6>
                                <span class="font-weight-bold">Name:</span> ${guestUser.firstName} ${guestUser.lastName}
                            </h6>
                        </div>

                        <div class="row m-1 align-items-end">
                            <h6>
                                <span class="font-weight-bold">Email:</span> ${guestUser.email}
                            </h6>
                        </div>

                        <div class="row m-1 align-items-end">
                            <h6>
                                <span class="font-weight-bold">Phone:</span> ${guestUser.phone}
                            </h6>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="card col-12 mt-3">

            <div class="mt-2 mb-0 border-bottom border-muted">
                <h4 class="text-dark">Payment details</h4>
            </div>

            <div class="row m-1 justify-content-between">
                <div class="row m-1 align-items-end">
                    <h6>
                        <span class="font-weight-bold">Name:</span> Ivan Ivanov
                    </h6>
                </div>

                <div class="row m-1 align-items-end">
                    <h6>
                        <span class="font-weight-bold">Card number:</span> **** **** **** 1111
                    </h6>
                </div>
            </div>

        </div>

        <div class="card col-12 mt-3">

            <div class="mt-2 mb-0 border-bottom border-muted">
                <h3 class="text-dark">Confirmation</h3>
            </div>

            <div class="row justify-content-center">
                <form id="changePasswordForm" method="POST" action="/controller" class="col-8">
                    <input type="hidden" name="command" value="change_password"/>
                    <p class="text-danger my-2">
                        Bla blab alb alb alb lab la dlfkjlasdkbj adslkbj a;ldkgj ldsafk; sdajkf haslfkjhas lfkhja sfdlh sadlfkha sfdlha sfd!
                    </p>
                    <div class="form-group d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Confirm</button>
                    </div>

                </form>
            </div>
        </div>



    </div>
</div>


</body>
</html>