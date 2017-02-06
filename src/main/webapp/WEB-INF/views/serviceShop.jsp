<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:userPage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    	<br>
		<div class="container">
    		<div class="jumbotron">
    			<p><h1 class="text-center">Shop</h1></p>
    		</div>
    	</div>
    	<div class="container">
    		<%@ include file="/WEB-INF/views/forms/serviceShopForm.jsp" %>
    	</div>
    	
    	<div class="container">
    		<p>${requestScope.result}</p>
    	</div>
    </jsp:body>
</t:userPage>