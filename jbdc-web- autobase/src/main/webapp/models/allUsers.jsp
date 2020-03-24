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
                <th><fmt:message key="allUsers.th.name" bundle="${msg}"/></th>
                <th><fmt:message key="allUsers.th.f_name" bundle="${msg}"/></th>
                <th><fmt:message key="allUsers.th.login" bundle="${msg}"/></th>
                <th><fmt:message key="allUsers.th.role" bundle="${msg}"/></th>
                <th><fmt:message key="allUsers.th.email" bundle="${msg}"/></th>
            </tr>
            <c:forEach items="${usersList}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.familyName}</td>
                    <td>${user.login}</td>
                    <td>${user.role}</td>
                    <td>${user.email}</td>
                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/user.DeleteUser">
                                <input type="hidden" name="user_id" id="user_id" value="${user.id}"/>
                                <button type="submit" class="btn"><fmt:message key="button.delete"
                                                                               bundle="${msg}"/></button>
                            </form>
                        </td>
                        <td>
                            <form method="get" action="${pageContext.request.contextPath}/user.GetUpdateUserPage">
                                <input type="hidden" name="user_id" value="${user.id}"/>
                                <button type="submit" class="btn"><fmt:message key="button.update"
                                                                               bundle="${msg}"/></button>
                            </form>
                        </td>
                        <td>
                            <form method="get"
                                  action="${pageContext.request.contextPath}/user.GetChangeUserPassPage">
                                <input type="hidden" name="user_id" value="${user.id}">
                                <button type="submit" class="btn_pass"><fmt:message
                                        key="button.change.pass"
                                        bundle="${msg}"/></button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
