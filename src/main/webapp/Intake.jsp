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
  function textShizzle() {
	  var text = document.getElementById("textinput").value; //|| document.getElementById("textinput").innerText;
	  document.getElementById("textDing").innerHTML = text;
	  
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
    <p><b>Enter your food over here:</b></p>
		<div id="tfheader">

			<form id="tfnewsearch" method="GET" action="search" autocomplete="on">
		        <input type="text" id="textinput" class="tftextinput" name="q" size="21" maxlength="120" oninput="textShizzle()"><input type="submit" value="search" class="tfbutton">
			</form>
		<div class="tfclear">
		</div>
	</div>
	<div id="textDing"> 
		<p>hier komt text</p>
	</div>
	<div>
		<ul>
			<li>Vodka - 100 Kcal - 1x</li>
			<li>curryWürst - 300 Kcal - 2x</li>
		</ul>
	</div>
	
	<p><b>Enter your activities over here:</b></p>
	<div id="tfheader">
		<form id="tfnewsearch" method="GET" action="http://www.google.com" autocomplete="on">
		        <input type="text" class="tftextinput" name="q" size="21" maxlength="120"><input type="submit" value="Search" class="tfbutton">
		</form>
	<div class="tfclear"></div>
	</div>
	<div>
		
		<ul>
			<li>Chopping wood - 200 Kcal</li>
			<li>Carrying tree logs - 500 Kcal</li>
		</ul>
	</div>
	
	<p><b>Enter your hours of sleep here:</b></p>
		<div id="tfheader">
		From <input type="text" class="tftextinput" name="q" size="16" maxlength="60"> till <input type="text" class="tftextinput" name="q" size="16" maxlength="60" autocomplete="on"><input type="submit" value="Submit" class="tfbutton">
	<div class="tfclear"></div>
	</div>
	
  </main>

  <footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>