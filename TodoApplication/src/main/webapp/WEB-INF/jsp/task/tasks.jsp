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

<script type="text/javascript">
    var itemListingApp = angular.module("tasksearch", []);
    var urlPrefix = "http://localhost:8080";

    itemListingApp.controller("searchTasksController", function ($scope, $http) {
        $scope.searchResultTable = true;

        $scope.getSearchResult = function () {
            var url = urlPrefix + "/task/search?text=" + $scope.txtSearch;

            $http.get(url).success(function (data) {
                $scope.searchResult = data;
            }).error(function (data, status, headers, config) {
                alert("Error : " + status);
            })
        }
    });

</script>

<div>
    <c:choose>
        <c:when test="${not empty username}">
            <h1>Hi ${username}</h1>

            <spring:url value="/task/add" var="taskAddUrl"/>
            <spring:url value="/signout" var="signoutUrl"/>

            <a href="${taskAddUrl}">Add Task</a>
            <a href="${signoutUrl}">Signout</a>

            <div id="searchTasks" ng-app="tasksearch" ng-controller="searchTasksController">
                <input ng-model="txtSearch" ng-keyup="getSearchResult()" ng-init="getSearchResult()">

                <table ng-show="searchResultTable">
                    <th width="20%">Heading</th>
                    <th width="60%">Description</th>
                    <th width="10%">Time</th>
                    <tr ng-repeat="x in searchResult">
                        <td width="20%">{{x.heading}}</td>
                        <td width="60%">{{x.description}}</td>
                        <td width="10%">{{x.time}}</td>
                    </tr>
                </table>
            </div>
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