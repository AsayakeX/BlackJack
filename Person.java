import java.util.*;

abstract public class Person	//See if we cant get this to be an abstract class
{
	//private int money;
	private List<Card> hand;

	public Person(){
		//this.money = 0;
		hand = new ArrayList<Card>();
	}

	public void getCard(Card card)	//basic
	{
		hand.add(card);
	}

	public void clearHand()		//Baisc
	{
		hand.clear();
	}

	abstract public int tally();

	abstract public void ShowHand();
}

//================================
//Player Class
//================================

public class Player extends Person
{
	private int money;
	private int bet;

	public Player()
	{
		money = 0;
		bet = 0;
	}

	public Player(int money)
	{
		this.money = money;
		bet = 0;
	}

	public int tally();		//Gets current score of hand
	{
		int score = 0;

		for (Card card : hand)
		{
			score = card.getRank() > 10 ? score+10 : score+card.getRank();
		}

		return score;
	}

	public void showHand();	//TODO Hpw to deal with face cards. plausible
	{
		for(Card card : hand)
		{
			System.out.println(card.getSuit().toString() + " " + card.getRank());
		}

		System.out.print("Total: " + this.tally() + System.lineSeperator());	//Generates a new line for format
	}

	public int getMoney()
	{
		return money;
	}

	public int getBet()
	{
		return bet;
	}

	public void setBet(int bet)
	{
		this.bet = bet;
	}

	public void result(boolean win)
	{
		money = win ? money+bet : money-bet;

		bet=0;
		clearHand();
	}

	public boolean placeBet(int bet)	//check function, does something when true
	{
		if(bet <= money)
		{
			this.bet = bet;
			return true;
		}
		return false;
	}
}


//================================
//Dealer Class
//================================

//Thougts: the deal is more of a class that is stripped of the player class
//What goes on with the dealer?
//There isna t a special instance varialbe with todo



public class Dealer extends Person 	//Have to deal with the first card being hidden
{
	public Dealer()	//necessary?
	{

	}

	public int tally(boolean reveal)		//Gets current score of hand
	{
		int score = 0;
		int index = reveal ? 0 : 1;		

		for(; index < hand.size(); index++)
		{
			score = hand.get(index).getRank() > 10 ? score+10 : score+hand.get(index).getRank();
		}
		return score;
	}

	public void showHand(boolean reveal);	//TODO Hpw to deal with face cards. plausible
	{
		int index = reveal ? 0 : 1;// = 1;	//init cval hides the first card

		if(!reveal)
		{
			System.out.print("Hidden Card" + System.lineSeperator());	//say how the first card is hidden
		}		

		for(; index < hand.size(); index++)
		{
			System.out.println(card.getSuit().toString() + " " card.getRank());
		}
		System.out.print("Total: " + this.tally(reveal) + System.lineSeperator());		
	}
}