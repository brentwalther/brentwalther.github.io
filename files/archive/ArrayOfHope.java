/**
 * @(#)ArrayOfHope.java
 *
 *
 * @author 
 * @version 1.00 2011/2/24
 */


public class ArrayOfHope {

    public static void main (String args[]) {
    	char ch[] = new char[26];
    	for(int i = 0; i < ch.length; i++)
    		ch[i] = (char)('A'+i);
    	for(int i = 0; i < ch.length; i++)
    		System.out.print(ch[i]+(i<25?", ":""));
    }
    
    
}