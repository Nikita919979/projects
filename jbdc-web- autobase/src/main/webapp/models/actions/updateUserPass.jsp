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
    <div style="text-align: center" class="default_main_text">
        <h3><fmt:message key="updateUserPass.main.name" bundle="${msg}"/></h3>
    </div>
    <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/user.UpdateUserPass">
        <div class="default_div">
            <input type="hidden" name="user_id" value="${user_id}">
            <div style="padding-top: 5px">
                <label for="password"><fmt:message key="newUser.pass" bundle="${msg}"/></label>
            </div>
            <div>
                <input type="password" id="password" required name="password" maxlength="20">
            </div>
            <div style="padding-top: 5px">
                <button type="submit" class="btn"><fmt:message key="button.change"
                                                               bundle="${msg}"/></button>
            </div>
        </div>
    </form>
</div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>