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
		location.href = "logout";
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
	<p>
		<b>Enter your food over here:</b>
	</p>
	<div id="tfheader">

		<form id="tfnewsearch" method="GET" action="search" autocomplete="on">
			<input type="hidden" name="action" value="search" /> <input
				type="text" id="textinput" class="tftextinput" name="q" size="21"
				maxlength="120" oninput="textShizzle()"><input type="submit"
				name="commit" value="search" class="tfbutton">
		</form>
		<div class="tfclear"></div>
	</div>

	<div id="textDing">
		<%
		    Object f = request.getAttribute("foodList");
		    if (f != null) {
		        String probFood = f.toString();
		        probFood = probFood.substring(1, probFood.length() - 1);
		        System.out.println("checkem:" + probFood + ":");
		        if (probFood.trim() != null) {
		            System.out.println("vage shit 1 " + probFood);
		            String[] x = probFood.split(":,");
		%>
			<ul class="foodSearchOptions">
				<%
				    System.out.println("vage shit 2 " + x.length);
				            for (int i = 0; i < x.length; i++) {
				                System.out.println("CHeck deza shizlle: " + x[i]);
				                x[i].trim();
				                if ( x[i].length() != 0) {

				                    System.out.println("vage shit 3 " + x[i].length());
				                    String[] y = x[i].split("::");
				%>
				<li>		
				<form id="tfnewFood" method="POST" action="intake">
			<input type="hidden" name="action" value="intake" />
			<input type="text" name="amount" value="1" class="nfinput"
					size="3">X<input type="submit" type="text" name="food"
					value="<%=y[0] + " " + y[1]%> : <%=y[2]%>" class="tfbutton">
					</form>
				</li>
				<%
				    }
				                else {
				                    %> 
				                    <p>food not found in the database, 
				                    please add it yourself or contact us at food@ubercoaching.com </p>
				                    <%
				                }
				            }

				        }
				    }
				%>
			</ul>
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



	<p>
		<b>Enter your activities over here:</b>
	</p>
	<div id="tfheader">

		<form id="tfnewsearch" method="GET" action="actsearch" autocomplete="on">
			<input type="hidden" name="action" value="actsearch" /> <input
				type="text" id="textinput" class="tftextinput" name="q" size="21"
				maxlength="120" oninput="textShizzle()"><input type="submit"
				name="commit" value="actsearch" class="tfbutton">
		</form>
		<div class="tfclear"></div>

	</div>

	<div id="textDing">
		<% Object a = request.getAttribute("activityList");
		if (a != null) {
		String probActivity = a.toString();
		probActivity = probActivity.substring(1, probActivity.length() - 1);
			String[] k = probActivity.split(":,"); %>
			
		<form id="tfnewActivity" method="POST" action="intakeA">
			<input type="hidden" name="action" value="intakeA" />
			<ul class="activitySearchOptions">
				<% 
				System.out.println("vage shit 2 " + k.length);
	            for (int i = 0; i < k.length; i++) {
	                System.out.println("CHeck deza shizlle: " + k[i]);
	                k[i].trim();
	                if ( k[i].length() != 0) {

	                    System.out.println("vage shit 3 " + k[i].length());
	                    String[] m = k[i].split("::");
			    %>
				<li><input type="text" name="amount" value="1" class="nfinput"
					size="3">X<input type="submit" type="text" name="activity"
					value="<%=m[0] + " " + m[1]%> : <%=m[2]%>" class="tfbutton">
				</li>
				<%
				    }
				                else {
				                    %> 
				                    <p>activity not found in the database, 
				                    please add it yourself or contact us at food@ubercoaching.com </p>
				                    <%
				                }
				            }

				        }
				%>
			</ul>
		</form>
	</div>
	<div>
		<ul>
			<%
			    Object d = request.getAttribute("myAct");
			    String myActivityList = d.toString();
			    myActivityList = myActivityList.substring(1, myActivityList.length() - 1).trim();
			    System.out.println("fuckerdefuck: " + myActivityList);
			    if (myActivityList.length() != 0) {
			        String[] e = myActivityList.split(":]");
			        int totaal = 0;
			        for (int i = 0; i < e.length; i++) {
			            e[i] = e[i].substring(1);
			            String actjes = e[i].split(":,")[1];
			            String calties = e[i].split(":,")[2];
			            totaal += Integer.parseInt(calties.trim());
			%>
			<li><%=actjes%> : <%=calties%></li>
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
	<div>
		<p>
			<b>Enter your hours of sleep here:</b>
		</p>
		<div id="tfheader">
		<form id="sleepform" method="POST" action="sleep">
			From hour: <input type="text" class="tftextinput" name="sleepstarthour" size="2"
				maxlength="2"> min: <input type="text" class="tftextinput" name="sleepstartmin" size="2"
				maxlength="2"> till hour: <input type="text" class="tftextinput" name="sleependhour" size="2"
				maxlength="2"> min: <input type="text" class="tftextinput" name="sleependmin" size="2"
				maxlength="2"><input
				type="submit" value="Submit" class="tfbutton">
				</form>
				<% Object e = request.getAttribute("sleepdur");
					if (e != null) {
					String sleepresponse = e.toString();
					System.out.println("damn son" + sleepresponse);
				%>
				<%= sleepresponse %>
				<% } %>
		</div>
		<div class="tfclear"></div>

	</div>

	<div>
		<p>
			<b>If you can't find your meal enter it here</b>
		</p>
		<form id="tfFoodjes" method="POST" action="moreFood">
			<input type="hidden" name="action" value="intake" /> 
			name of the food:<input type="text" class="tftextinput" name="name" value="bier" required> <br>
			KiloCalorie per amount:<input type="text" class="tftextinput" name="calorie" value="100" required> <br>
			amount:<input type="text" class="tftextinput" name="amount" value="100" required> <br>
			unit of amount:<input type="text" class="tftextinput" name="unit" value="gr." required> <br>
			<input type="submit" name="commit" value="addFood" class="tfbutton">
			</form>
	</div>
	<div>
		<br>
		<p>look up the amount of Kilocalories here<p>
		<form method="GET" action="http://www.google.com/search"
			target="blank">
			<input type="text" name="q" value=""><input type="submit"
				name="commit" value="search">
		</form>
	</div>
	</main>

	<footer class="siteFooter container">
    <p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
  </footer>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="js/concise.min.js"></script>
</body>
</html>