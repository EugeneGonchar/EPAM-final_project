<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/jsp/header.jsp" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="import" href="header.jsp">
    <title>Welcome</title>

</head>
<body>

<jsp:include page = "/jsp/header.jsp"></jsp:include>

<div class="padding-top">
    <div  class="card container col-lg-3">
        <article class="card-body">
            <h4 class="card-title text-center mb-4 mt-1">Sign in</h4>
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

            <form id="loginForm" method="POST" action="controller">
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-user">

                                </i>
                            </span>
                        </div>
                        <input type="hidden" name="command" value="login"/>
                        <input name="login" class="form-control" placeholder="Login" type="text">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">
                                <i class="fa fa-lock">

                                </i>
                            </span>
                        </div>
                        <input name="password" class="form-control" placeholder="******" type="password" value="">
                    </div> <!-- input-group.// -->
                </div> <!-- form-group// -->
                <br/>
                <div class="form-group d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div> <!-- form-group// -->
                <p class="text-center"><a href="#" class="btn">Forgot password?</a></p>
            </form>
        </article>
    </div> <!-- card.// -->
</div>



</div>
</body>
</html>