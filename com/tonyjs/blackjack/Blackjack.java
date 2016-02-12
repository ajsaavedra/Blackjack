package com.tonyjs.blackjack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Blackjack extends JFrame
{
	private JPanel houseP, playerP;
	private JLabel myCardsL[], houseCardsL[], houseL, playerL;
	private JButton shuffleB, hitB, standB, newB, quitB;
	private JTextField messageTF;
	private Deck myDeck;
	private BJHand myHand, houseHand;
	private Container myCP;
	private static final int MAX_CARDS_IN_HAND = 5;
	
	public Blackjack()
	{
		super("\u2660 Blackjack \u2660");
		setSize(600, 600);
		setLocation(100, 100);
		setBackground(Color.LIGHT_GRAY);
		myCP = getContentPane();
		myCP.setLayout(null);
		setLocationRelativeTo(null);
		
		Font funFont = new Font("Geneva", 0, 12);
		
		shuffleB = makeButton("Shuffle", 50, 20, 100, 500, funFont, new Color(0, 100, 0), new ShuffleBHandler());
		hitB = makeButton("Hit", 50, 20, 160, 500, funFont, new Color(0, 100, 0), new HitBHandler());
		hitB.setEnabled(false);
		standB = makeButton("Stand", 50, 20, 220, 500, funFont, new Color(0, 100, 0), new StandBHandler());
		standB.setEnabled(false);
		newB = makeButton("New Game", 90, 20, 280, 500, funFont, new Color (0, 100, 0), new NewBHandler());
		newB.setEnabled(false);
		quitB = makeButton("Quit Game", 90, 20, 380, 500, funFont, new Color(0, 100, 0), new QuitBHandler());
		quitB.setEnabled(false);
		
		houseL = new JLabel("House");
		houseL.setSize(100, 20);
		houseL.setLocation(20, 25);
		myCP.add(houseL);
		
		playerL = new JLabel("Player");
		playerL.setSize(100, 20);
		playerL.setLocation(20, 280);
		myCP.add(playerL);
		
		houseP = makePanel(MAX_CARDS_IN_HAND * 105, 150, 20, 45, new Color(49, 130, 61));
		playerP = makePanel(MAX_CARDS_IN_HAND * 105, 150, 20, 300, new Color(49, 130, 61));
		
		messageTF = makeTextField( "To begin, press the Shuffle button.", 400, 25, 95, 525, funFont, false);

		myCardsL = new JLabel [5];
		houseCardsL = new JLabel [5];
		
		for (int i = 0; i < MAX_CARDS_IN_HAND; i++) {
			myCardsL[i] = new JLabel();
			playerP.add(myCardsL[i]);
			houseCardsL[i] = new JLabel();
			houseP.add(houseCardsL[i]);
		}
		
		myHand = new BJHand(myCardsL, MAX_CARDS_IN_HAND);
		houseHand = new BJHand(houseCardsL, MAX_CARDS_IN_HAND);
		
		myDeck = new Deck();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		 
		setVisible(true);
		setResizable(false);
	}
	
	private JButton makeButton(String theText, int theWidth, int theHeight, int theX, int theY, Font thefont,
			Color theForeground, ActionListener theHandler)
	{
		JButton buttonToReturn = new JButton(theText);
		buttonToReturn.setSize(theWidth, theHeight);
		buttonToReturn.setLocation(theX, theY);
		buttonToReturn.setFont(thefont);
		buttonToReturn.setForeground(theForeground);
		myCP.add(buttonToReturn);
		buttonToReturn.addActionListener(theHandler);
		return buttonToReturn;
	}
	
	private JTextField makeTextField(String theText, int theWidth, int theHeight, int theX, int theY,
			 Font thefont, boolean editability)
	{
		JTextField textFieldToReturn = new JTextField(theText);
		textFieldToReturn.setSize(theWidth, theHeight);
		textFieldToReturn.setLocation(theX, theY);
		textFieldToReturn.setFont(thefont);
		textFieldToReturn.setEditable(editability);
		myCP.add(textFieldToReturn);
		return textFieldToReturn;
	}
	
	private JPanel makePanel(int theWidth, int theHeight, int theX, int theY,
			Color theBackground)
	{
		JPanel panelToReturn = new JPanel();
		panelToReturn.setLayout(new GridLayout(1, MAX_CARDS_IN_HAND));
		panelToReturn.setSize(theWidth, theHeight);
		panelToReturn.setLocation(theX, theY);
		panelToReturn.setBackground(theBackground);
		myCP.add(panelToReturn);
		return panelToReturn;
	}
	
	public void disableButtonsAfterWin()
	{
		hitB.setEnabled(false);
		standB.setEnabled(false);
		houseHand.turnFirstUp();
	}
	
	public class ShuffleBHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			myHand.resetHand();
			houseHand.resetHand();
			myDeck.shuffle();
			myDeck.setNextCard(0);
			shuffleB.setEnabled(false);
			newB.setEnabled(true);
			hitB.setEnabled(false);
			quitB.setEnabled(true);
			messageTF.setText("The entire deck has been shuffled. Press New Game to start.");
		}
	}
	
	public class HitBHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			standB.setEnabled(true);
			
			if (myHand.getNumOfCards() >= 2) {
					CardWithImage c = myDeck.dealOne(true);
					CardWithImage d = myDeck.dealOne(true);
					myHand.acceptCard(c);
					messageTF.setText(c.displayCard() + " has been dealt. Your hand point value is: "
						+ myHand.calcPointCount());
			} else {
				messageTF.setText ("There are no cards left. You must shuffle." );
				shuffleB.setEnabled(true);
				hitB.setEnabled(false);
				standB.setEnabled(false);
			}
			
			if (myHand.calcPointCount() > 21) {
				disableButtonsAfterWin();
				messageTF.setText("Player bust! House wins. Try again?");
			}else if (houseHand.calcPointCount() == 21) {
				disableButtonsAfterWin();
				messageTF.setText("House wins. Try again?");
			}else if (myHand.calcPointCount() <= 21 && myHand.getNumOfCards() == 5) {
				disableButtonsAfterWin();
				messageTF.setText("Congratulations! You win! Play again?");
			}
		}
	}
	
	public class StandBHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			hitB.setEnabled(false);
			standB.setEnabled(false);
			houseHand.turnFirstUp();
			if (houseHand.calcPointCount() < 17) {
				while (houseHand.calcPointCount( ) < 17 &&
						houseHand.getNumOfCards( ) < 5 ) {
						houseHand.acceptCard(myDeck.dealOne(true)); 
					}
			}
			if (houseHand.calcPointCount() >= 17 && houseHand.calcPointCount() <= 20) {
				if (myHand.calcPointCount() > houseHand.calcPointCount()) {
					messageTF.setText("Congratulations! You win! Play Again?");
				} else {
					messageTF.setText("House wins. Try again?");
				}
				if (myHand.calcPointCount() == houseHand.calcPointCount()) {
					messageTF.setText("It's a draw! Play again?");
				}
			}
			if (houseHand.calcPointCount() == 21) {
				messageTF.setText("House wins. Try again?");
			} else if (houseHand.calcPointCount() > 21) {
					messageTF.setText("House bust. You win! Play again?");
			} else if (houseHand.calcPointCount() < 21 && houseHand.getNumOfCards() == 5) {
					messageTF.setText("House wins by default. Try again?");
			}
		}
	}
	
	public class NewBHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			myHand.resetHand();
			houseHand.resetHand();
			messageTF.setText("");
			if (myDeck.cardsLeft() >  9) {
				if (myHand.getNumOfCards() == MAX_CARDS_IN_HAND) {
					myHand.resetHand();
				}
				if (houseHand.getNumOfCards() == MAX_CARDS_IN_HAND) {
					houseHand.resetHand();
				}
				if (myHand.getNumOfCards() == 0) {
				CardWithImage card1 = myDeck.dealOne(true);
				CardWithImage card2 = myDeck.dealOne(false);
				CardWithImage card3 = myDeck.dealOne(true);
				CardWithImage card4 = myDeck.dealOne(true);
				myHand.acceptCard(card1);
				houseHand.acceptCard(card2);
				myHand.acceptCard(card3);
				houseHand.acceptCard(card4);
				messageTF.setText(card3.displayCard() + " has been dealt. Your hand point value is: "
						+ myHand.calcPointCount());
				}
			}
			if (myHand.calcPointCount() == 21) {
				disableButtonsAfterWin();
				messageTF.setText("You win! Play again?");
			} else{
				hitB.setEnabled(true);
				standB.setEnabled(true);
			}
		}
	}
	
	public class QuitBHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			myHand.resetHand();
			houseHand.resetHand();
			messageTF.setText("To restart the game, click the Shuffle button.");
			quitB.setEnabled(false);
			newB.setEnabled(false);
			hitB.setEnabled(false);
			standB.setEnabled(false);
			shuffleB.setEnabled(true);
		}
	}
	
	public static void main (String args[])
	{
		Blackjack myAppF = new Blackjack();
	}
}
