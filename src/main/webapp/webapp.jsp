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
		location.href = "logout";
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
				<a href="webapp" class="buttonlink"><li>Personal Home</li></a>
				<a href="InAbout" class="buttonlink"><li>About</li></a>
				<a href="InContact" class="buttonlink"><li>Contact</li></a>
				<a href="#" class="buttonlink" onclick="logOut()"><li>LogOut</li></a>
		
      </ul>
    </nav>
  </header>
 
  <main class="siteContent container">
  <div class="row gutters">
      <div class="column-4">
		<a href="Intake">
			<img class="columnLogo" src="images/CalorieIntake.jpg" alt="CalorieIntake">
		</a>
        <h3><b>Input</b></h3>

        <p>What have you eaten today?
		<br>How much have you slept today?
		<br>Did you drink enough today?
		</p>

        <a href="Intake" class="btn bg-small">PROCEED &raquo;</a>
      </div>

      <div class="column-4">
		<a href="Statistics">
			<img class="columnLogo" src="images/statistics.jpg" alt="statistics">
		</a>
        <h3><b>Statistics</b></h3>

        <p>It is very hard and tedious to keep count of every calorie, but we make it easy.</p>
		</br>
        <a href="statistics" class="btn bg-small">PROCEED &raquo;</a>
      </div>

      <div class="column-4">
		<a href="Forum">
			<img class="columnLogo" src="images/Forum.jpg" alt="Forum">
		</a>
        <h3><b>Forum</b></h3>

        <p>We keep track of your average calorie intake and inform you of the best course of action.</p>
		</br>
        <a href="forum" class="btn bg-small">PROCEED &raquo;</a>
      </div>
      
      <div class="column-4">
		<a href="Balance">
			<img class="columnLogo" src="images/target.jpg" alt="Goal">
		</a>
        <h3><b>Goal</b></h3>

        <p>Directly see your goal and your Kcal balance.</p>
		</br>
        <a href="Balance" class="btn bg-small">PROCEED &raquo;</a>
      </div>
    </div>
    <div>
    	<a href="SetGoal" class="btn bg-small">
    	Set Goal
    	</a> 
    </div>
    
  </main>
  

  <footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>