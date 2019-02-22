/**
 * @(#)Solitaire.java
 *
 * Solitaire application
 *
 * @author 
 * @version 1.00 2011/1/25
 */
 
public class Solitaire {
    
    public static void main(String[] args) {
    	
    	Deck _deck = new Deck(true);
    	_deck.shuffle();
    	while(_deck.hasNextCard())
    		System.out.print("["+_deck.nextCard()+"] ");
    }
}
