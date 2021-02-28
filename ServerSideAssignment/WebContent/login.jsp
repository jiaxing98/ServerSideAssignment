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
	<h1>Login</h1>
	<br>
	<br>
	<form action="LoginServlet" method="post">
		<pre>
		Username : <input type="text" name="username">
				
		Password : <input type="password" name="password">
				
				   <input type="submit" value="Submit">
	</pre>
	</form>
	<br>
	<br>
	<a href="index.html"> Back to Homepage </a>
</body>
</html>