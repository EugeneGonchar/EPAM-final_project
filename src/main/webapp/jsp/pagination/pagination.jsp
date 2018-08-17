<div class="d-flex justify-content-end">
    <ul class="pagination pagination-sm">
        <li class="page-item <c:if test="${pageDTO.currentPage eq 1}">disabled</c:if>">
            <a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.currentPage - 1}" aria-label="Previous">
                <i class="fa fa-arrow-left" aria-hidden="true"></i>
            </a>
        </li>
        <c:choose>
            <c:when test="${pageDTO.pagesCount le 5}">
                <c:forEach var="i" begin="1" end="${pageDTO.pagesCount}">
                    <c:choose>
                        <c:when test="${i eq pageDTO.currentPage}">
                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${i}">${i}</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${pageDTO.currentPage gt 3 and pageDTO.currentPage lt pageDTO.pagesCount - 2}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=1">1</a></li>
                        <li class="page-item disabled"><a class="page-link" href="#">..</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.currentPage - 1}">${pageDTO.currentPage - 1}</a></li>
                        <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.currentPage}">${pageDTO.currentPage}</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.currentPage + 1}">${pageDTO.currentPage + 1}</a></li>
                        <li class="page-item disabled"><a class="page-link" href="#">..</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.pagesCount}">${pageDTO.pagesCount}</a></li>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${pageDTO.currentPage le 3}">
                                <c:forEach var="i" begin="1" end="4">
                                    <c:choose>
                                        <c:when test="${i eq pageDTO.currentPage}">
                                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <li class="page-item"><a class="page-link" href="#">..</a></li>
                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.pagesCount}">${pageDTO.pagesCount}</a></li>
                            </c:when>
                            <c:when test="${pageDTO.currentPage ge pageDTO.pagesCount - 2}">
                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=1">1</a></li>
                                <li class="page-item disabled"><a class="page-link" href="#">..</a></li>
                                <c:forEach var="i" begin="${pageDTO.pagesCount - 3}" end="${pageDTO.pagesCount}">
                                    <c:choose>
                                        <c:when test="${i eq pageDTO.currentPage}">
                                            <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <li class="page-item <c:if test="${pageDTO.currentPage eq pageDTO.pagesCount}">disabled</c:if>">
            <a class="page-link" href="${pageContext.request.contextPath}/controller?command=${command}&elementsOnPage=${pageDTO.elementsOnPage}&page=${pageDTO.currentPage + 1}" aria-label="Previous">
                <i class="fa fa-arrow-right" aria-hidden="true"></i>
            </a>
        </li>
    </ul>
</div>