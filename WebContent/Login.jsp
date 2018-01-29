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
<style>
.bgstyle
{


}
</style>
</head>

<body>
	<!-- Start Header Section -->
	<%@include file="base/header.html"%>
	<!-- End Header Section -->
<div class="bgstyle">
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div align="center"  style="font-size: 18px;">

		<form action="Login" method="post" >
			<div
				style="float: left; width: 40%; position: relative; padding-left: 150px; margin-right: 20px; padding-right: 10px"
				id="containt" class="leftimage">
				<br> <br>

				<h1>Secure Smart Grid System</h1>
				<center>
					<img src="img/download.jpeg" style="width: 100%; height: 100%;">
				</center>

			</div>
			
				
				<h1>User Sign In</h1>
				<table >
					<tr>
						<td>User Name: <br /> <br />
						</td>
						<td><input type="text" name="txtuname" autocomplete="off" required><br />
							<br /></td>
					</tr>
					<tr>
						<td>Password:<br /> <br />
						</td>
						<td><input type="password" name="txtpassword" autocomplete="off" required><br />
							<br /></td>
					</tr>




					<tr>
						<td colspan="2">
							<center>
								<input type="radio" name="r1" value="admin">Admin <input
									type="radio" name="r1" checked value="user">Customer
							</center>
						</td>
					</tr>
					<tr>
						<td><br/>&nbsp;</td>
						<td><center>
								<input type="submit" value="LOGIN">
								<input type="reset" value="RESET"><br />
							</center> <br>
							<p style="text-align: right ">
								<h2><a href="Registration.jsp">New User? Register Here</a></h2>
							</p></td>
						<td>&nbsp;</td>
					</tr>
				</table>
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
					<p align=" center">
						2017 All Rights Reserved <a href="index.jsp"> Secure Smart Grid Framework System </a>
					</p>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<!-- End footer Section -->
	
	
	</div>
</body>
</html>