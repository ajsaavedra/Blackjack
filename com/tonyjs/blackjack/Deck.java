package com.tonyjs.blackjack;

public class Deck
{
	private static final String  SUITS[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private static final String  RANKS[] = {"Ace", "Two", "Three", "Four", "Five", 
		"Six", "Seven", "Eight", "Nine", "Ten", 
		"Jack", "Queen", "King"};
	
	private static final int[] VALUES = {1,2,3,4,5,6,7,8,9,10,10,10,10};

	private int nextCardPosition;

	private CardWithImage[] deckOfCards;

	public Deck()
	{
		deckOfCards = new CardWithImage[52];

		for (int position = 0; position < deckOfCards.length; position++) {
			deckOfCards[position] = 
					new CardWithImage(RANKS[position%13], SUITS[position/13],  VALUES[position%13]);
		}
		nextCardPosition = 0;
	}

	public void setNextCard(int theNextCardPosition)
	{
		nextCardPosition = theNextCardPosition;
	}

	private void swap(int i1, int i2)
	{
		CardWithImage hold = deckOfCards[i1];
		deckOfCards[i1] = deckOfCards[i2];
		deckOfCards[i2] = hold;
	}

	public void shuffle()
	{
		for (int position = 0; position < deckOfCards.length; position++) {
			int swapPosition = (int) (Math.random()*52);
			swap(position, swapPosition);
		}
	}

	public CardWithImage dealOne(boolean faceUpValue) 
	{

		if (nextCardPosition > -1 && nextCardPosition < deckOfCards.length) {
			deckOfCards[nextCardPosition].setFaceUp( faceUpValue );
			return deckOfCards[nextCardPosition++];
		}
		System.out.println("Error: index of next card in deck is out of range.");
		return null;
	}
	
	public int cardsLeft()
	{
		return 52 - nextCardPosition;
	}
}
