<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.codehaus.jettison.json.JSONArray"%>
<%@ page import="org.codehaus.jettison.json.JSONObject"%>
<%@ page import="java.util.List"%>
<%@ page import="net.andree.MyInterns.common.dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
	 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		List<UserDTO> users = (List<UserDTO>) session.getAttribute("users");
	%>
	<!-- Header -->
	<header class="bg-primary text-center py-5 mb-4">
		<div class="container">
			<h1 class="font-weight-light text-white">Welcome <a href="update.jsp"><%=username%></a></h1>
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
		%>
			<div class="col-xl-3 col-md-6 mb-4">
				<div class="card border-0 shadow">
					<img src="https://source.unsplash.com/TMgQMXoglsM/500x350"
						class="card-img-top" alt="...">
					<div class="card-body text-center">
						<h5 class="card-title mb-0"><%=user.getUsername()%></h5>
						<div class="card-text text-black-50">Web Developer</div>
						<form method="get" action="DeleteUser">
							<input type=submit value=delete class="fa-trash">
							<input type="hidden" name="deleteUser" value=<%=user.getUsername()%>>
						</form>
					</div>
				</div>
			</div>
			<%
			}
		%>
		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->
</body>
</html>