<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.codehaus.jettison.json.JSONArray"%>
<%@ page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page import="java.util.List"%>
<%@ page import="net.andree.MyInterns.common.dto.UserDTO"%>
<%@ page import="net.andree.MyInterns.common.dto.StudentDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

<link rel="icon" type="image/png"
	href="/resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
</head>
<body>
	<%
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		List<UserDTO> users = (List<UserDTO>) session.getAttribute("users");
		List<StudentDTO> students = (List<StudentDTO>) session.getAttribute("students");
	%>
	<!-- Header -->
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

	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<%
				int var1 = 0;
				for (UserDTO user : users) {
					if (!user.getUsername().equals(username)) {
			%>
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-0 shadow">
					<img
						src="https://mra.ro/uploads/modelpics/580b7495e2276-31-Alexandra.jpg"
						class="card-img-top" alt="...">
					<div class="card-body text-center">
						<form method="get" action="viewMore">
							<input type=submit value=<%=user.getUsername()%>> <input
								type="hidden" name="viewMore" value=<%=user.getUsername()%>>
						</form>
						<div class="card-text text-black-50">
							<%
								String role = "Student";
										if (user.getIsMentor()) {
											role = "Mentor";
										}
							%>
							<%=role%>
						</div>
					</div>
				</div>
			</div>

			<%
				 }
			   }
			%>
		</div>
	</div>
</body>