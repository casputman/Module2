


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
  <link rel="stylesheet" type="text/css" media="screen" href="style/webapp.css" />
  
  <script type="text/javascript">
  function logOut() {
	var logOut = confirm("Are you sure you want to log out ?");
	if( logOut == true ){
		location.href = "start";
	}else{
		location.href = "#";
	  }
		return false;
   }
	</script>
</head>

<body>
  <header class="siteHeader container clearfix">
    <a href="webapp">
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

  <main class="siteContent container">
  <%
  System.out.println("lolol");
  core.User user = core.User.fromIdUser(4);
  System.out.println("lolol2");
  //TODO user koppelen.
  double bmi = 0;
  double vet = 0;
  if (user != null) {
  	bmi = user.getUserBMI().getBMI();
  	vet = user.getUserVet().getVPT();
  }
  %>
		<p> Uw BMI: <%= bmi%></p>
		
		<p> Uw Vetpercentage: <%= vet%> </p>
		
  </main>

  <footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>