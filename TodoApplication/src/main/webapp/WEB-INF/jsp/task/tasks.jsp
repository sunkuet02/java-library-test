<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 3/5/17
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp" %>

<div>
    <c:choose>
        <c:when test="${not empty username}">
            <h1>Hi ${username}</h1>

            <spring:url value="/task/add" var="taskAddUrl"/>
            <spring:url value="/signout" var="signoutUrl" />

            <a href="${taskAddUrl}">Add Task</a>
            <a href="${signoutUrl}">Signout</a>

            <c:if test="${not empty tasks}">
                <table>
                    <th width="15%">Heading</th>
                    <th width="60%">Description</th>
                    <th width="15%">Time</th>

                    <c:forEach var="task" items="${tasks}">
                        <tr>
                            <td>${task.heading}</td>
                            <td>${task.description}</td>
                            <td>${task.time}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

        </c:when>
        <c:otherwise>
            <spring:url value="/signup" var="signupUrl"/>
            <spring:url value="/signin" var="signinUrl"/>
            <a href="${signinUrl}">Signin</a> <br/>
            <a href="${signupUrl}">Signup</a>
        </c:otherwise>
    </c:choose>
</div>

<%@include file="footer.jsp" %>