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
            <h3><fmt:message key="updateCity.main.name" bundle="${msg}"/></h3>
        </div>
        <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/city.UpdateCity">
            <div class="default_div">
                <input type="hidden" value="${cityDto.id}" name="city_id"/>
                <div style="padding-top: 5px">
                    <label for="name"><fmt:message key="allCities.th.name"
                                                   bundle="${msg}"/></label>
                </div>
                <div>
                    <input id="name" name="name" required maxlength="30" value="${cityDto.name}">
                </div>
                <div style="padding-top: 5px">
                    <button type="submit" class="btn"><fmt:message key="button.update"
                                                                   bundle="${msg}"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
