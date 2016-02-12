package com.tonyjs.blackjack;

import javax.swing.*;

public class CardWithImage extends Card
{
	final static String sep = java.io.File.separator;
	private static final ImageIcon cardBackImage = 
				new ImageIcon("cards" + sep + "cardBack.png");
	
	private ImageIcon myImage;
	
	public CardWithImage(String theRank, String theSuit, int theValue)
	{
		super(theRank, theSuit, theValue);
		myImage = new ImageIcon("cards" + sep + theRank + theSuit + ".png");
	}
	
	public CardWithImage(String theRank, String theSuit)
	{
		super(theRank, theSuit);
		myImage = new ImageIcon("cards" + sep + theRank + theSuit + ".png");
	}
	
	public ImageIcon getImage()
	{
		return isFaceUp() ? myImage : cardBackImage;
	}
}
