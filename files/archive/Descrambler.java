/**
 * @Descrambler.java
 *
 * @Prints all possible combinations of the entered letters
 * @Brent Walther
 * @version 1.00 2010/11/29
 */
import java.util.Scanner;
import java.io.File;

public class Descrambler {
	
	static String[] words;
	static File wordList;

    public static void main(String args[]) {
    	Scanner input = new Scanner(System.in);
    	
    	
    	Descrambler ds = new Descrambler("dictionaries\\web2.txt");
    	
    	while(true) {
    		System.out.print("Please enter scrambled letters (or nothing to exit): ");
    			String scrambled = input.nextLine().replaceAll(" ","");
    		
    		if(scrambled.equals(""))
    			break;
    		ds.solve(scrambled, "");
    	}
    }
    
    public Descrambler(String dict) {
    	System.out.println("Generating word list. Please wait... ");
    	
    	try {
    		wordList = new File(dict);
    		Scanner reader = new Scanner(wordList);
    		
    		// Populate the words list
    		int length = 0;
    		while(reader.hasNextLine())
    		{
    			length++;
    			reader.nextLine();
    		}
    		
    		reader = new Scanner(wordList);
    		words = new String[length]; //This size is equal to amount of words (the amount is known)    		
    		for(int index = 0; reader.hasNextLine(); index++)
    		{
    			words[index] = reader.nextLine(); 
    		}
    			
    		System.out.println("Word list generation complete. "+words.length+" words.");
    	}
    	catch (Exception e) {
    		System.out.println("Could not generate words list: "+e);
    		System.exit(0);
    	}
    }
    
    /**
     * Checks every possible combination of the given letters
     * against the dictionary and prints the valid words.
     *
     * @param letters
     *			The letters (in any order) to check
     * @param check
     *			When calling the function, check is always null.
     *			The function uses this to piece together a 
     *			possible word to check.
     */    
    public static void solve(String letters, String check) {
    	if(letters.equals("")) {
    		if(isValidWord(check))
    			System.out.println(check);
    	}
    	else {
    		for(int i = 0; i < letters.length(); i++)
    			solve(letters.substring(0,i) + letters.substring(i+1),check+letters.charAt(i));
    	}
    }
    
    /**
     * Method checks to see whether or not the given word is
     * a valid word in the dictionary. Uses binary search
     * because the dictioanry is assumed to be in alphabetical
     * order. Iterates a maximum of log2(wordList.length)
     *
     * @param word
     *			the word to check
     * @return wether or not the word is in the dictionary
     */
    public static boolean isValidWord(String word) {
    	boolean isValid = false;
    	int start = 0, end = words.length, current = words.length/2;
    	while(start <= end) {
    		int compare = words[current].compareTo(word);
    		if(compare == 0) {
    			isValid = true;
    			break;
    		}
    		if(compare > 0) {
    			end = current-1;
    		}
    		else {
    			start = current+1;
    		}
    		current = start + (end-start)/2;
    	}
    	return isValid;
    }
    
}