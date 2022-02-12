package com.OnlineAuctionSystem._2170283_Assigment2_IT7374;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import com.OnlineAuctionSystem.DataBaseHadleir.*;


public class loginService 
{

	DatabaseKonnecter konnector = new DatabaseKonnecter();
	/*	@POST
    //@Path("/postmanpat")
	public String login(@FormParam("username") String userName,
			@FormParam("password") String password) {
		return checkDaUsers( userName , password );
//	} */ 

	public String checkDaUsers(String userName, String password) 
	{
		try
		{
			Connection konekshon = konnector.connectToDatabase();
			PreparedStatement sqlStament = konekshon.prepareStatement("SELECT * FROM assigment2.user");
			ResultSet resultSet = sqlStament.executeQuery();
			//ArrayList<User> currentUsers = new ArrayList<User>();
			while( resultSet.next())
			{
				//User nyuUser = new User( resultSet.getInt("user_id"), resultSet.getString("user_name") , resultSet.getString("location") , resultSet.getInt("rating") , resultSet.getString("password"));
				//the user table, the user_name attribute is set to unique so there should be no duplicates.
				if( resultSet.getString("user_name").equals(userName) && resultSet.getString("password").equals(password))
				{
					return "logged in with "+ resultSet.getString("user_name");
				}
			}
			konekshon.close();
		}
		catch( Exception e)
		{
			return e.getMessage();
		}

		return "Incorrect Username or Password";
	}

	//check for the sql database data
	public String listAllUser()
	{
		String listOUszerz ="";
		try
		{
			//konnector.connectToDatabase();
			PreparedStatement sqlStament = konnector.connectToDatabase().prepareStatement("SELECT * FROM assigment2.user");
			ResultSet resultSet = sqlStament.executeQuery();
			//ArrayList<User> currentUsers = new ArrayList<User>();
			while( resultSet.next())
			{
				//User nyuUser = new User( resultSet.getInt("user_id"), resultSet.getString("user_name") , resultSet.getString("location") , resultSet.getInt("rating") , resultSet.getString("password"));
				//the user table, the user_name attribute is set to unique so there should be no duplicates.
				listOUszerz = listOUszerz + resultSet.getString("user_name") + " :: " + resultSet.getString("password") + "\n";
			}
			return listOUszerz;
		}
		catch( Exception e)
		{
			return e.getMessage();
		}

	}
}

