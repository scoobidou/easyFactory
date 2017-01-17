<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
        <link rel="stylesheet" href="cover.css">
        <script type="text/javascript"
		    src="http://code.jquery.com/jquery-1.10.1.min.js">
        </script>
    </head>
    
<!--     
    <div class="site-wrapper">
    	<div class="site-wrapper-inner">
    		<div class="masthead clearfix">
   				<div class="inner">
   					<h3 class="masthead-brand">EasyFactory</h3>
   					<nav class="nav-masthead">
   						<a class="nav-link active" href="#">Home</a>
   						<a class="nav-link" href="#">Team</a>
   						<a class="nav-link" href="#">Project</a>
   						<a class="nav-link" href="#">Contact</a>
   					</nav>
   				</div>
   			</div>
   			<div class="inner cover">
   				<h1 class="cover-heading">Easy Factory</h1>
   				<p class="lead">
   					A simple way to develop, build, test, run and deploy applications with Docker.
   				</p>
   			</div>
    	</div>
  	</div> -->


<body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">EasyFactory</h3>
              <br/>
              <nav class="nav nav-masthead">
                <a class="nav-link active" href="#">Home</a>
                <a class="nav-link" href="#">Team</a>
                <a class="nav-link" href="#">Project</a>
                <a class="nav-link" href="#">Contact</a>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Easy Factory</h1>
            <p class="lead perso">Develop, Build, Run, Test and Deploy applications with Docker.</p>
             <div id="show" align="center"></div>
            <p class="lead">
              <a href="#" class="btn btn-lg btn-secondary">Connect</a>
            </p>
          </div>
          
         

          <div class="mastfoot">
            <div class="inner">
              <p>EasyFactory by the "Industrialisation by Docker" Team</a>.</p>
            </div>
          </div>

        </div>

      </div>

    </div>	
</body>
    
    
	    <!-- <nav class="navbar navbar-default navbar-fixed-top">
	  		<a class="navbar-brand" href="#">EasyFactory</a>
	  		<div class="navbar-collapse collapse">
	  			<ul class="nav navbar-nav">
	  				<li class="active">
	  					<a href="#" >Accueil</a>
	  				</li>
	  				<li>
	  					<a href="#" >L'équipe</a>
	  				</li>
	  				<li>
	  					<a href="#" >Le projet</a>
	  				</li>
	  				<li>
	  					<a href="#" >Les tutos</a>
	  				</li>
	  				<li>
	  					<a href="#" >Contact</a>
	  				</li>
	  			</ul>
	  		</div>
		</nav>
		<br>
		<br>
		<br>
		<div class="container">
			<div class="jumbotron">
				<h1 class="text-center">EasyFactory</h1> 
					<p class="text-center">
						Une solution clef en main d'usine logicielle
					</p>
			</div>
		</div> -->
		
    	<%-- <nav class="navbar navbar-inverse navbar-fixed-top">
    		<div class="container">
    			<div class="navbar-header">
    			<a class="navbar-brand" href="#">EasyFactory</a>
    			</div>
    		</div>
		</nav>
		
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            <% 
            String attribut = (String) request.getAttribute("test");
            out.println( attribut );

            String parametre = request.getParameter( "auteur" );
            out.println( parametre );
            %>
        </p>
        <p>
            Récupération du bean :
            <%	
	    beans.User user = (beans.User) request.getAttribute("user");
	    out.println( user.getFirstName() );
            out.println( user.getLastName() );
            %>
        </p> --%>
<!--     	<script src="webjars/jquery/2.1.4/jquery.js"></script>
  		<script src="webjars/bootstrap/3.3.5/js/bootstrap.js"></script>
    </body> -->
    <script src="js/reloadScript.js"></script>
</html>
