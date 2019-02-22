/*
 *Brent Walther
 *11.18.09
 *Desc: Adds the pattern
 */


public class AddPattern {

    public static void main(String args[]) {
    	
    	int a=1, b=2;
    	long sum = 0;
    
    	for(; b <= 20; b++) {
    		
    		sum += (long)Math.pow(a,b);
    		System.out.println(a+"^"+b+" = "+(long)Math.pow(a,b));
    		a = b;
    	}
    	System.out.println(sum);
    }
    
    
}