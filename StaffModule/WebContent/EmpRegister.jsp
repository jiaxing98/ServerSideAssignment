<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Form (New Employee)</title>
<style>
body h4 {
	color: red;
	font-style: italic;
}
</style>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body>
	<%
		String username = (String) request.getAttribute("username");
	%>
	<div class="container">
		<h2>Register Form for adding New Employee</h2>
		<h4>Field with * is required to filled!</h4>
		<form action="EmpRegisterServlet" method="post">
			<div class="form-group">
				<label for="lname">*Last Name:</label> <input type="text"
					class="form-control" id="lname"
					placeholder="Enter Last name" name="lname" required>
			</div>
			<div class="form-group">
				<label for="fname">*First Name:</label> <input
					type="text" class="form-control" id="fname"
					placeholder="Enter first name" name="fname" required>
			</div>
			<div class="form-group">
				<label for="ext">*Extension:</label> <input
					type="text" class="form-control" id="ext"
					placeholder="Enter extension" name="ext" required>
			</div>
			<div class="form-group">
				<label for="email">*Email:</label> <input type="text"
					class="form-control" id="email" placeholder="Enter Email"
					name="email" required>
			</div>
			<div class="form-group">
				<label for="ocode">*Office code:</label> <input
					type="text" class="form-control" id="ocode"
					placeholder="Enter Office code" name="ocode" required>
			</div>
			<div class="form-group">
				<label for="repto">*Reports to:</label> <input type="text"
					class="form-control" id="repto" placeholder="Enter Reports to"
					name="repto" required>
			</div>
			<div class="form-group">
				<label for="jobt">*Job Title:</label> <input type="text"
					class="form-control" id="jobt" placeholder="Enter Job title" name="jobt" required>
			</div>
			 <input type="hidden" name="uname" value="<%=username%>" />
			<button type="submit" class="btn btn-default" value="Submit">Submit</button>
			<button type="reset" class="btn">Reset</button>
		</form>
	</div>
</body>
</html>