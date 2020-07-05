public class Card
{
	private Face face;
	private Suit suit;

	public Card(Face f, Suit s){	//Constructor
		face = f;
		suit = s;
	}

	public String getCard(){	//make this a string statement?
		String card = face.toString() + " of " + suit.toString();
		return card;
	}

	public int getFaceVal(){	//Returns the value of the card
		return face.getVal();
	}

	public int getSuitRank(){	//Returns the rank of the suit
		return suit.getRank();
	}
}