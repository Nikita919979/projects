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
                <th><fmt:message key="allTransits.th.number" bundle="${msg}"/></th>
                <th><fmt:message key="allTransits.th.status" bundle="${msg}"/></th>
                <th><fmt:message key="allTransits.th.city.from" bundle="${msg}"/></th>
                <th><fmt:message key="allTransits.th.city.to" bundle="${msg}"/></th>
                <th><fmt:message key="allTransits.th.car" bundle="${msg}"/></th>
                <th><fmt:message key="allTransits.th.user" bundle="${msg}"/></th>
                <th><fmt:message key="allTransits.th.driver" bundle="${msg}"/></th>
            </tr>
            <c:forEach items="${transitsList}" var="transit">
                <tr>
                    <td>${transit.id}</td>
                    <td>${transit.status}</td>
                    <td>${transit.city_from.name}</td>
                    <td>${transit.city_to.name}</td>
                    <td>${transit.car.carNumber} ${transit.car.model}</td>
                    <td>${transit.user.name} ${transit.user.familyName}</td>
                    <td>${transit.driver.name} ${driver.user.familyName}</td>
                    <c:if test="${(sessionScope.role == 'ADMIN') || (sessionScope.role == 'DISPATCHER')}">
                        <td>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/transit.DeleteTransit">
                                <input type="hidden" name="transit_id" id="transit_id" value="${transit.id}"/>
                                <button type="submit" class="btn"><fmt:message key="button.delete"
                                                                               bundle="${msg}"/></button>
                            </form>
                        </td>
                    </c:if>
                    <td>
                        <form method="get"
                              action="${pageContext.request.contextPath}/transit.GetUpdateTransitPage">
                            <input type="hidden" name="transitDto_id" value="${transit.id}"/>
                            <button type="submit" class="btn"><fmt:message key="button.update"
                                                                           bundle="${msg}"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>

