import java.util.*;
import java.util.ArrayList;

public class Person	//See if we cant get this to be an abstract class
{
	private final int initFunds = 500;
	
	private ArrayList<Integer> hand;
	private int funds;
	private int score;

//Constructors
	public Person(){
		hand = new ArrayList<>();
		funds = initFunds;
		score = 0;
	}

	public Person(int f){
		hand = new ArrayList<>();
		funds = f;
		score = 0;
	}

//Functions
	public void clearHand(){
		hand.clear();
		score = 0;
	}

	public void getCard(int c){
		hand.add(c);
	}

	public int getFunds(){
		return funds;
	}

	public String showFunds(){
		return Integer.toStirng(funds);
	}

	public void addFunds(int n){
		this.funds += n;
	}

	public void subFunds(int n){
		this.funds -= n;
	}

	public void tally(Deck deck){	//wrong, currently only has references to pos
		for(int i: hand){
			score += deck.getVal(i);	//testing
		}
	}

	public void printHand(Deck deck){	//testing
		for(int i: hand){
			System.out.println(deck.printCard(i));
		}
	}

	public int getScore(){
		return score;
	}

	public String showScore(){
		return Integer.toString(score);
	}




}