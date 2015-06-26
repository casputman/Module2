<!doctype html>
<html>
<head>
<link rel="shortcut icon" type="image/ico" href="favicon.ico">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="HandheldFriendly" content="True">

<title>&Uuml;ber-coaching</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="style/concise.min.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="style/contactstyle.css" />

<script type="text/javascript">
	function logOut() {
		var logOut = confirm("Are you sure you want to log out ?");
		if (logOut == true) {
			location.href = "start";
		} else {
			location.href = "#";
		}
		return false;
	}
	function demoShow(id) {
		if (document.getElementById(id).style.display == "block") {
			document.getElementById(id).style.display = "none";
		} else {
			document.getElementById(id).style.display = "block";
		}
	}
</script>

</head>

<body>
	<header class="siteHeader container clearfix">
		<a href="webapp"> <img class="logo" src="images/Ubercoaching.png"
			alt="Ubercoaching">
		</a>
		<nav class="nav">
			<ul>
				<li><a href="login" class="buttonlink">Login/Personal</a></li>
				<li><a href="start" class="buttonlink">Home</a></li>
				<li><a href="startAbout" class="buttonlink">About</a></li>
				<li><a href="contact" class="buttonlink">Contact</a></li>
		</nav>
	</header>


	<main class="siteContent container">
	<center>
		<h3>
			<b>Contact form</b>
		</h3>

		</head>
		<body>
			<form form method="post" id="ContactForm" action="/ubercoaching/contactForm.jsp" onSubmit="alert('Thank you for your feedback.');"  class="cd-form floating-labels">
				<fieldset>
					</div>

					<div class="icon">
						<label class="cd-label" for="cd-name"><b>Name</b></label> <input
							class="user" type="text" name="cd-name" id="cd-name" required>
					</div>

					<div class="icon">
						<label class="cd-label" for="cd-company"><b>Subject</b></label> <input
							class="company" type="text" name="cd-company" id="cd-company">
					</div>

					<div class="icon">
						<label class="cd-label" for="cd-email"><b>Email</b></label> <input
							class="email error" type="email" name="cd-email" id="cd-email"
							required>
					</div>
					<div class="icon">
						<label class="cd-label" for="cd-textarea"><b>Message</b></label>
						<textarea class="message" name="cd-textarea" id="cd-textarea"
							required></textarea>
					</div>

					<div>
						<input type="submit" value="Send Message">
					</div>
				</fieldset>
			</form>

			</div>
	</main>

	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>
</body>
</html>

