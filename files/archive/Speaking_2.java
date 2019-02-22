/*
 *Brent Walther
 *02.11.10
 *Desc: Prints a table of dec 65-90 and conversions
 */
public class Speaking_2 {
	public static void main(String args[]) {
    	System.out.println("Decimal\tBinary\t\tOctal\tHex\tCharacter");
    	for(int i = 65; i < 4000; i++)//DO THE INTEGER LOOP YO
    		System.out.println(i+"\t"+toBase(i,2)+ //CONVERTS AND PRINTS YO
    			"\t\t"+toBase(i,8)+"\t"+toBase(i,16)+"\t"+(char)i);
    }
    
    public static String toBase(int input, int base) { //CONVERTS A DECIMAL NUMBER TO A DIFFERENT BASE YO
    	String output = "";
    	while (input > 0) {
    		if(input % base > 9)
    			output = (char)(input % base - 10 + 'A') + output;
    		else 
    			output = input % base + output; //TAKES THE REMAINDER AND ADDS IT TO THE BEGINNING YO
    		input /= base; 
    	}
    	return output;
    }
}