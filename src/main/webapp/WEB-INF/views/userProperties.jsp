<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:genericPage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    <br><br><br>
    <%@ include file="/WEB-INF/views/forms/userPropertiesFormTest.jsp" %>
    <%@ include file="/WEB-INF/views/forms/userPropertiesFormTestDelete.jsp" %>
    
    <h3>${sessionScore.userProperties.ssh_user}</h3>
    </jsp:body>
</t:genericPage>