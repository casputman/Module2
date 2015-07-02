<%@include file="_header.jsp" %>
	<main class="siteContent container">
	
	<div>
		<h3><b>Status</b></h3>
	 <%
     Object q = request.getAttribute("myBMI");
     String myBmi = q.toString();
 %>
	<p>
		Your <b>BMI</b> is:
		<%=myBmi%></p>

	<%Object g = request.getAttribute("myVET");
	String myVet = g.toString();%>
	<p>Your <b>fat percentage</b> is:
	<%=myVet %></p>
	<%
	    Object f = request.getAttribute("mybalance");
	    String myBalance = f.toString();
	    if (myBalance.equals("-1.0")) {
	      %> <p><i>Not all of the required information is known to us, please make sure your age, gender, length, waistline and weight is known to us.</i> </p> <%
	    } else {
	%>
	<p>
		Your <b>Kcal</b> balance is:
		<%=myBalance%>
	</p>
	<%} %>
	<% Object m = request.getAttribute("myWeight"); 
	String weight = m.toString();%>
	<% Object n = request.getAttribute("myTimeAgo");
	String timeAgo = n.toString();%>
	<p> Your weight was <%= weight %>, <%= timeAgo %> days ago</p>
	<form method="POST" action="updateWeight">
	What is your current <b>weight</b>? <br><br>
	<input type="text" name="weight" placeholder="Weight in Kg" class="mytext" required/> <br><br>
	For more information and a better calculation, please also fill in your <b>waistline span</b>. <br><br>
	<input type="text" name="width" placeholder="Waistline in cm" class="mytext"> <br><br>
	<input type="submit" value="Submit">
	</form>
	</div>
	<div>
		<form method="POST" action="GoalDetails">
		<br>
		<hr class = style-two>	
		<p><b>Goal information:</b></p>
		<p>
			<p>What <b>weight</b> do want to achieve?</p>
			<input type="text" name="goalWeight" value="" placeholder=" Weight in KG" class="mytext" required/>
		</p>
		<p>
			<p>For <b>when</b> do you want to set this goal?</p>
			<input type="date" name="goalDate" value="" placeholder=" DD/MM/YYYY" class="mytext" required />
		</p>
		    <p><input type="submit" value="Set goal"></p>
		</form>
		
	</div>
		<div class="backgroundstatistics">
		<div class="siteContent container statistics">
		<%Object j = request.getAttribute("myGoal");
		if (j != null) {
		 double goalWeight = ((searches.Goal) j).getGoalweight();
		 String goalDate = ((searches.Goal) j).getGoaldate();%>
			<p>
			Your current goal is:
			<b><%=goalWeight%></b>
			</p>

			<p>
				You have until:	<b><%=goalDate%></b>,
				to achieve your goal.
			</p>
<%} %>
		</div>
		</div>
	</main>

	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>

</body>
</html>