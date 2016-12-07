<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
    </head>
    <div class="jumbotron">
    	<p>HELLO WORLD</p>
    </div>
    <body>
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
        </p>
    	<script src="webjars/jquery/2.1.4/jquery.js"></script>
  		<script src="webjars/bootstrap/3.3.5/js/bootstrap.js"></script>
    </body>
</html>