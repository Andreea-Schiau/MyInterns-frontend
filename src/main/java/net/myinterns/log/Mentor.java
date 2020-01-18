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

public class Mentor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Mentor() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String deleteUser = request.getParameter("deleteUser");
		long id = Long.parseLong(deleteUser);

		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/delete/" + id);
			ClientResponse res = webResource.get(ClientResponse.class);

			if (res.getStatus() != 204) {
				System.out.println(res.getStatus());
				System.out.println("The student is still there!");
			} else {
				System.out.println("The student was deleted!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect("mentor.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		HttpSession session = request.getSession();
//		Client c = Client.create();
//
//		WebResource webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/users");
//		ClientResponse responseUsers = webResource.type("application/json").get(ClientResponse.class);
//		JSONArray resultUser = responseUsers.getEntity(JSONArray.class);

//		int u = 0;
//		JSONObject objU = new JSONObject();
//		List<UserDTO> users = new ArrayList<UserDTO>();
//
//		while (u < resultUser.length()) {
//			try {
//				objU = resultUser.getJSONObject(u);
//				UserDTO userDTO = new UserDTO();
//				userDTO.setUsername(objU.getString("username"));
//				userDTO.setIsMentor(objU.getBoolean("isMentor"));
//				users.add(userDTO);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			u++;
//		}
		
//		webResource = c.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/student/students");
//		ClientResponse responseStudents = webResource.type("application/json").get(ClientResponse.class);
//		JSONArray resultStudents = responseStudents.getEntity(JSONArray.class);
//
//		int s = 0;
//		JSONObject objS = new JSONObject();
//		List<StudentDTO> students = new ArrayList<StudentDTO>();
//
//		while (s < resultStudents.length()) {
//			try {
//				objS = resultStudents.getJSONObject(s);
//				StudentDTO studentDTO = new StudentDTO();
//				studentDTO.setName(objS.getString("name"));
//				studentDTO.setSurname(objS.getString("surname"));
//				students.add(studentDTO);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			s++;
//		}
//
////		session.setAttribute("users", users);
//		session.setAttribute("students", students);
	}
}
