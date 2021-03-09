<%@page import="domain.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Update Page</title>
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
		Employee emp = (Employee) request.getAttribute("EMP");
		String username = (String) request.getParameter("username");
		String role = (String) request.getParameter("role");
	%>
	<form action="EmployeeController" method="post">
		<table>
			<tr>
				<td>Employee ID</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"id\" readonly value=" + emp.getId());
					%>
				</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"lname\" value=" + emp.getLastname());
					%>
				</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"fname\" value=" + emp.getFirstname());
					%>
				</td>
			</tr>
			<tr>
				<td>Extension</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"ext\" value=" + emp.getExtension());
					%>
				</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"email\" value=" + emp.getEmail());
					%>
				</td>
			</tr>
			<tr>
				<td>Office Code</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"ocode\" value=" + emp.getOffice().getId());
					%>
				</td>
			</tr>
			<tr>
				<td>Reports To</td>
				<td>
					<%
						if (emp.getReportsto() != null) {
							out.println("<input type=\"text\" name=\"repto\" value=" + emp.getReportsto());
						} else {
							out.println("<input type=\"text\" name=\"repto\" value=" + "null");
						}
					%>
				</td>
			</tr>
			<tr>
				<td>Job Title</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"jobt\" value=" + emp.getJobtitle());
					%>
				</td>
			</tr>
			<tr>
				<td>User Name(ReadOnly)</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"uname\" readonly value=" + emp.getUser().getUsername());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
			<input type="hidden" name="username" value="<%=username%>" />
			<input type="hidden" name="role" value="<%=role%>" />

	</form>
</body>
</html>

