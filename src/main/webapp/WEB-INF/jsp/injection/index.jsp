<%--
  Created by IntelliJ IDEA.
  User: danielsomerfield
  Date: 10/29/14
  Time: 10:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="phoneBookApp">
<head>
    <title>Injection Demo</title>
    <script src="${pageContext.request.contextPath}/js/angular.js"></script>
    <script src="${pageContext.request.contextPath}/js/controllers.js"></script>
</head>
<body ng-controller="PhoneBookController">
<h1>Search for a name</h1>
Filter: <input type="text" id="filter" ng-model="filter"/>
<ul id="names">
    <li ng-repeat="entry in entries | filter:filter">{{entry.firstname}} {{entry.lastname}}</li>
</ul>
</body>
</html>
