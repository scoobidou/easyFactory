<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
        <link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
    </head>
    <div class="jumbotron">
    	<p>DOCKER API - INFORMATIONS</p>
    </div>
    <body>
        <p>Voici les informations extraites en live du serveur Docker</p>
        <p>
        	TOUTES LES INFORMATIONS DU /info de l'api <br/>
            <% 
            String attribut = (String) request.getAttribute("messages");
            out.println( attribut );

            %>
        </p>
        <p>
        	Le nombre de Containers : 
        	<%
        		String element = (String) request.getAttribute("info");
        		out.println(element);
        	%>
        	<br/>
    	<script src="webjars/jquery/2.1.4/jquery.js"></script>
  		<script src="webjars/bootstrap/3.3.5/js/bootstrap.js"></script>
    </body>
</html>