<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css">
    <fmt:setBundle basename="${sessionScope['bundleFile']}" var="text"/>
    <fmt:setLocale value="${sessionScope['locale']}"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <div id="sidebar1" class="aside">
        <jsp:include page="leftSidePage.jsp"/>
    </div>
    <div id="sidebar2" class="aside">

    </div>
    <div id="article">
        <div>
            <div style="text-align: center" class="default_main_text">
                <h3><fmt:message key="registration.main.name" bundle="${msg}"/></h3>
            </div>
            <div>
                <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/Registration">
                    <div class="default_div">
                        <div style="padding-top: 5px">
                            <label for="name"><fmt:message key="registration.label.name" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <input id="name" name="name" required maxlength="20">
                        </div>
                        <div style="padding-top: 5px">
                            <label for="f_name"><fmt:message key="registration.label.f_name" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <input id='f_name' name="f_name" required maxlength="20">
                        </div>
                        <div style="padding-top: 5px">
                            <label for="email"><fmt:message key="registration.label.email" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <input id="email" name="email" required maxlength="30">
                        </div>
                        <div style="padding-top: 5px">
                            <label for="login"><fmt:message key="registration.label.login" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <input id="login" name="login" required maxlength="20">
                        </div>
                        <div style="padding-top: 5px">
                            <label for="pass"><fmt:message key="registration.label.pass" bundle="${msg}"/></label>
                        </div>
                        <div>
                            <input type="password" id="pass" required name="pass" maxlength="20">
                        </div>
                        <div style="padding-top: 5px">
                            <button type="submit" class="btn"><fmt:message key="registration.button.submit"
                                                                           bundle="${msg}"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
