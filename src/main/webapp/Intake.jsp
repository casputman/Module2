<!doctype html>
<html>
<head>
  <link rel="shortcut icon" type="image/ico" href="favicon.ico">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="HandheldFriendly" content="True">
	
  <%@ page import="java.util.ArrayList" %>
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
	  //document.getElementById("textDing").innerHTML = text;
	  
	  $ajax({ 
		  url: 'localhost:8080/ubercoaching/Intake',
		  method: 'GET',
		  dataType: 'html' 
	  });
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
					<input type="hidden" name="action" value="search" />
		        <input type="text" id="textinput" class="tftextinput" name="q" size="21" maxlength="120" oninput="textShizzle()"><input type="submit" name="commit" value="search" class="tfbutton">
			</form>
		<div class="tfclear">
		</div>
	</div>
	
	<div id="textDing"> 
			<% Object f = request.getAttribute("foodList");
		if (f != null) {
		String probFood = f.toString();
		probFood = probFood.substring(1, probFood.length() - 1);
			String[] x = probFood.split(":,"); %>
	<form id="tfnewFood" method="POST" action="intake">
	<input type="hidden" name="action" value="intake"/>
			<ul class="foodSearchOptions"> 
			<% 
			for (int i = 0; i < x.length; i++) {
			    %> <li><input type="text" name="amount" value="1"><input type="submit" type="text" name="food" value="<%= x[i] %>" class="tfbutton"> </li>  <%
			}
		}	
		%>
			</ul>
		</form>
	</div>

	<div>
		<ul>
			<%
			    Object z = request.getAttribute("myFood");
			    String myFoodList = z.toString();
			    myFoodList = myFoodList.substring(1, myFoodList.length() - 1).trim();
			    System.out.println("fuckerdefuck: " + myFoodList);
			    if (myFoodList.length() != 0) {
			        String[] c = myFoodList.split(":]");
			        int totaal = 0;
			        for (int i = 0; i < c.length; i++) {
			            c[i] = c[i].substring(1);
			            String foodjes = c[i].split(":,")[1];
			            String caltjes = c[i].split(":,")[2];
			            totaal += Integer.parseInt(caltjes.trim());
			%>
			<li><%=foodjes%> : <%=caltjes%></li>
			<%
			    }
			%>
			<li>Totaal aantal Kcal vandaag: <%=totaal%>
			</li>
			<%
			    }
			%>
		</ul>

	</div>
	
	<form id="tfFoodjes" method="POST" action="moreFood">
		<input type="hidden" name="action" value="intake"/>
		name of the food: <input type="text" class="tftextinput" name="name" value="bier">
		Calorie per amount: <input type="text" class="tftextinput" name="calorie" value="100">
		amount: <input type="text" class="tftextinput" name="amount" value="100">
		unit of amount: <input type="text" class="tftextinput" name="unit" value="gr.">
		<input type="submit" name="commit" value="addFood" class="tfbutton">
	</form>

	<p><b>Enter your activities over here:</b></p>
	<div id="tfheader">
		<form id="tfnewsearch" method="GET" action="http://www.google.com" autocomplete="on">

		        <input type="text" class="tftextinput" name="q" size="21" maxlength="120"><input type="submit" name="commit" value="Search" class="tfbutton">
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