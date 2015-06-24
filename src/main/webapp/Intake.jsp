<%@include file="_header.jsp" %>

	<div class="siteContent container">
	<div class="container vertical-divider">
		<div class="column two-thirds">
			<p>
				<b>Enter your food over here:</b>
			</p>
			<div id="tfheader">
				<form id="tfnewsearch" method="GET" action="search" autocomplete="on">
					<input type="hidden" name="action" value="search" />
					<input type="text" id="textinput" class="tftextinput" name="q" size="21" maxlength="120" />
					<input type="submit" name="commit" value="Search food!" class="tfbutton" />
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
							<input type="text" name="amount" value="1" class="nfinput" size="3">
							X
							<input type="submit" name="food" 
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
					double totalCalories = 0;
					if (fi != null && fi.size() > 0) {
						for (Object obj : fi) {
						    final core.Intake intake = (core.Intake) obj;
						    final double calories = intake.getAmount() * intake.getFood().getCalorie();
						    totalCalories += calories;
					%>
					<tr><td><%=intake.getFood().getName()%></td><td><%=calories%></td></tr>
						<%
						}
						%>
					<tr><td>Totaal aantal caloriën vandaag:</td><td><%=totalCalories%></td></tr>
					<%
					}
					%>
				</table>

			</div>



			<p>
				<b>Enter your activities over here:</b>
			</p>
			<div id="tfheader">

				<form id="tfnewsearch" method="GET" action="actsearch"
					autocomplete="on">
					<input type="hidden" name="action" value="actsearch" /> <input
						type="text" id="textinput" class="tftextinput" name="q" size="21"
						maxlength="120" oninput="textShizzle()"><input
						type="submit" name="commit" value="Search activity!"
						class="tfbutton">
				</form>
				<div class="tfclear"></div>

			</div>

			<div id="textDing">
				<%
					Object a = request.getAttribute("activityList");
					if (a != null) {
						String probActivity = a.toString();
						probActivity = probActivity.substring(1,
								probActivity.length() - 1);
						String[] k = probActivity.split(":,");
				%>

				<form id="tfnewActivity" method="POST" action="intakeA">
					<input type="hidden" name="action" value="intakeA" />
					<ul class="activitySearchOptions">
						<%
							System.out.println("vage shit 2 " + k.length);
								for (int i = 0; i < k.length; i++) {
									System.out.println("CHeck deza shizlle: " + k[i]);
									k[i].trim();
									if (k[i].length() != 0) {

										System.out.println("vage shit 3 " + k[i].length());
										String[] m = k[i].split("::");
						%>
						<li><input type="text" name="amount" value="1"
							class="nfinput" size="3">X<input type="submit"
							type="text" name="activity"
							value="<%=m[0]%>" class="tfbutton">
						</li>
						<%
							} else {
						%>
						<p>Activity not found in the database, please add it yourself
							or contact us at food@ubercoaching.com</p>
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
						myActivityList = myActivityList.substring(1,
								myActivityList.length() - 1).trim();
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
				<form id="tfFoodjes" method="POST" action="moreFood">
					<input type="hidden" name="action" value="intake" /> <b>Name
						of the food:</b><input type="text" class="tftextinput" name="name"
						value="Beer" required> <br>
					<br> <b>Kcal per amount:</b>
					<br> <input type="text" class="tftextinput" name="calorie"
						value="100" required> <br><br> <b>Amount:</b><br> <input
						type="text" class="tftextinput" name="amount" value="100" required>
					<br>
					<br> <b>Unit of specified amount:</b><input type="text"
						class="tftextinput" name="unit" value="gr." required> <br>
					<br> <input type="submit" name="commit"
						value="Add food!" class="tfbutton">
				</form>
			</div>
			<div>
				<br>
				<p><I>Don't know how many calories your meal contains? Search
					below!</I>
				<p>
				<form method="GET" action="http://www.google.com/search"
					target="blank">
					<input type="text" name="q" value=""><input type="submit"
						name="commit" value="Search">
				</form>
			</div>
		</div>
	</div>
	</div>

<%@include file="_footer.jsp" %>