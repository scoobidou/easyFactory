<form class="form-inline" method="post" name="setupEnvLog" action="${pageContext.request.contextPath}/setupEnv">
	<div class="form-group">
	
		<label>User</label>
		<input id="ssh_user" value="${sessionScope.ssh_user}" type="text" class="form-control" name="ssh_user"  placeholder="User" required autofocus>
		
		<label>Host</label>
		<input id="ssh_host" value="${sessionScope.ssh_host}" type="text" class="form-control" name="ssh_host" placeholder="Host" required autofocus>
		
		<label>Password</label>
		<input type="password" value="${sessionScope.ssh_password}" class="form-control" name="ssh_password" placeholder="Password" required autofocus>
		
		<label>Final result</label>
		<input id="ssh_host_full" type="text" class="form-control" disabled>
		 
		<label class="checkbox-inline"><input type="checkbox" value="remember" name="ssh_checkbox">Remember</label> 
		     
		<button class="btn btn-primary btn-inline" type="submit">Save</button>
		
		
	</div>

</form>