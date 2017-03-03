<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 3/2/17
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>

<div>
    <spring:url value="/task/add" var="taskAddUrl" />
    <form id="signupform" action="${taskAddUrl}" method="post">
        <label for="heading"> Heading </label>
        <input name="heading" id="heading" type="text" required>
        <br/>

        <label for="description">Description </label>
        <input name="description" id="description" type="text" required>
        <br/>

        <label for="date"> Date </label>
        <input name="date" id="date" type="datetime">
        <br />

        <input type="submit" name="submit" value="submit"/>
    </form>
</div>

<%@include file="footer.jsp" %>