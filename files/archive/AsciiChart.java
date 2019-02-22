/*
 *Brent Walther
 *1.23.2010
 *Desc:
 */

public class AsciiChart {
        
    public static void main(String[] args) {
        for(int i = 1; i < 256; i++) {
        	System.out.print((char)i + " " + i + "\t");
        	if(i%9 == 0)
        		System.out.print("\n");
        }
    }
}
