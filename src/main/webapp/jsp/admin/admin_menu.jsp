<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${role.name eq 'Admin'}">
        <div class="left-container float-left col-2 px-0 left-admin-menu">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <div id="MainMenu">
                    <div class="list-group panel ">
                        <a href="#demo1" class="list-group-item font-weight-bold text-muted" data-toggle="collapse" data-parent="#MainMenu">
                            <i class="fa fa-area-chart"></i> Statistics <i class="fa fa-caret-down"></i>
                        </a>
                        <div class="collapse list-group-submenu" id="demo1">
                            <a href="${pageContext.request.contextPath}/admin/statistics/income_expenses" class="list-group-item text-muted">Income and expenses</a>
                            <a href="${pageContext.request.contextPath}/admin/statistics/users_visits" class="list-group-item text-muted">Users visits</a>
                        </div>
                        <a href="#demo2" class="list-group-item font-weight-bold text-muted" data-toggle="collapse" data-parent="#MainMenu">
                            <i class="fa fa-table"></i> Tables <i class="fa fa-caret-down"></i>
                        </a>
                        <div class="collapse list-group-submenu" id="demo2">
                            <a href="${pageContext.request.contextPath}/admin/tables/accidents" class="list-group-item text-muted">Accidents</a>
                            <a href="${pageContext.request.contextPath}/controller?command=cars_table" class="list-group-item text-muted">Cars</a>
                            <a href="${pageContext.request.contextPath}/controller?command=orders_table" class="list-group-item text-muted">Orders</a>
                            <a href="${pageContext.request.contextPath}/controller?command=users_table" class="list-group-item text-muted">Users</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
