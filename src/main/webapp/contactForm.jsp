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
				<a href="webapp" class="buttonlink"><li>Personal Home</li></a>
				<a href="InAbout" class="buttonlink"><li>About</li></a>
				<a href="InContact" class="buttonlink"><li>Contact</li></a>
				<a href="#" class="buttonlink" onclick="logOut()"><li>LogOut</li></a>
		</nav>
	</header>


	<main class="siteContent container">
	<center>
		<h3>
			<b>Contactform</b>
		</h3>

	<form name="htmlform" method="post" action="html_form_send.php">
		<table width="450px">
			</tr>
			<tr>
				<td valign="top"><label for="first_name">First Name *</label></td>
				<td valign="top"><input type="text" name="first_name"
					maxlength="50" size="30"></td>
			</tr>

			<tr>
				<td valign="top""><label for="last_name">Last Name *</label></td>
				<td valign="top"><input type="text" name="last_name"
					maxlength="50" size="30"></td>
			</tr>
			<tr>
				<td valign="top"><label for="email">Email Address *</label></td>
				<td valign="top"><input type="text" name="email" maxlength="80"
					size="30"></td>

			</tr>
			<tr>
				<td valign="top"><label for="telephone">Telephone
						Number</label></td>
				<td valign="top"><input type="text" name="telephone"
					maxlength="30" size="30"></td>
			</tr>
			<tr>
				<td valign="top"><label for="comments">Comments *</label></td>
				<td valign="top"><textarea name="comments" maxlength="1000"
						cols="25" rows="6"></textarea></td>

			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="submit"
					value="Submit">
				</td>
			</tr>
		</table>
			</center>
	</form>
	</main>

	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>
</body>
</html>

