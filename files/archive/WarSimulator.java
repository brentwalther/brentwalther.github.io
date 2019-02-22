/**
 * @(#)WarSimulator.java
 *
 *	Simulates a game of the popular card game "War"
 *
 * @Brent Walther
 * @version 1.00 2011/1/25
 */
import java.util.Scanner;
import java.util.ArrayList;

public class WarSimulator {

    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
    	
    	//Gets the amount of players (2-9)
    	int players;
    	while(true)
    	{
    		System.out.println("How many players? (2-9)");
    		players = input.nextInt();
    		if(players > 9 || players < 2)
    			System.out.println("That wouldn't be a very fun game! Hint: try (2-9) players");
    		else
    			break;
    	}
    	input.nextLine(); //Clear scanner buffer
    	
    	//Asks the user whether or not the would like to include jokers
    	boolean playWithJokers = false;
    	System.out.println("Would you like to play with jokers? (y/n)");
    	if(input.nextLine().equalsIgnoreCase("y"))
    		playWithJokers = true;
    	
    	//Setup the decks
    	Deck _deck = new Deck(playWithJokers);
    	ArrayList playerDeck[] = new ArrayList[players];
    	for(int i = 0; i < playerDeck.length; i++)
    		playerDeck[i] = new ArrayList();
    	
    	//Deal the cards into the player's decks
    	//The dealing is not always even, but it is close. For the purpose of this game, it is sufficient
    	int cardCount = 0;
    	_deck.shuffle();
    	while(_deck.hasNextCard())
    		playerDeck[cardCount++ % players].add(_deck.nextCard());
    		
    	//These variables will keep statistics for the end
    	int wins[] = new int[players], totalBattles = 1;
    	
    	//This loop will continue to iterate until we determine one player has all of the cards. At that point, we will break.
    	ArrayList<Card> table;
    	while(true)
    	{
    		//Take the top card off each deck and add to the table in order
    		table = new ArrayList<Card>();
    		for(ArrayList a : playerDeck)
    			table.add((Card)a.remove(0));
    				
    		//Compare the cards and get the index of the greatest card
    		int greatest = 0;
    		for(int c = 0; c < table.size(); c++)
    			if(table.get(c).getCard() > table.get(greatest).getCard())
    				greatest = c;
    				
    		//Determine if the highest card won or if it tied
    		String tied = ""+greatest;
    		for(int c = 0; c < table.size(); c++)
    			if(table.get(c).getCard() == table.get(greatest).getCard() && c != greatest)
    				tied += c;
    		
    		//If the length of tied is greather than one, a tie occured
    		//The length of tie is the number of players who had the greatest card.
    		if(tied.length() > 1)
    		{
    			System.out.println(tied+" tied on turn "+totalBattles+"!");
    			//Play by the rules of each player puts three cards on the table, and the fourth is the playable tie-breaker
    			for(int c = 0; c < 4; c++)
    				for(int p = 0; p < tied.length(); p++)
    					table.add((Card)playerDeck[tied.charAt(p)-48].remove(0));
    						
    			int winner = table.size()-1;
    			for(int i = table.size()-1; i >= table.size()-tied.length(); i--)
    				if(table.get(i).getCard() > table.get(winner).getCard())
    					winner = i;
    			//Subtract all the misc cards to get the index of the winner.
    			greatest = winner - (table.size()-tied.length());
    		}
    		//Add all the cards to the winner's deck
    		playerDeck[greatest].addAll(table);
    		
    		//Check to see if anybody is out
    		String playersToRemove = "";
    		for(int i = 0; i < playerDeck.length; i++)
    			if(playerDeck[i].size() == 0)
    				playersToRemove += i;
    		
    		//If the list is populated, remove those players from the array of players.
    		if(playersToRemove.length() > 0)
    		{
    			ArrayList temp[] = new ArrayList[playerDeck.length-playersToRemove.length()];
    			int index = 0;
    			for(int i = 0; i < playerDeck.length; i++)
    				if(playerDeck[i].size() == 0)
    					System.out.println("Player "+i+" out of the game on turn "+totalBattles);
    				else
    					temp[index++] = playerDeck[i];
    			playerDeck = temp;
    		}
    		if(playerDeck.length == 1)
    		{
    			System.out.println("Winner!");
    			break;
    		}
    		
    		//Update statistic variables
    		wins[greatest]++;
    		totalBattles++;	    			
    	}
    	
    }
}