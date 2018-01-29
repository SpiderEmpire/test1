<%@ page import="dal.Dbconnect,services.*,java.io.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	List<Region> RegionList = Dbconnect.getAllRegionDetails();
request.setAttribute("RegionList", RegionList);
pageContext.setAttribute("RegionList", RegionList);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Secure Smart Grid system</title>
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

<title>Add Region</title>
</head>
<body>

	<!-- Start Header Section -->
	<%@include file="base/adminheader.html"%>
	<!-- End Header Section -->

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div align="center" class="divleftright">
		<div
			style="float: left; width: 40%; position: relative; padding-left: 150px; margin-right: 20px; padding-right: 10px"
			id="containt" class="leftimage">
			<br> <br> <br> <br>

			<center>
				<img src="img/region.jpg" style="width: 100%; height: 100%;">
			</center>

		</div>
		<div>
			<center>
				<h1>Registration For Regional Cloud</h1>



				<form action="AddRegion" method="post">
					<table style="font-size: 18px;">
						<tr>
							<td>Region name</td>
							<td><input type="text" name="txtregion" required
								autocomplete="off" autofocus></td>
						</tr>
						<tr>
							<td>Data Center Name</td>
							<td><input type="text" name="txtDC" required
								autocomplete="off"></td>
						</tr>

						<tr>
							<td>Cost Per Memory</td>
							<td><input type="number" name="txtcost" required
								autocomplete="off"></td>
						</tr>
						<tr>
							<td>Server Name</td>
							<td><input type="text" name="txtSnm" required
								autocomplete="off"></td>
						</tr>
						<tr>
							<td>Server Description</td>
							<td><input type="text" name="txtSdesc" required
								autocomplete="off"></td>
						</tr>
						<tr>
							<td colspan="2"><center>
									<input type="submit" name="Submit" value="Submit"> <input
										type="reset" name="Submit" value="Reset">
								</center></td>

						</tr>
					</table>
				</form>

			</center>
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
						2017 All Rights Reserved <a href="index.jsp"> Secure Smart
							Grid Framework </a>
					</p>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<!-- End footer Section -->

	<script type="text/javascript"
		src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script src="js/vendor/jquery-1.11.2.min.js"></script>

	<script src="js/isotope.pkgd.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/appear.js"></script>
	<script src="js/jquery.counterup.min.js"></script>
	<script src="js/waypoints.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/showHide.js"></script>
	<script src="js/jquery.nicescroll.min.js"></script>
	<script src="js/jquery.easing.min.js"></script>
	<script src="js/scrolling-nav.js"></script>
	<script src="js/plugins.js"></script>
	<!-- Google Map js -->
	<script src="https://maps.googleapis.com/maps/api/js"></script>
	<script>
		function initialize() {
			var mapOptions = {
				zoom : 14,
				scrollwheel : false,
				center : new google.maps.LatLng(41.092586000000000000,
						-75.592688599999970000)
			};
			var map = new google.maps.Map(document.getElementById('googleMap'),
					mapOptions);
			var marker = new google.maps.Marker({
				position : map.getCenter(),
				animation : google.maps.Animation.BOUNCE,
				icon : 'img/map-marker.png',
				map : map
			});
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>
	<script src="js/main.js"></script>

	<script src="showHide.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(document).ready(function() {

			$('.show_hide').showHide({
				speed : 1000, // speed you want the toggle to happen	
				easing : '', // the animation effect you want. Remove this line if you dont want an effect and if you haven't included jQuery UI
				changeText : 1, // if you dont want the button text to change, set this to 0
				showText : 'View',// the button text to show when a div is closed
				hideText : 'Close' // the button text to show when a div is open

			});

		});
	</script>
	<script>
		jQuery(document).ready(function($) {
			$('.counter').counterUp({
				delay : 10,
				time : 1000
			});
		});
	</script>

	<script>
		//Hide Overflow of Body on DOM Ready //
		$(document).ready(function() {
			$("body").css("overflow", "hidden");
		});

		// Show Overflow of Body when Everything has Loaded 
		$(window).load(function() {
			$("body").css("overflow", "visible");
			var nice = $('html').niceScroll({
				cursorborder : "5",
				cursorcolor : "#00AFF0",
				cursorwidth : "3px",
				boxzoom : true,
				autohidemode : true
			});

		});
	</script>



</body>
</html>
