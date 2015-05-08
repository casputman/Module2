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
		<a href="login" class="buttonlink" ><li>Login</li></a>
        <a href="start" class="buttonlink" ><li>Home</li></a>
        <a href="startAbout" class="buttonlink" ><li>About</li></a>
        <a href="contact" class="buttonlink" ><li>Contact</li></a>
    </nav>
  </header>


  <main class="siteContent container">
	<div>
		<form method="post" action="webapp">
		</form>
		<p>
		<b>Please insert your username:</b> </br>
		<input type="text" name="login" value="" placeholder=" Username" class="mytext">
		</p>
		<p>
		<b>Please insert your password: </b></br>
		<input type="password" name="password" value="" placeholder=" Password" class="mytext">
		</P>
		<p>
		<b>Please insert your password again:</b></br>
		<input type="password" name="password" value="" placeholder=" Password" class="mytext">
		</P>
		<button  onclick="location.href='registerinfo.html'">
		<b>Register now!</b>
		</button>
	</div>
  </main>

  <footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>