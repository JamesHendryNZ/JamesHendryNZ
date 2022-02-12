package com.OnlineAuctionSystem._2170283_Assigment2_IT7374;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.OnlineAuctionSystem.DataBaseHadleir.DatabaseKonnecter;
import com.OnlineAuctionSystem.DataBaseHadleir.SellItem;

@Path("/bidhub")
public class BidView 
{
	ArrayList<SellItem> availableAuctions = new ArrayList<SellItem>();

	String cssStyle="<style>"
			+ ".auction"
			+ "{"
			+ "border: 10px outset grey;"
			+ "}"
			+ "img"
			+ "{"
			+ "height: 300px;"
			+ "width: 300px;"
			+ "}"
			+ "</style>";
	//
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String showAllbids()
	{
		return cssStyle + getAvalibleAuctions();
		//return "<p style=\"color:orange\">Not implemented yet</p>";
	}

	public String getAvalibleAuctions()
	{
		String listOfAuctions =  "";
		try
		{
			DatabaseKonnecter konnector = new DatabaseKonnecter();
			Connection konekshon = konnector.connectToDatabase();
			PreparedStatement selectStatement = konekshon.prepareStatement("SELECT * FROM assigment2.sell_item");
			ResultSet results = selectStatement.executeQuery();
			//
			while( results.next() )
			{
				ArrayList<String> imageLocations = new ArrayList<String>();
				PreparedStatement auctionImages = konekshon.prepareStatement("SELECT * FROM assigment2.images LEFT JOIN assigment2.sell_item " 
						+"ON assigment2.images.sell_id = assigment2.sell_item.sell_id "
						+"WHERE assigment2.images.sell_id = ?;");
				auctionImages.setInt(1, results.getInt("sell_id"));
				ResultSet imageSet = auctionImages.executeQuery();

				while( imageSet.next() )
				{
					imageLocations.add(imageSet.getString("image_url"));
				}	

				SellItem newAuction = new SellItem(results.getInt("sell_id"), results.getString("name"), results.getString( "description" ), 
						results.getDouble("minumum_sell_amount") , results.getDate("opening_date") , results.getDate("closing_date") , 
						results.getString("opended") , results.getString("sold") , imageLocations );

				//
				/*listOfAuctions = listOfAuctions + "<div><tr style=\"border: 10px dotted black\">"
						+ "<img src=\"file:///"+ results.getString("image_url") +"\">"
						+ "</tr>";*/
				listOfAuctions = listOfAuctions + makeSellBlock(newAuction);
			}

			konekshon.close();
			return "<p style=\"color:green\"> Got to the data base</p><br>"+ listOfAuctions ;
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}


	String makeSellBlock( SellItem item )
	{
		String auctionBlock = "<div class=\"auction\"><td>"
				+ "<h2>"+ item.getName() + "</h2><br>"
				+ "<h3> Description </h3><br>"
				+ item.getDescription() + "<br>"
				+ "Going for: $" + item.getMiniumumSellAmount()+"<br>"
				+ "Auction closing date:" + item.getClosingDate().toString()
				+ "<br></td><td>";
		for (int i = 0; i < item.getImages().size(); i++) 
		{
			auctionBlock = auctionBlock + "<img src=\"file:///"+ item.getImages().get(i) +"\">";
		}

		auctionBlock = auctionBlock + "</td></div>";
		return auctionBlock;
	}
}
