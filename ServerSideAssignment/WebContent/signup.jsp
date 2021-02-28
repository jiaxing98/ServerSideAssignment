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
	<h1>Sign Up</h1>
	<br>
	<br>
	<form name="signup" onSubmit="return validateSignUp();"
		action="SignUpServlet" method="post">
		<pre>
		Username	: <input type="text" name="username">
				
		Password 	: <input type="password" name="password">
						   
		Confirm Password : <input type="password" name="confirm_pw">
		
						   <input type="submit" value="Submit">
	</pre>
	</form>
	<br>
	<br>
	<a href="index.html"> Back to Homepage </a>
</body>
</html>