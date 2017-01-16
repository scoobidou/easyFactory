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
   	<c:if test="${setupEnv_connectionError != null}">
        <div class="alert alert-danger" role="alert">
	 		${setupEnv_connectionError }
		</div>
		<c:remove var="setupEnv_connectionError" scope="session"/>
    </c:if>
    <c:if test="${setupEnv_connectionInfo != null}">
        <div class="alert alert-success" role="alert">
	 		${setupEnv_connectionInfo }
		</div>
		<c:remove var="setupEnv_connectionInfo" scope="session"/>
    </c:if>
   		SETUP ENV TEST
   		<br>
   		______________________________
   		<br>
   		BUT
   		<br>
   		_____________________________
   		<br>
   		<div class="col-md-8">
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
	    			<c:if test="${setupEnv_step != -1 && setupEnv_step != 1}">
	   					<div class="col-md-1">
	   						<%@ include file="/WEB-INF/views/forms/setupEnv_connectionTest.jsp" %>
	   					</div>
	    			</c:if>
	    			<c:if test="${setupEnv_step == 1}">
	        			<span class="glyphicon glyphicon-ok custom-ok" aria-hidden="true"></span>
	    			</c:if>
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Second step : Copy script</h4>
	   			</div>
	   			<div class="row">
					
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Third step : Execute installation script</h4>
	   			</div>
	   			<div class="row">
	
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Fourth step : Test Docker API</h4>
	   			</div>
	   			<div class="row">
	
	   			</div>
	   		</div>
	   		<div class="row">
	   			<div class="col-md-6">
	   				<h4>Fifth step : Run test container</h4>
	   			</div>
	   			<div class="row">
	
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
  					</h4>
  				</div>
  				<div class="panel-body">
  					<c:if test="${setupEnv_step == -1 }">
  						<h5>Unable to connect.
  						<br>
  						First step must be validated to continue</h5>
  					</c:if>
  					<c:if test="${setupEnv_step == 1 }">
  						<h5>Connected to host
  						<br>
  						Now you can pass to step two</h5>
  					</c:if>
  				</div>
			</div>
   		</div>
   	</div>
   </jsp:body>
</t:genericPage>