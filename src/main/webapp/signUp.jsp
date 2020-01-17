<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SignUp</title>
</head>
<body>
	<div>Create an account!</div>
	<form method="post" action="signUp">
		<input type="text" placeholder="Name" required name="name" required><br>
		<input type="text" placeholder="Surname" required name="surname"><br>
		<input type="text" placeholder="Email" required name="email" required><br>
		<input type="text" placeholder="Username" required name="username" required><br>
		<input type="text" placeholder="Description" required name="description"><br>
		<input type="password" placeholder="Password" required name="password" required><br>

		<input type="submit" value="Signup">
	</form>
</body>
</html>
