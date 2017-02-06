<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<meta charset="utf-8" />
	<title>EasyFactory - ${title}</title>
	<link rel="stylesheet" href="webjars/bootstrap/3.3.5/css/bootstrap.css">
	<link rel="stylesheet" href="custom.css">
	<%
		request.setAttribute("stuff", request.getContextPath().equals("/easyFactory"));
	%>
	<c:set var="title" value="${title}" scope="request" />
</head>
<html>
	<body>
			<div class="row">
	      		<div class="col-sm-2">
	      			<br>
	      			<br>
					<ul id="sidebar" class="nav nav-stacked affix">
			            <li><a href="#">Dashboard</a></li>
			            <li><a href="#">Service shop</a></li>
			            <li><a href="#">Auto install</a></li>

			</div>

				<div id="body">
			      <jsp:doBody/>
			    </div>
			</div>
			
    
	</body>
	
	<script src="webjars/jquery/2.1.4/jquery.js"></script>
	<script src="webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="js/custom.js"></script>
	<script src="js/autoWrite.js"></script>
</html>