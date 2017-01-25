<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<t:genericPage>
   <jsp:attribute name="header">
   </jsp:attribute>
   <jsp:attribute name="footer">
   </jsp:attribute>
   <jsp:body>
   <br>
   <br>
   <br>
   <br>
   <br> 
   	<div class="container">
   	<c:if test="${setupEnv_error != null}">
        <div class="alert alert-danger" role="alert">
	 		${setupEnv_error }
		</div>
		<c:remove var="setupEnv_error" scope="session"/>
    </c:if>
    <c:if test="${setupEnv_info != null}">
        <div class="alert alert-success" role="alert">
	 		${setupEnv_info }
		</div>
		<c:remove var="setupEnv_info" scope="session"/>
    </c:if>
    
<%@ include file="/WEB-INF/views/forms/setupEnvLogForm.jsp" %>
    
    <form action="setupEnv">
    	<input type="submit" name="reset_button" value="reset"/>
    </form>
    
    <div class="col-md-8">
    		<div>
    			<p>${sessionScope.setupEnv_step}</p>
    		</div>
   			<div class="row">
	   			<div class="col-md-6">
	   				<h4>First step : Check connection</h4>
	   			</div>
	   			<div class="row">
	   				<c:if test="${setupEnv_step == -1}">
	   					<div class="col-md-1">
	   						<span class="glyphicon glyphicon-remove custom-remove" aria-hidden="true"></span>
	   					</div>
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step == 1}">
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step >= 2}">
	        			<span class="glyphicon glyphicon-ok custom-ok" aria-hidden="true"></span>
	    			</c:if>
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Second step : Copy script</h4>
	   			</div>
	   			<div class="row">
					<c:if test="${setupEnv_step == 2}">
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step == -3}">
	   					<div class="col-md-1">
	   						<span class="glyphicon glyphicon-remove custom-remove" aria-hidden="true"></span>
	   					</div>
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step >= 3}">
	        			<span class="glyphicon glyphicon-ok custom-ok" aria-hidden="true"></span>
	    			</c:if>
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Third step : Execute installation script</h4>
	   			</div>
	   			<div class="row">
					<c:if test="${setupEnv_step == 3}">
	   					<div class="col-md-1">
	   					<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step == -4}">
	   					<div class="col-md-1">
	   						<span class="glyphicon glyphicon-remove custom-remove" aria-hidden="true"></span>
	   					</div>
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step >= 4}">
	        			<span class="glyphicon glyphicon-ok custom-ok" aria-hidden="true"></span>
	    			</c:if>
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Fourth step : Test Docker API</h4>
	   			</div>
	   			<div class="row">
					<c:if test="${setupEnv_step == 4}">
						<div class="col-md-1">
	   					<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	   				</c:if>
	   				<c:if test="${setupEnv_step == -5}">
	   					<div class="col-md-1">
	   						<span class="glyphicon glyphicon-remove custom-remove" aria-hidden="true"></span>
	   					</div>
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step >= 5}">
	        			<span class="glyphicon glyphicon-ok custom-ok" aria-hidden="true"></span>
	    			</c:if>
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Fifth step : Run test container</h4>
	   			</div>
	   			<div class="row">
					<c:if test="${setupEnv_step == 5}">
						<div class="col-md-1">
	   					<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	   				</c:if>
	   				<c:if test="${setupEnv_step == -6}">
	   					<div class="col-md-1">
	   						<span class="glyphicon glyphicon-remove custom-remove" aria-hidden="true"></span>
	   					</div>
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step >= 6}">
	        			<span class="glyphicon glyphicon-ok custom-ok" aria-hidden="true"></span>
	    			</c:if>
	   			</div>
	   		</div>
   		</div>
   		<div class="col-md-4">
   			<div class="panel panel-default">
  				<div class="panel-heading">
  					<h4>Status : 
  					<c:if test="${setupEnv_step == -1 }">
  						Checking connection
  					</c:if>
  					<c:if test="${setupEnv_step == 1 }">
  						Checking connection
  					</c:if>
  					<c:if test="${setupEnv_step == 2 }">
  						Copy script
  					</c:if>
  					<c:if test="${setupEnv_step == -2 }">
  						Copy script
  					</c:if>
  					<c:if test="${setupEnv_step == 3 }">
  						Execute script
  					</c:if>
  					<c:if test="${setupEnv_step == -3 }">
  						Execute script
  					</c:if>
  					</h4>
  				</div>
  				<div class="panel-body">
  					<c:if test="${setupEnv_step == -1 }">
  						<h5>Unable to connect.
  						<br>
  						First step must be validated to continue</h5>
  					</c:if>
  					<c:if test="${setupEnv_step == -2 }">
  						<h5>Unable to copy script on host.
  						<br>
  						Second step must be validated to continue</h5>
  					</c:if>
  					<c:if test="${setupEnv_step == -3 }">
  						<h5>Error during execution.
  						<br>
  						Second step must be validated to continue, please check logs</h5>
  					</c:if>
  					<c:if test="${setupEnv_step == 1 }">
  						<h5>Connected to host
  						<br>
  						Now you can pass to step two</h5>
  					</c:if>
  					<c:if test="${setupEnv_step == 2 }">
  						<h5>File copy on host
  						<br>
  						Now you can pass to step three</h5>
  					</c:if>
  					<c:if test="${setupEnv_step == 3 }">
  						<h5>Script execution
  						<br>
  						Now you can pass to step four</h5>
  					</c:if>
  				</div>
			</div>
   		</div>
   </jsp:body>
</t:genericPage>