package net.myinterns.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUp() {
        super();
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Client c = Client.create();
		
		WebResource webResource = c.resource("http://localhost:8080/MyInterns8-0.0.1-SNAPSHOT/user/add");
		String input = "{\"username\":" + username + ",\"password\":" + password + "}";
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
		
	}
}
