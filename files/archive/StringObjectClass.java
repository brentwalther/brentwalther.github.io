/*
 *Brent Walther
 *12.03.09
 *Desc: Test project for a new class extending class String
 */


public class StringObjectClass {
	
	public static void main(String args[])
	{
		str lol = new str("Testing");
		System.out.println(lol);
	}

    private class str extends String {
    }
    
    
}