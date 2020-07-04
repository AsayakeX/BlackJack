import java.util.Random;
import java.util.Scanner;

public class BlackJack
{
	final int deckSize = 52;
	boolean game = true;
	
	ArrayList<Card> cards = new ArrayList<>(deckSize);	//Set of cards
	int[] deck = new int[deckSize];	//Ints to represent card placement in deck

	ArrayList<Integer> playerHand = new ArrayList<>();
	ArrayList<Integer> dealerHand = new ArrayList<>();

	int topCard = deckSize;
	int playerVal = 0;
	int dealerVal = 0;
	int bet = 0;

	boolean bustDealer = false;	//check for bust
	boolean bustPlayer = false;	//check for bust
	boolean validBet = false;
	boolean validInput = false;

	String choice;
	
	//===============================
	//	Deck Functions 
	//===============================
	public void buildCards(){	//Initializes 52 playing cards
		for(CardSuit suit: CardSuit.values()){
			for(CardFace face: CardFace.values()){
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
		for(Card card: cards){
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
			System.out.println(cards[i].getCard());
		}

		System.out.println("\nHand Value: " + Integer.toString(handVal));
	}

	public void calcPrintHand(ArrayList<Interger> hand, int handVal){
		calcHand(hand, handVal);
		printHand(hand, handVal);
	}

	//===============================
	//	Game Functions 
	//===============================
	public void initState(){
		playerHand.clear();	//resets the hands
		dealerHand.clear();

		topCard = deckSize;
		playerVal = 0;
		dealerVal = 0;
		bet = 0;
		
		bustDealer = false;	//check for bust
		bustPlayer = false;	//check for bust
		validBet = false;
		validInput = false;
	}

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

	public void hit(ArrayList<Interger> hand, int handVal, int top, boolean bust){
		hand.add(deal(top));	//card is added
		calcPrintHand(hand, handVal);

		if(handVal > 21){
			bust = true;
		}
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
	//	Main
	//===============================

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Person player = new Person();	//Player setup

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
			
			//player phase
			do{
				System.out.println("Hit or Stand?\n(h/s): ");
				choice = sc.nextLine();

				switch(choice.toLowerCase()){
					case "h":
						hit(playerHand, playerVal, topCard, bustPlayer);	//player get card
						if(bustPlayer)	//if the player busts
							validInput = true;	//player phase ends
						break;
					case "s":
						validInput = true;
						break;
					default:
						System.out.println("Invalid input.");
				}
			}
			while(!validInput);

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

			validInput = false;	//check for a valid input from user

			do{
				System.out.println("Play again? \n(y/n): ");
				choice = sc.nextLine();

				switch(choice.toLowerCase()){
					case "y":
						validInput = true;
						initState();
						break;
					case "n":
						validInput = true;	//
						game = false;	//flag to exit game
						break;
					default:
						System.out.println("Invalid input.");
				}
			}
			while(!validInput);
		}
		while(game);
	}
}