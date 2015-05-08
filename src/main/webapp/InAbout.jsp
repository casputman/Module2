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
        <a href="webapp" class="buttonlink" ><li>Personal Home</li></a>
        <a href="InAbout" class="buttonlink" ><li>About</li></a>
        <a href="InContact" class="buttonlink" ><li>Contact</li></a>
		<a href="#" class="buttonlink" onclick="logOut()"><li>Log Out</li></a>
      </ul>
    </nav>
  </header>


  <main class="siteContent container">
    <div class="row">
		<div class="column-16">
			<h3><b>About</b></h3>
			<img src="images/transparentabout.png" alt="About" align="right" width="25%" height="25%">
&Uuml;ber-coaching bestaat uit een groep van meerdere diet-coaches die een brede groep klanten van advies op maat voorziet wat betreft voeding en sport. De doelgroep bestaat voornamelijk uit amateursporters, maar ook sporters op hoger niveau en zwaarlijvigen behoren tot het dagelijks klantenbestand. 

&Uuml;ber-coaching, niet te verwarren met het uit &Uuml;ber-coaching ontsprongen taxiconcern Uber, werd opgericht te Enschede en opereerde sinds omstreeks 2007 enkel in deze regio. In het jaar 2009 expandeerde het bedrijf naar andere delen van Nederland en over de grens richting Münster, Duitsland. Anno 2015 opereert het bedrijf in vijf landen in noordwest-Europa. Het klantenbestand bestaat inmiddels uit ruim 42.000 klanten en het eind is nog lang niet in zicht.

			
		</div>
		</div>
		</div>
		
	</div>
  </main>

  <footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>