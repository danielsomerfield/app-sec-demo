<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AppSec Demo</title>
    <jsp:include page="imports.jsp" />
    <script src="${pageContext.request.contextPath}/js/app.js"></script>
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
            <tr class="entry-row">
                <td><span><a class="show-entry" href="#" data-id="{id}">{lastName}</a></span></td>
                <td><span><a class="show-entry" href="#" data-id="{id}">{firstName}</a></span></td>
                <td class="admin-required"><span><a href="#" class="delete-entry" data-id="{id}">[ x ]</a></span></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="panel">
        <div id="login-panel" class="navbar-right">
            <form id="login-form" class="form-inline" role="form">
                <input id="login_username" name="username" type="text" placeholder="username" class="form-control" />
                <input id="login_password" name="password" type="password" placeholder="password" class="form-control" />
                <button type="submit" class="btn btn-default">Login</button>
            </form>
        </div>
        <div id="logout-panel" class="navbar-right admin-required">
            <form id="logout-form" class="form-inline" role="form" method="post">
                <button type="submit" class="btn btn-default">Logout</button>
            </form>
        </div>
    </div>
</div>
<div id="entry-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">Entry View</h4>
            </div>
            <div class="modal-body">
                <iframe id="entry-frame">Loading...</iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
</body>
</html>
