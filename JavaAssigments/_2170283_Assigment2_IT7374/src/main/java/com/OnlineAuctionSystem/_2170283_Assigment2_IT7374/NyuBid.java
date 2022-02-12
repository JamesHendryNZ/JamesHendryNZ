package com.OnlineAuctionSystem._2170283_Assigment2_IT7374;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/NyuBid")
public class NyuBid 
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String ShowHub()
	{
		return "<p style=\"color:orange\"> Nothing implemented yet.</p>";
	}
}
