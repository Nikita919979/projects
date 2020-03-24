<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/main.css">
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
        <div style="text-align: center" class="default_main_text">
            <h3><fmt:message key="sign_in.main.name" bundle="${msg}"/></h3>
        </div>
        <div>
            <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/Login">
                <div class="default_div">
                    <div style="padding-top: 5px">
                        <label for="login"><fmt:message key="sign_in.main.login" bundle="${msg}"/></label>
                    </div>
                    <div>
                        <input id="login" name="login" required maxlength="20">
                    </div>
                    <div style="padding-top: 5px">
                        <label for="pass"><fmt:message key="sign_in.main.pass" bundle="${msg}"/></label>
                    </div>
                    <div>
                        <input type="password" id="pass" required name="pass" maxlength="20">
                    </div>
                    <div style="padding-top: 5px">
                        <button type="submit" class="btn"><fmt:message key="sign_in.button.submit"
                                                                       bundle="${msg}"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
