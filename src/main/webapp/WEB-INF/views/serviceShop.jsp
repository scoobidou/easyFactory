<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:userPage>
    <jsp:body>
    <div class="row">
    	<div class="col-md-10 col-md-offset-1">
					<div class="container">
    		<div class="jumbotron">
    			<h1 class="text-center">Shop</h1>
    		</div>
    	</div>
    	<div class="container">
    		<%@ include file="/WEB-INF/views/forms/serviceShopForm.jsp" %>
    	</div>
    	
    	<div class="container">
    		<p>${requestScope.result}</p>
    	</div>
    </div>
			    	
    	</div>
    	
    </jsp:body>
</t:userPage>