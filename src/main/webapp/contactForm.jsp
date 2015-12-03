<%@include file="_header.jsp" %>
	<main class="siteContent container">
	<center>

<div>
			<form method="post" id="ContactForm" action="/ubercoaching/contactForm.jsp" onSubmit="alert('Thank you for your feedback.');"  class="cd-form floating-labels">
				<fieldset>

					<div class="icon">
						<label class="cd-label" for="cd-name"><b>Name</b></label> <input
							class="user" type="text" name="cd-name" id="cd-name" required>
					</div>

					<div class="icon">
						<label class="cd-label" for="cd-company"><b>Subject</b></label> <input
							class="company" type="text" name="cd-company" id="cd-company" required>
					</div>

					<div class="icon">
						<label class="cd-label" for="cd-email"><b>Email</b></label> <input
							class="email error" type="email" name="cd-email" id="cd-email"
							required>
					</div>
					<div class="icon">
						<label class="cd-label" for="cd-textarea"><b>Message</b></label>
						<textarea class="message" name="cd-textarea" id="cd-textarea"
							required></textarea>
					</div>

					<div>
						<input type="submit" value="Send Message">
					</div>
				</fieldset>
			</form>

			</div>
			</center>
	</main>

	<footer class="siteFooter container">
		<p>Copyright &copy; 2015 by &Uuml;ber-coaching</p>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="js/concise.min.js"></script>
</body>
</html>

