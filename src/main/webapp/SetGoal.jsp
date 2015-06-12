<%@page import="searches.GoalShow"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/ico" href="favicon.ico">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="HandheldFriendly" content="True">

  <title>&Uuml;ber-coaching</title>

  <link rel="stylesheet" type="text/css" media="screen" href="style/concise.min.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="style/contactstyle.css" />
  
</head>
<body>
  <header class="siteHeader container clearfix">
    <a href="start">
		<img class="logo" src="images/Ubercoaching.png" alt="Ubercoaching">
	</a>
    <nav class="nav">
     <ul>
        <a href="webapp" class="buttonlink" href=""><li>Personal Home</li></a>
        <a href="InAbout" class="buttonlink" href=""><li>About</li></a>
        <a href="InContact" class="buttonlink" href=""><li>Contact</li></a>
		<a href="#" class="buttonlink" onclick="logOut()"><li>Log Out</li></a>
		
      </ul>
    </nav>
  </header>
<%
final java.util.Map<String, String> returnMap = (java.util.Map<String, String>) request.getAttribute("return");
final String goalWeight =  returnMap == null ? "" : returnMap.get("GoalWeight");
final String goalDate =  returnMap == null ? "" : returnMap.get("GoalDate");
%>

  <div class="siteContent container">
	<div>
		<form method="post" action="GoalDetails">
		<p><b>Goal information:</b></p>
		<p>
			<p>What weight do want to achieve?</p>
			<input type="text" name="goalWeight" value="<%=goalWeight%>" placeholder=" Username" class="mytext" required/>
		</p>
		<p>
			<p>For when do you want to set this goal?</p>
			<input type="date" name="goalDate" value="<%=goalDate%>" placeholder=" DD/MM/YYYY" class="mytext" required />
		</P>
		    <p><input type="submit" value="Set Goal"></p>
		</form>
		
	</div>
  </div>

  <footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>