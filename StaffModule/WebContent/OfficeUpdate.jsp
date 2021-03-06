<%@page import="domain.Office"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		Office off = (Office) request.getAttribute("OFF");
	%>
	<form action="OfficeController" method="post">
		<table>
			<tr>
				<td>Office Code</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"id\" readonly value=" + off.getId());
					%>
				</td>
			</tr>
			<tr>
				<td>City</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"city\" value=" + off.getCity());
					%>
				</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"phone\" value=" + off.getPhone());
					%>
				</td>
			</tr>
			<tr>
				<td>Address Line 1</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"adr1\" value=" + off.getAddressline1());
					%>
				</td>
			</tr>
			<tr>
				<td>Address Line 2</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"adr2\" value=" + off.getAddressline2());
					%>
				</td>
			</tr>
			<tr>
				<td>State</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"state\" value=" + off.getState());
					%>
				</td>
			</tr>
			<tr>
				<td>Country</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"country\" value=" + off.getCountry());
					%>
				</td>
			</tr>
			<tr>
				<td>Postal Code</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"pcode\" value=" + off.getPostalcode());
					%>
				</td>
			</tr>
			<tr>
				<td>Territory</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"terr\" value=" + off.getTerritory());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>
</body>
</html>

