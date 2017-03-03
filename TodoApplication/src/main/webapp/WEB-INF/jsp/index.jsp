<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2/28/17
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp"%>

<div>
    <spring:url value="/signup" var="signupUrl" />
    <spring:url value="/signin" var="signinUrl" />
    <button onclick="signin.jsp">Signin</button>
    <button onclick="${signupUrl}">Signup</button>
</div>

<%@ include file="footer.jsp"%>