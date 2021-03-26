<%@page import="domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Information</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body class="m-3">
	<h1>Account Information</h1>
	<div class="row col-md-6">
		<table class="table table-striped table-bordered table-sm">
			<%
				Customer customer = (Customer) request.getAttribute("customer");
				out.println("<tr><td>Customer Number</td>");
				out.println("<td>" + customer.getCustomernumber() + "</td></tr>");
				out.println("<tr><td>Customer Name</td>");
				out.println("<td>" + customer.getCustomername() + "</td></tr>");
				out.println("<tr><td>Contact Last Name</td>");
				out.println("<td>" + customer.getContactlastname() + "</td></tr>");
				out.println("<tr><td>Contact First Name</td>");
				out.println("<td>" + customer.getContactfirstname() + "</td></tr>");
				out.println("<tr><td>Phone No.</td>");
				out.println("<td>" + customer.getPhone() + "</td></tr>");
				out.println("<tr><td>Address Line 1</td>");
				out.println("<td>" + customer.getAddressline1() + "</td></tr>");
				out.println("<tr><td>Address Line 2</td>");
				out.println("<td>" + customer.getAddressline2() + "</td></tr>");
				out.println("<tr><td>City</td>");
				out.println("<td>" + customer.getCity() + "</td></tr>");
				out.println("<tr><td>State</td>");
				out.println("<td>" + customer.getState() + "</td></tr>");
				out.println("<tr><td>Postal Code</td>");
				out.println("<td>" + customer.getPostalcode() + "</td></tr>");
				out.println("<tr><td>Country</td>");
				out.println("<td>" + customer.getCountry() + "</td></tr>");
								
				if(customer.getEmployee() != null){
					out.println("<tr><td>Sales Rep Employee No</td>");
					out.println("<td>" + customer.getEmployee().getEmployeenumber() + "</td></tr>");
					out.println("<tr><td>Sales Rep Employee Last Name</td>");
					out.println("<td>" + customer.getEmployee().getLastname() + "</td></tr>");
					out.println("<tr><td>Sales Rep Employee First Name</td>");
					out.println("<td>" + customer.getEmployee().getFirstname() + "</td></tr>");
				}
								
				out.println("<tr><td>Credit Limit</td>");
				out.println("<td>" + customer.getCreditlimit() + "</td>");
				out.println("<tr><td>Username</td>");
				out.println("<td>" + customer.getUser().getUsername() + "</td>");
				out.println("<tr><td><a href=\"CustomerController?id=" + customer.getCustomernumber() + "\">Update</a></td></tr>");
			%>
		</table>
		<a href="UserSession.jsp"> Back to Homepage </a>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>