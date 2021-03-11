<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Office Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="m-3">
    <h1>View Office Details</h1>
	<h2>Please Select Number of Pages</h2>
	<form action="OffPaginationServlet">
		<input type="hidden" name="currentPage" value="1"> <input
			type="hidden" name="keyword" value="" />
		<div class="form-group col-md-4">
			<label for="records">Select records per page:</label> <select
				class="form-control" id="records" name="recordsPerPage">
				<option value="3">3</option>
				<option value="6" selected>6</option>
				<option value="9" selected>9</option>
				<option value="12">12</option>
			</select>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
	
	<br>
	<br>
	
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js">
</script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>