<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html class="no-js" lang="">
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
</head>
<body>
	<!-- start preloader -->
	<div id="loader-wrapper">
		<div class="logo"></div>
		<div id="loader"></div>
	</div>
	<!-- end preloader -->
	<!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->


	
<jsp:include page="base/uheader.jsp" />

<div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>


	<div align="center" class="divleftright" Style="min-height: 400px;font-size:18px;">
		<div
			style="float: left; width: 40%; position: relative; padding-left: 150px; margin-right: 20px; padding-right: 10px"
			id="containt" class="leftimage">
			<br> <br> <br> <br> <br>

			<center>
				<img src="img/FileUpload.png" style="width: 100%; height: 100%;">
			</center>

		</div>
		
		<div
			style="position: relative; margin-right: 20px; padding-right: 10px">

			<center>
				<h1>Save Your Files Here</h1>

				<form action="FileUploader" method="post"
					enctype="multipart/form-data">
					<br>
					<table style="width: 20%;line-height: 20px;">

						<tr>
							<td colspan="2"><center>
									<input type="file" id="myfile" name="myfile" accept=".txt" required>
								</center></td>
						</tr>
						<tr><td> </td><td> </td></tr>
						<tr><td> </td><td> </td></tr>

						<tr>
							<td colspan="2"><input type="submit" name="Submit" value="Upload">
								<input type="reset" name="Submit" value="Reset"></td>
						</tr>
					</table>


				</form>
			</center>
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
						2017 All Rights Reserved <a href="index.jsp"> Secure Smart Grid
							Framework </a>
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

	<script src="js/main.js"></script>

	<script src="showHide.js" type="text/javascript"></script>

	

</div>
</body>
</html>
