<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.admin.menu.statistics_part" var="statistics_part" />
<fmt:message bundle="${loc}" key="local.admin.menu.income_expenses_link" var="income_expenses_link" />
<fmt:message bundle="${loc}" key="local.admin.menu.users_visits_link" var="users_visits_link" />
<fmt:message bundle="${loc}" key="local.admin.menu.tables_part" var="tables_part" />
<fmt:message bundle="${loc}" key="local.admin.menu.accidents_link" var="accidents_link" />
<fmt:message bundle="${loc}" key="local.admin.menu.cars_link" var="cars_link" />
<fmt:message bundle="${loc}" key="local.admin.menu.orders_link" var="orders_link" />
<fmt:message bundle="${loc}" key="local.admin.menu.users_link" var="users_link" />

<c:choose>
    <c:when test="${role.name eq 'Admin'}">
        <div class="left-container float-left col-2 px-0 left-admin-menu">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <div id="MainMenu">
                    <div class="list-group panel ">
                        <a href="#statistics" class="list-group-item font-weight-bold text-muted" data-toggle="collapse" data-parent="#MainMenu">
                            <i class="fa fa-area-chart"></i> ${statistics_part} <i class="fa fa-caret-down"></i>
                        </a>
                        <div class="collapse list-group-submenu" id="statistics">
                            <a href="${pageContext.request.contextPath}/admin/statistics/income_expenses" class="list-group-item text-muted">${income_expenses_link}</a>
                            <a href="${pageContext.request.contextPath}/admin/statistics/users_visits" class="list-group-item text-muted">${users_link}</a>
                        </div>
                        <a href="#tables" class="list-group-item font-weight-bold text-muted" data-toggle="collapse" data-parent="#MainMenu">
                            <i class="fa fa-table"></i> ${tables_part} <i class="fa fa-caret-down"></i>
                        </a>
                        <div class="collapse list-group-submenu" id="tables">
                            <a href="${pageContext.request.contextPath}/controller?command=accidents_table&elementsOnPage=10&page=1" class="list-group-item text-muted">${accidents_link}</a>
                            <a href="${pageContext.request.contextPath}/controller?command=cars_table&elementsOnPage=10&page=1" class="list-group-item text-muted">${cars_link}</a>
                            <a href="${pageContext.request.contextPath}/controller?command=orders_table&elementsOnPage=10&page=1" class="list-group-item text-muted">${orders_link}</a>
                            <a href="${pageContext.request.contextPath}/controller?command=users_table&elementsOnPage=10&page=1" class="list-group-item text-muted">${users_link}</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
