package com.darshanmodh.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/register")
public class Register {
	
	@GET
	@Path("/doregister")
	@Produces(MediaType.APPLICATION_JSON)
	public String doRegister(@QueryParam("name") String name,
			@QueryParam("username") String username, 
			@QueryParam("password") String password) {
		String response = "";
		int returnCode = registerUser(name, username, password);
		switch (returnCode) {
		case 0:
			response = Utility.constructJSON("register", true);
			break;
		case 1:
			response = Utility.constructJSON("register", false, "You are already registered.");
			break;
		case 2:
			response = Utility.constructJSON("register", false, "Special character are not allowed in username and password");
			break;
		case 3:
			response = Utility.constructJSON("register", false, "Internal Error");
			break;
		}
		return response;
	}

	private int registerUser(String name, String username, String password) {
		int result = 3;
		if(Utility.isNotNull(username) && Utility.isNotNull(password)) {
			try {
				if(DBConnection.insertUser(username, name, password))
					result = 0;
			} catch (SQLException sqle) {
				if(sqle.getErrorCode() == 1062)		// primary-violation
					result = 1;
				else if(sqle.getErrorCode() == 1064)	// special character violation
					result = 2;
			} catch (Exception e) {
				e.printStackTrace();
				result = 3;
			}
		} else {
			result = 3;
		}
		return result;
	}

}
