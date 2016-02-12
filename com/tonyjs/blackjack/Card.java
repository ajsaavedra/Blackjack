package com.tonyjs.blackjack;

public class Card
{
	private String myRank;
	private String mySuit;
	private int myValue;
	private boolean isFaceUp; 

	public Card(String theRank, String theSuit, int theValue)
	{
		myRank= theRank;
		mySuit= theSuit;
		myValue = theValue;
		isFaceUp = false;
	}

	public Card(String theRank, String theSuit)
	{
		myRank= theRank;
		mySuit= theSuit;
		myValue = 0;
		isFaceUp = false;
	}
	
	public String displayCard()
	{
		if (isFaceUp) {
			return myRank + " of " + mySuit;
		}
		return "************";
	}

	public String getSuit()
	{
		return mySuit;
	}

	public String getRank()
	{
		return myRank;
	}

	public int getValue()
	{
		return myValue;
	}

	public boolean isFaceUp()
	{
		return isFaceUp;
	}

	public void setValue(int theValue)
	{
		myValue = theValue;
	}

	public void setFaceUp(boolean faceUpValue)
	{
		isFaceUp = faceUpValue;
	}
}
