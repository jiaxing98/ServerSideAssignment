<%@page import="java.util.List"%>
<%@page import="domain.Employee"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Detail</title>
<script type="text/javascript">
function homepage() {
	var role = "${sessionScope.role}";

	if(role === "user") {
		document.getElementById("homepage").href="UserSession.jsp"; 
	} else if (role === "staff") {
		document.getElementById("homepage").href="StaffSession.jsp"; 
	} else if (role === "admin") {
		document.getElementById("homepage").href="AdminSession.jsp"; 
	}	
}
</script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body class="m-3">
	<h1>Account Information</h1>

	<div class="row col-md-6">
		<table class="table table-striped table-bordered table-sm">
			<%
				Employee emp = (Employee) request.getAttribute("EMP");
				out.println("<tr><td>Employee Number</td>");
				out.println("<td>" + emp.getEmployeenumber() + "</td></tr>");
				out.println("<tr><td>Last Name</td>");
				out.println("<td>" + emp.getLastname() + "</td></tr>");
				out.println("<tr><td>First Name</td>");
				out.println("<td>" + emp.getFirstname() + "</td></tr>");
				out.println("<tr><td>Extension</td>");
				out.println("<td>" + emp.getExtension() + "</td></tr>");
				out.println("<tr><td>Email</td>");
				out.println("<td>" + emp.getEmail() + "</td></tr>");
				out.println("<tr><td>Office Code</td>");
				out.println("<td>" + emp.getOffice().getOfficecode() + "</td></tr>");
				out.println("<tr><td>Reports To</td>");
				out.println("<td>" + emp.getReportsto() + "</td></tr>");
				out.println("<tr><td>Job Title</td>");
				out.println("<td>" + emp.getJobtitle() + "</td></tr>");
				out.println("<tr><td>User Name</td>");
				out.println("<td>" + emp.getUser().getUsername() + "</td></tr>");
				out.println("<tr><td>Update</td>");
				out.println("<td><a href=\"EmployeeController?id=" + emp.getEmployeenumber() + "\">Update</a></td>");
				out.println("</tr>");
			%>
		</table>
	</div>
	<a href="#" id="homepage" onclick="homepage()">Back to Home Page</a>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>