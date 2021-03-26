<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="domain.Product"%>
<%@page import="domain.Productline"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Shop at Classic Models</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}
* {
	box-sizing: border-box;
}
​#orderPrompt{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -50px;
    margin-left: -50px;
    width: 100px;
    height: 100px;
}
/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
	background-color: #555;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	opacity: 0.8;
	position: fixed;
	bottom: 23px;
	right: 28px;
	width: 280px;
}
/* The popup form - hidden by default */
.form-popup {
	overflow-x: hidden;
	overflow-y: auto;
	height: 400px;
	display: none;
	position: fixed;
	top: 60%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
}
/* Add styles to the form container */
.form-container {
	max-width: 500px;
	padding: 10px;
	background-color: white;
}
/* Full-width input fields */
.form-container input[type=text], .form-container input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	border: none;
	background: #f1f1f1;
}
/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container input[type=password]:focus
	{
	background-color: #ddd;
	outline: none;
}
/* Set a style for the submit button */
.form-container .btn {
	background-color: #4CAF50;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	margin-bottom: 10px;
	opacity: 0.8;
}
/* Add a red background color to the cancel button */
.form-container .cancel {
	background-color: red;
}
/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
	opacity: 1;
}
.pageref {
	text-align: center;
	font-weight: bold;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<body class="m-3">

	<%
		int currentPage = (int) request.getAttribute("currentPage");
		int recordsPerPage = (int) request.getAttribute("recordsPerPage");
		int nOfPages = (int) request.getAttribute("nOfPages");
		int totalItem = (int) request.getAttribute("totalItem");
		String keyword = (String) request.getAttribute("keyword");
		int nOfPdlFilter = (int) request.getAttribute("nOfPdlFilter");
	%>

	<div class="row col-md-6">
		<form class="form-inline md-form mr-auto mb-4"
			action="ProductPagination" method="get">
			<input type="hidden" name="currentPage" value="<%=currentPage%>" />
			<input type="hidden" name="recordsPerPage"
				value="<%=recordsPerPage%>" />
			<p>
				<b>Use the search box or select any of the product lines below !</b>
			</p>

			<table id="pdltable">
				<tbody>
					<%
						//List<Productline> pdlist = pdlist.getAllProductline();
						out.println("<tr>");
						List<Productline> pdl = (List<Productline>) request.getAttribute("productline");
						int pdlcount = 1;
						if (pdl.size() != 0) {
							for (Productline p : pdl) {
								if (pdlcount % 4 == 0) {
									out.println("<td><input type=\"checkbox\" class=\"pdlSelection\" name=\"pdlSelection\" value=\""
											+ p.getProductline() + "\"/>" + p.getProductline() + "</td>");
									out.println("</tr>");
									out.println("<tr>");
									pdlcount++;
								} else {
									out.println("<td><input type=\"checkbox\" class=\"pdlSelection\" name=\"pdlSelection\" value=\""
											+ p.getProductline() + "\"/>" + p.getProductline() + "</td>");
									pdlcount++;
								}
							}
						} else {
							out.println("<tr>");
							String status = "No productline records";
							for (int i = 0; i < 8; i++) {
								out.println("<td>" + status + "</td>");
							}
							out.println("</tr>");
						}
						out.println("<tr id=\"pdlFilter\">");
						out.println("<td><input type=\"hidden\" id=\"nOfPdlFilter\" name\"nOfPdlFilter\" value=\"0\" /></td>");
						out.println("</tr>");
					%>
					<tr>
						<td><button
								class="btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info"
								type="submit">Search</button></td>
					</tr>
				</tbody>
			</table>
			<table id="pdlSubmit">
				<tbody style="display: none">

				</tbody>
				<tr>
					<td><input type="hidden" id="nOfPdlFilter" name="nOfPdlFilter"
						value="0"></td>
				</tr>

			</table>
		</form>
		<div class="row col-md-6">
			<table class="table table-striped table-bordered table-sm">
				<thead>
					<tr class="table100-head">
						<th>Item</th>
						<th>Product Line</th>
						<th>Product Name</th>
						<th style="width: 500px">Description</th>
						<th>Price</th>
						<th>Remaining</th>
						<th>Buy Quantity</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
						List<Product> products = (List<Product>) request.getAttribute("products");
						int count = (currentPage * recordsPerPage) - 19;
						if (products.size() != 0) {
							for (Product pr : products) {
								out.println("<tr>");
								out.println("<td><input type=\"text\" id=\"pdCount" + count + "\" value=\"" + count
										+ "\" readonly/>" + "</td>");
								out.println("<td><input type=\"text\" id=\"pdLine" + pr.getProductcode() + "\" value=\""
										+ pr.getProductlineBean().getProductline() + "\" readonly/>" + "</td>");
								out.println("<td><input type=\"text\" id=\"pdName" + pr.getProductcode() + "\" value=\""
										+ pr.getProductname() + "\" readonly/>" + "</td>");
								out.println("<td>" + pr.getProductdescription() + "</td>");
								out.println("<td><input type=\"text\" id=\"pdPrice" + pr.getProductcode() + "\" value=\""
										+ pr.getBuyprice() + "\" readonly/>" + "</td>");
								out.println("<td><input type=\"text\" class=\"remainingQty\" value=\"" + pr.getQuantityinstock()
										+ "\" readonly/>" + "</td>");
								out.println("<td><input type=\"number\" id=\"buyQty" + pr.getProductcode()
										+ "\" name=\"quantity\" min=\"1\" max=\"" + pr.getQuantityinstock() + "\"/>" + "</td>");
								out.println("<td><button id=\"" + pr.getProductcode()
										+ "\" class=\"buySelection btn aqua-gradient btn-rounded btn-sm my-0 btn btn-info\" onclick=\"add_to_cart(event)\">Add Item</button></td>");
								out.println("</tr>");
								++count;
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

				</tbody>
			</table>
		</div>

		<div>
			<nav aria-label="Navigation for Customers">
				<ul class="pagination">
					<%
						if (currentPage != 1 && nOfPages != 0) {
					%>
					<%
						out.println("<li class=\"page-item\">");
							out.println("<a class=\"page-link\" href=\"" + "ProductPagination?recordsPerPage=" + recordsPerPage
									+ "&currentPage=1" + "&keyword=" + keyword + "&nOfPdlFilter=" + nOfPdlFilter + "&totalItem="
									+ totalItem + "\">First</a>");
							out.println("</li>");
					%>
					<li class="page-item">
						<%
							out.println("<a class=\"page-link\" href=\"" + "ProductPagination=?recordsPerPage=" + recordsPerPage
										+ "&currentPage=" + (currentPage - 1) + "&keyword=" + keyword + "&nOfPdlFilter=" + nOfPdlFilter
										+ "&totalItem=" + totalItem + "\">Previous</a>");
						%>
					</li>
					<%
						}
					%>
					<%
						if (currentPage < nOfPages) {
							out.println("<li class=\"page-item\">");
							out.println("<a class=\"page-link\" href=\"" + "ProductPagination?recordsPerPage=" + recordsPerPage
									+ "&currentPage=" + (currentPage + 1) + "&keyword=" + keyword + "&nOfPdlFilter=" + nOfPdlFilter
									+ "&totalItem=" + totalItem + "\">Next</a>");
							out.println("</li>");
							out.println("<li class=\"page-item\">");
							out.println("<a class=\"page-link\" href=\"" + "ProductPagination?recordsPerPage=" + recordsPerPage
									+ "&currentPage=" + nOfPages + "&keyword=" + keyword + "&nOfPdlFilter=" + nOfPdlFilter
									+ "&totalItem=" + totalItem + "\">Last</a>");
							out.println("</li>");
						}
					%>
				</ul>
			</nav>

			<%
				if (nOfPages != 0) {
					out.println("<p class=\"pageref\">");
					out.println(currentPage + " of " + nOfPages);
					out.println("</p>");
				}
			%>
		</div>
		<div id="orderPrompt">
		<br><p> Interested to order? <a href="login.jsp">Sign in</a> with your account to start purchasing </p>
		<br><p> Don't have an account yet? <a href="signup.jsp">Sign Up</a> today !</p>
		
		</div>
	</div>
	
		<script type="text/javascript">
		// Select all checkboxes with the name 'settings' using querySelectorAll.
		var checkboxes = document
				.querySelectorAll("input[type=checkbox][name=pdlSelection]");
		let checkedpdl = [];
		/*
		For IE11 support, replace arrow functions with normal functions and
		use a polyfill for Array.forEach:
		https://vanillajstoolkit.com/polyfills/arrayforeach/
		 */
		 checkboxes.forEach(function(checkbox) {
			  checkbox.addEventListener('change', function() {
			    checkedpdl = 
			    	 Array.from(checkboxes) // Convert checkboxes to an array to use filter and map.
				      .filter(this => this.checked) // Use Array.filter to remove unchecked checkboxes.
				      .map(this => this.value); // Use Array.map to extract only the checkbox values from the array of objects.
			      var tbodyRef = document.getElementById('pdlSubmit').getElementsByTagName('tbody')[0];
			      var pdlcount = 0;
			      tbodyRef.innerHTML = '';
			      if (checkedpdl.length > 0){
			    	  for (String pdl : checkedpdl) {
					      var newRow = tbodyRef.insertRow(-1);
					      var newCell = newRow.insertCell();
					      pdlcount++;
					      newCell.innerHTML = "<input type=\"hidden\" name=\"pdlFilter" + pdlcount + "\" value=\"" + pdl + "\" readonly>";
// 					      newCell.setAttribute("name", "pdlFilter" + pdlcount);				      
// 					      newCell.setAttribute("value", pdl);
					      }
				      document.getElementById('nOfPdlFilter').value = "\"" + pdlcount "\"";
				      }
			      else
			    	  document.getElementById('nOfPdlFilter').value = 0;
			      
			  });
			});
	
</script>

</body>
</html>