package net.myinterns.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class NeibInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NeibInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = request.getParameter("viewMore");
		Client c = Client.create();
		
		long id=0;
		try {
			id = getUserByUsername(c, username);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/getBy/" + id);
		ClientResponse responseUsers = webResource.type("application/json").get(ClientResponse.class);
		JSONObject result1 = responseUsers.getEntity(JSONObject.class);

		String name = null, description = null, surname = null, email = null;

		try {
			name = result1.getString("name");
			surname = result1.getString("surname");
			description = result1.getString("description");
			email = result1.getString("email");
			System.out.println(" TestJSON: " + name + " " + surname);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		session.setAttribute("usernameNeib", username);
		session.setAttribute("email", email);
		session.setAttribute("surname", surname);
		session.setAttribute("description", description);
		session.setAttribute("name", name);
		
		response.sendRedirect("neibInfo.jsp");
	}

	protected long getUserByUsername(Client c, String username) throws JSONException {

		WebResource webResource = c
				.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/getByUsername/" + username);
		ClientResponse responseId = webResource.type("application/json").get(ClientResponse.class);
		JSONObject output = responseId.getEntity(JSONObject.class);
		long id = 0;

		id = output.getLong("id");

		return id;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
