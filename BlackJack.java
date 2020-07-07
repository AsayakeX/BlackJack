import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class BlackJack
{
	boolean game = true;

	int bet = 0;
	String bust = "n";	//p/d/n

	Person player = new Person();
	Person dealer = new Person();

	Deck deck = new Deck();

	//===============================
	//	Game Functions 
	//===============================
	public static void reset(){
		player.clearHand();
		dealer.clearHand();

		deck.shuffle();
		bet = 0;
	}

	//public void compareHands(Person player, int pHand, int dHand){
	public static void compareHands(Person p, Person d){
		if(p.getScore() == d.getScore())
			return;

		if(p.getScore() > d.getScore()){
			player.addFunds(bet);
		}
		else{
			player.subFunds(bet);
		}
	}

	public static void getBet(Scanner sc){
		boolean valid = false;
		int temp = 0;

		do{
			System.out.println("Place bet: ");
			temp = sc.nextInt();
			if(player.getFunds() >= bet){
				System.out.println("Bet accepted");
				bet = temp;
				valid = true;
			}
			else{
				System.out.println("Not enough funds");
			}
		}while(!valid);
	}

	public static void playerPhase(Scanner sc){
		boolean valid = false;
		String temp = "";

		do{
			System.out.println("Hit or Stand?\n(h/s): ");
			temp = sc.nextLine();

			switch(temp.toLowerCase()){
				case "h":
					player.getCard(deck.deal()); //get the card from the player
					player.tally(deck);	//update score
					if(player.getScore() > 21){	//check for bust
						bust = "p";
						valid = true;	//player phase ends
					}				
					break;
				case "s":
					valid = true;
					break;
				default:
					System.out.println("Invalid input.");
			}
		}while(!valid);
	}

	public static void dealerPhase(){
		while(dealer.getScore() < 17){	//Continue to get cards until over 17
			dealer.getCard(deck.deal());	//get card
			dealer.tally(deck);		//update the score
		}
				
		if(dealer.getScore() > 21){	//check if bust
			bust = "d";
		}			
	}

	public static void playAgn(Scanner sc){
		boolean valid = false;
		String temp = "";

		do{
			System.out.println("Play again? \n(y/n): ");
			temp = sc.nextLine();

			if(temp.toLowerCase() == "y" || temp.toLowerCase() == "n"){
				valid = true;
				if(temp.toLowerCase() == "n"){
					game = false;
				}
			}
			else{
				System.out.println("Invalid input.");
			}
		}while(!valid);
	}

	//===============================
	//	Main
	//===============================

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		do{
			reset();
			for(int i = 0; i < 0; i++){
				player.getCard(deck.deal());
				dealer.getCard(deck.deal());
			}
			
			getBet(sc);		//get valid bet from user
			playerPhase(sc);	//player hit or stay

			if(bust != "p"){	//if the player didnt bust
				dealerPhase();	//calculate the dealer

				if(bust != "d"){	//if the dealer didnt bust
					compareHands(player, dealer);	//calculate the real score
				}
				else{
					player.addFunds(bet);				//dealer busted add to player funds
				}
			}
			else{	//if the player busted
				player.subFunds(bet);			//deduct from player funds
			}
			playAgn();	//ask user if want to play again

		}while(game);

		

		/*
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


			while(!validInput);
		}
		while(game);
		*/
	}
}