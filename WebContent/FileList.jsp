<%@ page import="dal.Dbconnect,services.*,java.io.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	List<ClService> FileList = Dbconnect.getAdminFiles();
	request.setAttribute("FileList", FileList);
	pageContext.setAttribute("FileList", FileList);
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
<title>Region List</title>
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

			<h1>File List</h1>

			<%
				if (FileList.isEmpty()) {
			%>

			<h1>System has no data on cloud...</h1>

			<a href="UploadAdminFile.jsp">Click here to Upload Files</a>
			<%
				} else {
			%>
			<table border=2
				style="text-align: center; width: 60%; line-height: 25px;">
				<tr style="line-height: 40px;">
					<th>Sr. No.</th>
					<th>File Name</th>
					<th>Download</th>
					<th>Delete</th>

				</tr>
				<%
					int cnt = 1;
				%>
				<c:forEach var="file" items="${FileList}">
					<tr>
						<td>
							<%
								out.print(cnt);
							%>
						</td>
						<td><c:out value="${file.getName()}" /></td>
						<td>
							<form action="AdminFileDownload" method="post">
			
								<input type="hidden" value="${file.getFileid()}" name="fileid">
								<input type="hidden" value="${file.getName()}" name="filenm">
								<input type="hidden" value="${file.getRegion()}" name="region">
								<input type="hidden" value="${file.getN()}" name="n">
								<input type="submit" value="Download">
							</form>

						</td>
						
						<td>
							<form action="AdminDeletefile" method="post">
								
								<input type="hidden" value="${file.getFileid()}" name="fileid">
								<input type="hidden" value="${file.getName()}" name="filenm">
								<input type="hidden" value="${file.getRegion()}" name="region">
								<input type="hidden" value="admin" name="uid">
								<input type="hidden" value="${file.getCipher()}" name="cipher">

								<button name="btndelete" onclick="{return confirmComplete();}"><img src="images/close_1.png"></button>
							</form>
						</td>


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