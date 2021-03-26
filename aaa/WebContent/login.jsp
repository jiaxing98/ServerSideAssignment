<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body>
<div class="container">
		<h2>Login</h2>
		<form name="login" action="LoginServlet" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="password">Password:</label> <input type="password"
					class="form-control" id="password" name="password">
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