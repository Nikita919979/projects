<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="inf" uri="taglib" %>
<fmt:setLocale value="${sessionScope['locale']}" scope="session"/>
<fmt:setBundle basename="${sessionScope['bundleFile']}" var="msg" scope="session"/>
<c:set var="role" value="${sessionScope.role}" scope="session"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<div>
    <h2 class="header_main_text"><fmt:message key="header.main.name" bundle="${msg}"/></h2>
    <form id="changeLocale" name="changeLocale" style="margin-block-end: 1em">
        <select class="btn" id="language" name="language" onchange="this.form.submit()">
            <option
                    <c:if test="${sessionScope['locale'].getLanguage().equals('en')}">
                        selected
                    </c:if>
                    value="en"><fmt:message key="header.language.en" bundle="${msg}"/></option>
            <option
                    <c:if test="${sessionScope['locale'].getLanguage().equals('ru')}">
                        selected
                    </c:if>
                    value="ru"><fmt:message key="header.language.ru" bundle="${msg}"/>
            </option>
        </select>
    </form>
    <c:if test="${sessionScope.login != null}">
        <h1><inf:HelloUser/></h1>
    </c:if>
</div>

<div id="nav">
    <ul>
        <div>
            <li><a href="index.jsp"><fmt:message key="header.main.page" bundle="${msg}"/></a></li>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <li><a href="${pageContext.request.contextPath}/user.GetAllUsers"><fmt:message
                            key="header.admin.button.allUsers"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/transit.GetAllTransits"><fmt:message
                            key="header.admin.button.allTransits"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/car.GetAllCars"><fmt:message
                            key="header.admin.button.allCars"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/city.GetAllCities"><fmt:message
                            key="header.admin.button.allCities"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/order.GetAllOrders"><fmt:message
                            key="header.admin.button.newOrder"
                            bundle="${msg}"/></a></li>
                </c:when>
                <c:when test="${sessionScope.role == 'DISPATCHER'}">
                    <li><a href="${pageContext.request.contextPath}/transit.GetAllTransits"><fmt:message
                            key="header.admin.button.allTransits"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/car.GetAllCars"><fmt:message
                            key="header.admin.button.allCars"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/city.GetAllCities"><fmt:message
                            key="header.admin.button.allCities"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/order.GetAllOrders"><fmt:message
                            key="header.admin.button.newOrder"
                            bundle="${msg}"/></a></li>
                </c:when>
                <c:when test="${sessionScope.role == 'DRIVER'}">
                    <li><a href="${pageContext.request.contextPath}/transit.GetAllTransits"><fmt:message
                            key="header.admin.button.allTransits"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/city.GetAllCities"><fmt:message
                            key="header.admin.button.allCities"
                            bundle="${msg}"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/order.GetAllOrders"><fmt:message
                            key="header.admin.button.newOrder"
                            bundle="${msg}"/></a></li>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.login == null}">
                    <li><a href="signIn.jsp"><fmt:message key="header.button.signin" bundle="${msg}"/></a></li>
                    <li><a href="registration.jsp"><fmt:message key="header.button.signup" bundle="${msg}"/></a></li>
                </c:when>
                <c:otherwise>
                    <li>
                    <li><a href="${pageContext.request.contextPath}/Logout"><fmt:message key="header.button.signout"
                                                                                         bundle="${msg}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </div>
    </ul>
</div>
</body>
</html>
