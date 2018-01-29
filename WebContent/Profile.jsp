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

</head>
<body background="whiteimage.jpg">

	<%
		session = request.getSession(false);
	String uname=null;
			if (session != null) {
		uname=(String)session.getAttribute("username");
			
			}
		String[] User = Dbconnect.getUserDetails(uname);
		request.setAttribute("User", User);
		pageContext.setAttribute("User", User);
	%>
	<!-- Start Header Section -->

	<jsp:include page="base/uheader.jsp" />
	<!-- End Header Section -->

	<br>
	<br>
	<br>
	<br>



	<div>
		<form action="UpdateProfile" class="register" method="post">
			<h1>Profile</h1>
			<fieldset class="row1">
				<legend>Consumer Details </legend>
				<p>
					<label>Region </label> <input type="text" name="region" readonly
						value="<%=User[2]%>"> <label>Consumer Number </label> <input
						type="text" name="txtconsumer" value="<%=User[11]%>" readonly/>
				</p>
				<p>
					<label>Meter Number </label> <input type="text" name="txtmeterno" value="<%=User[3]%>" readonly/>

				</p>
			</fieldset>
			<fieldset class="row1">
				<legend>Login Details </legend>
				<p>
					<label>UserId </label> <input type="text" name="txtuserid"  value="<%=User[6]%>" readonly/>
					 <label>Password
					</label> <input type="text"  name="txtpassword" maxlength="8" value="<%=User[7]%>"/>
				</p>



			</fieldset>
			<fieldset class="row1">
				<legend>User Details </legend>
				<p>
					<label>First Name </label> <input type="text" name="txtfnm" value="<%=User[8]%>" pattern='[A-Za-z\\s]*'/> <label>Last
						Name </label> <input type="text" name="txtlnm" value="<%=User[9]%>" pattern='[A-Za-z\\s]*'/>
				<p>
					<label>Email Address </label> <input type="email" name="txtemail" value="<%=User[5]%>"/>

					<label>Mobile Number </label> <input type="text" name="txtphone" pattern="[0-9]{9}" value="<%=User[10]%>"/>
					<label>Address </label>
					<textarea rows="3" cols="20" name="address" required><%=User[4]%></textarea>

				</p>


			</fieldset>

			<fieldset class="row1">
				<div align="center">
					<button class="button">Update &raquo;</button>
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
			var meterno = document.registration.txtmeterno;
			var fnm = document.registration.txtfnm;
			var lnm = document.registration.txtlnm;
			var uemail = document.registration.txtemail;
			var passid = document.registration.txtpassword;
			var repass = document.registration.txtrepassword;
			var phone = document.registration.txtphone;
			var addr = document.registration.address;
			var pincode = document.registration.pincode;

			if (allLetter(fnm)) {
				if (allLetter(lnm)) {
					if (ValidateEmail(uemail)) {
						if (passid_validation(passid, 6, 12)) {
							if (passwordmatch(passid, repaass)) {
								if (allnumeric(phone)) {
									if (alphanumeric(addr)) {
										if (allnumericpin(pincode, 6)) {

											return true;
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
			var repassword = repass.value
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
				alert('Username must have alphabet characters only');
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
					return true;
				} else {
					alert('Phone numbere must have numeric characters only');
					phone.focus();
					return false;
				}
			}
		}

		function allnumericpin(pin, size) {
			alert("pincode enter");
			var numbers = /^[0-9]+$/;
			if (pin.value.match(numbers)) {
				var pin_len = pin.value.length;
				if (pin == 0 || pin > 6 || pin < 6) {
					alert("pincode number should have length 6");
					pin.focus();
					return true;
				} else {
					alert('pincode numbere must have numeric characters only');
					pin.focus();
					return false;
				}
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