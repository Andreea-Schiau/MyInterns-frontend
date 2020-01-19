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

import net.andree.MyInterns.common.dto.StudentDTO;
import net.andree.MyInterns.common.dto.UserDTO;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		Client c = Client.create();

		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/login");

		String input = "{\"username\":" + username + ",\"password\":" + password + "}";

		ClientResponse res = webResource.type("application/json").post(ClientResponse.class, input);
		JSONObject output = null;
		if (res.getStatus() == 200) {
			output = res.getEntity(JSONObject.class);
		}

		res.getStatus();

		session.setAttribute("username", username);
		session.setAttribute("password", password);

//		String studentEmail = getStudentId(c, getUserId(c, username));

//		session.setAttribute("email", studentEmail);

		if (output == null || output.isNull("username")) {
			response.sendRedirect("wrongUser.jsp");
		} else {
			if (isMentor(c, username)) {

				response.sendRedirect("mentor.jsp");

			} else {
				response.sendRedirect("studentInfo.jsp");
			}
		}

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

		webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/students");
		responseUsers = webResource.type("application/json").get(ClientResponse.class);
		result = responseUsers.getEntity(JSONArray.class);

		int s = 0;
		objS = new JSONObject();
		List<StudentDTO> students = new ArrayList<StudentDTO>();

		while (s < result.length()) {
			try {
				objS = result.getJSONObject(s);
				StudentDTO studentDTO = new StudentDTO();
				studentDTO.setName(objS.getString("name"));
				studentDTO.setSurname(objS.getString("surname"));
				studentDTO.setId(objS.getLong("id"));
				students.add(studentDTO);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			s++;
		}

		long id=0;
		try {
			id = getUserByUsername(c, username);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("idStudent: " + id);
		webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/getBy/" + id);
		responseUsers = webResource.type("application/json").get(ClientResponse.class);
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

		session.setAttribute("email", email);
		session.setAttribute("surname", surname);
		session.setAttribute("description", description);
		session.setAttribute("name", name);
		session.setAttribute("users", users);
	}

	protected boolean isMentor(Client c, String username) {

		WebResource webUserType = c
				.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/getByUsername/" + username);
		ClientResponse responseUserType = webUserType.type("application/json").get(ClientResponse.class);
		JSONObject outputIsMentor = responseUserType.getEntity(JSONObject.class);
		boolean isRealyMentor = false;
		try {
			isRealyMentor = outputIsMentor.getBoolean("isMentor");
			if (isRealyMentor)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	protected boolean usernameExist(Client c, String username) {

		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/users");
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

	protected long getUserId(Client c, String username) {

		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/users");
		ClientResponse responsUsers = webResource.type("application/json").get(ClientResponse.class);
		JSONArray resultUsers = responsUsers.getEntity(JSONArray.class);

		JSONObject objS = new JSONObject();

		String usernameDB = "";

		for (int i = 0; i < resultUsers.length(); i++) {
			try {
				objS = resultUsers.getJSONObject(i);
				usernameDB = objS.getString("username");

				if (usernameDB.contentEquals(username)) {

					return objS.getLong("id");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	protected String getStudentId(Client c, long idUser) {

		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/students");
		ClientResponse responsStudent = webResource.type("application/json").get(ClientResponse.class);
		JSONArray resultStudents = responsStudent.getEntity(JSONArray.class);

		JSONObject objS = new JSONObject();

		long idStudentDB;

		for (int i = 0; i < resultStudents.length(); i++) {
			try {
				objS = resultStudents.getJSONObject(i);
				idStudentDB = objS.getLong("user_id");

				if (idStudentDB == idUser) {

					return objS.getString("email");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return null;
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
}