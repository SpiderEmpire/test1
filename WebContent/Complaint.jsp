<%@ page import="dal.Dbconnect.* , java.util.*"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

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

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div align="justify"
		style="font-size: 18px; margin-left: 20%; margin-right: 20%">
		<%
			ArrayList<String> RegionList = dal.Dbconnect.getAllRegion();
			request.setAttribute("RegionList", RegionList);
			pageContext.setAttribute("RegionList", RegionList);
		%>
		<%
			session = request.getSession(false);
			String uname = null;
			if (session != null) {
				uname = (String) session.getAttribute("username");

			}
			String[] User = dal.Dbconnect.getUserDetails(uname);
			request.setAttribute("User", User);
			pageContext.setAttribute("User", User);
		%>
		<h1 style="font-size: 22px;">Complaints</h1>


		<p></p>
		<form method="post" action="ComplaintServlet">

			<table>
				<tr>
					<td>Complaint type:</td>
					<td><select name="type"><option>Voltage
								Related</option>
							<option>Transformer Related</option>
							<option>Meter Related</option>
							<option>Bill Related</option></select></td>
				</tr>
				<tr>
					<td>Consumer Number</td>
					<td><input class="contact" type="text" name="consumer"
						value="<%=User[11]%>" readonly required="" /></td>
				</tr>

				<tr>
					<td>Name</td>
					<td><input class="contact" type="text" name="name"
						value="<%=User[1]%>" required="" /></td>
				</tr>
				<tr>
					<td><br>Email</td>
					<td><input class="contact" type="text" name="email" value=""
						pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required="" /></td>
				</tr>
				<tr>
					<td><br>Phone</td>
					<td><input class="contact" type="text" name="phone" value=""
						pattern="[7-9]{1}[0-9]{9}" required="" /></td>
				<tr>
					<td>Select Region</td>
					<td><select name="region">
							<%
								for (int i = 0; i < RegionList.size(); i++) {
							%>

							<option><%=RegionList.get(i)%></option>

							<%
								}
							%>


					</select></td>
				</tr>
				<tr>
					<td><br>Address</td>
					<td><input class="contact" type="text" name="address"
						value="<%=User[4]%>" required="" /></td>
				<tr>
				<tr>
					<td><br>Message</td>
					<td><br> <textarea class="contact textarea" rows="5"
							cols="50" name="msg" required=""></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><br>
						<center>
							<input class="submit" type="submit" name="contact_submitted"
								value="Send" required /> &nbsp;&nbsp;<input type="reset"
								value="Reset">
						</center></td>

				</tr>
			</table>

		</form>

	</div>
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

	<script src="js/main.js"></script>

	<script src="showHide.js" type="text/javascript"></script>



</body>
</html>
