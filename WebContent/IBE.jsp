<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
<head>

<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Secure smart grid system</title>
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


	<jsp:include page="base/uheader.jsp" />

	<br>
	<br>
	<br>


	<div align="justify" style="font-size:18px; margin-left:20%;margin-right:20%">
		<br> <br> <br> <br>
		<div>
		<h1>Identity Based Encryption</h1>
			<p>ID-based encryption, or identity-based encryption (IBE), is an
				important primitive of ID-based cryptography. As such it is a type
				of public-key encryption in which the public key of a user is some
				unique information about the identity of the user (e.g. a user's
				email address). This means that a sender who has access to the
				public parameters of the system can encrypt a message using e.g. the
				text-value of the receiver's name or email address as a key. The
				receiver obtains its decryption key from a central authority, which
				needs to be trusted as it generates secret keys for every user.</p>

			<p>ID-based encryption was proposed by Adi Shamir in 1984. He
				was however only able to give an instantiation of identity-based
				signatures. Identity-based encryption remained an open problem for
				many years. The pairing-based Boneh–Franklin scheme and Cocks's
				encryption scheme based on quadratic residues both solved the IBE
				problem in 2001. Usage[edit] Identity-based systems allow any party
				to generate a public key from a known identity value such as an
				ASCII string. A trusted third party, called the Private Key
				Generator (PKG), generates the corresponding private keys. To
				operate, the PKG first publishes a master public key, and retains
				the corresponding master private key (referred to as master key).
				Given the master public key, any party can compute a public key
				corresponding to the identity by combining the master public key
				with the identity value. To obtain a corresponding private key, the
				party authorized to use the identity ID contacts the PKG, which uses
				the master private key to generate the private key for identity ID.
				As a result, parties may encrypt messages (or verify signatures)
				with no prior distribution of keys between individual participants.
				This is extremely useful in cases where pre-distribution of
				authenticated keys is inconvenient or infeasible due to technical
				restraints. However, to decrypt or sign messages, the authorized
				user must obtain the appropriate private key from the PKG. A caveat
				of this approach is that the PKG must be highly trusted, as it is
				capable of generating any user's private key and may therefore
				decrypt (or sign) messages without authorization.</p>


			<p>The steps involved are depicted in this diagram:</p>
				<img src="img/IBE.PNG" style="width: 80%; height: 80%;">

			<h2>Advantages</h2>
			<ul>
				<li>One of the major advantages of any identity-based
					encryption scheme is that if there are only a finite number of
					users, after all users have been issued with keys the third party's
					secret can be destroyed.This can take place because this system
					assumes that, once issued, keys are always valid (as this basic
					system lacks a method of key revocation).The majority of
					derivatives of this system which have key revocation lose this
					advantage.</li>

				<li>Moreover, as public keys are derived from identifiers, IBE
					eliminates the need for a public key distribution infrastructure.
					The authenticity of the public keys is guaranteed implicitly as
					long as the transport of the private keys to the corresponding user
					is kept secure (Authenticity, Integrity, Confidentiality).</li>
				<li>Apart from these aspects, IBE offers interesting features
					emanating from the possibility to encode additional information
					into the identifier. For instance, a sender might specify an
					expiration date for a message. He appends this timestamp to the
					actual recipient's identity (possibly using some binary format like
					X.509). When the receiver contacts the PKG to retrieve the private
					key for this public key, the PKG can evaluate the identifier and
					decline the extraction if the expiration date has passed.
					Generally, embedding data in the ID corresponds to opening an
					additional channel between sender and PKG with authenticity
					guaranteed through the dependency of the private key on the
					identifier.</li>
			</ul>

			<h2>Drawbacks</h2>
			<ul>

				<li>If a Private Key Generator (PKG) is compromised, all
					messages protected over the entire lifetime of the public-private
					key pair used by that server are also compromised. This makes the
					PKG a high-value target to adversaries. To limit the exposure due
					to a compromised server, the master private-public key pair could
					be updated with a new independent key pair. However, this
					introduces a key-management problem where all users must have the
					most recent public key for the server.</li>
				<li>Because the Private Key Generator (PKG) generates private
					keys for users, it may decrypt and/or sign any message without
					authorization. This implies that IBE systems cannot be used for
					non-repudiation. This may not be an issue for organizations that
					host their own PKG and are willing to trust their system
					administrators and do not require non-repudiation.</li>
				<li>The issue of implicit key escrow does not exist with the
					current PKI system, wherein private keys are usually generated on
					the user's computer. Depending on the context key escrow can be
					seen as a positive feature (e.g., within Enterprises). A number of
					variant systems have been proposed which remove the escrow
					including certificate-based encryption, secret sharing, secure key
					issuing cryptography and certificateless cryptography.</li>
				<li>A secure channel between a user and the Private Key
					Generator (PKG) is required for transmitting the private key on
					joining the system. Here, a SSL-like connection is a common
					solution for a large-scale system. It is important to observe that
					users that hold accounts with the PKG must be able to authenticate
					themselves. In principle, this may be achieved through username,
					password or through public key pairs managed on smart cards.</li>
				<li>IBE solutions may rely on cryptographic techniques that are
					insecure against code breaking quantum computer attacks.</li>

			</ul>

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
