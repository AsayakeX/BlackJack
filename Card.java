
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
	Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), 
	Nine(9), Ten(10), Jack(10), Queen(10), King(10), Ace(1);

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
	private CardSuit suit;
	private CardFace face;

	public Card(CardSuit suit, CardFace face)	//Constructor
	{
		this.suit = suit;
		this.face = face;
	}

	public void getCard(){	//make this a string statement?
		System.out.println(face.toString() + " of " + suit.toString());

	}

	public int getSuitRank(){	//Returns the rank of the suit
		return suit.getRank();
	}

	public int getFaceVal(){	//Returns the value of the card
		return face.getVal();
	}
}