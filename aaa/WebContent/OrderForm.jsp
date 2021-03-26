<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="domain.Customer"%>

<html class="supernova">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="alternate" type="application/json+oembed"
	href="https://www.jotform.com/oembed/?format=json&amp;url=https%3A%2F%2Fform.jotform.com%2F210611081141437"
	title="oEmbed Form">
<link rel="alternate" type="text/xml+oembed"
	href="https://www.jotform.com/oembed/?format=xml&amp;url=https%3A%2F%2Fform.jotform.com%2F210611081141437"
	title="oEmbed Form">
<meta property="og:title" content="Online Store">
<meta property="og:url"
	content="https://form.jotform.com/210611081141437">
<meta property="og:description"
	content="Please click the link to complete this form.">
<meta name="slack-app-id" content="AHNMASS8M">
<link rel="shortcut icon" href="https://cdn.jotfor.ms/favicon.ico">
<link rel="canonical" href="https://form.jotform.com/210611081141437" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=1" />
<meta name="HandheldFriendly" content="true" />
<title>Online Store</title>
<link type="text/css" media="print" rel="stylesheet"
	href="https://cdn.jotfor.ms/css/printForm.css?3.3.23622" />
<link type="text/css" rel="stylesheet"
	href="https://cdn.jotfor.ms/themes/CSS/5e6b428acc8c4e222d1beb91.css?themeRevisionID=5eb3b4ae85bd2e1e2966db96" />
<link type="text/css" rel="stylesheet"
	href="https://cdn.jotfor.ms/css/styles/payment/payment_styles.css?3.3.23622" />
<link type="text/css" rel="stylesheet"
	href="https://cdn.jotfor.ms/css/styles/payment/payment_feature.css?3.3.23622" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/punycode/1.4.1/punycode.min.js"></script>
<script
	src="https://cdn.jotfor.ms/js/vendor/jquery-1.8.0.min.js?v=3.3.23622"
	type="text/javascript"></script>
<script
	src="https://cdn.jotfor.ms/js/vendor/maskedinput.min.js?v=3.3.23622"
	type="text/javascript"></script>
<script
	src="https://cdn.jotfor.ms/js/vendor/jquery.maskedinput.min.js?v=3.3.23622"
	type="text/javascript"></script>
<script src="https://cdn.jotfor.ms/static/prototype.forms.js"
	type="text/javascript"></script>
<script src="https://cdn.jotfor.ms/static/jotform.forms.js?3.3.23622"
	type="text/javascript"></script>
<script
	src="https://cdn.jotfor.ms/js/payments/paypalcomplete.js?v=3.3.23622"
	type="text/javascript"></script>
<script
	src="https://cdn.jotfor.ms/js/payments/paymentUtils.js?v=3.3.23622"
	type="text/javascript"></script>
<script src="https://cdn.jotfor.ms/js/libraries/promise-polyfill.js"></script>
<script
	src="https://cdn.jotfor.ms/js/payments/payment_form_embedded.js?v=3.3.23622"
	type="text/javascript"></script>
<script type="text/javascript">
	JotForm.setConditions([ {
		"action" : [ {
			"id" : "action_1581690116457",
			"visibility" : "Hide",
			"isError" : false,
			"field" : "10"
		} ],
		"id" : "1581690133174",
		"index" : "0",
		"link" : "Any",
		"priority" : "0",
		"terms" : [ {
			"id" : "term_1581690116457",
			"field" : "17",
			"operator" : "isEmpty",
			"value" : "",
			"isError" : false
		} ],
		"type" : "field"
	}, {
		"action" : [ {
			"id" : "action_0_1581690045611",
			"visibility" : "Hide",
			"isError" : false,
			"field" : "10"
		} ],
		"id" : "1581689801697",
		"index" : "1",
		"link" : "Any",
		"priority" : "1",
		"terms" : [ {
			"id" : "term_0_1581690045611",
			"field" : "17",
			"operator" : "equals",
			"value" : "Yes",
			"isError" : false
		} ],
		"type" : "field"
	}, {
		"action" : [ {
			"field" : "11",
			"visibility" : "Show",
			"id" : "action_1_1581689802691"
		} ],
		"id" : "1581689543679",
		"index" : "2",
		"link" : "Any",
		"priority" : "2",
		"terms" : [ {
			"field" : "15",
			"operator" : "equals",
			"value" : "Yes"
		} ],
		"type" : "field"
	}, {
		"action" : [ {
			"field" : "18",
			"visibility" : "Show",
			"id" : "action_0_1581945983644",
			"isError" : false
		} ],
		"id" : "1581689543680",
		"index" : "3",
		"link" : "Any",
		"priority" : "3",
		"terms" : [ {
			"field" : "15",
			"operator" : "equals",
			"value" : "Yes",
			"id" : "term_0_1581945983644",
			"isError" : false
		} ],
		"type" : "field"
	}, {
		"action" : [ {
			"field" : "10",
			"visibility" : "Show",
			"id" : "action_3_1581689802691"
		} ],
		"id" : "1581689543681",
		"index" : "4",
		"link" : "Any",
		"priority" : "4",
		"terms" : [ {
			"field" : "15",
			"operator" : "equals",
			"value" : "Yes"
		} ],
		"type" : "field"
	} ]);
	JotForm
			.init(function() {
				productID = {
					"0" : "input_19_1001",
					"1" : "input_19_1002",
					"2" : "input_19_1003"
				};
				paymentType = "product";
				JotForm.setCurrencyFormat('USD', true, 'point');
				JotForm.totalCounter({
					"input_19_1001" : {
						"price" : "1",
						"quantityField" : "input_19_quantity_1001_0"
					},
					"input_19_1002" : {
						"price" : "5",
						"quantityField" : "input_19_quantity_1002_0"
					},
					"input_19_1003" : {
						"price" : "10",
						"quantityField" : "input_19_quantity_1003_0"
					}
				});
				$$('.form-product-custom_quantity')
						.each(
								function(el, i) {
									el
											.observe(
													'blur',
													function() {
														isNaN(this.value)
																|| this.value < 1 ? this.value = '0'
																: this.value = parseInt(this.value)
													})
								});
				$$('.form-product-custom_quantity').each(function(el, i) {
					el.observe('focus', function() {
						this.value == 0 ? this.value = '' : this.value
					})
				});
				JotForm.handleProductLightbox();
				JotForm.paymentProperties = {
					"styleColor" : "gold",
					"styleShape" : "rect",
					"styleSize" : "medium",
					"styleLabel" : "checkout",
					"styleLayout" : "vertical",
					"payLaterEnabled" : "No",
					"paymentFormProperties" : {
						"products" : [
								{
									"connectedCategories" : "[]",
									"connectedProducts" : "[]",
									"customPrice" : "",
									"customPriceSource" : "0",
									"description" : "",
									"fitImageToCanvas" : "No",
									"hasExpandedOption" : "",
									"hasQuantity" : "",
									"hasSpecialPricing" : "",
									"icon" : "",
									"images" : "[\"https://www.jotform.com/uploads/EdwardWrighton/form_files/sample6.jpg?nc=1\"]",
									"name" : "T-Shirt",
									"options" : [
											{
												"type" : "quantity",
												"properties" : "1\n2\n3\n4\n5\n6\n7\n8\n9\n10",
												"name" : "Quantity",
												"defaultQuantity" : "",
												"specialPricing" : false,
												"specialPrices" : "1,2,3,4,5,6,7,8,9,10",
												"expanded" : false
											},
											{
												"type" : "custom",
												"name" : "Color",
												"properties" : "Green\nBlue\nRed\nYellow\nMagenta\nGrey",
												"defaultQuantity" : "",
												"specialPricing" : false,
												"expanded" : false,
												"specialPrices" : ""
											},
											{
												"type" : "custom",
												"name" : "T-Shirt Size",
												"properties" : "XS\nS\nM\nL\nXL\nXXL\nXXXL"
											} ],
									"period" : "Monthly",
									"pid" : "1001",
									"price" : "1",
									"recurringtimes" : "No Limit",
									"setupfee" : "",
									"trial" : "None"
								},
								{
									"connectedCategories" : "[]",
									"connectedProducts" : "[]",
									"customPrice" : "",
									"customPriceSource" : "0",
									"description" : "",
									"fitImageToCanvas" : "No",
									"hasExpandedOption" : "",
									"hasQuantity" : "1",
									"hasSpecialPricing" : "",
									"icon" : "",
									"images" : "[\"https://www.jotform.com/uploads/EdwardWrighton/form_files/sample8 copy.jpg?nc=1\"]",
									"name" : "Sweatshirt",
									"options" : [
											{
												"type" : "quantity",
												"name" : "Quantity",
												"properties" : "1\n2\n3\n4\n5\n6\n7\n8\n9\n10"
											},
											{
												"type" : "custom",
												"name" : "Color",
												"properties" : "Green\nBlue\nRed\nBlack\nMagenta",
												"specialPrices" : ""
											},
											{
												"type" : "custom",
												"name" : "Sweatshirt Size",
												"properties" : "XS\nS\nM\nL\nXL\nXXL\nXXXL"
											} ],
									"period" : "Monthly",
									"pid" : "1002",
									"price" : "5",
									"recurringtimes" : "No Limit",
									"setupfee" : "",
									"trial" : "None"
								},
								{
									"connectedCategories" : "[]",
									"connectedProducts" : "[]",
									"customPrice" : "",
									"customPriceSource" : "0",
									"description" : "",
									"fitImageToCanvas" : "Yes",
									"hasExpandedOption" : "",
									"hasQuantity" : "1",
									"hasSpecialPricing" : "",
									"icon" : "",
									"images" : "[\"https://www.jotform.com/uploads/EdwardWrighton/form_files/sample9.jpg?nc=1\"]",
									"name" : "Shoes",
									"options" : [
											{
												"type" : "quantity",
												"name" : "Quantity",
												"properties" : "1\n2\n3\n4\n5\n6\n7\n8\n9\n10"
											},
											{
												"type" : "custom",
												"name" : "Shoe Size",
												"properties" : "8\n8.5\n9\n9.5\n10\n10.5\n11\n11.5\n12\n13\n14"
											} ],
									"period" : "Monthly",
									"pid" : "1003",
									"price" : "10",
									"recurringtimes" : "No Limit",
									"setupfee" : "",
									"trial" : "None"
								} ]
					}
				}
				setTimeout(function() {
					$('input_3').hint('ex: myname@example.com');
				}, 20);
				JotForm.setPhoneMaskingValidator('input_5_full',
						'(###) ###-####');
				if (window.JotForm && JotForm.accessible)
					$('input_18').setAttribute('tabindex', 0);
				if (window.JotForm && JotForm.accessible)
					$('input_14').setAttribute('tabindex', 0);
				JotForm.newDefaultTheme = true;
				JotForm.extendsNewTheme = false;
				JotForm.newPaymentUIForNewCreatedForms = false;
				JotForm.newPaymentUI = true;
				JotForm
						.alterTexts(
								{
									"couponApply" : "Apply",
									"couponBlank" : "Please enter a coupon.",
									"couponChange" : "",
									"couponEnter" : "Enter coupon",
									"couponExpired" : "Coupon is expired. Please try another one.",
									"couponInvalid" : "Coupon is invalid.",
									"couponValid" : "Coupon is valid.",
									"shippingShipping" : "Shipping",
									"taxTax" : "Tax",
									"totalSubtotal" : "Subtotal",
									"totalTotal" : "Total"
								}, true);
				/*INIT-END*/
			});

	JotForm.prepareCalculationsOnTheFly([ null, {
		"name" : "onlineStore",
		"qid" : "1",
		"text" : "Online Store",
		"type" : "control_head"
	}, {
		"name" : "fullName2",
		"qid" : "2",
		"text" : "Full Name",
		"type" : "control_fullname"
	}, {
		"name" : "email3",
		"qid" : "3",
		"subLabel" : "example@example.com",
		"text" : "E-mail",
		"type" : "control_email"
	}, {
		"name" : "billingAddress",
		"qid" : "4",
		"text" : "Billing Address",
		"type" : "control_address"
	}, {
		"name" : "contactNumber",
		"qid" : "5",
		"text" : "Contact Number",
		"type" : "control_phone"
	}, null, null, null, null, {
		"name" : "shippingAdress",
		"qid" : "10",
		"text" : "Shipping Adress",
		"type" : "control_address"
	}, {
		"name" : "recipientsFull",
		"qid" : "11",
		"text" : "Recipient's Full Name",
		"type" : "control_fullname"
	}, null, {
		"name" : "submit",
		"qid" : "13",
		"text" : "Submit Order",
		"type" : "control_button"
	}, {
		"name" : "specialInstructions",
		"qid" : "14",
		"text" : "Special Instructions",
		"type" : "control_textarea"
	}, {
		"name" : "sendGift15",
		"qid" : "15",
		"text" : "Send Gift?",
		"type" : "control_radio"
	}, null, {
		"name" : "isShipping17",
		"qid" : "17",
		"text" : "Is shipping address same as billing address?",
		"type" : "control_radio"
	}, {
		"name" : "giftMessage",
		"qid" : "18",
		"text" : "Gift Message",
		"type" : "control_textarea"
	}, {
		"name" : "products",
		"qid" : "19",
		"text" : "Products",
		"type" : "control_paypalcomplete"
	}, {
		"name" : "paymentMethods",
		"qid" : "20",
		"text" : "Payment Methods",
		"type" : "control_paymentmethods"
	} ]);
	setTimeout(function() {
		JotForm.paymentExtrasOnTheFly([ null, {
			"name" : "onlineStore",
			"qid" : "1",
			"text" : "Online Store",
			"type" : "control_head"
		}, {
			"name" : "fullName2",
			"qid" : "2",
			"text" : "Full Name",
			"type" : "control_fullname"
		}, {
			"name" : "email3",
			"qid" : "3",
			"subLabel" : "example@example.com",
			"text" : "E-mail",
			"type" : "control_email"
		}, {
			"name" : "billingAddress",
			"qid" : "4",
			"text" : "Billing Address",
			"type" : "control_address"
		}, {
			"name" : "contactNumber",
			"qid" : "5",
			"text" : "Contact Number",
			"type" : "control_phone"
		}, null, null, null, null, {
			"name" : "shippingAdress",
			"qid" : "10",
			"text" : "Shipping Adress",
			"type" : "control_address"
		}, {
			"name" : "recipientsFull",
			"qid" : "11",
			"text" : "Recipient's Full Name",
			"type" : "control_fullname"
		}, null, {
			"name" : "submit",
			"qid" : "13",
			"text" : "Submit Order",
			"type" : "control_button"
		}, {
			"name" : "specialInstructions",
			"qid" : "14",
			"text" : "Special Instructions",
			"type" : "control_textarea"
		}, {
			"name" : "sendGift15",
			"qid" : "15",
			"text" : "Send Gift?",
			"type" : "control_radio"
		}, null, {
			"name" : "isShipping17",
			"qid" : "17",
			"text" : "Is shipping address same as billing address?",
			"type" : "control_radio"
		}, {
			"name" : "giftMessage",
			"qid" : "18",
			"text" : "Gift Message",
			"type" : "control_textarea"
		}, {
			"name" : "products",
			"qid" : "19",
			"text" : "Products",
			"type" : "control_paypalcomplete"
		}, {
			"name" : "paymentMethods",
			"qid" : "20",
			"text" : "Payment Methods",
			"type" : "control_paymentmethods"
		} ]);
	}, 20);
</script>
</head>
<body>
	<form action="OrderController" method="post" class="jotform-form
		accept-charset="utf-8" autocomplete="on">
		<input type="hidden" name="formID" value="210611081141437" /> <input
			type="hidden" id="JWTContainer" value="" /> <input type="hidden"
			id="cardinalOrderNumber" value="" />
		<div role="main" class="form-all">
			<ul class="form-section page-section">
				<li id="cid_1" class="form-input-wide" data-type="control_head">
					<div class="form-header-group  header-large">
						<div class="header-text httal htvam">
							<h1 id="header_1" class="form-header" data-component="header">
								Classic Models</h1>
						</div>
						
						<div class="row col-md-6">
							<table class="table table-striped table-bordered table-sm">
								<tr>
									<th>Product Name</th>
									<th>Product Line</th>
									<th>Quantity</th>
									<th>Price</th>
								</tr>

								<%
									int totalItem = Integer.valueOf(request.getParameter("totalItem"));
									float payAmount = 0;
									for (int i = 1; i <= totalItem; i++) {
										String pdLine = request.getParameter("pdLine" + i);
										String pdName = request.getParameter("pdName" + i);
										int buyQty = Integer.valueOf(request.getParameter("buyQty" + i));
										float totalPrice = Float.valueOf(request.getParameter("totalPrice" + i));
										payAmount += totalPrice;

										out.println("<tr>");
										out.println(
												"<td><input type=\"text\" name=\"pdName" + i + "\" value=\"" + pdName + "\" readonly/></td>");
										out.println(
												"<td><input type=\"text\" name=\"pdLine" + i + "\" value=\"" + pdLine + "\" readonly/></td>");
										out.println(
												"<td><input type=\"text\" name=\"buyQty" + i + "\" value=\"" + buyQty + "\" readonly/></td>");
										out.println("<td><input type=\"text\" name=\"totalPrice" + i + "\" value=\"" + totalPrice
												+ "\" readonly/></td>");
										out.println("</tr>");
									}
									out.println("<br><br>");
									out.println("<tr><th>Total</th></tr>");
									out.println("<tr>");
									out.println("<td><input type=\"text\" name=\"payAmount\" value=\"" + payAmount + "\" readonly/></td>");
									out.println("<td><input type=\"hidden\" name=\"totalItem\" value=\"" + totalItem + "\" readonly/></td>");
									out.println("</tr>");
								%>
							</table>
						</div>
					</div>
				</li>

				<%
					String username = (String) session.getAttribute("username");
					String role = (String) session.getAttribute("role");
					Customer c = (Customer) request.getAttribute("customer");
				%>

				<li class="form-line" data-type="control_fullname" id="id_2"><label
					class="form-label form-label-top" id="label_2" for="first_2">
						Full Name </label>
					<div id="cid_2" class="form-input-wide" data-layout="full">
						<div data-wrapper-react="true">
							<span class="form-sub-label-container"
								style="vertical-align: top" data-input-type="first"> <input
								type="text" id="first_2" name="q2_fullName2[first]"
								class="form-textbox" size="10" readonly
								value="<%=c.getCustomername()%>" data-component="first"
								aria-labelledby="label_2 sublabel_2_first" /> <label
								class="form-sub-label" for="first_2" id="sublabel_2_first"
								style="min-height: 13px" aria-hidden="false"> Full Name
							</label>
						</div>
					</div></li>
				<li class="form-line" data-type="control_fullname" id="id_2"><label
					class="form-label form-label-top" id="label_2" for="first_2">
						Contact Name </label>
					<div id="cid_2" class="form-input-wide" data-layout="full">
						<div data-wrapper-react="true">
							<span class="form-sub-label-container"
								style="vertical-align: top" data-input-type="first"> <input
								type="text" id="first_2" name="c2_fullName2[first]"
								class="form-textbox" size="10" readonly
								value="<%=c.getContactfirstname()%>" data-component="first"
								aria-labelledby="label_2 sublabel_2_first" /> <label
								class="form-sub-label" for="first_2" id="sublabel_2_first"
								style="min-height: 13px" aria-hidden="false"> First Name
							</label>
							</span> <span class="form-sub-label-container"
								style="vertical-align: top" data-input-type="last"> <input
								type="text" id="last_2" name="c2_fullName2[last]"
								class="form-textbox" size="15" readonly
								value="<%=c.getContactlastname()%>" data-component="last"
								aria-labelledby="label_2 sublabel_2_last" /> <label
								class="form-sub-label" for="last_2" id="sublabel_2_last"
								style="min-height: 13px" aria-hidden="false"> Last Name
							</label>
							</span>
						</div>
					</div></li>
				<li class="form-line" data-type="control_phone" id="id_5"><label
					class="form-label form-label-top" id="label_5" for="input_5_full">
						Contact Number </label>
					<div id="cid_5" class="form-input-wide" data-layout="half">
						<span class="form-sub-label-container" style="vertical-align: top">
							<input type="tel" id="input_5_full" name="q5_contactNumber[full]"
							data-type="mask-number"
							class="mask-phone-number form-textbox validate[Fill Mask]"
							style="width: 310px" data-masked="true" readonly
							value="<%=c.getPhone()%>" placeholder="(000) 000-0000"
							data-component="phone" aria-labelledby="label_5" /> <label
							class="form-sub-label is-empty" for="input_5_full"
							id="sublabel_5_masked" style="min-height: 13px"
							aria-hidden="false"> </label>
						</span>
					</div></li>
				<li class="form-line" data-type="control_address" id="id_4"><label
					class="form-label form-label-top" id="label_4"
					for="input_4_addr_line1"> Shipping Address </label>
					<div id="cid_4" class="form-input-wide" data-layout="full">
						<div summary="" class="form-address-table jsTest-addressField">
							<div
								class="form-address-line-wrapper jsTest-address-line-wrapperField">
								<span
									class="form-address-line form-address-street-line jsTest-address-lineField">
									<span class="form-sub-label-container"
									style="vertical-align: top"> <input type="text"
										id="input_4_addr_line1" name="q4_billingAddress[addr_line1]"
										class="form-textbox form-address-line" readonly
										value="<%=c.getAddressline1()%>"
										data-component="address_line_1"
										aria-labelledby="label_4 sublabel_4_addr_line1" required="" />
										<label class="form-sub-label" for="input_4_addr_line1"
										id="sublabel_4_addr_line1" style="min-height: 13px"
										aria-hidden="false"> Shipping Address Line 1</label>
								</span>
								</span>
							</div>
							<%
								String address2 = "";
								if(c.getAddressline2() != null)
									address2 = c.getAddressline2();
							%>
							<div
								class="form-address-line-wrapper jsTest-address-line-wrapperField">
								<span
									class="form-address-line form-address-street-line jsTest-address-lineField">
									<span class="form-sub-label-container"
									style="vertical-align: top"> <input type="text"
										id="input_4_addr_line2" name="q4_billingAddress[addr_line2]"
										class="form-textbox form-address-line" readonly
										value="<%=address2%>"
										data-component="address_line_2"
										aria-labelledby="label_4 sublabel_4_addr_line2" /> <label
										class="form-sub-label" for="input_4_addr_line2"
										id="sublabel_4_addr_line2" style="min-height: 13px"
										aria-hidden="false"> Street Address Line 2 </label>
								</span>
								</span>
							</div>
							<div
								class="form-address-line-wrapper jsTest-address-line-wrapperField">
								<span
									class="form-address-line form-address-city-line jsTest-address-lineField ">
									<span class="form-sub-label-container"
									style="vertical-align: top"> <input type="text"
										id="input_4_city" name="q4_billingAddress[city]"
										class="form-textbox form-address-city" readonly
										value="<%=c.getCity()%>" data-component="city"
										aria-labelledby="label_4 sublabel_4_city" required="" /> <label
										class="form-sub-label" for="input_4_city" id="sublabel_4_city"
										style="min-height: 13px" aria-hidden="false"> City </label>
								</span>
								</span> <span
									class="form-address-line form-address-state-line jsTest-address-lineField ">
									<span class="form-sub-label-container"
									style="vertical-align: top"> <input type="text"
										id="input_4_state" name="q4_billingAddress[state]"
										class="form-textbox form-address-state" readonly
										value="<%=c.getState()%>" data-component="state"
										aria-labelledby="label_4 sublabel_4_state" required="" /> <label
										class="form-sub-label" for="input_4_state"
										id="sublabel_4_state" style="min-height: 13px"
										aria-hidden="false"> State / Province </label>
								</span>
								</span>
							</div>
							<div
								class="form-address-line-wrapper jsTest-address-line-wrapperField">
								<span
									class="form-address-line form-address-zip-line jsTest-address-lineField ">
									<span class="form-sub-label-container"
									style="vertical-align: top"> <input type="text"
										id="input_4_postal" name="q4_billingAddress[postal]"
										class="form-textbox form-address-postal" readonly
										value="<%=c.getPostalcode()%>" data-component="zip"
										aria-labelledby="label_4 sublabel_4_postal" required="" /> <label
										class="form-sub-label" for="input_4_postal"
										id="sublabel_4_postal" style="min-height: 13px"
										aria-hidden="false"> Postal / Zip Code </label>
								</span>
								</span> <span
									class="form-address-line form-address-state-line jsTest-address-lineField ">
									<span class="form-sub-label-container"
									style="vertical-align: top"> <input type="text"
										id="input_4_country" name="q4_billingAddress[country]"
										class="form-textbox form-address-state" readonly
										value="<%=c.getCountry()%>" data-component="country"
										aria-labelledby="label_4 sublabel_4_country" required="" /> <label
										class="form-sub-label" for="input_4_country"
										id="sublabel_4_country" style="min-height: 13px"
										aria-hidden="false"> Country </label>
								</span>
								</span>
							</div>
						</div>
					</div></li>

				<li class="form-line" data-type="control_textarea" id="id_14">
					<label class="form-label form-label-top" id="label_14"
					for="input_14"> Leave any comment or instructions here </label>
					<div id="cid_14" class="form-input-wide" data-layout="full">
						<textarea id="input_14" class="form-textarea"
							name="comments" value=""
							style="width: 648px; height: 163px" data-component="textarea"
							aria-labelledby="label_14"></textarea>
					</div>
				</li>

				<li class="form-line" data-type="control_button" id="id_13">
					<div id="cid_13" class="form-input-wide" data-layout="full">
						<div data-align="center"
							class="form-buttons-wrapper form-buttons-center   jsTest-button-wrapperField">
							<button id="input_13" type="submit"
								class="form-submit-button submit-button jf-form-buttons jsTest-submitField"
								data-component="button" data-content="">Submit Order</button>
						</div>
					</div>
				</li>
				<li style="display: none">Should be Empty: <input type="text"
					name="website" value="" />
				</li>
			</ul>
		</div>
		<script>
			JotForm.showJotFormPowered = "new_footer";
		</script>
		<script>
			JotForm.poweredByText = "Powered by JotForm";
		</script>
		<input type="hidden" class="simple_spc" id="simple_spc"
			name="simple_spc" value="210611081141437" />
		<script type="text/javascript">
			var all_spc = document
					.querySelectorAll("form[id='210611081141437'] .si" + "mple"
							+ "_spc");
			for (var i = 0; i < all_spc.length; i++) {
				all_spc[i].value = "210611081141437-210611081141437";
			}
		</script>
	</form>
	<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

	<script type="text/javascript">
		JotForm.ownerView = true;
	</script>
	<script
		src="https://cdn.jotfor.ms//js/vendor/smoothscroll.min.js?v=3.3.23622"></script>
	<script src="https://cdn.jotfor.ms//js/errorNavigation.js?v=3.3.23622"></script>
</body>
</html>

