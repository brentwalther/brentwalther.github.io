/**
 * @(#)Xyl.java
 *
 *
 * @author 
 * @version 1.00 2011/3/7
 */


public class Xyl {
    int y;
    static int L;
    	
    public Xyl(int y) {
    	this.y = y;
   	}
    
    public static int getL() {
    	return L;
    }
    
    public int product() {
    	return y*getL();
    }
}