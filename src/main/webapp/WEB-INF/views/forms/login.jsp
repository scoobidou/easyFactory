<form method="post" name="login" action="${pageContext.request.contextPath}/home">
	<div class="form-group">
		
		<h3 class="homeTitleContent">Please log in</h3>
		
		<label class="homeTitleContent" for="">Email</label>
		<input type="email" class="form-control" name="user_mail" placeholder="Email address" required autofocus>
		
		<label class="homeTitleContent" for="">Password</label>
		<input type="password" class="form-control" name="user_password" placeholder="Password" required>
		
		<div class="checkbox">
			<label class="homeTitleContent">
				<input type="checkbox" value=""> Remember me
			</label>
		</div>
		<div class="wrapper">
			<button class="btn btn-md-primary" type="submit">Sign in</button>
		</div>
	</div>
</form>