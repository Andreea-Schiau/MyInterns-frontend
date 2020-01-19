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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
		String usernameNeib = (String) session.getAttribute("usernameNeib");
		String nameNeib = (String) session.getAttribute("name");
		String surnameNeib = (String) session.getAttribute("surname");
		String descriptionNeib = (String) session.getAttribute("description");
		String emailNeib = (String) session.getAttribute("email");
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
								href="studentInfo.jsp" style="text-decoration: none;"><%=username%></a>
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
					<li class="list-group-item"><span class="info">Username:</span> <span
						class="badge"><%=usernameNeib%></span></li>
					<li class="list-group-item"><span class="info">Name:</span> <span
						class="badge"><%=nameNeib%></span></li>
					<li class="list-group-item"><span class="info">Surname:</span>
						<span class="badge"><%=surnameNeib%></span></li>
					<li class="list-group-item"><span class="info">Email:</span> <span
						class="badge"><%=emailNeib%></span></li>
					<li class="list-group-item"><span class="info">Description:</span>
						<span class="badge"><%=descriptionNeib%></span></li>
				</ul>
			</div>
			<button type="button" class="btn btn-secondary btn-lg">
				<a class="font-weight-light text-white" href="student.jsp">See
					neighborhood <i class="fa fa-rocket">
				</a>
			</button>
		</div>
	</div>
</body>
</html>