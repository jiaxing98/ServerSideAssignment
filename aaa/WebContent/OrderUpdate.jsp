<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="domain.Order"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<body>
	<%
		Order order = (Order) request.getAttribute("order");
	%>
	<form action="OrderController" method="post">
		<table>
			<tr>
				<td>Order Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"orderNumber\" readonly value=" + order.getOrdernumber());
					%>
				</td>
			</tr>
			<tr>
				<td>Order Date</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"oDate\" readonly value=" + order.getOrderdate());
					%>
				</td>
			</tr>
			<tr>
				<td>Required Date</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"rDate\" value=" + order.getRequireddate());
					%>
				</td>
			</tr>
			<tr>
				<td>Shipped Date</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"sDate\" value=" + order.getShippeddate());
					%>
				</td>
			</tr>
			<tr>
				<td>Status</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"status\" value=" + order.getStatus());
					%>
				</td>
			</tr>
			<tr>
				<td>Comments</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"comments\" value=" + order.getComments());
					%>
				</td>
			</tr>
			<tr>
				<td>Customer Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"cusNo\" readonly value=" + order.getCustomernumber());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>

</body>
</html>