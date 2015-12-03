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
		var bmi = Math.round(GetUrlValue('weight')
				/ Math.pow((GetUrlValue('length') / 100), 2));
		if (bmi > 25){
			alert ("Your BMI = " + bmi + ". You are considered overweight. Register, and let us help!") }
			else { if (bmi < 19){
				alert ("Your BMI = " + bmi + ". You are considered underweight. Register, and let us help!")
			}
			else { alert ("Your BMI = " + bmi + ". You are on a healthy weight, keep up te good work!")
		}}
	</script>
</body>
</html>
