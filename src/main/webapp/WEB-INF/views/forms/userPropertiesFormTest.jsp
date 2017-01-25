<form method="post" name="UserProperties" action="${pageContext.request.contextPath}/userProperties">
	<div class="form-group">
		<h3>Please sign up</h3>
		
		<label>id User</label>
		<input type="text" class="form-control" name="id_user"  placeholder="ID User" required autofocus>
		
		<label>ssh user</label>
		<input type="text" class="form-control" name="ssh_user" placeholder="SSH USER" required>
		
		<label>ssh host</label>
		<input type="text" class="form-control" name="ssh_host" placeholder="SSH HOST" required>
		
		<label>ssh password</label>
		<input type="text" class="form-control" name="ssh_password" placeholder="SSH PASSWORD" required>

		<div class="wrapper">
			<button class="btn btn-md-primary" type="submit">Insert</button>
		</div>
		
	</div>
</form>