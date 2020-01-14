<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String username = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");
	%>
	<p>	Edit <%=username%> </p>

	<form action="update" method="post">
		Username: <input type="text" name="updateUsername" value=<%=username%>
			placeholder=<%=username%> readonly> 
		Password: <input type="text" name="updatePassword" value=<%=password%>
			placeholder=<%=username%>> 
		<input type="submit" value="Update">
	</form>
</body>
</html>