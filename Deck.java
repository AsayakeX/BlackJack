import java.util.ArrayList;

public class Deck{
	final int deckSize = 52;
	int top = deckSize-1;

	ArrayList<Card> cards;// = new ArrayList<>(deckSize);	//Set of cards
	int[] deck;// = new int[deckSize];	//Ints to represent card placement in deck

	public Deck(){
		cards = new ArrayList<>(deckSize);
		deck = new int[deckSize];
	}

	public void buildCards(){	//Initializes 52 playing cards
		for(Suit suit: Suit.values()){
			for(Face face: Face.values()){
				cards.add(new Card(face, suit));
			}
		}
	}

	public void buildDeck(){	//Reference numbers to cards
		for(int i = 0; i < deckSize; i++){
			deck[i] = i;
		}
	}

	public void buildCD(){	//Calls buildCards and buildDeck
		buildCards();
		buildDeck();
	}

	public void shuffle(){	//test this later
		for(int i = 0; i < deckSize; i++){
			//int rng = (int)(Math.random()*deckSize);
			int rng = (int)(Math.random()*deckSize-1)+0;
			deck[i] += deck[rng];
			deck[rng] = deck[i]-deck[rng];
			deck[i] -= deck[rng];
		}
		top = deckSize-1;
	}

	public String printCard(int c){	//testing
		return cards.get(c).getCard();

	}

	public int getVal(int c){	//test function
		return cards.get(c).getVal();

	}

	public int deal(){	//Returns current top referrence and updates
		int topCard = deck[top];
		top--;
		return topCard;
	}

	//QA functions
	public void checkCards(){	//QA check cards in deck
		for(Card c: cards){
			System.out.println(c.getCard());
		}
	}

	public void checkDeck(){	//QA check reference in deck
		for(int i: deck)
			System.out.println(Integer.toString(i));
	}

}