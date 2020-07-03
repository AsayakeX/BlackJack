import java.util.*;

public class Person	//See if we cant get this to be an abstract class
{
	private int funds;
	private final int initFunds = 500;

//Constructors
	public Person(){
		funds = initFunds;
	}

	public Person(int f){
		funds = f;
	}

//Functions
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
}