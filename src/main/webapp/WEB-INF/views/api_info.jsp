<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:userPage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    	
    	<div class="row">
    	
	    	<div class="col-md-8 col-md-offset-1">
	    	<div class="container">
    		<div class="jumbotron">
    			<p><h1 class="text-center">Dashboard</h1></p>
    		</div>
    	</div>
	    	<%@ include file="/WEB-INF/views/forms/docker_host_form.jsp" %>
	    		<div class="panel panel-default">
				  <div class="panel-heading"><h3 class="text-center">Docker Host</h3></div>
				  <div class="panel-body">
				  	<div class="table-responsive">
					  <table class="table table-striped">
					    <thead>
					      <tr>
					        <th><h5 class="text-center">ID</h5></th>
					        <th><h5 class="text-center">Number of containers</h5></th>
					        <th><h5 class="text-center">Number of running containers</h5></th>
					        <th><h5 class="text-center">Number of paused containers</h5></th>
					        <th><h5 class="text-center">Number of stopped containers</h5></th>
					        <th><h5 class="text-center">Number of images</h5></th>
					        <th><h5 class="text-center">Kernel version</h5></th>
					        <th><h5 class="text-center">OS Type</h5></th>
					        <th><h5 class="text-center">OS</h5></th>
					        <th><h5 class="text-center">Architecture</h5></th>
					      </tr>
					    </thead>
					    <tbody>
					    	<tr>
					    		<td><h5 class="text-center">${docker_host.idHost}</h5></td>
					    		<td><h5 class="text-center">${docker_host.nbContainers}</h5></td>
					    		<td><h5 class="text-center">${docker_host.nbRunningContainers}</h5></td>
					    		<td><h5 class="text-center">${docker_host.nbPausedContainers}</h5></td>
					    		<td><h5 class="text-center">${docker_host.nbStoppedContainers}</h5></td>
					    		<td><h5 class="text-center">${docker_host.nbImages}</h5></td>
					    		<td><h5 class="text-center">${docker_host.kernelVersion}</h5></td>
					    		<td><h5 class="text-center">${docker_host.osType}</h5></td>
					    		<td><h5 class="text-center">${docker_host.os}</h5></td>
					    		<td><h5 class="text-center">${docker_host.architecture}</h5></td>
					    	</tr>
					    </tbody>
					  </table>
					</div>
				  </div>
				</div>
			</div>
		</div>
    	<div class="row">
   			<c:forEach var="container" items="${requestScope.containerList}">
   				<div class="col-md-3 col-md-offset-1">
   					<div class="panel panel-default">
					  <div class="panel-heading"><h3 class="text-center"><c:out value="${container.name}"/></h3></div>
					  <div class="panel-body">
					 <div class="table-responsive">
			 	 	 	<table class="table table-striped">
						    <tbody>
						    	<tr>
						    		<td><h5 class="text-center">ID container</h5></td>
						    		<td><h5 class="text-left">${container.idContainer}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Name</h5></td>
						    		<td><h5 class="text-left">${container.name}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Image</h5></td>
						    		<td><h5 class="text-left">${container.image}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Command</h5></td>
						    		<td><h5 class="text-left">${container.command}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">State</h5></td>
						    		<td><h5 class="text-left">${container.state}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Status</h5></td>
						    		<td><h5 class="text-left">${container.status}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">IP</h5></td>
						    		<td><h5 class="text-left">${container.ip}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Private Port</h5></td>
						    		<td><h5 class="text-left">${container.privatePort}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Public Port</h5></td>
						    		<td><h5 class="text-left">${container.publicPort}</h5></td>
						    	</tr>
						    	<tr>
						    		<td><h5 class="text-center">Type</h5></td>
						    		<td><h5 class="text-left">${container.type}</h5></td>
						    	</tr>
						    </tbody>
						  </table>
						</div>
					  </div>
					</div>
   				</div>
   			</c:forEach>
   		</div>
    </jsp:body>
</t:userPage>