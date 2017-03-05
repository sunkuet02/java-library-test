<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2/28/17
  Time: 1:43 PM
--%>
<%@ include file="../header.jsp" %>

<script>
    function _validate() {
        var pass = document.getElementById('password').value;
        var rePass = document.getElementById('repassword').value;
        if(pass != rePass) {
            alert("Password doesn't match");
            return;
        }

        document.getElementById('signupform').submit();
    }
</script>

<div>
    <div>
        <spring:url value="/signup" var="signupUrl" />
        <form id="signupform" action="${signupUrl}" method="post">
            <label for="username"> Username </label>
            <input name="username" id="username" type="text" required>
            <br />

            <label for="password"> Password </label>
            <input name="password" id="password" type="password"required>
            <br />

            <label for="repassword">Retype Password </label>
            <input name="repassword" id="repassword" type="password"  required>
            <br />

            <label for="email"> Email </label>
            <input name="email" id="email" type="email" required>
            <br />

            <input type="button" name="submitbtn" value="submit" onclick="_validate();"/>
        </form>
    </div>
</div>

<%@ include file="../footer.jsp" %>