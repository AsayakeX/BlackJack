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
	public void buildCards(){	//Initializes 52 playing cards
		for(CardSuit suit: CardSuit.values()){
			for(CardFace face: CardFace.values()){
				cards.add(new Card(suit, face));
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

//QA functions
	public void checkCards(){	//QA check cards in deck
		for(Card card: deckBJ){
			card.getCard();
		}
	}

	public void checkDeck(){	//QA check reference in deck
		for(int i: deck)
			System.out.println(Integer.toString(i));
	}

	

//===============================
//	Player Functions 
//===============================
	public void calcHand(ArrayList<Interger> hand, int handVal){
		handVal = 0;
		for(int i: hand){
			handVal += cards[i].getFaceVal();
		}
	}

	public void printHand(ArrayList<Integer> hand, int handVal){
		for(int i: hand){
			cards[i].getCard();
		}

		System.out.println("\nHand: " + Integer.toString(handVal));
	}

	public void calcPrintHand(ArrayList<Interger> hand, int handVal){
		calcHand(hand, handVal);
		printHand(hand, handVal);
	}

	public boolean checkBet(Person p, int b){
		if(p.getFunds() >= b){
			System.out.println("Bet accepted");
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
	//public void compareHands(Person player, int pHand, int dHand){
	public void compareHands(Person player){
		if(playerVal == dealerVal)
			return;

		if(playerVal > dealerVal){
			player.addFunds(bet);
		}
		else{
			player.subFunds(bet);
		}
	}

	public void dealPhase(){
		dealerHand.add(deal(topCard));
		playerHand.add(deal(topCard));

		dealerHand.add(deal(topCard));
		playerHand.add(deal(topCard));
	}

	//public void betPhase

	public void hit(ArrayList<Interger> hand, int handVal, int top, boolean bust){
		hand.add(deal(top));	//card is added
		calcPrintHand(hand, handVal);

		if(handVal > 21){
			bust = true;
		}
	}

	public void revealPhase

//===============================
//	Main
//===============================

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Person player = new Person();	//Player setup
		
		boolean game = true;
		boolean bustDealer = false;	//check for bust
		boolean bustPlayer = false;	//check for bust
		boolean validBet = false;
		boolean validInput = false;

		String choice;

		buildCD();	//Deck setup

		do{		//Game Setup
			do{		//Bet phase
				System.out.println("Place bet: ");
				bet = sc.nextInt();
				validBet = checkBet(player, bet);
			}
			while(!validBet);

			shuffle();
			dealPhase(); //deal initial cards
			calcPrintHand(playerHand, playerVal);
			calcPrintHand(dealerHand, dealerVal);

			boolean pTurn = true;

			//player phase
			do{
				System.out.println("Hit or Stand?\n(h/s): ");
				choice = sc.nextLine();

				switch(choice){
					case "h":
						hit(playerHand, playerVal, topCard, bustPlayer);	//player get card
						if(bustPlayer)	//if the player busts
							pTurn = false;	//player phase ends
						break;
					case "s":
						pTurn = false;
						break;
					default:
						System.out.println("Invalid input.");
				}
			}
			while(pTurn);

			if(bustPlayer){//player loses so go to next game
				player.subFunds(bet);	
			}
			else{	//Dealer Phase: when player did not bust
				while(dealerVal < 17){	// this works because dealer bust when over 17 anyway
					hit(dealerHand, dealerVal, topCard, bustDealer);	//dealer get card
				}
				
				if(bustDealer){
					player.addFunds(bet);	//player wins so go to next game
				}
				else{	//Reveal Phase
					compareHands(player);	//deals with win/lose/draw
				}				
			}

			//ask usr to play again
			do{
				System.out.println("Play again? \n(y/n): ");
				choice = sc.nextLine();

				switch(choice){
					case "y":
						break;
					case "n":
						game = false;
						break;
					default:
						System.out.println("Invalid input.");
				}
			}
			while();
		}
		while(game);
	}
}