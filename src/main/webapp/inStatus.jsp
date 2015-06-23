<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 	<% core.User user = null;
 	if (request.getSession().getAttribute("user") != null) {
 		user = core.User.fromIdUser(((core.User) request.getSession()
 				.getAttribute("user")).getIdUser());
 	}
 	double bmi = 0;
 	double vet = 0;
 	if (user != null) {
 		System.out.println("user is not null");
 		if (user.getUserBMI() != null) {
 			bmi = user.getUserBMI().getBMI();
 			System.out.println("BMI is written" + bmi);
 		} else {
 			bmi = -1;
 			System.out.println("BMI is -1");
 		}
 		if (user.getUserVet() != null) {
 			vet = user.getUserVet().getVPT();
 		} else {
 			vet = -1;
 		}
 	} %>
 	
 				Your current <b>BMI</b>:
				<%=bmi%></p>
				
				Your current <b>fat-percentage</b>:
				<%=vet%>
				
				Your current <b>goal</b>:
				<%=goal%>, set for date <%=goaldate%>
				
				Your <b>intake</b> for today:
				<%=intake%>
</body>
</html>