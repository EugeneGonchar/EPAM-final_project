<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.admin.tables.users.users_message" var="users_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.items_on_page_message" var="items_on_page_message" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.id_column" var="id_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.name_column" var="name_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.surname_column" var="surname_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.login_column" var="login_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.email_column" var="email_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.phone_column" var="phone_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.role_column" var="role_column" />
<fmt:message bundle="${loc}" key="local.admin.tables.users.actions_column" var="actions_column" />

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=1200, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/style.css">
    <title>Users</title>
</head>
<body>

<jsp:include page="/jsp/header.jsp" />
<jsp:include page="/jsp/admin/admin_menu.jsp" />

<div class="container my-4">
    <div class="container">
        <div class="card border rounded-0">
            <h5 class="card-header mb-2">${users_message}</h5>

            <c:set var="command" scope="session" value="users_table"/>
            <%@ include file = "/jsp/pagination/items_on_page.jsp" %>

            <table class="table col-8">
                <thead class="thead-light">
                <tr>
                    <th scope="col" class="align-middle text-center">#</th>
                    <th scope="col" class="align-middle">${name_column} ${surname_column}</th>
                    <th scope="col" class="align-middle">${login_column}</th>
                    <th scope="col" class="align-middle text-center">${email_column}</th>
                    <th scope="col" class="align-middle text-center">${phone_column}</th>
                    <th scope="col" class="align-middle text-center">${role_column}</th>
                    <th scope="col" class="align-middle text-center">${actions_column}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userRoleDTO" items="${userRoleDTOList}" varStatus="status">
                    <tr>
                        <th scope="row" class="align-middle text-center">${(pageDTO.currentPage-1) * pageDTO.elementsOnPage + status.count}</th>
                        <td class="align-middle">${userRoleDTO.firstName} ${userRoleDTO.lastName}</td>
                        <td class="align-middle">${userRoleDTO.login}</td>
                        <td class="align-middle">${userRoleDTO.email}</td>
                        <td class="align-middle text-center">${userRoleDTO.phone}</td>
                        <td class="align-middle text-center">${userRoleDTO.role}</td>
                        <td class="align-middle text-center">
                            <button class="btn btn-success btn-sm">
                                <i class="fa fa-edit"></i>
                            </button>
                            <button class="btn btn-danger btn-sm">
                                block
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav class="card-footer">

                <%@ include file = "/jsp/pagination/pagination.jsp" %>

            </nav>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js" type="text/javascript"></script>


</body>
</html>