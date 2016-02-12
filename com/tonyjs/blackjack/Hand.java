package com.tonyjs.blackjack;

import javax.swing.*;

public class Hand
{
	protected CardWithImage myCards[];
	protected int myNumOfCards;
	protected JLabel myLabels[];
	private int myMaxCards;
	
	public Hand(JLabel[] theLabels, int theMaxCards)
	{
		myCards = new CardWithImage[theMaxCards];
		myMaxCards = theMaxCards;
		myNumOfCards = 0;
		myLabels = theLabels;
	}
		
	public int getNumOfCards()
	{
		return myNumOfCards;
	}

	public int getMaxCards()
	{
		return myMaxCards;
	}

	public void setNumOfCards(int theNumOfCards)
	{
		myNumOfCards = theNumOfCards;
	}
	
	public void resetHand()
	{	
		for (int i = 0; i < myNumOfCards; i++) {
			myLabels[i].setIcon(null);
		}
		myNumOfCards = 0;
	}
	
	public void acceptCard(CardWithImage theCard)
	{
		if (myNumOfCards > myMaxCards - 1) {
			System.out.println("Error, hand cannot accept more cards!");
		} else {
			myLabels[myNumOfCards].setIcon(theCard.getImage());
			myLabels[myNumOfCards].repaint();
			myCards[myNumOfCards++] = theCard;
		}
	}
}
