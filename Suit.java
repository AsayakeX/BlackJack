
//public enum CardSuit 	//Determins the Card suit and suit rank
public enum Suit 	//Determins the Card suit and suit rank
{	
	DIAMOND(0), CLUB(1), HEART(2), SPADE(3);

	private int rank;

	Suit(int r){	//Constructor
		rank = r;
	}

	public int getRank(){
		return rank;
	}
}