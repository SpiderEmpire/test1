<%@ page import="dal.Dbconnect , java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/textbutton.css">

<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Secure Smart Grid Framework System</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="apple-touch-icon.png">
<!--[if lt IE 9]> <script src="js/html5shiv.js"></script> 
	<script src="js/respond.min.js"></script> <![endif]-->
<!-- Place favicon.ico in the root directory -->
<link href='https://fonts.googleapis.com/css?family=Lato:400,300,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/responsive.css">

<link rel="stylesheet" type="text/css" href="css/default.css" />
<link rel="stylesheet" href="css/style.css">
<style>
.required label:after {
	font-weight: bold;
	font-size: 14px;
	color: #e32;
	content: ' *';
	display: inline;
}
</style>
</head>
<body background="whiteimage.jpg">
	<%
		ArrayList<String> RegionList = Dbconnect.getAllRegion();
			request.setAttribute("RegionList", RegionList);
			pageContext.setAttribute("RegionList", RegionList);
	%>
	<!-- Start Header Section -->
	<%@include file="base/header.html"%>
	<!-- End Header Section -->

	<br>
	<br>
	<br>
	<br>



	<div>
		<form action="Registration" class="register" method="post"
			name="registration" onSubmit="return formValidation()">
			<h1>Registration</h1>
			<fieldset class="row1">
				<legend>Consumer Details </legend>
				<p class="required">
					<label>Region &nbsp;&nbsp;&nbsp;</label> <select name="region" required>
					<option value="">None</option>
						<%
							for (int i = 0; i < RegionList.size(); i++) {
						%>
						<option><%=RegionList.get(i)%></option>
						<%
							}
						%>


					</select> <label>Consumer Number &nbsp;</label> <input type="text"
						class="required" name="txtconsumer" maxlength="6" minlength="6"
						required autocomplete="true" />
				</p>
				<p class="required">
					<label>Meter Number &nbsp;&nbsp;&nbsp;</label> <input type="text"
						class="required" name="txtmeterno" minlength="6" maxlength="6"
						required autocomplete="true" />

				</p>
			</fieldset>
			<fieldset class="row1">
				<legend>Login Details </legend>
				<p class="required">
					<label>User name &nbsp;&nbsp;&nbsp;</label> <input type="text"
						name="txtuserid" class="required" required autocomplete="true" />

					<label>Password &nbsp;&nbsp;&nbsp;</label> <input type="password"
						maxlength="8" minlength="6" name="txtpassword" required
						class="required" autocomplete="true" />
				</p>
				<p class="required">
					<label>Confirm Password &nbsp;</label> <input type="password"
						minlength="6" class="required" maxlength="8" name="txtrepassword"
						required autocomplete="true" />
				</p>


			</fieldset>
			<fieldset class="row1">
				<legend>User Details </legend>
				<p class="required">
					<label>First Name &nbsp;&nbsp;&nbsp;</label> <input type="text"
						name="txtfnm" class="required" required
						autocomplete="true" /> <label>Last Name
						&nbsp;&nbsp;&nbsp;</label> <input class="required"
						type="text" name="txtlnm" required autocomplete="true" />
				<p class="required">
					<label>Email Address &nbsp;&nbsp;&nbsp;</label> <input type="email"
						name="txtemail" style="border: 2px solid #E1E1E1;" required
						autocomplete="true" /> <label>Mobile Number
						&nbsp;&nbsp;&nbsp;</label> <input type="text" name="txtphone"
						maxlength="10" required pattern="[7-9]{1}[0-9]{9}"
						autocomplete="true" minlength="10" /> <label>Address
						&nbsp;&nbsp;&nbsp;</label>
					<textarea style="border: 2px solid #E1E1E1;" rows="3" cols="20"
						name="address" required autocomplete="true"></textarea>

				</p>


			</fieldset>
			<fieldset class="row1">
				<legend>Verification </legend>
				<p class="required">
					<img src="CaptchaServlet"> <label>Verification Code
						&nbsp;&nbsp;&nbsp;</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="text" name="txtcode" required autocomplete="true" />

				</p>

			</fieldset>
			<fieldset class="row1">
				<div align="center">
					<button class="button">Register &raquo;</button>
				</div>

			</fieldset>



		</form>
	</div>

	<!-- start footer Section -->
	<footer id="ft_sec">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="ft">
					<ul>
						<li><a href=""><i class="fa fa-facebook"></i></a></li>
						<li><a href=""><i class="fa fa-twitter"></i></a></li>
						<li><a href=""><i class="fa fa-dribbble"></i></a></li>
						<li><a href=""><i class="fa fa-rss"></i></a></li>
						<li><a href=""><i class="fa fa-flickr"></i></a></li>
						<li><a href=""><i class="fa fa-pinterest"></i></a></li>
						<li><a href=""><i class="fa fa-linkedin"></i></a></li>
						<li><a href=""><i class="fa fa-skype"></i></a></li>
						<li><a href=""><i class="fa fa-google"></i></a></li>
					</ul>
				</div>
				<ul class="copy_right">
					<center>
						<p>
							2017 All Rights Reserved <a href="main.jsp"> Secure Smart
								Grid Framework System </a>
						</p>
					</center>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<!-- End footer Section -->
	<script type="text/javascript">
		function formValidation() {
			var consumer = document.registration.txtconsumer;
			var meterno = document.registration.txtmeterno;
			var uname = document.registration.txtuserid;
			var fnm = document.registration.txtfnm;
			var lnm = document.registration.txtlnm;
			var uemail = document.registration.txtemail;
			var passid = document.registration.txtpassword;
			var repass = document.registration.txtrepassword;
			var phone = document.registration.txtphone;
			var addr = document.registration.address;
			var pincode = document.registration.pincode;
			var txtcode=document.registration.txtcode;
		
			if (allnumericconsumer(consumer)) {
				if (allnumericmeterno(meterno)) {
					if (passid_validation(passid, 6, 12)) {
						if (passwordmatch(passid, repass)) {
							if (allLetter(fnm)) {
								if (allLetter1(lnm)) {
									if (ValidateEmail(uemail)) {
										if (allnumeric(phone)) {
											if (alphanumeric(addr)) {
		
												return true;
											}

										}
									}
								}

							}
						}
					}
				}

			}

			return false;

		}
		function passid_validation(passid, mx, my) {
			var passid_len = passid.value.length;
			if (passid_len == 0 || passid_len >= my || passid_len < mx) {
				alert("Password should not be empty / length be between " + mx
						+ " to " + my);
				passid.focus();
				return false;
			}
			return true;
		}

		function passwordmatch(passid, repass) {
			var password = passid.value;
			var repassword = repass.value;
			if (password != repassword) {
				alert("Password and re enter password should match!!");
				passid.focus();
				return false;
			}
			return true;
		}
		function allLetter(uname) {
			var letters = /^[A-Za-z]+$/;
			if (uname.value.match(letters)) {
				return true;
			} else {
				alert('name must have alphabet characters only');
				uname.focus();
				return false;
			}
		}
		function allLetter1(uname) {
			var letters = /^[A-Za-z]+$/;
			if (uname.value.match(letters)) {
				return true;
			} else {
				alert('Last name must have alphabet characters only');
				uname.focus();
				return false;
			}
		}
		function alphanumeric(uadd) {
			var letters = /^[0-9a-zA-Z]+$/;
			if (uadd.value.match(letters)) {
				return true;
			} else {
				alert('User address must have alphanumeric characters only');
				uadd.focus();
				return false;
			}
		}

		function allnumeric(phone) {
			var numbers = /^[0-9]+$/;
			if (phone.value.match(numbers)) {
				var phone_len = phone.value.length;
				if (phone_len == 0 || phone_len > 10 || phone_len < 10) {
					alert("Mobile number should have length 10");
					phone.focus();
					return false;
				} else {
					return true;
				}
			} else {
				alert('Phone numbere must have numeric characters only');
				phone.focus();
				return false;
			}
		}
		function allnumericconsumer(phone) {
			var numbers = /^[0-9]+$/;
			if (phone.value.match(numbers)) {
				var phone_len = phone.value.length;
				if (phone_len == 0 || phone_len > 6 || phone_len < 6) {
					alert("consumer number should have length 6");
					phone.focus();
					return false;
				} else {
					return true;
				}
			} else {
				alert('consumer numbere must have numeric characters only');
				phone.focus();
				return false;
			}
		}
		function allnumericmeterno(phone) {
			var numbers = /^[0-9]+$/;
			if (phone.value.match(numbers)) {
				var phone_len = phone.value.length;
				if (phone_len == 0 || phone_len > 6 || phone_len < 6) {
					alert("meter number should have length 6");
					phone.focus();
					return false;
				} else {
					return true;
				}
			} else {
				alert('meter number must have numeric characters only');
				phone.focus();
				return false;
			}
		}

		function ValidateEmail(uemail) {
			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			if (uemail.value.match(mailformat)) {
				return true;
			} else {
				alert("You have entered an invalid email address!");
				uemail.focus();
				return false;
			}
		}
	</script>


</body>
</html>