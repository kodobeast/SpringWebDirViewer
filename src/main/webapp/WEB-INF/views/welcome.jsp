<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="folders" value="${folders}"/>

<c:set var="rootFolder">
    <spring:message code="root.folder"/>
</c:set>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} |
            <a class="alert-success" onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>

        <h4 class="text-center"><a href="${contextPath}/content/${rootFolder}">click to view [${rootFolder}] catalog</a></h4>
        <div align="center">

            <c:if test="${error != null}">
                <h3 class="text-warning">${error}</h3>
            </c:if>

            <c:if test="${error == null}">

                <c:forEach items="${directories}" var="item">
                    <p class="glyphicon">[<a href="${item}">${item}</a>]</p><br/>
                </c:forEach>

                <c:forEach items="${files}" var="item">
                    <c:out value="${item}"/><br/>
                </c:forEach>

            </c:if>
        </div>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>