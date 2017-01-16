<form method="post" name="createUser" action="${pageContext.request.contextPath}/home">
	<div class="form-group">
		<h3 class="homeTitleContent">Please sign up</h3>
		
		<label class="homeTitleContent" for="">First Name</label>
		<input type="text" class="form-control" name="user_first_name"  placeholder="First Name" required autofocus>
		
		<label class="homeTitleContent" for="">Last Name</label>
		<input type="text" class="form-control" name="user_last_name"  placeholder="Last Name" required autofocus>
		
		<label class="homeTitleContent" for="">Email</label>
		<input type="email" class="form-control" name="user_mail" placeholder="Email address" required autofocus>
		
		<label class="homeTitleContent" for="">Password</label>
		<input type="password" class="form-control" name="user_password" placeholder="Password" required>
		
		<label class="homeTitleContent" for="">Confirmation</label>
		<input type="password" class="form-control" name="user_confirmation" placeholder="Password" required>
		
		<div class="checkbox">
			<label class="homeTitleContent">
				<input type="checkbox" value=""> Remember me
			</label>
		</div>
		<label class="homeTitleContent">
			<a href="#" >Forgot password ?</a>
		</label>
		<div class="wrapper">
			<button class="btn btn-md-primary" type="submit">Sign in</button>
		</div>
		
	</div>
</form>
