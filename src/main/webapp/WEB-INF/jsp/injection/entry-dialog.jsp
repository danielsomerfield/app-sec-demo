<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <jsp:include page="imports.jsp"/>
</head>
<body>
<div class="entry">
    <%--@elvariable id="entry" type="demos.domain.DirectoryEntry"--%>
    <c:if test="${not empty entry}">
        <div class="entry">
            <div class="row">
                <div class="col-md-4">First Name</div>
                <div class="col-md-1">${entry.firstName}</div>
            </div>
            <div class="row">
                <div class="col-md-4">Last Name</div>
                <div class="col-md-1">${entry.lastName}</div>
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