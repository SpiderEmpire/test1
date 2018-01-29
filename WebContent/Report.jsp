<%@ page import="dal.Dbconnect , java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style>
.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 18px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
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
</head>
<body background="whiteimage.jpg">

	<!-- Start Header Section -->
	<jsp:include page="base/uheader.jsp" />
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>


	<div align="containt" class="divleftright"
		style="font-size: 18px; min-height: 500px;">
		<div style="float: left; margin: 80px;" id="containt"
			class="leftimage">

			<center>
				<img src="img/report.jpg" style="height: 400px; width: 300">
			</center>

		</div>
		<div style="float: right; margin-right: 280px">
			<center>
				<br> <br>
				<h1>Report</h1>
			</center>

			<form action="MonthlyBill" method="post">
				<table style="font-family: Constantia; font-size: 18"
					cellspacing="50" style="text-align: center" width="370px">
					<tr>
						<td>select month</td>
						<td><select name="month" required>
						<option value="">None</option>
								<%
									for (int i = 1; i <= 12; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td>Select Year</td>
						<td><select name="year" required>
						<option value="">None</option>
								<%
									for (int i = 2016; i <= 2017; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
						</select></td>
					</tr>


					<tr>
						<td><input type="submit" value="Submit"></td>
						<td>&nbsp;&nbsp;&nbsp;<input type="reset" class="button"
							value="Reset"></td>
					</tr>
				</table>

			
			</form>


			<br /> <br />
		</div>
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
						2017 All Rights Reserved <a href="main.jsp"> Secure Smart Grid
							Framework System </a>
					</p>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<!-- End footer Section -->
</body>
</html>