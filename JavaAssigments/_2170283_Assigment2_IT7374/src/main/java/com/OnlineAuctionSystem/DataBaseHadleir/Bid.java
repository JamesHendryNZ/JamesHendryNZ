package com.OnlineAuctionSystem.DataBaseHadleir;

import java.sql.Date;

public class Bid 
{
	private int bidId;
	private double amount;
	private Date bidTime;
	private User user;
	private SellItem sellItem;
	
	public Bid(int bidId, double amount, Date bidTime, User user, SellItem sellItem) {
		this.bidId = bidId;
		this.amount = amount;
		this.bidTime = bidTime;
		this.user = user;
		this.sellItem = sellItem;
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SellItem getSellItem() {
		return sellItem;
	}

	public void setSellItem(SellItem sellItem) {
		this.sellItem = sellItem;
	}
	
}
