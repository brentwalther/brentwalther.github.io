/*
 *Brent Walther
 *Date: 04.14.10
 *Desc: A class that encrypts/decrypts strings.
 */

public class Crypto {

    public Crypto() {
    }
    
    public String encrypt(String original) //Encrypts a String
    {
    	/*REPLACEMENTS:
    	 *v V = ag',r
    	 *m M = ssad
    	 *g G = jeb..w
    	 *b B = dug>?/
    	 */
    	return original.replaceAll("[mM]","ssad")
    		.replaceAll("[gG]","je@..w")
    			.replaceAll("[vV]","ag',r")
    				.replaceAll("[bB]","dug>?/")
    					.replace('@','b');
    }
    
    public String decrypt(String crypted) // Decrypts an encrypted String
    {
    	/*REPLACEMENTS:
    	 *ag',r  = v
    	 *ssad   = m
    	 *jeb..w = g
    	 *dug>?/ = b
    	 */
    	return crypted.replaceAll("ssad","m")
    		.replaceAll("ag',r","v")
    			.replaceAll("jeb..w","g")
    				.replaceAll("dug>\\?/","b");
    }
    
    
}