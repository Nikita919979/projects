<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css">
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="wrapper">
    <div id="sidebar1" class="aside">
        <jsp:include page="/leftSidePage.jsp"/>
    </div>
    <div id="sidebar2" class="aside">

    </div>
    <div id="article">
        <div style="text-align: center" class="default_main_text">
            <h3><fmt:message key="updateTransit.main.name" bundle="${msg}"/></h3>
        </div>
        <div align="center" style="margin: auto;">
            <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/transit.UpdateTransit">
                <div class="default_div">
                    <input type="hidden" id="transitDto_id" name="transitDto_id" value="${transitDto.id}">
                    <div style="padding-top: 5px">
                        <div>
                            <label for="status"><fmt:message key="allTransits.th.status" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <select name="status" id="status">
                                <c:forEach items="${statusList}" var="status">
                                    <option value="${status}">${status}
                                        <c:if test="${status} == ${transitDto.status}">
                                            selected
                                        </c:if>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <c:if test="${(sessionScope.role == 'ADMIN') || (sessionScope.role == 'DISPATCHER')}">
                        <div style="padding-top: 5px">
                            <div>
                                <label for="city_from"><fmt:message key="allTransits.th.city.from"
                                                                    bundle="${msg}"/></label>
                            </div>
                            <div>
                                <select name="city_from" id="city_from">
                                    <c:forEach items="${cityList}" var="city">
                                        <option value="${city.id}">${city.name}
                                            <c:if test="${city.id} == ${transitDto.city_from.id}">
                                                selected
                                            </c:if>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div style="padding-top: 5px">
                            <div>
                                <label for="city_to"><fmt:message key="allTransits.th.city.to" bundle="${msg}"/></label>
                            </div>
                            <div>
                                <select name="city_to" id="city_to">
                                    <c:forEach items="${cityList}" var="city">
                                        <option value="${city.id}">${city.name}
                                            <c:if test="${city.id} == ${transitDto.city_to.id}">
                                                selected
                                            </c:if>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div style="padding-top: 5px">
                            <div>
                                <label for="car"><fmt:message key="allTransits.th.car" bundle="${msg}"/></label>
                            </div>
                            <div>
                                <select name="car" id="car">
                                    <c:forEach items="${carList}" var="car">
                                        <option value="${car.id}">${car.carNumber} ${car.model} (fully
                                            functional:${car.fully_Functional})
                                            <c:if test="${car.id} == ${transitDto.car.id}">
                                                selected
                                            </c:if>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div style="padding-top: 5px">
                            <div>
                                <label for="driver"><fmt:message key="allTransits.th.driver" bundle="${msg}"/></label>
                            </div>
                            <div>
                                <select name="driver" id="driver">
                                    <c:forEach items="${driverList}" var="driver">
                                        <option value="${driver.id}">${driver.name} ${driver.familyName}
                                            <c:if test="${driver.id} == ${transitDto.driver.id}">
                                                selected
                                            </c:if>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>
                    <div style="padding-top: 5px">
                        <button type="submit" class="btn"><fmt:message key="button.update"
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
