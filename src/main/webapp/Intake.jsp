<%@include file="_header.jsp" %>

<div class="siteContent container">
	<div class="container vertical-divider">
		<div class="column two-thirds">
			<p>
				<b>Enter your food over here:</b>
			</p>
			<div id="tfheader">
				<form id="tfnewsearch" method="GET" action="intake" autocomplete="on">
					<input type="hidden" name="action" value="foodsearch" />
					<input type="text" id="textinput" class="tftextinput" name="q" size="21" maxlength="120" />
					<input type="submit" value="Search food!" class="tfbutton" />
				</form>
				<div class="tfclear"></div>
			</div>

			<div id="textDing">
				<%
				// Food search results.
				final java.util.List<?> fsr = (java.util.List<?>) request.getAttribute("foodSearchResults");
				if (fsr != null) {
				    if (fsr.size() == 0) {
				%>
				<p>Requested food not found in the database, please add it yourself or 
				   contact us at food@ubercoaching.com</p>
				<%  } %>
				<ul class="foodSearchOptions">
					<%
					for (Object obj : fsr) {
					    final core.Food food = (core.Food) obj;
					%>
					<li>
						<form id="tfnewFood" method="POST" action="intake">
							<input type="hidden" name="action" value="intake" />
							<input type="hidden" name="idfood" value="<%=food.getIdfood()%>"  />
							<input type="text" name="amount" value="1" class="nfinput" size="3">
							X
							<input type="submit"
								value="<%=food.getAmount()%> <%=food.getUnit()%>: <%=food.getName()%>" class="tfbutton" />
						</form>
					</li>
					<%
					}
					%>
				<%
				}
				%>
				</ul>
			</div>

			<div>
				<table>
					<%
					// The user's calorie intake for today.
					
					final java.util.List<?> fi = (java.util.List<?>) request.getAttribute("foodIntake");
					double totalCalorieIntake = 0;
					if (fi != null && fi.size() > 0) {
						for (Object obj : fi) {
						    final core.Intake intake = (core.Intake) obj;
						    final double calories = intake.getAmount() * intake.getFood().getCalorie();
						    totalCalorieIntake += calories;
					%>
					<tr><td><%=intake.getFood().getName()%></td><td><%=calories%></td></tr>
						<%
						}
						%>
					<tr><td>Totaal aantal caloriën vandaag:</td><td><%=totalCalorieIntake%></td></tr>
					<%
					}
					%>
				</table>

			</div>



			<p>
				<b>Enter your activities over here:</b>
			</p>
			<div id="tfheader">

				<form id="tfnewsearch" method="GET" action="intake" autocomplete="on">
					<input type="hidden" name="action" value="activitysearch" />
					<input type="text" id="textinput" class="tftextinput" name="q" size="21" maxlength="120" />
					<input type="submit" value="Search activity!" class="tfbutton" />
				</form>
				<div class="tfclear"></div>

			</div>

			<div id="textDing">
				<%
				
				// Activity search results.
				final java.util.List<?> asr = (java.util.List<?>) request.getAttribute("activitySearchResults");
				if (asr != null) {
				    if (asr.size() == 0) {
				%>
				<p>Activity not found in the database, please add it yourself or contact us at food@ubercoaching.com</p>
				<%
				    }
				%>
				<ul class="activitySearchOptions">
					<%
					for (Object obj : asr) {
					    final core.Activity activity = (core.Activity) obj;
					%>
					<li>
						<form id="tfnewActivity" method="POST" action="intake">
							<input type="hidden" name="action" value="usage" />
							<input type="text" name="amount" value="1" class="nfinput" size="3" />
							X
							<input type="submit" type="text" name="activity" value="<%=activity.getName()%>" class="tfbutton" />
						</form>
					</li>
					<%
					}
				}
				%>
				</ul>
			</div>
			<div>
				<table>
					<%
					// The user's calorie usage for today.
					final java.util.List<?> au = (java.util.List<?>) request.getAttribute("activityUsage");
					double totalCalorieUsage = 0;
					if (au != null && au.size() > 0) {
					    for (Object obj : au) {
					        final core.Usage usage = (core.Usage) obj;
					        final double calories = usage.getAmount() * usage.getActivity().getSelectedCalories();
					        totalCalorieUsage += calories;
					        %>
			        <tr><td><%=usage.getActivity().getName()%></td><td><%=calories%></td></tr>
			        		<%
					    }
					    %>
				    <tr><td>Totaal aantal verbruikte caloriën vandaag:</td><td><%=totalCalorieUsage%></td></tr>
				    <%
					}
					%>
				</table>

			</div>
			<div>
				<p>
					<b>Enter your hours of sleep here:</b>
				</p>
				<div id="tfheader">
					<form id="sleepform" method="POST" action="sleep">
						From hour: <input type="text" class="tftextinput"
							name="sleepstarthour" size="2" maxlength="2"> min: <input
							type="text" class="tftextinput" name="sleepstartmin" size="2"
							maxlength="2"> <br><br> Until hour: <input type="text"
							class="tftextinput" name="sleependhour" size="2" maxlength="2">
						min: <input type="text" class="tftextinput" name="sleependmin"
							size="2" maxlength="2"><br><br><input type="submit"
							value="Submit" class="tfbutton">
					</form>
					<%
						Object e = request.getAttribute("sleepdur");
						if (e != null) {
							String sleepresponse = e.toString();
							System.out.println("damn son" + sleepresponse);
					%>
					<%=sleepresponse%>
					<%
						}
					%>
				</div>
				<div class="tfclear"></div>
			</div>
		</div>

		<div class="column one-third">
			<div>
				<p>
					<b>Add your own meal to your personal list.</b>
				</p>
				<form id="tfFoodjes" method="POST" action="intake">
					<input type="hidden" name="action" value="food" />
					<b>Name of the food:</b>
					<input type="text" class="tftextinput" name="name" value="Beer" required />
					<br>
					<br><b>Kcal per amount:</b>
					<br><input type="text" class="tftextinput" name="calorie" value="100" required />
					<br>
					<br><b>Amount:</b>
					<br><input type="text" class="tftextinput" name="amount" value="100" required />
					<br>
					<br><b>Unit of specified amount:</b>
						<input type="text" class="tftextinput" name="unit" value="gr." required />
					<br>
					<br><input type="submit" value="Add food!" class="tfbutton" />
				</form>
			</div>
			<div>
				<br>
				<p><I>Don't know how many calories your meal contains? Search
					below!</I>
				<p>
				<form method="GET" action="http://www.google.com/search" target="blank">
					<input type="text" name="q" value="" />
					<input type="submit" value="Search" />
				</form>
			</div>
		</div>
	</div>
</div>

<%@include file="_footer.jsp" %>