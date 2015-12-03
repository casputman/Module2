<%@include file="_header.jsp" %>
	<div class="calculator">
		<h3>
			<b>CALCULATOR</b>
		</h3>
		<b>BMI CALCULATOR</b>
		<FORM ACTION="bmicalculator.jsp">
			<br> Type your weight: <INPUT TYPE="text" NAME="weight"><br>
			<br> Type your length: <INPUT TYPE="text" NAME="length"><br>
			<br> <b><INPUT TYPE="submit" VALUE="Calculate your BMI!"></b>
		</FORM>
		<br> <b>FAT PERCENTAGE CALCULATOR</b> <br> <br>

		<FORM ACTION="vptcalculator.jsp">
			Type your weight: <INPUT TYPE="text" NAME="weight"><br>
			<br> Type your waistline: <INPUT TYPE="text" NAME="waistline"><br>
			<br> Are you a man or a woman?<br> <br> <input
				type="radio" name="sex" value="male"> Male<br> <input
				type="radio" name="sex" value="female"> Female <br> <br>
			<b><INPUT TYPE="submit" VALUE="Calculate your fat percentage!"></b>
		</FORM>
	</div>
	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>
	<script>
		function GetUrlValue(VarSearch) {
			var SearchString = window.location.search.substring(1);
			var VariableArray = SearchString.split('&');
			for (var i = 0; i < VariableArray.length; i++) {
				var KeyValuePair = VariableArray[i].split('=');
				if (KeyValuePair[0] == VarSearch) {
					return KeyValuePair[1];
				}
			}
		}
		var male = Math
				.round((((-98.42 + (4.15 * ((GetUrlValue('waistline') / 2.54))) - (0.082 * ((GetUrlValue('weight') * 2.2)))) / (GetUrlValue('weight') * 2.2))) * 100);
		var female = Math
				.round((((-76.76 + (4.15 * ((GetUrlValue('waistline') / 2.54))) - (0.082 * ((GetUrlValue('weight') * 2.2)))) / (GetUrlValue('weight') * 2.2))) * 100);
		if (GetUrlValue('sex') == "male") { 
			if (male > 24){
				alert("Your fat percentage = " + male + "%" + ". This is considered above average. Register now and let us help!")
			}
			else { if (male < 14) {
				alert("Your fat percentage = " + male + "%" + ". This is considered below average. Register now and let us help!")
			} else {
				alert("Your fat percentage = " + male + "%" + ". This is considered average. Keep up te good work!");
			}}} else if (GetUrlValue('sex') == "female") {
			if (male > 31){
				alert("Your fat percentage = " + female + "%" + ". This is considered above average. Register now and let us help!")
			} else { if (male < 21) {
				alert("Your fat percentage = " + female + "%" + ". This is considered below average. Register now and let us help!")
			} else {
				alert("Your fat percentage = " + female + "%" + ". This is considered average. Keep up te good work!");
		}}} else {
			alert("Input invalid, check input!")}
	</script>

</body>
</html>
