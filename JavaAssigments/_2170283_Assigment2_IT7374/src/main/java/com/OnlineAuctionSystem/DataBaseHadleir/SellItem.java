package com.OnlineAuctionSystem.DataBaseHadleir;

import java.sql.Date;
import java.util.ArrayList;

public class SellItem 
{
	private int sellId;
	private String name;
	private String description;
	private double miniumumSellAmount;
	private Date openingDate;
	private Date closingDate;
	private boolean opended;
	private boolean sold;
	private ArrayList<String> images;
	


	public SellItem(int sellId, String name, String description, double miniumumSellAmount, Date openingDate,
			Date closingDate, String opended, String sold, ArrayList<String> images) {
		this.sellId = sellId;
		this.name = name;
		this.description = description;
		this.miniumumSellAmount = miniumumSellAmount;
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		
		if(opended.equals("f"))
			this.opended = false;
			else
			this.opended = true;	
		if(sold.equals("f"))
			this.sold = false;
		else
			this.sold = true;
		
		this.images = images;
	}

	
	
	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public void addImage( String imageUrl)
	{
		images.add(imageUrl);
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMiniumumSellAmount() {
		return miniumumSellAmount;
	}

	public void setMiniumumSellAmount(double miniumumSellAmount) {
		this.miniumumSellAmount = miniumumSellAmount;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public boolean isOpended() {
		return opended;
	}

	public void setOpended(boolean opended) {
		this.opended = opended;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}
	
	
}
