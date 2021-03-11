<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make New Payment</title>
<style>
body h4 {
	color: red;
	font-style: italic;
}
</style>
<script type="text/javascript">
	function getDate() {
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0');
		var yyyy = today.getFullYear();

		today = mm + '/' + dd + '/' + yyyy;

		var elem = document.getElementById("paymentdate");
		elem.value = today;
	}
</script>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
<body>
	<%
		String customernumber = (String) request.getAttribute("customernumber");
		String checknumber = (String) request.getAttribute("checknumber");
	%>
	<div class="container">
		<h2>Make New Payment</h2>
		<form action="PaymentController" method="post" onSubmit="getDate()">
			<div class="form-group">
				<label for="checknumber">Check Number:</label> <input type="text"
					class="form-control" name="checknumber" readonly value="<%=checknumber%>">
			</div>
			<div class="form-group">
				<label for="paymentmethod">Payment Method:</label> <select
					name="paymentmethod" id="paymentmethod" class="form-control">
					<option value="Paypal">Paypal</option>
					<option value="Debit/Credit Card">Debit/Credit Card</option>
					<option value="Online Banking">Online Banking</option>
				</select>
			</div>
			<div class="form-group">
				<label for="amount">Amount:</label> <input type="text"
					class="form-control" id="amount" name="amount" required>
			</div>
			<input type="hidden" name="paymentdate" id="paymentdate" value="" /> <input
				type="hidden" name="customernumber" value="103" />
			<%-- 			<input type="hidden" name="customernumber" value="<%=customernumber%>" /> --%>
			<button type="submit" class="btn btn-default" value="Submit">Submit</button>
			<button type="reset" class="btn">Reset</button>
		</form>
	</div>
</body>
</html>