<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericPage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    
    <br>
    <br>
    <br>
    <%@ include file="/WEB-INF/views/forms/setupEnvLogForm.jsp" %>
    </jsp:body>
</t:genericPage>