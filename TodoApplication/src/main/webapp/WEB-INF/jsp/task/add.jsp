<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 3/2/17
  Time: 5:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>
<script type="text/javascript">
    function validate_() {
        var time = document.getElementById('date').value;
        alert(time);
        document.getElementById('time').value = new Date(time).getTime();

        document.getElementById("addTaskForm").submit();
    }
</script>

<div>
    <spring:url value="/task/add" var="taskAddUrl"/>
    <form id="addTaskForm" action="${taskAddUrl}" method="post">
        <label for="heading"> Heading </label>
        <input name="heading" id="heading" type="text" required>
        <br/>

        <label for="description">Description </label>
        <input name="description" id="description" type="text" required>
        <br/>

        <label for="date"> Date </label>
        <input name="date" id="date" type="datetime"> <b>Format : MM/DD/YYYY HH:MM</b>
        <br/>

        <input type="hidden" name="time" id="time">
        <input type="button" name="submitform" value="Add" onclick="validate_();"/>
    </form>
</div>

<%@include file="footer.jsp" %>