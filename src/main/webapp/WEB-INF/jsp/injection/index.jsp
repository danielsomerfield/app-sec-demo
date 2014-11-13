<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Injection Demo</title>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"/>
</head>
<body>
<div class="container">
    <div class="panel">
        <h1>People Directory</h1>
    </div>

    <div class="panel panel-default main-panel">
        <div id="alert-wrapper">
            <div id="message-status" class="alert alert-danger"></div>
        </div>
        <div>
            <h2>Search for a name</h2>
            <label for="filter">Filter</label><input type="text" id="filter"/>
        </div>
        <div class="scrollable-table">
            <table class="table">
                <thead>
                <tr>
                    <th>Last Name</th>
                    <th>First Name</th>
                    <th class="admin-required">Admin</th>
                </tr>
                </thead>
                <tbody id="names"></tbody>
            </table>
        </div>

        <table style="display:none">
            <tbody id="template">
            <tr>
                <td><span>{lastName}</span></td>
                <td><span>{firstName}</span></td>
                <td class="admin-required"><span>[ x ]</span></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="panel">
        <div id="login-panel" class="navbar-right">
            <form id="login-form" class="form-inline" role="form" method="post">
                <input id="login_username" name="username" type="text" placeholder="username" class="form-control" />
                <input id="login_password" name="password" type="password" placeholder="password" class="form-control" />
                <button type="submit" class="btn btn-default">Login</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
