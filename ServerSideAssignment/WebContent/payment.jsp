<%@page import="java.util.List"%>
<%@page import="domain.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}
/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
	background-color: #555;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	opacity: 0.8;
	position: fixed;
	bottom: 23px;
	right: 28px;
	width: 280px;
}
/* The popup form - hidden by default */
.form-popup {
	overflow-x: hidden;
	overflow-y: auto;
	height: 400px;
	display: none;
	position: fixed;
	top: 60%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
/* Add styles to the form container */
.form-container {
	max-width: 500px;
	padding: 10px;
	background-color: white;
}
/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	border: none;
	background: #f1f1f1;
}
/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus
	{
	background-color: #ddd;
	outline: none;
}
/* Set a style for the submit button */
.form-container .btn {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	margin-bottom: 10px;
	opacity: 0.8;
}
/* Add a red background color to the cancel button */
.form-container .cancel {
	background-color: red;
}
/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
	opacity: 1;
}

.pageref {
	text-align: center;
	font-weight: bold;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>
	<%
		int currentPage = (int) request.getAttribute("currentPage");
		int recordsPerPage = (int) request.getAttribute("recordsPerPage");
		int nOfPages = (int) request.getAttribute("nOfPages");
		String keyword = (String) request.getAttribute("keyword");
	%>
	<form class="form-inline md-form mr-auto mb-4"
		action="PaymentPagination" method="get">
		<input class="form-control mr-sm-2" type="text" aria-label="Search"
			name="keyword" />
		<button class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info"
			type="submit">Search</button>
		<input type="hidden" name="currentPage" value="<%=currentPage%>" /> <input
			type="hidden" name="recordsPerPage" value="<%=recordsPerPage%>" />
	</form>
	<div class="row col-md-6">
		<table class="table table-striped table-bordered table-sm">
			<tr>
				<th>Customer Number</th>
				<th>Customer Name</th>
				<th>Check Number</th>
				<th>Payment Date</th>
				<th>Payment Method</th>
				<th>Amount</th>
			</tr>
			<%
				List<Payment> payment = (List<Payment>) request.getAttribute("payment");
				if (payment.size() != 0) {
					for (Payment t : payment) {
						out.println("<tr>");
						out.println("<td>" + t.getId().getCustomernumber() + "</td>");
						out.println("<td>" + t.getCustomer().getCustomername() + "</td>");
						out.println("<td>" + t.getId().getChecknumber() + "</td>");
						out.println("<td>" + t.getPaymentdate() + "</td>");
						out.println("<td>" + t.getPaymentmethod() + "</td>");
						out.println("<td>" + t.getAmount() + "</td>");
						out.println("</tr>");
					}
				} else {
					out.println("<tr>");
					String status = "No records";
					for (int i = 0; i < 8; i++) {
						out.println("<td>" + status + "</td>");
					}
					out.println("</tr>");
				}
			%>
		</table>
	</div>
	<nav aria-label="Navigation for staffs">
		<ul class="pagination">
			<%
				if (currentPage != 1 && nOfPages != 0) {
			%>
			<%
				out.println("<li class=\"page-item\">");
					out.println("<a class=\"page-link\" href=\"" + "PaymentPagination?recordsPerPage=" + recordsPerPage
							+ "&currentPage=1" + "&keyword=" + keyword + "\">First</a>");
					out.println("</li>");
			%>
			<li class="page-item">
				<%
					out.println("<a class=\"page-link\" href=\"" + "PaymentPagination?recordsPerPage=" + recordsPerPage
								+ "&currentPage=" + (currentPage - 1) + "&keyword=" + keyword + "\">Previous</a>");
				%>
			</li>
			<%
				}
			%>
			<%
				for (int i = 1; i <= nOfPages; i++) {
					if (currentPage == i) {
						out.println("<li class=\"page-item active\">");
						out.println("<a class=\"page-link\">" + i + "<span class=\"sr-only\">(current)</span></a></li>");
						out.println("</li>");
					} else {
						out.println("<li class=\"page-item\">");
						out.println("<a class=\"page-link\" href=\"" + "PaymentPagination?recordsPerPage=" + recordsPerPage
								+ "&currentPage=" + i + "&keyword=" + keyword + "\">" + i + "</a>");
						out.println("</li>");
					}
				}
			%>
			<%
				if (currentPage < nOfPages) {
					out.println("<li class=\"page-item\">");
					out.println("<a class=\"page-link\" href=\"" + "PaymentPagination?recordsPerPage=" + recordsPerPage
							+ "&currentPage=" + (currentPage + 1) + "&keyword=" + keyword + "\">Next</a>");
					out.println("</li>");
					out.println("<li class=\"page-item\">");
					out.println("<a class=\"page-link\" href=\"" + "PaymentPagination?recordsPerPage=" + recordsPerPage
							+ "&currentPage=" + nOfPages + "&keyword=" + keyword + "\">Last</a>");
					out.println("</li>");
				}
			%>
		</ul>
	</nav>
	<%
		if (nOfPages != 0) {
			out.println("<p class=\"pageref\">");
			out.println(currentPage + " of " + nOfPages);
			out.println("</p>");
		}
	%>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</body>
</html>