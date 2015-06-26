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
	href="style/webapp.css" />

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
		<li><a href="login" class="buttonlink">Login</a></li>
        <li><a href="start" class="buttonlink">Home</a></li>
        <li><a href="calclator.jsp" class="buttonlink">Calculator</a></li>
        <li><a href="startAbout" class="buttonlink">About</a></li>
        <li><a href="contact" class="buttonlink">Contact</a></li>
		</nav>
	</header>
	<div class="calculator">
		<h3>
			<b>CALCULATOR</b>
		</h3>
		<b>BMI CALCULATOR</b>
		<FORM ACTION="bmicalculator.jsp">
			<br> Type your weight: <INPUT TYPE="text" NAME="weight"><br>
			<br> Type your length: <INPUT TYPE="text" NAME="length"><br>
			<br> <b><INPUT TYPE="submit" VALUE="Calculate your BMI!"></b>
		</FORM>
		<br> <b>FAT PERCENTAGE CALCULATOR</b> <br> <br>

		<FORM ACTION="vptcalculator.jsp">
			Type your weight: <INPUT TYPE="text" NAME="weight"><br>
			<br> Type your waistline: <INPUT TYPE="text" NAME="waistline"><br>
			<br> Are you a man or a woman?<br> <br> <input
				type="radio" name="sex" value="male"> Male<br> <input
				type="radio" name="sex" value="female"> Female <br> <br>
			<b><INPUT TYPE="submit" VALUE="Calculate your fat percentage!"></b>
		</FORM>
	</div>
	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>
	<script>
		function GetUrlValue(VarSearch) {
			var SearchString = window.location.search.substring(1);
			var VariableArray = SearchString.split('&');
			for (var i = 0; i < VariableArray.length; i++) {
				var KeyValuePair = VariableArray[i].split('=');
				if (KeyValuePair[0] == VarSearch) {
					return KeyValuePair[1];
				}
			}
		}
		var bmi = Math.round(GetUrlValue('weight')
				/ Math.pow((GetUrlValue('length') / 100), 2));
		if (bmi > 25){
			alert ("Your BMI = " + bmi + ". You are considered overweight. Register, and let us help!") }
			else { if (bmi < 19){
				alert ("Your BMI = " + bmi + ". You are considered underweight. Register, and let us help!")
			}
			else { alert ("Your BMI = " + bmi + ". You are on a healthy weight, keep up te good work!")
		}}
	</script>
</body>
</html>
