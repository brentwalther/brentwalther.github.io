/*
 *Brent Walther
 *02.11.10
 *Desc: Prints a table of dec 65-90 and conversions
 */
public class Speaking {
	public static void main(String args[]) {
    	System.out.println("Decimal\tBinary\t\tOctal\tHex\tCharacter");
    	for(int i = 65; i < 91; i++)//DO THE INTEGER LOOP YO
    		System.out.println(i+"\t"+toBinary(i)+ //CONVERTS AND PRINTS YO
    			"\t\t"+toOctal(i)+"\t"+toHex(i)+"\t"+(char)i);
    }
}