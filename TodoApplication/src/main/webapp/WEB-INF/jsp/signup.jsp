<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sun
  Date: 2/28/17
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp" %>

<script>
    var app = angular.module("signupApp", []);
    app.controller("signupController", function($scope, $http) {
        $scope.signUpRequest = function () {
            var object = {
                'username':$scope.username,
                'password':$scope.password,
                'email':$scope.email
            };
            $.ajax({
                method:'POST',
                dataType: 'json',
                data:JSON.stringify(object),
                contentType: "application/json; charset=utf-8",
                url:'http://localhost:8080/signup'
            });
        }
    });
</script>

<div ng-app="signupApp">
    <div ng-controller="signupController">
        <form id="signupform" ng-submit="signUpRequest()">
            <label for="username"> Username </label>
            <input name="username" id="username" type="text" ng-model="username" required>
            <br />

            <label for="password"> Password </label>
            <input name="password" id="password" type="password" ng-model="password" required>
            <br />

            <label for="repassword">Retype Password </label>
            <input name="repassword" id="repassword" type="password" ng-model="repassword" required>
            <br />

            <label for="email"> Email </label>
            <input name="email" id="email" type="email" ng-model="email" required>
            <br />

            <input type="submit" name="submit" value="submit" />
        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>