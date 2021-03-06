<%@page import="domain.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product</title>
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
</head>
<body>
	<%
		 //pro = (Product) request.getAttribute("EMP");
	%>
	<form action="ProductController" method="post">
		<table>
			<tr>
				<td>Product Code</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"id\" readonly value=" + pro.getId());
					%>
				</td>
			</tr>
			<tr>
				<td>Product Name</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"pname\" value=" + pro.getProductname());
					%>
				</td>
			</tr>
			<tr>
				<td>Product Scale</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"pscale\" value=" + pro.getProductscale());
					%>
				</td>
			</tr>
			<tr>
				<td>Product Vendor</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"pvendor\" value=" + pro.getProductvendor());
					%>
				</td>
			</tr>
			<tr>
				<td>Product Description</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"pdes\" value=" + pro.getProductdesciption());
					%>
				</td>
			</tr>
			<tr>
				<td>Quantity in Stock</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"qtyinstock\" value=" + pro.getQuantityinstock());
					%>
				</td>
			</tr>
			<tr>
				<td>Buy Price</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"buyprice\" value=" + pro.getBuyprice());
					%>
				</td>
			</tr>
			<tr>
				<td>MSRP</td>
				<td>
					<%
						//out.println("<input type=\"text\" name=\"msrp\" value=" + pro.getMsrp());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>
</body>
</html>

