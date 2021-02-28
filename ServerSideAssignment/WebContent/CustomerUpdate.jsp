<%@page import="domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Update</title>
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
		Customer customer = (Customer) request.getAttribute("customer");
	%>
	<form action="CustomerController" method="post">
		<table>
			<tr>
				<td>Customer Number</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"id\" readonly value=" + customer.getCustomernumber());
					%>
				</td>
			</tr>
			<tr>
				<td>Customer Name</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"customername\" value=" + customer.getCustomername());
					%>
				</td>
			</tr>
						<tr>
				<td>Contact First Name</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"contactfname\" value=" + customer.getContactfirstname());
					%>
				</td>
			</tr>
			<tr>
				<td>Contact Last Name</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"contactlname\" value=" + customer.getContactlastname());
					%>
				</td>
			</tr>
			<tr>
				<td>Phone No.</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"phone\" value=" + customer.getPhone());
					%>
				</td>
			</tr>
			<tr>
				<td>Address Line 1</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"addressline1\" value=" + customer.getAddressline1());
					%>
				</td>
			</tr>
			<tr>
				<td>Address Line 2</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"addressline2\" value=" + customer.getAddressline2());
					%>
				</td>
			</tr>
			<tr>
				<td>City</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"city\" value=" + customer.getCity());
					%>
				</td>
			</tr>
			<tr>
				<td>State</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"state\" value=" + customer.getState());
					%>
				</td>
			</tr>
			<tr>
				<td>Postal Code</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"postalcode\" value=" + customer.getPostalcode());
					%>
				</td>
			</tr>
			<tr>
				<td>Country</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"country\" value=" + customer.getCountry());
					%>
				</td>
			</tr>
			<tr>
				<td>Sales Rep EmployeeNo</td>
				<td>
					<%
						if(customer.getEmployee() != null){
							out.println("<input type=\"text\" name=\"empno\" value=" + customer.getEmployee().getEmployeenumber());

						}
						else{
							out.println("<input type=\"text\" name=\"empno\" value=" + "null");
						}
					%>
				</td>
			</tr>
			<tr>
				<td>Credit Limit</td>
				<td>
					<%
						out.println("<input type=\"text\" name=\"creditlimit\" value=" + customer.getCreditlimit());
					%>
				</td>
			</tr>
		</table>
		<input type="submit" name="UPDATE" value="UPDATE" /> <input
			type="submit" name="DELETE" value="DELETE" />
	</form>
</body>
</html>