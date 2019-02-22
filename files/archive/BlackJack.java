/**
 * @(#)BlackJack.java
 *
 *
 * @author Brent Walther
 * @version 1.00 2011/1/28
 */
 import java.util.Scanner;

public class BlackJack {

    public static void main(String args[])
    {
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Welcome to blackjack! You start out with $5000");
    	int money = 5000, bet = 0;
    	
    	Deck deck1 = new Deck();
    	
    	do
    	{
    		deck1.shuffle();
    		while(true)
    		{
    			System.out.print("Place your bet: ");
    			bet = Integer.parseInt(input.nextLine());
    			if(bet <= money/2)
    				break;
    			System.out.println("Invalid.");
    		}
    		Card[] dealer = new Card[10], ph1 = new Card[10], ph2 = new Card[10];
    		ph1index = 0, dIndex = 0;
    		
    		dealer[dIndex++] = deck1.nextCard();
    		ph1[ph1index++] = deck1.nextCard();
    		dealer[dIndex++] = deck1.nextCard();
    		ph1[ph1index++] = deck1.nextCard();
    		if(ph1[0].getValue() == ph1[1].getValue())
    		{
    			System.out.println("You were dealt two "+ph1[0].getValue()+"s. Would you like to split? (y/n)");
    			if(input.nextLine().equalsIgnoreCase("y"))
    			{
    				ph2[0] = ph1[1];
    				ph1[1] = deck1.nextCard();
    				ph2[1] = deck1.nextCard();
    			}
    		}
    		if(ph1[0].getValue() == 1 && ph1[1].getValue() >= 10 || ph1[1].getValue() == 1 && ph1[0].getValue() >= 10)
    		{
    			System.out.println("You got a blackjack! Congratulations!");
    			money += bet;
    			continue;
    		}
    		else if(dealer[0].getValue() == 1 && dealer[1].getValue() >= 10 || dealer[1].getValue() == 1 && dealer[0].getValue() >= 10)
    		{
    			System.out.println("The dealer got a blackjack! You lost!");
    			money -= bet;
    			continue;
    		}
    		
    		System.out.println("Your got dealt: " + ph1[0] + " " + ph1[1]);
    		
    		do{
    			System.out.println("Please choose an option: \n1) Hit\n2 Stay");
    			int choice = Integer.parseInt(input.nextLine());
    		
    			if(choice == 1)
    			{
    				Card dealtCard = deck1.nextCard();
    				System.out.println("You were dealt a "+dealtCard);
    				ph1[ph1index++] = dealtCard;
    				
    			}
    		}while(choice != 2);
    	}while(money != 0);
    }
    public static int sizeOfHand(Card[] a)
    {
    	
    }
    
    
}