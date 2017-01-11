<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
        <meta charset="utf-8" />
        <title>EasyFactory - ${title}</title>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
        <link rel="stylesheet" href="custom.css">
        <% request.setAttribute("stuff", request.getContextPath().equals("/easyFactory")); %>
        <c:set var="title" value="${title}" scope="request"/>
</head>
<html>
  <body>
    <div id="pageheader">
    	<nav role="navigation" class="navbar navbar-default navbar-fixed-top navbarEzFac">
	        <!-- Brand and toggle get grouped for better mobile display -->
	        <div class="row">
	        	<div class="col-md-1 col-md-offset-3">
	        		<div class="navbar-header">
	            		<a href="/easyFactory" class="navbar-brand brandName">EasyFactory</a>
	        		</div>
	        	</div>
	        	<div class="col-md-1 col-md-offset-2">
        			<div class="navbar-header">
        				<c:if test="${title eq 'Home'}">
	            		<a href="/easyFactory" class="navbar-brand active">Home</a>
	            		</c:if>
	            		<c:if test="${title ne 'Home'}">
	            		<a href="/easyFactory" class="navbar-brand">Home</a>
	            		</c:if>
	        		</div>
	        	</div>
	        	<div class="col-md-1">
        			<div class="navbar-header">
        				<c:if test="${title eq 'Project'}">
	            			<a href="project" class="navbar-brand active">Project</a>
            			</c:if>
            			<c:if test="${title ne 'Project'}">
	            			<a href="project" class="navbar-brand">Project</a>
            			</c:if>
	        		</div>
	        	</div>
	        	<div class="col-md-1">
        			<div class="navbar-header">
        			<c:if test="${title eq 'Team'}">
	            		<a href="team" class="navbar-brand active">Team</a>
	            	</c:if>
	            	<c:if test="${title ne 'Team'}">
	            		<a href="team" class="navbar-brand">Team</a>
	            	</c:if>
	        		</div>
	        	</div>
	        	<div class="col-md-1">
        			<div class="navbar-header">
	        			<c:if test="${!empty sessionScope.user}">
		                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
		                    <p class="navbar-brand">Vous êtes connecté(e) avec l'adresse : ${sessionScope.user.email}</p>
	                	</c:if>
	                	<c:if test="${empty sessionScope.user}">
		                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
		                    <p class="navbar-brand">Non connecté</p>
	                	</c:if>
	        		</div>
	        	</div>
	        </div>
		</nav>
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <c:if test="!${stuff}">
 		<div id="pagefooter">
	    	MY FOOTER + ${stuff}
	      	<jsp:invoke fragment="footer"/>
	    </div>
	</c:if>
  </body>
  
  	<script src="webjars/jquery/2.1.4/jquery.js"></script>
	<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="js/custom.js"></script>
</html>