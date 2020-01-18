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
		String email = (String) session.getAttribute("email");
		String username = (String) session.getAttribute("username");
		String passwordUpdate = (String) session.getAttribute("password");
	%>

	<p>
		Edit
		<%=username%>
	</p>

	<form method="post" action="Update">
		<div>
			<input type="text" name="name" placeholder="name">
		</div>
		<div>
			<input type="text" name="surname" placeholder="surname">
		</div>
		<div>
			<input type="text" name="description" placeholder="description">
		</div>
		<div>
			<input type="text" name="email" placeholder="email">
		</div>
		<div>
			<input type="password" name="password" placeholder="password">
		</div>
		<input type="submit" name="submit" value="Edit">
	</form>
</body>
</html>