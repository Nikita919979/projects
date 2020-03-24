<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<div>
    <jsp:include page="header.jsp"/>
</div>
<div class="wrapper">
    <div id="sidebar1" class="aside">
        <jsp:include page="leftSidePage.jsp"/>
    </div>
    <div id="sidebar2" class="aside">
        <c:choose>
            <c:when test="${sessionScope.login == null}">
                <h3><fmt:message key="index.right.text" bundle="${msg}"/></h3>
                <ul>
                    <li><fmt:message key="index.right.li1" bundle="${msg}"/></li>
                    <li><fmt:message key="index.right.li2" bundle="${msg}"/></li>
                    <li><fmt:message key="index.right.li3" bundle="${msg}"/></li>
                    <li><fmt:message key="index.right.li4" bundle="${msg}"/></li>
                    <li><fmt:message key="index.right.li5" bundle="${msg}"/></li>
                </ul>
            </c:when>
        </c:choose>
    </div>
    <div id="article">
        <c:choose>
            <c:when test="${sessionScope.login == null}">
                <h2><fmt:message key="index.center.text" bundle="${msg}"/></h2>
                <p>
                    <fmt:message key="index.center.login.logout" bundle="${msg}"/>
                </p>
            </c:when>
        </c:choose>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
