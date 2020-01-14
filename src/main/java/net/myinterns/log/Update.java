package net.myinterns.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("updateUsername");
		String password = request.getParameter("updatePassword");

		Client c = Client.create();

		long id = 0;
		try {
			id = getUsernameId(c, username);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/update/" + id);

		String input = "{\"username\":" + username + ",\"password\":" + password + "}";

		webResource.type("application/json").post(ClientResponse.class, input);

		if (isMentor(c, username)) {

			response.sendRedirect("mentor.jsp");

		} else {
			response.sendRedirect("student.jsp");
		}
	}

	protected boolean isMentor(Client c, String username) {

		WebResource webUserType = c
				.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/getByUsername/" + username);
		ClientResponse responseUserType = webUserType.type("application/json").get(ClientResponse.class);
		JSONObject outputUserType = responseUserType.getEntity(JSONObject.class);
		boolean isRealyMentor = false;
		try {
			isRealyMentor = outputUserType.getBoolean("isMentor");
			if (isRealyMentor)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	protected long getUsernameId(Client c, String username) throws JSONException {

		WebResource webResource = c
				.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/getByUsername/" + username);
		ClientResponse responseId = webResource.type("application/json").get(ClientResponse.class);
		JSONObject output = responseId.getEntity(JSONObject.class);
		long id = 0;

		id = output.getLong("id");
		
		return id;
	}
}