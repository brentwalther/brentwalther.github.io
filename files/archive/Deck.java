/**
 * @(#)Deck.java
 *
 *
 * @Brent Walther
 * @version 1.00 2011/1/25
 */


public class Deck {
	
	private Card deck[];
	private int cardsUsed;

    public Deck() {
    	this(false);
    }
    
    public Deck(boolean useJokers)
    {
    	if(useJokers)
    	{
    		deck = new Card[54];
    		deck[52] = new Card(14,Card.JOKER);
    		deck[53] = new Card(14,Card.JOKER);
    	}
    	else
    		deck = new Card[52];
    	int deckPos = 0;
    	for(int s = 0; s < 4; s++)
    		for(int c = 1; c <= 13; c++)
    			deck[deckPos++] = new Card(c,s);
    }
    
	public void shuffle() {
		for ( int i = deck.length-1; i > 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}
    
    public boolean hasNextCard()
    {
    	return cardsUsed != deck.length-1;
    }
    public int cardsLeft()
    {
    	return deck.length - cardsUsed;
    }
    
    public Card nextCard()
    {
    	if(hasNextCard())
    		return deck[cardsUsed++];
    	else
    		throw new IllegalStateException("No cards left!");
    }
}