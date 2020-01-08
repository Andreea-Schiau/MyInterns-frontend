<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.codehaus.jettison.json.JSONArray"%>
<%@ page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyInterns App</title>
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
	%>
	<p>
		Welcome
		<%=username%></p>

	<form method="post" action="mentor">
		<input type="submit" value="Logout">
	</form>
	
</body>
</html>