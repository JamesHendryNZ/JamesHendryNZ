package com.OnlineAuctionSystem.DataBaseHadleir;

public class User 
{
	private int userId;
	private String userName;
	private String location;
	private int rating;
	private final int stars = 100;
	private String password;
	
	public User(int userId, String firstName, String location, int rating, String daPassword) {
		
		this.userId = userId;
		this.userName = firstName;
		this.location = location;
		this.rating = inforceRatingContraints(rating);
		password = daPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return userName;
	}

	public void setFirstName(String firstName) {
		this.userName = firstName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = inforceRatingContraints(rating);
	}
	
	private int inforceRatingContraints( int nyuRating )
	{
		if( nyuRating > stars )
			nyuRating = 100;
		else if( nyuRating < 0 )
			nyuRating = 0;
		
		return nyuRating;
	}
}
