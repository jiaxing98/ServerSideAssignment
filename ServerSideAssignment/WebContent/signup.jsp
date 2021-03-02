<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>
<script>
	function validateSignUp() {
		var name = signup.username.value;
		var pw = signup.password.value;
		var cpw = signup.confirm_pw.value;

		if(name.length > 7){
			alert("Length of username cannot more than 7 characters");
			return false;
		}
		
		if (pw !== cpw) {
			alert("Password is not same!");
			return false;
		}
	}
</script>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body>
<body>
<div class="container">
		<h2>Sign Up</h2>
		<form name="signup" onSubmit="return validateSignUp();"
			action="SignUpServlet" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password">
			</div>
			<div class="form-group">
				<label for="confirm_pw">Confirm Password:</label> <input type="password"
					class="form-control" id="confirm_pw" name="confirm_pw">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
			<button type="reset" class="btn">Reset</button>
		</form>
	<br>
	<br>
	<a href="index.html"> Back to Homepage </a>
</div>
</body>
</html>