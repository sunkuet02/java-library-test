<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2/28/17
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>

<div>
    <c:choose>
        <c:when test="${not empty username}">
            <h1>Hi ${username}</h1>
            <spring:url value="/task/add" var="taskAddUrl" />
            <a href="${taskAddUrl}">Add Task</a>
        </c:when>
        <c:otherwise>
            <spring:url value="/signup" var="signupUrl"/>
            <spring:url value="/signin" var="signinUrl"/>
            <a href="${signinUrl}">Signin</a> <br/>
            <a href="${signupUrl}">Signup</a>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="footer.jsp" %>