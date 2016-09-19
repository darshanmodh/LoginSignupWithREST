package com.darshanmodh.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class Login {
	
	@GET
	@Path("/dologin")
	@Produces(MediaType.APPLICATION_JSON)
	public String doLogin(@QueryParam("username") String username,
			@QueryParam("password") String password ) {
		String response = "";
		if(checkCredential(username, password)) {
			response = Utility.constructJSON("login", true);
		} else {
			response = Utility.constructJSON("login", false, "Invalid username or password");
		}
		return response;
	}

	private boolean checkCredential(String username, String password) {
		boolean result = false;
		if(Utility.isNotNull(username) && Utility.isNotNull(password)) {
			try {
				result = DBConnection.checkLogin(username, password);
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		} else {
			result = false; 	// null error
		}
		return result;
	}

}
