package net.myinterns.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.andree.MyInterns.common.dto.UserDTO;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUp() {
		super();
	}

	WebResource webResource;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		String email = req.getParameter("email");
		String description = req.getParameter("description");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Client c = Client.create();

		if (alreadyUsername(c, username)) {

			res.sendRedirect("tryAnother.jsp");

		} else {
			webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/addDTO");
			String input = "{\"name\":" + name + ",\"surname\":" + surname + ",\"description\":" + description
					+ ",\"email\":" + email + ",\"username\":" + username + ",\"password\":" + password + "}";

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			res.sendRedirect("index.jsp");

		}
	}

	protected boolean alreadyUsername(Client c, String username) {

		webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/users");
		ClientResponse responsUsers = webResource.type("application/json").get(ClientResponse.class);
		JSONArray resultUsers = responsUsers.getEntity(JSONArray.class);
		JSONObject objS = new JSONObject();

		String usernameDB = "";

		for (int i = 0; i < resultUsers.length(); i++) {
			try {
				objS = resultUsers.getJSONObject(i);
				usernameDB = objS.getString("username");

				if (usernameDB.contentEquals(username)) {

					return true;
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}
