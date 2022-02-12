package com.OnlineAuctionSystem.DataBaseHadleir;
import java.sql.*;

import javax.ws.rs.Path;

public class DatabaseKonnecter 
{
	//@Path("DatabaseKonnector")
    public Connection connectToDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException 
    {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection connection = null;
		String dbUrl = "jdbc:mysql://localhost:3306/assigment2";
		String user = "root";
		String password = "";
        
        try
        {
        connection = DriverManager.getConnection( dbUrl , user , password );    
        return connection;
        }
        catch( SQLException e )
        {
            return null;
        }
    }
}
