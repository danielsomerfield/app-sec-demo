<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Injection Demo</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css" />
</head>
<body>
<h1>Search for a name</h1>
<label for="filter">Filter</label><input type="text" id="filter"/>
<table>
    <thead>
        <tr>
            <th>Last Name</th>
            <th>First Name</th>
        </tr>
    </thead>
    <tbody id="names"></tbody>
</table>

<table style="display:none">
    <tbody id="template">
        <tr>
            <td><span>{lastName}</span></td>
            <td><span>{firstName}</span></td>
        </tr>
    </tbody>
</table>
</body>
</html>
