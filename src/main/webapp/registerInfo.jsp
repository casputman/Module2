<%@include file="_header.jsp" %>
  <main class="siteContent container">
	<div>
	<form method ="post" action = "register"><p>
			<b>To help you with your health we first need to know a little bit about you.</b>
		</p>

		<p> 
			<b>Please insert your length:</b> </br>
			<input type="number" name="length" value="" placeholder="Length" class="mytext">
			cm
		</p>
		<p>
			<b>Please insert your age: </b></br>
			<input type="number" name="age" value="" placeholder="Age" class="mytext">
			years old
		</p>
		<p>
			<b>Please insert your Surname: </b></br>
			<input type="text" name="surname" value="" placeholder="Surname" class="mytext">
		
		</p>
		<p>
			<b>Please insert your First Name: </b></br>
			<input type="text" name="firstname" value="" placeholder="First Name" class="mytext">
		
		</p>
		<p>
			<b>Please insert your email: </b></br>
			<input type="email" name="email" value="" placeholder="" class="mytext">
			
		</p>
		<p>
			<b>Please insert your gender: </b></br>
			<input type="radio" name="gender" value="m" placeholder="" class="mytext">Male
			<input type="radio" name="gender" value="f" placeholder="" class="mytext">Female
		</p>
	       <p><input type="submit" name="commit" value="register"></p>
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