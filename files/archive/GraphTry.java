import java.util.Scanner;

public class GraphTry
{
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Function: ");
		String eq = input.next();
		
		double yMin = -20, yMax = 20, yStep = (yMax-yMin)/24, xMin = -20, xMax = 20, xStep = (xMax-xMin)/80;		
		
		for (double y=yMax;y>=yMin;y-=yStep) // lines (y)
		{
			for(double x=xMin;x<xMax;x+=xStep) // (x), the actual going across
			{				
				double func = Evaluate.eval(eq,x); // function evaluated				
				
				if(func <= y+yStep && func >= y)
					System.out.print("X");
				else if(0 <= y+yStep && 0 >= y)
					System.out.print("-");
				else if(0 <= x+xStep && 0 >= x)
					System.out.print("|");
				else
					System.out.print(" ");					
					
			}
			System.out.println();
		}		
	}
}