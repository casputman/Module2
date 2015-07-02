<%@include file="_header.jsp" %>
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
			<input type="text" name="username" value="<%=username%>" placeholder=" Username" class="mytext" required/>
		</p>
		<p>
			<input type="password" name="password1" placeholder=" Password" class="mytext" required />
		</P>
		<p>
			<input type="password" name="password2" placeholder=" Password" class="mytext" required />
		</p>
		<p><b>To help you with your health we first need to know a little bit about you:</b>
		<p>
			<input type="number" name="length" value="<%=length%>" placeholder=" Length" class="mytext" required/>
		</p>
		<p>
			<input type="number" name="age" value="<%=age%>" placeholder=" Age" class="mytext" required/>
		</p>
		<p>
			<input type="text" name="surname" value="<%=surname%>" placeholder=" Surname" class="mytext" required/>
		</p>
		<p>
			<input type="text" name="firstname" value="<%=firstname%>" placeholder=" First Name" class="mytext" required/>
		</p>
		<p>
			<input type="email" name="email" value="<%=email%>" placeholder=" E-mail address" class="mytext" required/>
		</p>
		<p>
			<input type="radio" name="gender" value="m" placeholder="" class="mytext"<%=genderM%> /> Male<br />
			<input type="radio" name="gender" value="f" placeholder="" class="mytext"<%=genderV%> /> Female
		</p>
		
		What is your current <b>weight</b>? <br><br>
		<input type="text" name="weight" placeholder="Weight in Kg" class="mytext" required/> <br><br>
		For more information and a better calculation, please also fill in your <b>waistline span</b>. <br><br>
		<input type="text" name="width" placeholder="Waistline in cm" class="mytext" required> <br><br>
	
		<p><b>Goal information:</b></p>
		<p>
			<p>What weight do want to achieve?</p>
			<input type="text" name="goalWeight" value="" placeholder=" Weight in KG" class="mytext" required/>
		</p>
		<p>
			<p>For when do you want to set this goal?</p>
			<input type="date" name="goalDate" value="" placeholder=" DD/MM/YYYY" class="mytext" required />
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