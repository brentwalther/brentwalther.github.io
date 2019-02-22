/**
 * @(#)EndlessLoop.java
 *
 *
 * @author 
 * @version 1.00 2009/11/16
 */


public class EndlessLoop {

    public static void main(String args[]) {
    	for(int i=1;i>0;i+=3) {
    			System.out.print((char)200);
    			for(int p=0;p<1000000;p++);
    	}
    }
    
    
}