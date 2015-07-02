<%@include file="_header.jsp" %>
<%
final java.util.Map<String, String> returnMap = (java.util.Map<String, String>) request.getAttribute("return");
final String goalWeight =  returnMap == null ? "" : returnMap.get("GoalWeight");
final String goalDate =  returnMap == null ? "" : returnMap.get("GoalDate");
%>

  <div class="siteContent container">
	<div>
		<form method="post" action="GoalDetails">
		<p><b>Goal information:</b></p>
		<p>
			<p>What weight do want to achieve?</p>
			<input type="text" name="goalWeight" value="<%=goalWeight%>" placeholder=" Weight in KG" class="mytext" required/>
		</p>
		<p>
			<p>For when do you want to set this goal?</p>
			<input type="date" name="goalDate" value="<%=goalDate%>" placeholder=" DD/MM/YYYY" class="mytext" required />
		</P>
		    <p><input type="submit" value="Set Goal"></p>
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