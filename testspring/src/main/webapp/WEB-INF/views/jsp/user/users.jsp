<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2/22/17
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <script src="/resources/scripts/http.methods.js"></script>
</head>
<body>
<h1>User Details</h1>
<table>
    <th>No.</th>
    <th>Name</th>
    <th>Email</th>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <spring:url value="/users/delete/${user.id}" var="deleteUrl"/>
                <button onclick="post('${deleteUrl}');">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>

<h4><a href="/users/add">Add User</a></h4>
</body>
</html>
