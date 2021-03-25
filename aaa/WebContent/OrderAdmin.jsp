<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Admin</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="m-3">
	<h1>Select Number of Pages</h1>
	<form action="OrderPagination">
		<input type="hidden" name="currentPage" value="1">
		 <input type="hidden" name="keyword" value="">
		<div class="form-group col-md-4">
			<label for="records">Select records per page:</label> <select
				class="form-control" id="records" name="recordsPerPage">
				<option value="20">20</option>
				<option value="50" selected>50</option>
				<option value="70" selected>70</option>
				<option value="100">100</option>
			</select>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>

	<br>
	<br>
<!-- 	<form method="POST" action="upload" enctype="multipart/form-data"> -->
<!-- 		File: <br> -->
<!-- 		<input type="file" name="file" id="file" /> <br /> <input -->
<!-- 			type="submit" value="Upload" name="upload" id="upload" /> -->
<!-- 	</form> -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>