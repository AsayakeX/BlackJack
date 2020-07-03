
public enum CardSuit 	//Determins the Card suit and suit rank
{	
	DIAMOND(0), CLUB(1), HEART(2), SPADE(3);

	private int rank;

	public CardSuit(int suitRank){	//Constructor
		rank = suitRank;
	}

	public int getRank(){
		return rank;
	}
}

public enum CardFace	//Determines the cards value
{
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
	NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

	private int faceVal;

	public CardFace(int val){	//Constructor
		faceVal = val;
	}

	public int getVal(){
		return faceVal;
	}
}

public class Card
{
	private CardFace face;
	private CardSuit suit;

	public Card(CardFace face, CardSuit suit)	//Constructor
	{
		this.face = face;
		this.suit = suit;
	}

	public String getCard(){	//make this a string statement?
		String card = face.toString() + " of " + suit.toString();
		return card;
		//System.out.println(face.toString() + " of " + suit.toString());
	}

	public int getFaceVal(){	//Returns the value of the card
		return face.getVal();
	}

	public int getSuitRank(){	//Returns the rank of the suit
		return suit.getRank();
	}
}