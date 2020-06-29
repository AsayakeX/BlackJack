import java.util.*;

public class Person	//See if we cant get this to be an abstract class
{
	private funds;
	private final initFunds = 500

	public Person(){
		funds = initFunds;
	}

	public Person(int f){
		funds = f;
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
	
}