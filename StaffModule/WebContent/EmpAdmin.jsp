<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body class="m-3">
<%
	String username = (String) request.getParameter("username");
	String role = (String) request.getParameter("role");
%>
	<h1>View Employee Details</h1>
	<form action="EmpPaginationServlet">
		<input type="hidden" name="currentPage" value="1"> <input
			type="hidden" name="keyword" value="" />
		<div class="form-group col-md-4">
			<label for="records">Select records per page:</label> <select
				class="form-control" id="records" name="recordsPerPage">
				<option value="10">10</option>
				<option value="20" selected>20</option>
				<option value="30" selected>30</option>
				<option value="40">40</option>
			</select>
		</div>
		<input type="hidden" name="username" value="<%=username%>">
		<input type="hidden" name="role" value="<%=role%>">
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	<h1>Add New Employee</h1>
	<a href="Empsignup.jsp">Add</a>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>