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
        <h3><fmt:message key="updateUser.main.name" bundle="${msg}"/></h3>
    </div>
    <form method="post" autocomplete="off" action="${pageContext.request.contextPath}/user.UpdateUser"
          id="update_form">
        <div class="default_div">
            <input type="hidden" name="user_id" value="${userDto.id}">
            <div style="padding-top: 5px">
                <label for="name"><fmt:message key="allUsers.th.name" bundle="${msg}"/></label>
            </div>
            <div>
                <input id="name" name="name" required maxlength="20" value="${userDto.name}">
            </div>
            <div style="padding-top: 5px">
                <label for="familyName"><fmt:message key="allUsers.th.f_name" bundle="${msg}"/></label>
            </div>
            <div>
                <input id='familyName' name="familyName" required maxlength="20" value="${userDto.familyName}">
            </div>
            <div style="padding-top: 5px">
                <label for="email"><fmt:message key="allUsers.th.email" bundle="${msg}"/></label>
            </div>
            <div>
                <input id="email" name="email" required maxlength="30" value="${userDto.email}">
            </div>
            <div style="padding-top: 5px">
                <div>
                    <label for="role"><fmt:message key="allUsers.th.role" bundle="${msg}"/></label>
                </div>
                <div>
                    <select name="role" id="role">
                        <c:forEach items="${roleList}" var="role">
                            <option value="${role}">${role}
                                <c:if test="${userDto.role} == role">
                                    selected
                                </c:if>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div style="padding-top: 5px">
                <label for="login"><fmt:message key="allUsers.th.login" bundle="${msg}"/></label>
            </div>
            <div>
                <input id="login" name="login" required maxlength="20" value="${userDto.login}">
            </div>
            <div style="padding-top: 5px">
                <button form="update_form" type="submit" class="btn"><fmt:message key="button.update"
                                                                                  bundle="${msg}"/></button>
            </div>
        </div>
    </form>
</div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>