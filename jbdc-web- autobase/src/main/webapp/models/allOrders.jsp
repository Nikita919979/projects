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
                <th><fmt:message key="allOrders.th.number" bundle="${msg}"/></th>
                <th><fmt:message key="allOrders.th.carModel" bundle="${msg}"/></th>
                <th><fmt:message key="allOrders.th.city.from" bundle="${msg}"/></th>
                <th><fmt:message key="allOrders.th.city.to" bundle="${msg}"/></th>
                <th><fmt:message key="allOrders.th.user" bundle="${msg}"/></th>
            </tr>
            <c:forEach items="${ordersList}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.carModel}</td>
                    <td>${order.city_from.name}</td>
                    <td>${order.city_to.name}</td>
                    <td>${order.user.name}</td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/order.DeleteOrder">
                            <input type="hidden" name="order_id" value="${order.id}"/>
                            <button type="submit" class="btn"><fmt:message key="button.delete"
                                                                           bundle="${msg}"/></button>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/order.GetUpdateOrderPage">
                            <input type="hidden" name="order_id" value="${order.id}"/>
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

