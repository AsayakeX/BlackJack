import java.util.Random;
import java.util.Scanner;

class BlackJack
{
	int deckSize = 52;
	int index = 0;
	int funds = 100;

	Card[] reference = new Card[deckSize];
	int[] proxy = new int[deckSize]

	public void initDeck()
	{
		for(CardSuit suit : CardSuit.values())
		{
			for(int rank = 1; rank <= 13; rank++)
			{
				reference[index] = new Card(suit, rank);
				proxy[index] = index;
				index++;
			}
		}
	}
	

/*
	for(int i = 0; i < deckSize; i++)
	{
		proxy[i] = i;
	}
	*/

	public void shuffle()
	{
		Random rng = new Random();
		int rng1;
		int rng2;

		for(int i = 0; i < deckSize; i++)
		{
			rng1 = rng.nextInt(deckSize);
			rng2 = rng.nextInt(deckSize);
			
			proxy[rng1]+=proxy[rng2];//7
			proxy[rng2]=proxy[rng1]-proxy[rng2];//3
			proxy[rng1]-=proxy[rng2];//4
		}
	} 

	public Card deal()
	{
		Card card = reference[proxy[index]];
		index--;
		return card;
	}






	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Player player = new Player(funds);
		Dealer dealer = new Dealer();
		boolean outcome;
		boolean playerTurn = true;
		boolean dealerTurn = true;
		

		initDeck();
		shuffle();

		for(int i = 0; i < 2; i++)	//initial 2 cards
		{
			dealer.getCard(deal());
			player.getCard(deal());
		}
		
		dealer.showHand(false);
		player.showHand();


		do 		//Players Turn
		{
			System.out.print("(H)it or (S)tay?");
			char choice = sc.next().charAt(0);
			choice = Character.toUpperCase(choice);

			switch(choice)
			{
				case "H":
					player.getCard(deal());	//card added
					player.showHand();	//show hand

					if(player.tally() > 21)	//check if over
					{
						System.out.print("Player Busts");
						playerTurn = false;
					}
					break;
				case "S":
					playerTurn = false;				
					break;				
				default:
					System.out.print("Invalid Input!");
			}
		}
		while(playerTurn);

		dealer.showHand(true); //reveal dealers hand

//to deal with
		/*
		deal < player
			dealer wins


		deal > player

			while deal < 17
				get card
				if bust
					lose

		deal == player
		push

		*/

//Dealers turn and first check if dealer busts
		//Does dealer turn only if player doestnt bust, 
			//else skip dealer turn and present outcome
		//during dealer turn, dealer must get cards until least 17


		if(player.tally() <= 21)	//coducts the dealers turn after player is valid
		{
			while(dealer.tally() < 17)
			{
				dealer.getCard(deal());	//card add				
				dealer.showHand(true);	//show hand

			}

			if(dealer.tally(true) > 21)	//check if busts
			{
				System.out.println("Dealer Busts!");
				//dealerTurn = false;
			}
		}


/*
		do
		{
			//check if player already busts
			// or dealer hyand beats player
			if(player.tally() < dealer.tally(true))	
			{
				dealerTurn = false;
				//break;
			}

			//dealer gets a card under the following
			//dealer is less than player and less than 17
			else if(dealer.tally() < player.tally() && dealer.tally() < 17)
			{
				dealer.getCard(deal());	//card add				
				dealer.showHand(true);	//show hand

				if(dealer.tally(true) > 21)	//check if busts
				{
					System.out.println("Dealer Busts!");
					dealerTurn = false;

				}	

			}


		}
		while(dealerTurn)
*/

		//check outcome
		
		//outcome = player.tally() > dealer.tally(true) ? true : false;
		//player.result(outcome);
		player.result(player.tally() > dealer.tally(true));
		dealer.clearHand();
		index = deckSize;


		//get user input
		//hit or stay
		//if hit get card from deck
		
		//player.getCard(deal());
		//player.showHand()
		//if(player.tally() > 21)
		//bust and how to break?
		//




		//outcome block

		

	}
}