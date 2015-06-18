<% 
String pageTitle = request.getParameter("pageTitle");
pageTitle = pageTitle == null || pageTitle.length() == 0 ? null : pageTitle;

final core.User user = (core.User) request.getAttribute("user");
final boolean authorized = user != null;


 %>
<!doctype html>
<html>
<head>
<link rel="shortcut icon" type="image/ico" href="favicon.ico">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="HandheldFriendly" content="True">

  <title>&Uuml;ber-coaching<%=pageTitle == null ? "" : " - " + pageTitle %></title>

  <link rel="stylesheet" type="text/css" media="screen" href="style/concise.min.css" />
  <link rel="stylesheet" type="text/css" media="screen" href="style/login.css" />
  
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>

<body>
  <header class="siteHeader container clearfix">
    <a href="start">
		<img class="logo" src="images/Ubercoaching.png" alt="Ubercoaching">
	</a>
    <nav class="nav">
      <ul>
<% if (authorized) { %>
        <li><a href="webapp" class="buttonlink">Personal Home</a></li>
        <li><a href="InAbout" class="buttonlink">About</a></li>
        <li><a href="InContact" class="buttonlink">Contact</a></li>
		<li><a href="logout" class="confirm buttonlink">Log Out</a></li>
<% } else { %>
		<li><a href="login" class="buttonlink">Login</a></li>
        <li><a href="start" class="buttonlink">Home</a></li>
        <li><a href="calculator.jsp" class="buttonlink">Calculator</a></li>
        <li><a href="startAbout" class="buttonlink">About</a></li>
        <li><a href="contact" class="buttonlink">Contact</a></li>
<% } %>
      </ul>
    </nav>
  </header>