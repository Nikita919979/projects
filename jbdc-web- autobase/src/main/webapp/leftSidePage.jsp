<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope['locale']}" scope="session"/>
<fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg" scope="session"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css">
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="text"/>
    <fmt:setLocale value="${sessionScope['locale']}"/>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.login != null}">
        <h3><fmt:message key="leftSidePage.name.create" bundle="${msg}"/></h3>
        <ul>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <li><a href="${pageContext.request.contextPath}/car.GetNewCarPage"><fmt:message
                            key="leftSidePage.createCar"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/transit.GetNewTransitPage"><fmt:message
                            key="leftSidePage.createTransit"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/user.GetNewUserPage"><fmt:message
                            key="leftSidePage.createUser"
                            bundle="${msg}"/></a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/models/actions/newCity.jsp"><fmt:message
                            key="leftSidePage.createCity"
                            bundle="${msg}"/></a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/order.GetNewOrderPage"><fmt:message
                            key="leftSidePage.createOrder"
                            bundle="${msg}"/></a>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role == 'DISPATCHER'}">
                    <li><a href="${pageContext.request.contextPath}/transit.GetNewTransitPage"><fmt:message
                            key="leftSidePage.createTransit"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/models/actions/newCity.jsp"><fmt:message
                            key="leftSidePage.createCity"
                            bundle="${msg}"/></a>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/order.GetNewOrderPage"><fmt:message
                            key="leftSidePage.createOrder"
                            bundle="${msg}"/></a>
                    </li>
                </c:when>
                <c:when test="${sessionScope.role == 'DRIVER'}">
                    <li><a href="${pageContext.request.contextPath}/order.GetNewOrderPage"><fmt:message
                            key="leftSidePage.createOrder"
                            bundle="${msg}"/></a>
                    </li>
                </c:when>
            </c:choose>
        </ul>
    </c:when>
</c:choose>
</body>
</html>