<% 
String pageTitle = request.getParameter("pageTitle");
pageTitle = pageTitle == null || pageTitle.length() == 0 ? null : pageTitle;


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
</head>

<body>
  <header class="siteHeader container clearfix">
    <a href="start">
		<img class="logo" src="images/Ubercoaching.png" alt="Ubercoaching">
	</a>
    <nav class="nav">
      <ul>
		<li><a href="login" class="buttonlink" <%=request.getAttribute("user") == null ? "" : "style=\"text-decoration:line-through;\""%>>Login</a></li>
        <li><a href="start" class="buttonlink">Home</a></li>
        <li><a href="startAbout" class="buttonlink">About</a></li>
        <li><a href="contact" class="buttonlink">Contact</a></li>
      </ul>
    </nav>
  </header>