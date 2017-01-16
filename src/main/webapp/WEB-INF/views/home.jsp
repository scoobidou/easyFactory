<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

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