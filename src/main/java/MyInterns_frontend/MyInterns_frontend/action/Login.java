package MyInterns_frontend.MyInterns_frontend.action;

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

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();

		/* **** LOGIN **** */
		Client c = Client.create();

		WebResource webResource = c.resource("http://localhost:8080/MyInterns8-0.0.1-SNAPSHOT/user/login");

		String input = "{\"username\":" + username + ",\"password\":" + password + "}";

		ClientResponse res = webResource.type("application/json").post(ClientResponse.class, input);
		JSONObject output = res.getEntity(JSONObject.class);

		String firstname = "";
		String lastname = "";

		try {
			firstname = output.getString("firstname");
			lastname = output.getString("lastname");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
