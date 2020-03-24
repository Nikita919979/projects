<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="wrapper">
    <div id="sidebar1" class="aside">
        <jsp:include page="/leftSidePage.jsp"/>
    </div>
    <div id="article">
        <table border="1">
            <tr>
                <th><fmt:message key="allCars.th.carNumber" bundle="${msg}"/></th>
                <th><fmt:message key="allCars.th.model" bundle="${msg}"/></th>
                <th><fmt:message key="allCars.th.carTechnicalPassport" bundle="${msg}"/></th>
                <th><fmt:message key="allCars.th.releaseDate" bundle="${msg}"/></th>
                <th><fmt:message key="allCars.th.fully_Functional" bundle="${msg}"/></th>
            </tr>
            <c:forEach items="${carsList}" var="car">
                <tr>
                    <td>${car.carNumber}</td>
                    <td>${car.model}</td>
                    <td>${car.carTechnicalPassport}</td>
                    <td>${car.releaseDate}</td>
                    <td>${car.fully_Functional}</td>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/car.DeleteCar">
                                <input type="hidden" name="car_id" id="car_id" value="${car.id}"/>
                                <button type="submit" class="btn"><fmt:message key="button.delete"
                                                                               bundle="${msg}"/></button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${(sessionScope.role == 'ADMIN') || (sessionScope.role == 'DISPATCHER')}">
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/car.GetUpdateCarPage">
                                <input type="hidden" name="car_id" value="${car.id}"/>
                                <button type="submit" class="btn"><fmt:message key="button.update"
                                                                               bundle="${msg}"/></button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>

