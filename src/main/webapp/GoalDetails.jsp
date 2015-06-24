<!doctype html>
<%@page import="searches.Goal"%>
<%@page import="searches.GoalShow"%>
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
				<a href="webapp" class="buttonlink"><li>Personal Home</li></a>
				<a href="InAbout" class="buttonlink"><li>About</li></a>
				<a href="InContact" class="buttonlink"><li>Contact</li></a>
				<a href="#" class="buttonlink" onclick="logOut()"><li>LogOut</li></a>
			</ul>
		</nav>
	</header>

	<main class="siteContent container">
	
	<%
	int goalWeight = -1;
	String goalDate = "-1";
	core.User user = null;
	if (request.getSession().getAttribute("user") != null) {
 		user = core.User.fromIdUser(((core.User) request.getSession()
 				.getAttribute("user")).getIdUser());
 	}
	searches.GoalShow goalShow = new GoalShow();
	goalShow.getGoalBean(user);
	searches.Goal goal = user.getGoal();
	goalWeight = goal.getGoalweight();
	goalDate = goal.getGoaldate();
	
	%>
	<center>
		<p>
		<div class="backgroundstatistics">
		<div class="siteContent container statistics">
			<p>
			Your current Goal is:
			<%=goalWeight%>
			</p>

			<p>
				You have until:	<%=goalDate%>
				To complete your goal
			</p>

		</div>
		</div>
	</center>
	</main>

	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>
</body>
</html>
