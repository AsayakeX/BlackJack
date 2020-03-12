
public enum CardSuit 
{	
	DIAMOND, CLUB, HEART, SPADE;
}

public class Card
{
	private CardSuit suit;
	private int rank;

	public Card(CardSuit suit, int rank)	//Constructor
	{
		this.suit = suit;
		this.rank = rank;
	}

	public CardSuit getSuit()
	{
		return suit;
	}

	public int getRank()
	{
		return rank;
	}
}