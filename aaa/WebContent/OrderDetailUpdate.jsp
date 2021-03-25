<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="domain.Orderdetail"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
		Orderdetail od = (Orderdetail) request.getAttribute("order");
	%>
	<form action="OrderController" method="post">
		<table>
			<tr>
				<td>Order Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"orderNumber\" readonly value=" + od.getOrder().getOrdernumber());
					%>
				</td>
			</tr>
			<tr>
				<td>Product Code</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"pdCode\" readonly value=" + od.getProduct().getProductcode());
					%>
				</td>
			</tr>
			<tr>
				<td>Product Name</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"pdName\" readonly value=" + od.getProduct().getProductname());
					%>
				</td>
			</tr>
			<tr>
				<td>Quantity Ordered</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"qtyOrdered\" value=" + od.getQuantityordered());
					%>
				</td>
			</tr>
			<tr>
				<td>Price each</td>
				<td>s
					<%
						out.println("<input type=\"text\" name=\"prEach\" value=" + od.getPriceeach());
					%>
				</td>
			</tr>
			<tr>
				<td>Order Line Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"oLineNo\" value=" + od.getOrderlinenumber());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>

</body>
</html>