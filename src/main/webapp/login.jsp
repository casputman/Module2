<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
</head>
<body>
<% if (request.getAttribute("loginError") != null && request.getAttribute("loginError").equals(Boolean.TRUE)) { %>
Nope.
<% } %>

<form action="login" method="post">
<input type="hidden" name="action" value="login" />
<div><input type="text" placeholder="Username" name="username" /></div>
<div><input type="password" placeholder="Password" name="password" /></div>
<div><input type="submit" value="log in" /></div>
</form>
</body>
</html>