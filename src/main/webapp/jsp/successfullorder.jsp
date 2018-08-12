<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <title>Log in</title>

</head>
<body>

<c:choose>
    <c:when test="${not empty user}">
        <div class="padding-top">
            <div class="card container col-lg-3">
                <article class="card-body">
                    <h4 class="card-title text-center mb-4 mt-1">Congratulation</h4>
                    <hr>

                    <div class="form-group text-center">
                        <span class="text-danger">Note: </span>You can check out order's status on <a href="${pageContext.request.contextPath}/user/orders">Your Orders</a> page.
                    </div>
                    <div class="form-group d-flex justify-content-center">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/main">Ok</a>
                    </div> <!-- form-group// -->
                </article>
            </div> <!-- card.// -->
        </div>
    </c:when>
    <c:otherwise>
        <div class="padding-top">
            <div class="card container col-lg-3">
                <article class="card-body">
                    <h4 class="card-title text-center mb-4 mt-1">Congratulation</h4>
                    <hr>

                    <div class="form-group text-center">
                        <span class="text-danger">Note: </span>You can check out order's status using your new profile:
                        <br/><br/>Login:<h5> ${registeredUser.login}</h5>
                        Password:<h5> ${registeredUser.password}</h5>
                        <br/>
                    </div>
                    <div class="form-group d-flex justify-content-center">
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/main">Ok</a>
                    </div> <!-- form-group// -->
                </article>
            </div> <!-- card.// -->
        </div>
    </c:otherwise>
</c:choose>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>

</body>
</html>