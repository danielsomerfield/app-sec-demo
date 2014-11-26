<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="imports.jsp"/>
</head>
<body>
<div class="entry container">
    <%--@elvariable id="entry" type="demos.domain.DirectoryEntry"--%>
    <c:if test="${not empty entry}">
        <div class="entry panel panel-primary">
            <div class="panel-heading">
                <div class="col-sm-8">${entry.lastName}, ${entry.firstName}</div>
            </div>
            <div class="row">
                <div class="col-xs-6">Identification Number</div>
                <div class="col-xs-6">${entry.id}</div>
            </div>
            <div class="row">
                <div class="col-xs-6">Phone Number</div>
                <div class="col-xs-6">${entry.phoneNumber}</div>
            </div>
            <div class="row">
                <div class="col-xs-6">Email Address</div>
                <div class="col-xs-6">${entry.email}</div>
            </div>
            <div class="row">
                <div class="col-xs-6">Personal Statement</div>
                <div class="col-xs-6">${entry.statement}</div>
            </div>
        </div>
    </c:if>
    <%--@elvariable id="error" type="java.lang.String"--%>
    <c:if test="${not empty error}">
        Error: ${error}
    </c:if>
</div>
</body>
</html>