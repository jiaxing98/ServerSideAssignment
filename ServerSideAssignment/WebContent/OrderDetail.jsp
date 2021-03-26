<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List"%>
	<%@page import="domain.Orderdetail"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body>

<div class="row col-md-6">
		<table class="table table-striped table-bordered table-sm">
			<tr>
				<th>Order Number</th>
				<th>Product Code</th>
				<th>Product Name</th>
				<th>Quantity Ordered</th>
				<th>Price each</th>
				<th>Order Line Number</th>
				<th></th>
				<th></th>
			</tr>
			<%
				List<Orderdetail> odList = (List<Orderdetail>) request.getAttribute("orderDetailList");
				if (odList.size() != 0) {
					for (Orderdetail od : odList) {
						out.println("<tr>");
						out.println("<td>" + od.getOrder().getOrdernumber() + "</td>");
						out.println("<td>" + od.getProduct().getProductcode() + "</td>");
						out.println("<td>" + od.getProduct().getProductname() + "</td>");
						out.println("<td>" + od.getQuantityordered() + "</td>");
						out.println("<td>" + od.getPriceeach() + "</td>");
						out.println("<td>" + od.getOrderlinenumber() + "</td>");
						out.println("<td><a href=\"OrderDetailController?id=" + od.getOrder().getOrdernumber() + "&pCode=" + od.getProduct().getProductcode() + "\">Update</a></td>");
						out.println("<td><a href=\"OrderDetailController?id=" + od.getOrder().getOrdernumber() + "&pCode=" + od.getProduct().getProductcode() + "\">Delete</a></td>");
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
		
		<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>