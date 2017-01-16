<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:genericPage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
    <div class="site-wrapper">
    	<br>
    	<br>
    	<br>
    	<div class="container">
    		<c:if test="${loginInfo != null}">
            <%-- Si l'utilisateur a reussi a se connecter on affiche un message de succès --%>
            <div class="alert alert-success" role="alert">
			 ${loginInfo }
			</div>
			<c:remove var="loginInfo" scope="session"/>
	        </c:if>
	        <c:if test="${loginError != null}">
            <%-- Si l'utilisateur a reussi a se connecter on affiche un message de succès --%>
            <div class="alert alert-danger" role="alert">
			  ${loginError}
			</div>
			<c:remove var="loginInfo" scope="session"/>
	        </c:if>              	
 			<div class="Absolute-Center is-Responsive">
				<h1 class="homeTitleContent">Easy Factory</h1>
				<h4 class="text-center homeTitleContent">Develop, Build, Run, Test and Deploy applications with Docker.</h4>
									<div class="row">
						<div class="col-md-4 item">
							<a data-toggle="collapse" href="#collapse2">
						<button id="Log" type="button" class="btn btn-primary">Log In</button>
						<br>
					</a>
						</div>
						<div class="col-md-4 col-md-offset-4 item">
							<a data-toggle="collapse" href="#collapse3">
						<button id="sign" type="button" class="btn btn-success">Sign Up</button>
						<br>
					</a>
						</div>
					</div>	
				<div id="collapse2" class="collapse">
					<%@ include file="/WEB-INF/views/forms/login.jsp" %>
				</div>
				<div id="collapse3" class="collapse">
					<%@ include file="/WEB-INF/views/forms/signin.jsp" %>
				</div>
			</div>
    	</div>
    </jsp:body>
</t:genericPage>