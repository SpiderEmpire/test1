
<%@ page import="dal.Dbconnect,services.*,java.io.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<header class="main_menu_sec navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-12">
				<div class="lft_hd">
					<a href="index.html"><img
						src="img/can-stock-photo_csp15804806.jpg" alt="" /></a>
				</div>
				<div
					style="width: 170%; font-size: 34px; color: white; margin-top: 12%;">
					<a style="font-size: 34px; color: white; margin-top: 20%">Secure
						Smart Grid</a>
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
									<li><a class="page-scroll" href="Welcome.jsp">Home</a></li>

									<li><a class="page-scroll" href="SaveData.jsp">Meter
											Reading </a></li>
									<li><a class="page-scroll" href="#pr_sec">Personal</a>

										<ul>
											<li style="margin-left: 0px;"><a class=""
												href="Personal.jsp">File List </a></li>
											<li style="margin-left: 0px;"><a class=""
												href="Uploadfile.jsp">Save Files</a></li>

										</ul></li>

									<li><a class="page-scroll" href="Report">Report</a></li>
									<li><a class="page-scroll" href="Report.jsp">Monthly Bill</a></li>
									<li><a class="page-scroll" href="Complaint.jsp">Complaint
											Us</a></li>

									<li><a class="page-scroll" href="#pr_sec"> <% session
											= request.getSession(false); if (session != null) {
											out.println(session.getAttribute("name")); } %> </a>
										<ul>
											<li style="margin-left: 0px;"><a class=""
												href="Profile.jsp">Profile </a></li>
											<li style="margin-left: 0px;"><a class="" href="Logout">Logout
											</a></li>

										</ul></li>
										
<%int count=Dbconnect.GetNewNoticeCount(); %>
									<li><a href="ShowPublicNotice.jsp"
										style="position: relative; display: block; height: 50px; width: 50px; background: url('http://i.imgur.com/evpC48G.png');
										 background-size: contain; text-decoration: none;margin-top: 15px;"><span
											style="position: absolute; right: 11px; top: 2px; color: #fff;"><%=count %></span></a></li>

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