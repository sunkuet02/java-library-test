<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 3/1/17
  Time: 4:43 PM
--%>
<%@include file="../header.jsp"%>


<div>
    <div>
        <spring:url value="/signin" var="signinUrl" />
        <form id="signinform" action="${signinUrl}" method="post">
            <label for="username"> Username </label>
            <input name="username" id="username" type="text" required>
            <br />

            <label for="password"> Password </label>
            <input name="password" id="password" type="password" required>
            <br />

            <input type="submit" name="submit" value="Submit"/>
        </form>
    </div>
</div>

<%@ include file="../footer.jsp" %>