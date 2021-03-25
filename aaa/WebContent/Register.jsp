<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Form</title>
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
		<h2>Register Form</h2>
		<h4>Field with * is required to filled!</h4>
		<form action="RegisterServlet" method="post">
			<div class="form-group">
				<label for="customername">*Customer Name:</label> <input type="text"
					class="form-control" id="customername"
					placeholder="Enter customer name" name="customername" required>
			</div>
			<div class="form-group">
				<label for="contactfname">*Contact First Name:</label> <input
					type="text" class="form-control" id="contactfname"
					placeholder="Enter first name" name="contactfname" required>
			</div>
			<div class="form-group">
				<label for="contactlname">*Contact Last Name:</label> <input
					type="text" class="form-control" id="contactlname"
					placeholder="Enter last name" name="contactlname" required>
			</div>
			<div class="form-group">
				<label for="phone">*Phone No:</label> <input type="text"
					class="form-control" id="phone" placeholder="Enter phone number"
					name="phone" required>
			</div>
			<div class="form-group">
				<label for="addressline1">*Address Line 1:</label> <input
					type="text" class="form-control" id="addressline1"
					placeholder="Enter address" name="addressline1" required>
			</div>
			<div class="form-group">
				<label for="addressline2">Address Line 2:</label> <input type="text"
					class="form-control" id="addressline2" placeholder="Enter address"
					name="addressline2">
			</div>
			<div class="form-group">
				<label for="city">*City:</label> <input type="text"
					class="form-control" id="city" placeholder="Enter city" name="city" required>
			</div>
			<div class="form-group">
				<label for="postalcode">Postal Code:</label> <input type="text"
					class="form-control" id="postalcode"
					placeholder="Enter postal code" name="postalcode">
			</div>
			<div class="form-group">
				<label for="state">State:</label> <input type="text"
					class="form-control" id="state" placeholder="Enter state"
					name="state">
			</div>
			<div class="form-group">
				<label for="country">*Country:</label> <input type="text"
					class="form-control" id="country" placeholder="Enter country"
					name="country" required>
			</div>
			<div class="form-group">
				<label for="creditlimit">Credit Limit:</label> <input type="text"
					class="form-control" id="creditlimit"
					placeholder="Enter credit limit" name="creditlimit">
			</div>
			<input type="hidden" name="empno" value="null" /> <input
				type="hidden" name="username" value="<%=username%>" />
			<button type="submit" class="btn btn-default" value="Submit">Submit</button>
			<button type="reset" class="btn">Reset</button>
		</form>
	</div>
</body>
</html>