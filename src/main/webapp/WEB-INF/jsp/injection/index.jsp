<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Injection Demo</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
</head>
<body>
<h1>Search for a name</h1>
<label for="filter">Filter</label><input type="text" id="filter"/>
<ul id="names">

</ul>

<ul id="template" style="display:none">
    <li><span>{lastName}, {firstName}</span></li>
</ul>
</body>
</html>
