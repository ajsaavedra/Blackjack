package com.tonyjs.blackjack;

public class Deck {
	private static final String  SUITS[] = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private static final String  RANKS[] = {"Ace", "Two", "Three", "Four", "Five", 
		"Six", "Seven", "Eight", "Nine", "Ten", 
		"Jack", "Queen", "King"};
	//the values below are useful in a version of the game of Black Jack
	private static final int  VALUES[] = {1,2,3,4,5,6,7,8,9,10,10,10,10};

	private int myNextCardPosition;//position of next card to be dealt

	private CardWithImage myCards [];//an array of cardWithImage references

	public Deck() {
		myCards = new CardWithImage[52];//construct array of 52 cardWithImage references

		// for loop that constructs the 52 cards of a standard deck       
		for (int position=0; position < myCards.length; position++) {
			//construct card with appropriate suit, rank, and value
			myCards[position] = 
					new CardWithImage(RANKS[position%13], SUITS[position/13],  VALUES[position%13]);
		}// for loop
		myNextCardPosition = 0;//set first card in deck as first to be dealt
	} // Deck constructor

	public void setNextCard(int theNextCardPosition) {
		myNextCardPosition = theNextCardPosition;
	}//setNextCardPosition

	private void swap (int i1, int i2) {//used by shuffle method
		// switch the cards in positions i1 and i2 in the deck.
		CardWithImage hold = myCards[i1];
		myCards[i1] = myCards[i2];
		myCards[i2] = hold;
	} //swap

	public void shuffle () {
		for (int position=0; position<myCards.length; position++) {
			int swapPosition = (int) (Math.random()*52);
			// Call the swap method here with the appropriate arguments 
			swap( position, swapPosition );
		}// for loop
	}//shuffle

	public CardWithImage dealOne (boolean theFaceUp) {

		if (myNextCardPosition > -1 && myNextCardPosition < myCards.length) {// Make sure myNextCard 
			//is valid before trying to use it to index the deck

			myCards[myNextCardPosition].setFaceUp( theFaceUp ); // set the face up appropriately

			// Provide the code to both increment myFaceUp and return the appropriate Card
			return myCards[ myNextCardPosition++ ];
		}//if

		System.out.println("Error: index of next card in deck is out of range.");
		return null;
	}//dealOne

	public int cardsLeft() { //returns number of cards left in deck
		return 52 - myNextCardPosition;// calculate the number of Cards left to be dealt
	}//cardsLeft
}//Deck