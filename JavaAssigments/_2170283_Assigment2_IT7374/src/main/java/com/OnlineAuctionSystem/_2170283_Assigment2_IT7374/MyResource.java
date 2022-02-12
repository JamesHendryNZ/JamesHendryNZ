package com.OnlineAuctionSystem._2170283_Assigment2_IT7374;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.OnlineAuctionSystem.DataBaseHadleir.DatabaseKonnecter;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getIt() {
		return "<h1>Online Auctions Log in Page</h1><br>\n"+
				"<br>"
				+ "<br>"
				+ "<form action=\"myresource/post\" method=\"POST\">"
				+ "User name:<input name=\"username\" type=\"text\"><br>"
				+ "Password:<input name=\"password\" type=\"password\"><br>"
				+ "<input type=\"submit\" value=\"Submit\">"
				+ "</form>"
				+ "<a href=\"http://localhost:8080/_2170283_Assigment2_IT7374/webapi/nyuUser\">Not a User? Regisister Here</a>";

	}

	@POST
	@Path("/post")
	@Produces(MediaType.TEXT_HTML)
	public String login(@FormParam("username") String userName , @FormParam("password") String password) 
	{ 
	DatabaseKonnecter konnector = new DatabaseKonnecter();
	try
	{
		Connection konekshon = konnector.connectToDatabase();
		if(!konekshon.isClosed())
		{
			loginService loginer = new loginService();
			String usersAndPasswordz = loginer.checkDaUsers(userName, password);
			konekshon.close();
			return "<h1>" + "Got to the database" + "</h1><br>" + usersAndPasswordz;
		}
	}
	catch( Exception e )
	{
		return "<h1>" + "Exceptionsz" + "</h1><br>"
				+ e.getMessage(); 
	}
		// */

		return "<html><body><h1>" + userName + "<br>" + password + "</h1></body></html>";
	}	
	
	
}
