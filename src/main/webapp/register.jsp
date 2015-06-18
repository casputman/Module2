<!doctype html>
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
		<li><a href="login" class="buttonlink">Login</a></li>
        <li><a href="start" class="buttonlink">Home</a></li>
        <li><a href="calculator.jsp" class="buttonlink">Calculator</a></li>
        <li><a href="startAbout" class="buttonlink">About</a></li>
        <li><a href="contact" class="buttonlink">Contact</a></li>
      </ul>
    </nav>
  </header>
<%
final boolean passNotEqual = request.getAttribute("passNotEqual") != null && (Boolean) request.getAttribute("passNotEqual");
final boolean usernameInUse = request.getAttribute("usernameInUse") != null && (Boolean) request.getAttribute("usernameInUse");
final boolean emailInUse = request.getAttribute("emailInUse") != null && (Boolean) request.getAttribute("emailInUse");

final java.util.Map<String, String> returnMap = (java.util.Map<String, String>) request.getAttribute("return");
final String username = returnMap == null ? "" : returnMap.get("username");
final String length = returnMap == null ? "" : returnMap.get("length");
final String surname = returnMap == null ? "" : returnMap.get("surname");
final String firstname = returnMap == null ? "" : returnMap.get("firstname");
final String email = returnMap == null ? "" : returnMap.get("email");
final String age = returnMap == null ? "" : returnMap.get("age");
final String gender = returnMap == null ? "" : returnMap.get("gender");
final String genderM = gender.equals("m") ? " checked=\"checked\"" : "";
final String genderV = gender.equals("v") ? " checked=\"checked\"" : "";


%>

  <div class="siteContent container">
	<div>
		<form method="post" action="register">
		<% if (passNotEqual) { %>
	  	<p style="color:red">Passwords don't match.</p>
	  	<% } 
	  	if (usernameInUse) { %>
	  	<p style="color:red">Username is already in use.</p>
	  	<% }
	  	if (emailInUse) { %>
	  	<p style="color:red">E-mail address is already in use.</p>
	  	<% } %>
		<p><b>Account information:</b></p>
		<p>
			<input type="text" name="username" value="<%=username%>" placeholder=" Username" class="mytext" />
		</p>
		<p>
			<input type="password" name="password1" placeholder=" Password" class="mytext" required />
		</P>
		<p>
			<input type="password" name="password2" placeholder=" Password" class="mytext" required />
		</p>
		<p><b>To help you with your health we first need to know a little bit about you:</b>
		<p>
			<input type="number" name="length" value="<%=length%>" placeholder=" Length" class="mytext" />
		</p>
		<p>
			<input type="number" name="age" value="<%=age%>" placeholder=" Age" class="mytext" />
		</p>
		<p>
			<input type="text" name="surname" value="<%=surname%>" placeholder=" Surname" class="mytext" />
		</p>
		<p>
			<input type="text" name="firstname" value="<%=firstname%>" placeholder=" First Name" class="mytext" />
		</p>
		<p>
			<input type="email" name="email" value="<%=email%>" placeholder=" E-mail address" class="mytext" />
		</p>
		<p>
			<input type="radio" name="gender" value="m" placeholder="" class="mytext"<%=genderM%> /> Male<br />
			<input type="radio" name="gender" value="f" placeholder="" class="mytext"<%=genderV%> /> Female
		</p>
		
		    <p><input type="submit" value="Register"></p>
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