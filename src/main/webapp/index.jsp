<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Main page</title>
</head>
<body>

<a href="login">login</a>
<br /><a href="welcome.jsp">welcome</a>
<br /><a href="logout">logout</a>
<br />
<br />Session user data:
<br /><%= request.getSession().getAttribute("user") %>
<br />
<br />Cookies: 
<ul>
<% if (request.getCookies() == null) { %>
<li>none</li>
<% } else {
	for (Cookie cookie : request.getCookies()) { %>
<li><%= cookie.getName() %> : <%= cookie.getValue() %></li>
<% }
} %>
</ul>

</body>
</html>