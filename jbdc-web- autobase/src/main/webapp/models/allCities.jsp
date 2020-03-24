<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<jsp:include page="/header.jsp"/>

<div id="sidebar1" class="aside">
    <jsp:include page="/leftSidePage.jsp"/>
</div>
<div id="article">
    <table border="1">
        <tr>
            <th><fmt:message key="allCities.th.name" bundle="${msg}"/></th>
        </tr>
        <c:forEach items="${citiesList}" var="city">
            <tr>
                <td>${city.name}</td>
                <c:if test="${(sessionScope.role == 'ADMIN') || (sessionScope.role == 'DISPATCHER')}">
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/city.DeleteCity">
                            <input type="hidden" name="city_id" value="${city.id}"/>
                            <button type="submit" class="btn"><fmt:message key="button.delete"
                                                                           bundle="${msg}"/></button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/city.GetUpdateCityPage">
                            <input type="hidden" name="city_id" value="${city.id}"/>
                            <button type="submit" class="btn"><fmt:message key="button.update"
                                                                           bundle="${msg}"/></button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<jsp:include page="/footer.jsp"/>
</body>
</html>

