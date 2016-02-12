package com.tonyjs.blackjack;

import javax.swing.*;

public class BJHand extends Hand
{
	private int playerPointCount;
	private boolean hasAce;

	public BJHand(JLabel [] theLabels, int theMaxCards)
	{
		super(theLabels, theMaxCards);
		playerPointCount = 0;
		hasAce = false;
	}

	public void resetHand()
	{
		super.resetHand();
		playerPointCount = 0;
		hasAce = false;
	}

	public int calcPointCount()
	{
		int sum = 0;
		for (int i = 0; i < myNumOfCards; i++) {
			sum = sum + myCards[i].getValue();
		}
		if (hasAce && sum <= 11) {
			return sum + 10;
		}
		return sum;
	}


	public void acceptCard(CardWithImage theCard)
	{
		super.acceptCard(theCard);
		if (theCard.getValue() == 1) {
			hasAce = true;
		}
		playerPointCount = calcPointCount();
	}

	public void turnFirstUp()
	{
		myCards[0].setFaceUp(true);
		myLabels[0].setIcon(myCards[0].getImage());
		myLabels[0].repaint();
	}
}
