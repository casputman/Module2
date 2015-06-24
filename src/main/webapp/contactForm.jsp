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
			<b>Contactform</b>
		</h3>

		<script language="javascript">
				var sa_email_id = '55055-f1b58';
				var sa_sent_text = 'Thank you for contacting us. We will get back to you soon.';
			</script>
		<script language="javascript"
			src="http://s1.smartaddon.com/sa_htmlform.js"></script>
		<div id="sa_contactdiv">

				<form name=sa_htmlform style="margin: 0px"
					onsubmit="return sa_contactform()">
					<table>
						<tr>
							<td>Name:<br> <input type="text" name="name" /></td>
						</tr>
						<tr>
							<td>E-mail Address: <span style="color: #D70000">*</span><br>
								<input type="text" name="email" required="true" /></td>
						</tr>
						<tr>
							<td>Phone Number:<br> <input type="text" name="number" /></td>
						</tr>
						<tr>
							<td>Subject: <span style="color: #D70000">*</span><br>
								<input type="text" name="subject" required="true" /></td>
						</tr>
						<tr>
							<td>Message: <span style="color: #D70000">*</span><br>
								<textarea name="message" cols="42" rows="9" required="true"></textarea></td>
						</tr>
						<tr>
							<td><input type="submit" value="Send Message"
								style="font-weight: bold"></td>
						</tr>
					</table>
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

