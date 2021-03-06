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
    <div id="sidebar2" class="aside">

    </div>
    <div id="article">
        <div style="text-align: center" class="default_main_text">
            <h3><fmt:message key="newCar.main.name" bundle="${msg}"/></h3>
        </div>
        <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/car.NewCar">
            <div class="default_div">
                <div style="padding-top: 5px">
                    <label for="number"><fmt:message key="allCars.th.carNumber" bundle="${msg}"/></label>
                </div>
                <div>
                    <input id="number" name="number" required maxlength="20">
                </div>
                <div style="padding-top: 5px">
                    <div>
                        <label for="model"><fmt:message key="allCars.th.model" bundle="${msg}"/></label>
                    </div>
                    <div>
                        <select name="model" id="model">
                            <c:forEach items="${modelList}" var="model">
                                <option value="${model}">${model}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div style="padding-top: 5px">
                    <label for="technicalPassport"><fmt:message key="allCars.th.carTechnicalPassport"
                                                                bundle="${msg}"/></label>
                </div>
                <div>
                    <input id="technicalPassport" name="technicalPassport" required maxlength="20">
                </div>
                <div style="padding-top: 5px">
                    <label for="releaseDate"><fmt:message key="allCars.th.releaseDate" bundle="${msg}"/></label>
                </div>
                <div>
                    <input type="date" id="releaseDate" name="releaseDate" required maxlength="20">
                </div>
                <div style="padding-top: 5px">
                    <label for="fully_Functional"><fmt:message key="allCars.th.fully_Functional"
                                                               bundle="${msg}"/></label>
                    <input type="checkbox" name="fully_Functional" id="fully_Functional"/>
                </div>
                <div style="padding-top: 5px">
                    <button type="submit" class="btn"><fmt:message key="button.create"
                                                                   bundle="${msg}"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
