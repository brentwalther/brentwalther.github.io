/**
 * @(#)Card.java
 *
 *
 * @Brent Walther
 * @version 1.00 2011/1/25
 */


public class Card {
	
	public static final int 
		CLUBS = 0,
		DIAMONDS = 1,
		HEARTS = 2,
		SPADES = 3,
		JOKER = 4,
		JACK = 11,
		QUEEN = 12,
		KING = 13,
		ACE = 1;
		
	private final int value, suit;
		

    public Card(int value, int suit) {
    	this.value = value;
    	this.suit = suit;
    }
    
    public int getValue()
    {
    	return value;
    }
    public int getSuit()
    {
    	return suit;
    }
    public String toString()
    {
    	String ret = "";
    	if(suit == Card.JOKER)
    		return "Joker";
    	switch(suit)
    	{
    		case 0:
	    					ret += (char)5;
	    					break;
    		case 1:
	    					ret += (char)4;
	    					break;
    		case 2:
	    					ret += (char)3;
	    					break;
    		case 3:
	    					ret += (char)6;
	    					break;
    	}
	    switch(value)
	    {
	    	case 2:
	    	case 3:
	    	case 4:
	    	case 5:
	    	case 6:
	    	case 7:
	    	case 8:
	    	case 9:
	    	case 10:	ret += value;
	    				break;
	    	case 11:
	    				ret += "J";
	    				break;
	    	case 12:
	    				ret += "Q";
	    				break;
	    	case 13:
	    				ret += "K";
	    				break;
	    	case 1:
	    				ret += "A";
	    				break;
	    }   	
    	return ret;
    }
    
}