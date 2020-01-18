package net.myinterns.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		HttpSession session = req.getSession();
		
		Client c = Client.create();

		webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/users");
		ClientResponse responseUsers = webResource.type("application/json").get(ClientResponse.class);
		JSONArray result = responseUsers.getEntity(JSONArray.class);

		int u = 0;
		JSONObject objS = new JSONObject();
		List<UserDTO> users = new ArrayList<UserDTO>();

		while (u < result.length()) {
			try {
				objS = result.getJSONObject(u);
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(objS.getString("username"));
				userDTO.setIsMentor(objS.getBoolean("isMentor"));
				users.add(userDTO);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			u++;
		}
		
		session.setAttribute("users", users);
		
		if (alreadyUsername(c, username) || alreadyEmail(c, email)) {

			res.sendRedirect("tryAnother.jsp");

		} else {

			webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/addDTO");
			String input = "{\"name\":" + name + ",\"surname\":" + surname + ",\"description\":" + description
					+ ",\"email\":" + email + ",\"username\":" + username + ",\"password\":" + password + "}";

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

			res.sendRedirect("login.jsp");
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

	protected boolean alreadyEmail(Client c, String email) {

		webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/students");
		ClientResponse responseStudents = webResource.type("application/json").get(ClientResponse.class);
		JSONArray resultStudents = responseStudents.getEntity(JSONArray.class);
		JSONObject objS = new JSONObject();

		String emailDB = "";

		for (int i = 0; i < resultStudents.length(); i++) {
			try {
				objS = resultStudents.getJSONObject(i);
				emailDB = objS.getString("email");

				if (emailDB.contentEquals(email)) {

					return true;
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return false;
	}
}