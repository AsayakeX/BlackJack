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
		//boolean dealerTurn = true;		

		initDeck();



//Keep playing until player quits orruns out of funds
		while(player.getMoney() > 0)
		{
			/*
			get the players bet
			also ask if want to keep playing

			*/
			shuffle();

			//initial 2 cards
			for(int i = 0; i < 2; i++)	
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

			//coducts the dealers turn after player is valid
			if(player.tally() <= 21)	
			{
				while(dealer.tally() < 17)
				{
					dealer.getCard(deal());	//card add				
					dealer.showHand(true);	//show hand

				}
			}


			//Results

			if(player.tally() > 21)
			{
				player.result(false);
				System.out.println("Player Busts!");
			}
			else if(dealer.tally(true) > 21)
			{
				player.result(true);
				System.out.println("Dealer Busts!");
			}
			else if(player.tally() < dealer.tally(true))
			{
				player.result(false);
				System.out.println("You Lose!");
			}
			else if(player.tally() > dealer.tally(true))
			{
				player.result(true);
				System.out.println("You Win!");
			}
			else
			{
				player.setBet(0);
				player.clearHand();
				System.out.println("Push!");
			}


			dealer.clearHand();
			index = deckSize;
		}

		

	}
}