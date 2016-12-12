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
					<form id="logContent">
						<div class="form-group">
							<h3 class="homeTitleContent">Please log in</h3>
							<label class="homeTitleContent" for="">Email</label>
							<input type="email" class="form-control" placeholder="Email address" required autofocus>
							
							<label class="homeTitleContent" for="">Password</label>
							<input type="password" class="form-control" placeholder="Password" required>
							
							<div class="checkbox">
								<label class="homeTitleContent">
									<input type="checkbox" value=""> Remember me
								</label>
							</div>
							<label class="homeTitleContent">
								<a href="#" >Forgot password ?</a>
							</label>
							<div class="wrapper">
								<button class="btn btn-md-primary" type="submit">Log in</button>
							</div>
						</div>
					</form>
				</div>
				<div id="collapse3" class="collapse">
					<%@ include file="/WEB-INF/views/forms/signin.jsp" %>
					<!-- <form>
						<div class="form-group">
							<h3 class="homeTitleContent">Please sign up</h3>
							
							<label class="homeTitleContent" for="">First Name</label>
							<input type="text" class="form-control" placeholder="First Name" required autofocus>
							
							<label class="homeTitleContent" for="">Last Name</label>
							<input type="text" class="form-control" placeholder="Last Name" required autofocus>
							
							<label class="homeTitleContent" for="">Email</label>
							<input type="email" class="form-control" placeholder="Email address" required autofocus>
							
							<label class="homeTitleContent" for="">Password</label>
							<input type="password" class="form-control" placeholder="Password" required>
							
							<div class="checkbox">
								<label class="homeTitleContent">
									<input type="checkbox" value=""> Remember me
								</label>
							</div>
							<label class="homeTitleContent">
								<a href="#" >Forgot password ?</a>
							</label>
							<div class="wrapper">
								<button class="btn btn-md-primary" type="submit">Log in</button>
							</div>
						</div>
					</form> -->
				</div>
				<%-- <div class="row">
					<%@ include file="/WEB-INF/views/forms/signin.jsp" %>
				</div> --%>
			</div>
    	</div>
    	
    	<!-- <div class="container">
			<div class="panel-group">
			  <div class="panel panel-default">
			    <div class="panel-heading">
			      <h4 class="panel-title">
			        <a data-toggle="collapse" href="#collapse1">Collapsible panel</a>
			      </h4>
			    </div>
			    <div id="collapse1" class="panel-collapse collapse">
			      <div class="panel-body">Panel Body</div>
			      <div class="panel-footer">Panel Footer</div>
			    </div>
			  </div>
			</div>    		
    	</div> -->
    </div>
    	
		
	          <!-- <div class="inner cover">
	            <h1 class="cover-heading">Easy Factory</h1>
	            <p class="lead perso">Develop, Build, Run, Test and Deploy applications with Docker.</p>
	            <p class="lead">
	              <a href="#" class="btn btn-lg btn-secondary">Connect</a>
	            </p>
	          </div> -->
    </jsp:body>
</t:genericPage>