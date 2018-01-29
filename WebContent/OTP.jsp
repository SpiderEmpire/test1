<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
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
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/textbutton.css">




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>User Login</title>

<script type="text/javascript">
	//Set timeout variables.
	var timoutWarning = 1000; // Display warning in 14 Mins.
	var timoutNow = 300000; // Timeout in 15 mins.
	var logoutUrl = 'index.jsp'; // URL to logout page.

	var warningTimer;
	var timeoutTimer;

	// Start timers.
	function StartTimers() {
		//warningTimer = setTimeout("IdleWarning()", timoutWarning);
		timeoutTimer = setTimeout("IdleTimeout()", timoutNow);
	}

	// Reset timers.
	function ResetTimers() {
		clearTimeout(warningTimer);
		clearTimeout(timeoutTimer);
		StartTimers();
		//$("#timeout").dialog('close');
	}

	// Show idle timeout warning dialog.
	/*function IdleWarning() {
	 $("#timeout").dialog({
	 modal: true
	 });
	 }*/

	// Logout the user.
	function IdleTimeout() {
		window.location = logoutUrl;
	}
</script>
</head>

<body  background="whiteimage.jpg"
	onload="StartTimers();" onmousemove="ResetTimers();">

	<!-- Start Header Section -->
	<header class="main_menu_sec navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-12">
				<div class="lft_hd">
					<a href="index.html"><img
						src="img/can-stock-photo_csp15804806.jpg" alt="" /></a>
				</div>
			</div>
			<div class="col-lg-9 col-md-9 col-sm-12">
				<div class="rgt_hd">
					<div class="main_menu">
						<nav id="nav_menu">
						<button aria-controls="navbar" aria-expanded="false"
							data-target="#navbar" data-toggle="collapse"
							class="navbar-toggle collapsed" type="button">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<div id="navbar">
							<ul>
								<li><a class="page-scroll" href="index.jsp">Home</a></li>


								<li><a class="page-scroll" href="Registration.jsp">Add
										New User</a></li>
								<li><a class="page-scroll" href="Login.jsp">User Login</a></li>

							</ul>
						</div>
						</nav>
					</div>

				</div>
			</div>
		</div>
	</div>
	</header>
	<!-- End Header Section -->



	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<center>
		<div style="width: 30%; font-size: 18px;">
			<h1 class="title">
				<a>Verification</a>
			</h1>

	<%
	if(request.getAttribute("Message")!=null)
	{
		%><form name="form1" id="form1" method="post" action="OTP">
		<h3><%=request.getAttribute("Message")%>
			<input type="hidden" id="otpold" name="otpold"
				value="<%=request.getAttribute("otp")%>" name="otpold" />
		</h3>
		Enter verification code sent to your email <br> <input
			type="password" name="otpnew" id="otpnew" name="otpnew"
			style="width: 50%;"> <br> <input type="submit"
			value="Submit" id="btnsubmit">

	</form>
	<%}
	
	else
	{
		%>
		<h1>Failed to send Otp...Please try again</h1>
	<%
	}%>

		
		</div>

	</center>
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
					<p align=" center">
						2017 All Rights Reserved <a href="index.jsp"> Secure Smart
							Grid Framework System </a>
					</p>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<!-- End footer Section -->

</body>
</html>