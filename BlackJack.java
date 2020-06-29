import java.util.Random;
import java.util.Scanner;

class BlackJack
{
	//create a deck
	int final deckSize = 52;
	int topCard = deckSize;

	ArrayList<Integer> playerHand = new ArrayList<>();
	int playerVal = 0;

	ArrayList<Integer> dealerHand = new ArrayList<>();
	int dealerVal = 0;

	ArrayList<Card> cards = new ArrayList<>(deckSize);	//Set of cards
	int[] deck = new int[deckSize];	//Ints to represent card placement in deck

	int bet = 0;
	
//===============================
//	Deck Functions 
//===============================
	public void initCards(){	//Initializes 52 playing cards
		for(CardSuit suit: CardSuit.values()){
			for(CardFace face: CardFace.values()){
				cards.add(new Card(suit, face));
			}
		}
	}

	public void initDeck(){	//Reference numbers to cards
		for(int i = 0; i < deckSize; i++){
			deck[i] = i;
		}
	}

	public void initCD(){	//Calls initCards and initDeck
		initCards();
		initDeck();
	}

	public void checkCards(){	//QA check cards in deck
		for(Card card: deckBJ){
			card.getCard();
		}
	}

	public void checkDeck(){	//QA check reference in deck
		for(int i: deck)
			System.out.println(Integer.toString(i));
	}

	public void shuffle(){	//test this later
		//int rng;
		//int min = 0;

		for(int i = 0; i < deckSize; i++){
			//rng = (int)(Math.random()*((deckSize - min)+1))+min;
			int rng = (int)(Math.random()*deckSize);
			deck[i] += deck[rng];
			deck[rng] = deck[i]-deck[rng];
			deck[i] -= deck[rng]
		}
	}

	public int deal(int d){	//Returns current top referrence and updates
		int card = deck[d];
		d--;
		return card;
	}

//===============================
//	Player Functions 
//===============================

	public void printHand(ArrayList<Integer> hand, int handVal){
		for(int i: hand){
			cards[i].getCard();
		}

		System.out.println("\nHand: " + Integer.toString(handVal));
	}

	public void calcHand(ArrayList<Interger> hand, int handVal){
		handVal = 0;
		for(int i: hand){
			handVal += cards[i].getFaceVal();
		}
	}

	public boolean checkBet(Person p, int b){
		if(p.getFunds() >= b){
			System.out.println("Bet placed");
			bet = b;
			return true;
		}
		else{
			System.out.println("Not enough funds");
			return false;
		}
	}

//===============================
//	Game Functions 
//===============================
	public void dealPhase(){
		dealerHand.add(deal(topCard));
		playerHand.add(deal(topCard));



		dealerHand.add(deal(topCard));
		playerHand.add(deal(topCard));



	}

	public void betPhase

	public void playerPhase

	public void revealPhase









	public static void main(String[] args)
	{

		Person player = new Person();	//Player setup
		initCD();	//Deck setup

		/*game phase

		shuffle

		player palces bet

		deal card dealer
		deal card player

		deal card dealer
		deal card player

		player hits or stays
			if player busts auto lose
			else continue

		dealer reveals

		check dealer and player
		if player wins
			add to player bet
		if player lose
			subtract from pllayter bet
		else draw
			do nothing

		reset




		*/


		//create a hand

		
	}
}