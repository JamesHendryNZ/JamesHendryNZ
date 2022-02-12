package com.OnlineAuctionSystem._2170283_Assigment2_IT7374;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.OnlineAuctionSystem.DataBaseHadleir.DatabaseKonnecter;

@Path("/nyuUser")
public class NyuUser 
{
	public String defaltOrNot( String pathName )
	{
		return
				"<h1>Register New User</h1><br>"
				+ "<br>"
				+ "<form action=\""+ pathName +"\" method=\"POST\">"
				+ "User name:<input name=\"username\" type=\"text\"><br>"
				+ "Password:<input name=\"password\" type=\"password\"><br>"
				+ "Location:<input name=\"location\" type=\"text\"><br>"
				+ "<input type=\"submit\" value=\"Submit\">"
				+ "</form>";
	}
		/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ReigisterUser() 
	{
		return defaltOrNot("nyuUser/post");

	}

	
	@POST
	@Path("/post")
	@Produces(MediaType.TEXT_HTML)
	public String createNewUser(@FormParam ("username") String user_name ,@FormParam ("password") String password ,@FormParam ("location") String location)
	{
		try
		{
			DatabaseKonnecter konnector = new DatabaseKonnecter();
			Connection konnekshon = konnector.connectToDatabase();
			PreparedStatement insertStatement = konnekshon.prepareStatement("INSERT INTO user (user_name , password , location , rating) "
					+ "VALUES (?,?,?,?);" );
			insertStatement.setString(1, user_name);
			insertStatement.setString(2, password);
			insertStatement.setString(3, password);
			insertStatement.setInt(4, 50);
			insertStatement.executeUpdate();
			konnekshon.close();
			return "<p style=\"color:green\">Registered and Good 2 go</p>" + defaltOrNot("");
		}
		catch (Exception e)
		{
			return "<p style=\"color:red\">" + e.getMessage() + "</p>" + defaltOrNot("");
		}
		//return "<p style=\"color:Orange\">There is a hole in the logic of createNewUser if it gets here</p>" + originalPage;

	}
}
