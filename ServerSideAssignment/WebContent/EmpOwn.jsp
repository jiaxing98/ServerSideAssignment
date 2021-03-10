<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Own Detail</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body class="m-3">
	<h1>View Employee Details</h1>
	<form action="EmpPaginationServlet">
		<input type="hidden" name="currentPage" value="1"> <input
			type="hidden" name="keyword" value="" />
			<input type="hidden" name="recordsPerPage" value="1">
		<div class="form-group col-md-4">
		
		</div>
		<button type="submit" class="btn btn-primary">View</button>
	</form>
	
	
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>