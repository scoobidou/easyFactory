<form method="post" name="login" action="${pageContext.request.contextPath}/login">
	<div class="form-group">
		<h3 class="homeTitleContent">Please sign up</h3>
		
		<label class="homeTitleContent" for="">Email</label>
		<input type="text" class="form-control" name="user_mail"  placeholder="Email address" required autofocus>
		
		<label class="homeTitleContent" for="">Password</label>
		<input type="password" class="form-control" name="user_password" placeholder="Password" required>
		
		<div class="checkbox">
			<label class="homeTitleContent">
				<input type="checkbox" value=""> Remember me
			</label>
		</div>
		
		<label class="homeTitleContent">
			<a href="#" >Forgot password ?</a>
		</label>
		<div class="wrapper">
			<button class="btn btn-md-primary" type="submit">Login</button>
		</div>
		
	</div>
</form>