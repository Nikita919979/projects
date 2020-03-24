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
        <div style="text-align: center" class="default_main_text">
            <h3><fmt:message key="newOrder.main.name" bundle="${msg}"/></h3>
        </div>
        <div align="center" style="margin: auto;">
            <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/order.NewOrder">
                <div class="default_div">
                    <div style="padding-top: 5px">
                        <div>
                            <label for="carModel"><fmt:message key="allOrders.th.carModel" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <select name="carModel" id="carModel">
                                <c:forEach items="${carModels}" var="model">
                                    <option value="${model}">${model}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div style="padding-top: 5px">
                        <div>
                            <label for="city_from"><fmt:message key="allOrders.th.city.from" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <select name="city_from" id="city_from">
                                <c:forEach items="${cityList}" var="city">
                                    <option value="${city.id}">${city.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div style="padding-top: 5px">
                        <div>
                            <label for="city_to"><fmt:message key="allOrders.th.city.to" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <select name="city_to" id="city_to">
                                <c:forEach items="${cityList}" var="city">
                                    <option value="${city.id}">${city.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div style="padding-top: 5px">
                        <button type="submit" class="btn"><fmt:message key="button.create"
                                                                       bundle="${msg}"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
