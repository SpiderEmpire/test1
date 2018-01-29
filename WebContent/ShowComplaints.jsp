<%@ page import="dal.Dbconnect,services.*,java.io.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
session = request.getSession(false);

	List<Complaints> Complaints = Dbconnect.getUserComplaints();
	request.setAttribute("Complaints", Complaints);
	pageContext.setAttribute("Complaints", Complaints);
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
<link rel="stylesheet" href="css/table.css">
<title>User Complaints</title>
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
	<center>


		<div style="font-size: 18px; min-height: 500px">

			<h1>User Complaints</h1>

			<%
				if (Complaints.isEmpty()) {
			%>

			<h1>Customer has not any complaints...</h1>

		
			<%
				} else {
			%>
			<table border=2
				style="text-align: center; width: 60%; line-height: 25px;">
				<tr style="line-height: 40px;">
					<th>Sr. No.</th>
					<th>Name</th>
					<th>Consumer Number</th>					
					<th>Email</th>
					<th>Phone</th>
					<th>Region</th>
					<th>Address</th>
					<th>Complaints type</th>
					<th>Complaints</th>
				</tr>
				<%
					int cnt = 1;
				%>
				<c:forEach var="file" items="${Complaints}">
					<tr>
						<td>
							<%
								out.print(cnt);
							%>
						</td>
						<td><c:out value="${file.getName()}" /></td>	
						<td><c:out value="${file.getConsumer()}" /></td>
						<td><c:out value="${file.getEmail()}" /></td>
						<td><c:out value="${file.getPhone()}" /></td>
						<td><c:out value="${file.getRegion()}" /></td>
						<td><c:out value="${file.getAddress()}" /></td>
						<td><c:out value="${file.getType()}" /></td>
						<td><c:out value="${file.getComplaint()}" /></td>
								
						
					
					</tr>
					<%
						cnt++;
					%>
				</c:forEach>

			</table>

			<%
				}
			%>

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
						2017 All Rights Reserved <a href="main.jsp"> Secure smart Grid System </a>
					</p>
				</ul>
			</div>
		</div>
	</div>
	</footer>
	<!-- End footer Section -->
	<script type="text/javascript">
	function confirmComplete() {
		
		var answer=confirm("Are you sure you want to delete");
		if (answer==true)
		  {
		    return true;
		  }
		else
		  {
		    return false;
		  }
		}
	
	
	</script>
</body>
</html>