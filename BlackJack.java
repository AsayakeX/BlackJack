import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class BlackJack
{
	//===============================
	//	Game Functions 
	//===============================
	public static void reset(Person p, Person d, Deck deck){
		p.clearHand();
		d.clearHand();
		deck.shuffle();
	}

	public static void showHandD(Person d, Deck deck){
		System.out.println("\nDealer hand:");
		d.printHand(deck);
		System.out.println("\nDealer score:" + d.showScore());
	}

	public static void showHandP(Person p, Deck deck){
		System.out.println("\nYour hand:");
		p.printHand(deck);
		System.out.println("\nYour score:" + p.showScore());
	}

	public static void showHands(Person d, Person p, Deck deck){
		showHandD(d, deck);
		showHandP(p, deck);
	}

	public static void compareHands(Person p, Person d, int bet){
		if(p.getScore() == d.getScore()){
			System.out.println("\nPush");
			return;
		}

		if(p.getScore() > d.getScore()){
			System.out.println("\nYou win");
			p.addFunds(bet);
		}
		else{
			System.out.println("\nYou lose");
			p.subFunds(bet);
		}
	}

	public static int getBet(Scanner sc, Person p){//, int bet){
		boolean valid = false;
		int temp = 0;

		do{
			System.out.println("\nAvailable Funds: " + p.showFunds());
			System.out.print("\nPlace bet: ");

			temp = Integer.parseInt(sc.nextLine());
			if(temp < 0){
				System.out.println("\nInvalid Input");
			}
			else{
				if(p.getFunds() >= temp){
					System.out.println("\nBet accepted");
					valid = true;
				}
				else{
					System.out.println("\nNot enough funds");
				}
			}
		}while(!valid);
		return temp;
	}

	public static String playerPhase(Scanner sc, Person d, Person p, Deck deck){
		boolean valid = false;
		String temp = "";
		String bust = "";

		do{
			System.out.print("\nHit or Stand?\n(h/s): ");
			temp = sc.nextLine();

			switch(temp.toLowerCase()){
				case "h":
					p.getCard(deck.deal(), deck); //get the card from the player
					showHands(d, p, deck);	//show hands
					if(p.getScore() > 21){	//check for bust
						bust = "p";
						valid = true;	//player phase ends
					}				
					break;
				case "s":
					valid = true;
					break;
				default:
					System.out.println("\nInvalid input.");
			}
		}while(!valid);
		return bust;
	}

	public static String dealerPhase(Person d, Person p, Deck deck){
		String bust = "";

		while(d.getScore() < 17){	//Continue to get cards until over 17
			d.getCard(deck.deal(), deck);	//get card
			showHands(d, p, deck);	//show hands
		}
				
		if(d.getScore() > 21){	//check if bust
			bust = "d";
		}

		return bust;
	}

	public static Boolean playAgn(Scanner sc, Person p){
		boolean valid = false;
		Boolean game = true;
		String temp = "";

		if(p.getFunds() <= 0){
			System.out.println("\nYou have no funds left!");
			game = false;
		}
		else{
			do{
				System.out.print("\nPlay again?\n(y/n): ");
				temp = sc.nextLine();

				if(temp.equalsIgnoreCase("y")  || temp.equalsIgnoreCase("n")){
					valid = true;
					if(temp.equalsIgnoreCase("y")){
						System.out.println("\nSetting up next game");
						game = true;
					}
					else{
						System.out.println("\nExiting game");
						game = false;
					}
				}
				else{
					System.out.println("\nInvalid input.");
				}
			}while(!valid);
		}
		return game;
	}

	//===============================
	//	Main
	//===============================

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		Person player = new Person();
		Person dealer = new Person();
		
		boolean game = true;
		String bust = "n";	//p/d/n
		int bet = 0;

		Deck deck = new Deck();
		
		deck.buildCD();

		do{
			reset(player, dealer, deck);
			bet = getBet(sc, player);		//get valid bet from user

			for(int i = 0; i < 2; i++){		//Pass initial cards
				player.getCard(deck.deal(), deck);
				dealer.getCard(deck.deal(), deck);
			}

			showHands(dealer, player, deck);
			
			bust = playerPhase(sc, dealer, player, deck);	//player hit or stay

			if(!bust.equalsIgnoreCase("p")){	//if the player didnt bust
				bust = dealerPhase(dealer, player, deck);	//calculate the dealer

				if(!bust.equalsIgnoreCase("d")){	//if the dealer didnt bust
					compareHands(player, dealer, bet);	//calculate the real score
				}
				else{
					System.out.println("\nYou win");
					player.addFunds(bet);				//dealer busted add to player funds
				}
			}
			else{	//if the player busted
				System.out.println("\nYou lose");
				player.subFunds(bet);			//deduct from player funds
			}
			game = playAgn(sc, player);	//ask user if want to play again

		}while(game);

		
	}
}