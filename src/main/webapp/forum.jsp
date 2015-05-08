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
   	function demoShow(id) {
		if (document.getElementById(id).style.display == "block") {
			document.getElementById(id).style.display="none";
		} else {
			document.getElementById(id).style.display="block";
		}
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
    </nav>
  </header>


  <main class="siteContent container">
  	<center><h3><b>&Uuml;-Forum</b></h3></center>
    <div class="forumHolder">
		<img src="images/ForumNL.png" alt="About" class="forumImg">
		<ul class="forumTips">
			<li><center>MOTD</center></li>
			<li><input type="button" value="Drink water." onclick="demoShow(&#34;but1&#34;)" class="but"></li>
			<li><input type="button" value="Stay alive." onclick="demoShow(&#34;but2&#34;)" class="but"></li>
			<li><input type="button" value="Eat varied." onclick="demoShow()" class="but"></li>
			<li><input type="button" value="Get some Exercise." onclick="demoShow()" class="but"></li>
		</ul>
		<div id="but1">
		<p>
			Studies have shown that adults need around 2 litres of water/fluids per day, 
			calculate your recommended water intake <a href="http://www.medindia.net/patients/calculators/daily-water-req-result.asp">here</a>.
		</p>
		</div>
			<div id="but2">
		<p>
			Studies have shown that adults are more healthy when they are not dead, 
			check whether you are alive <a href="http://amialive.com/">here</a>.
		</p>
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