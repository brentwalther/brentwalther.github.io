/**
 * @(#)BigBucks.java
 *
 *
 * @author Brent Walther
 * @version 1.00 2011/4/11
 */
import java.io.*;
import java.util.*;

public class BigBucks {

    public static void main(String args[]) {
    	String name;
    	ArrayList<BankAccount> aryList = new ArrayList<BankAccount>();
    	do {
    		Scanner kbReader = new Scanner(System.in);
    		System.out.print("Please enter the name to whom the account belongs. (\"Exit\") to abort)");
    		
    		name = kbReader.nextLine();
    		if(!name.equalsIgnoreCase("EXIT")) {
    			System.out.print("Please enter the amount of the deposit. ");
    			double amount = kbReader.nextDouble();
    			System.out.println("");
    			aryList.add(new BankAccount(name, amount));
    		}
    	} while(!name.equalsIgnoreCase("EXIT"));
    	
    	BankAccount ba = aryList.get(0);
    	double maxBalance = ba.balance;
    	String maxName = ba.name;
    	for(int i = 1; i < aryList.size(); i++) {
    		if(aryList.get(i).balance > maxBalance) {
    			maxBalance = aryList.get(i).balance;
    			maxName = aryList.get(i).name;
    		}
    	}
    	System.out.printf("The account with the alrgest balance belongs to "+maxName+".\nThe account contains $%.2f \n",maxBalance);
    }
    
    
}