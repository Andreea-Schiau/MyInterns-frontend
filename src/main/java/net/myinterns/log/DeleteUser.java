package net.myinterns.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class DeleteUser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("deleteUser");

		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://localhost:8090/MyInterns8-0.0.1-SNAPSHOT/user/delete/user" + username);
			ClientResponse res = webResource.get(ClientResponse.class);

			if (res.getStatus() != 204) {
				System.out.println(res.getStatus());
				System.out.println("The user is still there!");
			} else {
				System.out.println("The user was deleted!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("mentor.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
