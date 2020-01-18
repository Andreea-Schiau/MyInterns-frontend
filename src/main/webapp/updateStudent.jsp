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
		String nameUpdate = (String) session.getAttribute("name");
		String surnameUpdate = (String) session.getAttribute("surname");
		String descriptionUpdate = (String) session.getAttribute("description");
		String emailUpdate = (String) session.getAttribute("emailUpdate");
		String username = (String) session.getAttribute("username");
		String passwordUpdate = (String) session.getAttribute("password");
	%>
		<p>
			Edit
			<%=username%>
		</p>

	<form method="post" action="updateStudent">
		<div>
			<input type="text" name="nameUpdate" placeholder="name">
		</div>
		<div>
			<input type="text" name="surnameUpdate" placeholder="surname">
		</div>
		<div>
			<input type="text" name="descriptionUpdate" placeholder="description">
		</div>
		<div>
			<input type="text" name="emailUpdate"  placeholder="emailUpdate">
		</div>
		<div>
			<input type="password" name="passwordUpdate" placeholder="password">
		</div>
		<div>
			<input type="text" name="username" placeholder="username">
		</div>
		<input type="submit" name="submit" value="Edit">
	</form>
</body>
</html>