<%@page import="domain.Productline"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Product Line</title>
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
		 Productline prol = (Productline) request.getAttribute("PROL");
		
	%>
	<form action="ProductlineController" method="post">
		<table>
			<tr>
				<td>Product Line</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"productline\" readonly value=\"" + prol.getProductline()+"\"/>");
					%>
				</td>
			</tr>
			<tr>
				<td>Text Description </td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"td\" value=\"" + prol.getTextdescription()+"\"/>");
						
					%>
				</td>
			</tr>
			<tr>
				<td>HTML Description </td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"hd\" value=\"" + prol.getHtmldescription()+"\"/>");
					%>
				</td>
			</tr>
			<tr>
				<td>Image</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"img\" value=\"" + prol.getImage()+"\"/>");
					%>
				</td>
			</tr>
			
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> 
		<input type="submit" name="DELETE" value="DELETE" />
	</form>
</body>
</html>

