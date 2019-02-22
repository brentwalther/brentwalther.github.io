/*
 *Brent Walther
 *11.16.09
 *Description: Grades and counts incorrect and correct.
 */

import java.util.Scanner;
public class Grader {

    public static void main(String args[]) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	int correct = 0; // Counter variable to keep up with correct answers
    	char answer; // temporarily stores the user's answer
    	String result = "";  //storage for the result
    	
//-- QUESTION ONE --------------------
    	System.out.println("what is the answer to this question?\n\nA. the answer\nB. this answer\nC. this question\nD. 9-11-2001");
    		answer = input.next().charAt(0);
    		
    	
    	switch(answer) {	//Checks for the correct answer
    		case 'D':
    		case 'd':	System.out.println("You are correct.");
    					correct++;
    					break;
    		
    		default:	System.out.println("WRONG. YOU ARE WRONG!\n");
    				
    	}
    	
//-- QUESTION TWO --------------------
    	System.out.println("we live in texas.\n\nA. yeah?\nB. no!\nC. boots\nD. howdy partner");
    		answer = input.next().charAt(0);
    		
    	
    	switch(answer) {	//Checks for the correct answer
    		case 'D':
    		case 'd':	System.out.println("You are correct.");
    					correct++;
    					break;
    		
    		default:	System.out.println("Idiot.\n");
    				
    	}
    	
//-- QUESTION THREE --------------------
    	System.out.println("can you teach Chuck Norris how to jerk\n\nA. sure, if he won't punch me\nB. no, i don't know how\nC. you can't teach Chuck Norris\nD. maybe");
    		answer = input.next().charAt(0);
    		
    	
    	switch(answer) {	//Checks for the correct answer
    		case 'C':
    		case 'c':	System.out.println("You are correct.");
    					correct++;
    					break;
    		
    		default:	System.out.println("Wow.\n");
    				
    	}
    	
//-- QUESTION FOUR --------------------
    	System.out.println("What is your dogs name?\n\nA. Chuck Norris\nB. Fido\nC. Spike\nD. Reginald");
    		answer = input.next().charAt(0);
    		
    	
    	switch(answer) {	//Checks for the correct answer
    		case 'A':
    		case 'a':	System.out.println("You are correct.");
    					correct++;
    					break;
    		
    		default:	System.out.println("lol?\n");
    		
    }
    
//------------------------- OUTPUTS GRADE ----------------------------
    switch (correct*25) {
    	
    	case 100:  result=" are impressive. You got a "+correct*25;
    		break;
    		
    	case 75:  result=" performed average. You got a "+correct*25;
    		break;
    		
    	case 50:  result=" didn't do well. You got a "+correct*25;
    		break;
    		
    	case 25:  result=" suck. You got a "+correct*25;
    		break; 
    			   	
    	case 0:  result="r brain is nonexistant. You got a "+correct*25;
    		break;

    }
    System.out.println("You"+result);
    
}
}