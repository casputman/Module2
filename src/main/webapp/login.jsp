
<jsp:include page="_header.jsp">
	<jsp:param value="" name="pageTitle"/>
</jsp:include>
  

  <div class="masthead">
    <div class="container">
	<div class = "fadein">
    <div class="login">
	
      <h1><b>&Uuml;-LOGIN</b></h1>
      <form method="post" action="login">
	  	<input type="hidden" name="action" value="login" />
	  
	  	<% if (request.getAttribute("loginError") != null && (Boolean) request.getAttribute("loginError")) { %>
	  	<p style="color:red">Wrong username and/or password.</p>
	  	<% } %>
	 
        <p><input type="text" name="username" placeholder=" Username" class="mytext"></p>
        <p><input type="password" name="password" placeholder=" Password" class="mytext"></p>
        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me" value="1" />
				Remember me on this computer
          </label>
        </p>
        <p><input type="submit" name="commit" value="Login"></p>
      </form>
      <p>Forgot your password? <br>
	  <a href="start">Click here to reset it</a></p>
	  <p>No account?
	  <br>
	  <a href="register">Click here to register</a></p>
	</div>
	</div>



    </div>
  </div>

<jsp:include page="_footer.jsp" />