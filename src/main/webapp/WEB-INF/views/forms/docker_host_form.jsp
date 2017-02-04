<form class="form-inline" method="post" name="apiInfo" action="${pageContext.request.contextPath}/api_info">
	<div class="form-group">
	
		<label>Docker Host</label>
		<input id="docker_host" type="text" class="form-control" name="docker_host" placeholder="Docker Host (host + port)" required autofocus>
		     
		<button class="btn btn-primary btn-inline" type="submit">Contact host</button>
		
	</div>

</form>