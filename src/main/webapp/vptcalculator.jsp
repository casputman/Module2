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
				<a href="#" class="buttonlink" onclick="logOut()"><li>Log
						Out</li></a>
		</nav>
	</header>


	<main class="siteContent container">
	<center>
		<h3>
			<b>Calculator Demo</b>
		</h3>
	</center>
	<b>BMI CALCULATOR</b> <br>
	<br>
	<FORM METHOD="LINK" ACTION="bmicalculator.jsp">
		Type your weight: <INPUT TYPE="integer" NAME="weight"><br>
		<br> Type your length: <INPUT TYPE="integer" NAME="length"><br>
		<br> <INPUT TYPE="submit" VALUE="Calculate your BMI!">
	</FORM>
	<br>
	<b>FAT PERCENTAGE CALCULATOR</b> <br>
	<br>

	<FORM METHOD="LINK" ACTION="vptcalculator.jsp">
		Type your weight: <INPUT TYPE="integer" NAME="weight"><br>
		<br> Type your waistline: <INPUT TYPE="integer" NAME="waistline"><br>
		<br> Are you a man or a woman? <br> <br> <input
			type="radio" name="sex" value="male"> Male<br> <input
			type="radio" name="sex" value="female"> Female <br> <br>
		<INPUT TYPE="submit" VALUE="Calculate your fat percentage!">
	</FORM>
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
<script>

function GetUrlValue(VarSearch){
    var SearchString = window.location.search.substring(1);
    var VariableArray = SearchString.split('&');
    for(var i = 0; i < VariableArray.length; i++){
        var KeyValuePair = VariableArray[i].split('=');
        if(KeyValuePair[0] == VarSearch){
            return KeyValuePair[1];
        }
    }
}

alert("Your fat percentage = " + (if (GetUrlValue('sex') == "male") {
 Math.round(((-98.42+4.15*(GetUrlValue('waistline')/2.54)-0.082*(GetUrlValue('weight')*2.2))/GetUrlValue('weight')*2.2)); 
	}
	else {
		Math.round(((-76.76+4.15*(GetUrlValue('waistline')/2.54)-0.082*(GetUrlValue('weight')*2.2))/GetUrlValue('weight')*2.2));));
		}
		
</script>
