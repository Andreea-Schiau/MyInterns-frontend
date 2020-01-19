<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
		String name = (String) session.getAttribute("name");
		String surname = (String) session.getAttribute("surname");
		String description = (String) session.getAttribute("description");
		String email = (String) session.getAttribute("email");
	%>
	<div class="container">
		<header class=" py-6 mb-4 topContainer">
			<div class="container text-right background-test">
				<div class="row">
					<div class="text-left col-md-6">
						<h1>My Interns</h1>
					</div>
					<div class="text-right col-md-6">
						<h1 class="font-weight-light text-white">
							Welcome <a class="font-weight-light text-white"
								href="updateStudent.jsp" style="text-decoration: none;"><%=username%></a>
						</h1>
					</div>
				</div>
				<form method="post" action="mentor">
					<input type="submit" value="Logout">
				</form>
			</div>

		</header>
		<div class="row">
			<div class="col-md-5">
				<img class="userPhoto"
					src="https://mra.ro/uploads/modelpics/580b7495e2276-31-Alexandra.jpg" />
			</div>
			<div class="col-md-7">
				<ul class="list-group">
					<li class="list-group-item"><span class="info">Name:</span> <span
						class="badge"><%=name%></span></li>
					<li class="list-group-item"><span class="info">Surname:</span>
						<span class="badge"><%=surname%></span></li>
					<li class="list-group-item"><span class="info">Email:</span> <span
						class="badge"><%=email%></span></li>
					<li class="list-group-item"><span class="info">Description:</span>
						<span class="badge"><%=description%></span></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>