<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.admin.tables.users.items_on_page_message" var="items_on_page_message" />

<div class="row mx-1 col-3 align-items-center">
    <label>
        <select class="form-control rounded-0" onchange="top.location=this.value">
            <option value="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=10&page=1" <c:if test="${pageDTO.elementsOnPage eq 10}">selected="selected"</c:if>>
                10
            </option>
            <option value="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=25&page=1" <c:if test="${pageDTO.elementsOnPage eq 25}">selected="selected"</c:if>>
                25
            </option>
            <option value="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=50&page=1" <c:if test="${pageDTO.elementsOnPage eq 50}">selected="selected"</c:if>>
                50
            </option>
        </select>
    </label>
    <h6 class="mx-2">${ items_on_page_message}</h6>
</div>