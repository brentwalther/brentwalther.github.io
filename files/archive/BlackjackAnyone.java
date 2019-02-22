/*
 *Brent Walther
 *12.09.09
 *Desc: Blackjack anyone challenge on cstutoringcenter.com
 */

import java.util.Scanner;
import java.io.*;
public class BlackjackAnyone {

    public static void main(String args[]) throws FileNotFoundException {
    	Scanner input = new Scanner(new File("blackjack.txt"));
    	
    	while(input.hasNext()) {
    		System.out.println(input.nextLine());
    	}
    }
    
    
}