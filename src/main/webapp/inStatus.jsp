<!doctype html>
<%@page import="core.UserServlet"%>
<%@page import="core.User"%>
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
</script>
</head>

<body>
	<header class="siteHeader container clearfix">
		<a href="webapp"> <img class="logo" src="images/Ubercoaching.png"
			alt="Ubercoaching">
		</a>
		<nav class="nav">
			<ul>
				<a href="webapp" class="buttonlink" href=""><li>Personal
						Home</li></a>
				<a href="InAbout" class="buttonlink" href=""><li>About</li></a>
				<a href="InContact" class="buttonlink" href=""><li>Contact</li></a>
				<a href="#" class="buttonlink" onclick="logOut()"><li>Log
						Out</li></a>
			</ul>
		</nav>
	</header>

	<main class="siteContent container">
	<div>
	 <%
     Object q = request.getAttribute("myBMI");
     String myBmi = q.toString();
 %>
	<p>
		Your BMI is:
		<%=myBmi%></p>

	<%Object g = request.getAttribute("myVET");
	String myVet = g.toString();%>
	<p>Your fat percentage is:
	<%=myVet %></p>
	<%
	    Object f = request.getAttribute("mybalance");
	    String myBalance = f.toString();
	    if (myBalance.equals("-1.0")) {
	      %> <p>not all of the required information is known to us, please make sure your age, gender, length and weight is filled in </p> <%
	    } else {
	%>
	<p>
		Your Kcal balance is:
		<%=myBalance%>
	</p>
	<%} %>
	<% Object m = request.getAttribute("myWeight"); 
	String weight = m.toString();%>
	<% Object n = request.getAttribute("myTimeAgo");
	String timeAgo = n.toString();%>
	<p> Your weight was <%= weight %>, <%= timeAgo %> days ago</p>
	<form method="POST" action="updateWeight">
	What is your current weight? <br>
	<input type="text" name="weight" placeholder="Weight in KG" class="mytext" required/> <br>
	For more information and a better calculation please also fill in your waistline span <br>
	<input type="text" name="width" placeholder="Waistline in cm" class="mytext"> <br>
	<input type="submit" value="Enter weight">
	</form>
	</div>
	<div>
		<form method="POST" action="GoalDetails">
		<p><b>Goal information:</b></p>
		<p>
			<p>What weight do want to achieve?</p>
			<input type="text" name="goalWeight" value="" placeholder=" Weight in KG" class="mytext" required/>
		</p>
		<p>
			<p>For when do you want to set this goal?</p>
			<input type="date" name="goalDate" value="" placeholder=" DD/MM/YYYY" class="mytext" required />
		</p>
		    <p><input type="submit" value="Set Goal"></p>
		</form>
		
	</div>
		<div class="backgroundstatistics">
		<div class="siteContent container statistics">
		<%Object j = request.getAttribute("myGoal");
		if (j != null) {
		 double goalWeight = ((searches.Goal) j).getGoalweight();
		 String goalDate = ((searches.Goal) j).getGoaldate();%>
			<p>
			Your current Goal is:
			<%=goalWeight%>
			</p>

			<p>
				You have until:	<%=goalDate%>
				To complete your goal
			</p>
<%} %>
		</div>
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