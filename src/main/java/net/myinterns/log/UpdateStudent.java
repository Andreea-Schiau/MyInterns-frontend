package net.myinterns.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateStudent() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("nameUpdate");
		String surname = request.getParameter("surnameUpdate");
		String description = request.getParameter("descriptionUpdate");
		String email = request.getParameter("email");
		String password = request.getParameter("passwordUpdate");
		String username = request.getParameter("username");

		Client c = Client.create();

		WebResource webResource = c
				.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/updateByEmail/" + email);

		String input = "{\"name\":" + name + ",\"surname\":" + surname + ",\"description\":" + description
				+ ",\"email\":" + email + ",\"password\":" + password + ",\"username\":" + username + "}";
		
		webResource.type("application/json").post(ClientResponse.class, input);
		
		response.sendRedirect("student.jsp");
	}
}
