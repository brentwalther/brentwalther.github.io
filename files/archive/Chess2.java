/**
 * @(#)Chess2.java
 *
 *
 * @author Brent Walther
 * @version 1.00 2011/1/25
 */
import java.util.Scanner;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Chess2 {

    public static void main(String args[]) throws IOException
    {
    	Scanner input = new Scanner(new File("chess.in"));    	
    	PrintStream writer = new PrintStream(new FileOutputStream(new File("chess.out")));
    	
    	int iterations = input.nextInt();
    	input.nextLine();
    	while(iterations-- > 0)
    	{
    		Piece.globalBoard = new boolean[17][17];
    		int pieces = input.nextInt();
    		Piece list[] = new Piece[pieces];
    		
    		//Files up piece list
    		for(int i = 0; i < pieces; i++)
    			list[i] = new Piece(input.next().charAt(0),new Point(input.nextInt(),input.nextInt()));
    			
    		//System.out.println(Piece.board());
    		for(Piece p : list)
    		{
    			p.fill();
    			//System.out.println(p.moves());
    		}
    			
    		//Counts number of threatend pieces
    		int threats = 0;
    		for(Piece p : list)
    		{
    			if(p.isThreatened(list))
    				threats++;
    		}
    		
    		writer.println(threats);
    	}
    }
    
    static class Piece {
    	static boolean[][] globalBoard;
    	boolean[][] legalMoves;
    	private Point location;
    	private char type;
    	
    	public Piece(char type, Point location)
    	{
    		legalMoves = new boolean[17][17]; //the 0th column and row will remain unused for easier coding
    		this.location = location; //Set the location of this piece
    		globalBoard[location.y][location.x] = true;
    		this.type = type;
    	}
    	public void fill()
    	{
    		switch(type)
    		{
    			case 'K': fillMoves(1);break;//King
    			case 'Q': fillMoves(2);break;//Queen
    			case 'R': fillMoves(3);break;//Rook
    			case 'B': fillMoves(4);break;//Bishop
    			case 'N': fillMoves(5);break;//Knight
    			case 'S': fillMoves(6);break;//Nightrider
    			case 'A': fillMoves(7);break;//Archbishop
    			case 'E': fillMoves(8);break;//Kraken
    		}
    	}
    	
    	public boolean isThreatened(Piece[] list)
    	{
    		for(Piece p : list)
    			if(p != this && p.legalMoves[this.location.y][this.location.x])
    				return true;    				
    		return false;
    	}
    	
    	private void fillMoves(int type)
    	{
    		//King 1
    		//Queen 2
    		//Rook 3
    	    //Bishop 4
    	    //Knight 5
    	    //Nightrider 6
    	    //Archbishop 7
    	    //Kraken 8
    	    
    		/*
    		 *
    		 * King
    		 *
    		 */
    		if(type == 1)
    		{
    			for(int x = location.x-1; x <= location.x+1; x++)
    				for(int y = location.y-1; y <= location.y+1; y++)
    				{
    					try
    					{
    						legalMoves[x][y] = true;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//Not a legal move. Disregard.
    					}
    				}
    		}
    		
    	    /*
    		 *
    		 * Queen or Bishop or Archbishop
    		 *
    		 */
    	    if(type == 2 || type == 4 || type == 7)
    	    {
    	    	int x = location.x, y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x--][y--] = true;
    						if(globalBoard[x+1][y+1] && location.x != x+1 && location.y != y+1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x++][y--] = true;
    						if(globalBoard[x-1][y+1] && location.x != x-1 && location.y != y+1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x--][y++] = true;
    						if(globalBoard[x+1][y-1] && location.x != x+1 && location.y != y-1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x++][y++] = true;
    						if(globalBoard[x-1][y-1] && location.x != x-1 && location.y != y-1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    }
    	    
    	    /*
    		 *
    		 * Queen or Rook
    		 *
    		 */
    	    if(type == 3 || type == 2)
    	    {
    	    	int x = location.x, y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x][y--] = true;
    						if(globalBoard[x][y+1] && location.y != y+1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x][y++] = true;
    						if(globalBoard[x][y-1]&& location.y != y-1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x--][y] = true;
    						if(globalBoard[x+1][y] && location.x != x+1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x++][y] = true;
    						if(globalBoard[x-1][y] && location.x != x-1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    	    	}
    	    }
    	    /*
    		 *
    		 * Knight or Archbishop
    		 *
    		 */
    	    if(type == 5 || type == 7)
    	    {
    	    	int x = location.x, y = location.y;
    	    	try {legalMoves[x-2][y-1] = true;}catch(ArrayIndexOutOfBoundsException e){} //Disregard these exceptions.
    	    	try {legalMoves[x-2][y+1] = true;}catch(ArrayIndexOutOfBoundsException e){} //They simply mean the spot is invalid
    	    	try {legalMoves[x+2][y-1] = true;}catch(ArrayIndexOutOfBoundsException e){}
    	    	try {legalMoves[x+2][y+1] = true;}catch(ArrayIndexOutOfBoundsException e){}
    	    	
    	    	try {legalMoves[x-1][y-2] = true;}catch(ArrayIndexOutOfBoundsException e){}
    	    	try {legalMoves[x+1][y-2] = true;}catch(ArrayIndexOutOfBoundsException e){}
    	    	try {legalMoves[x-1][y+2] = true;}catch(ArrayIndexOutOfBoundsException e){}
    	    	try {legalMoves[x+1][y+2] = true;}catch(ArrayIndexOutOfBoundsException e){}
    	    }
    	    
    	    /*
    	     *
    	     * Nightrider
    	     *
    	     */
    	     if(type == 6)
    	     {
    	     	int x = location.x, y = location.y, step1 = 0, step2 = 0;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x+step1][y+step2] = true;
    						if(globalBoard[x+step1][y+step2] && location.x != x+step1 && location.y != y+step2)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				try
    					{
    						legalMoves[x+step2][y+step1] = true;
    						if(globalBoard[x+step2][y+step1] && location.x != x+step2 && location.y != y+step1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				step1-=2;
    				step2-=1;
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	step1 = 0;
    	    	step2 = 0;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x+step1][y+step2] = true;
    						if(globalBoard[x+step1][y+step2] && location.x != x+step1 && location.y != y+step2)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				try
    					{
    						legalMoves[x+step2][y+step1] = true;
    						if(globalBoard[x+step2][y+step1] && location.x != x+step2 && location.y != y+step1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				step1-=2;
    				step2+=1;
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	step1 = 0;
    	    	step2 = 0;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x+step1][y+step2] = true;
    						if(globalBoard[x+step1][y+step2] && location.x != x+step1 && location.y != y+step2)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				try
    					{
    						legalMoves[x+step2][y+step1] = true;
    						if(globalBoard[x+step2][y+step1] && location.x != x+step2 && location.y != y+step1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				step1+=2;
    				step2-=1;
    	    	}
    	    	x = location.x;
    	    	y = location.y;
    	    	step1 = 0;
    	    	step2 = 0;
    	    	while(true)
    	    	{
    	    		try
    					{
    						legalMoves[x+step1][y+step2] = true;
    						if(globalBoard[x+step1][y+step2] && location.x != x+step1 && location.y != y+step2)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				try
    					{
    						legalMoves[x+step2][y+step1] = true;
    						if(globalBoard[x+step2][y+step1] && location.x != x+step2 && location.y != y+step1)
    							break;
    					}
    					catch(ArrayIndexOutOfBoundsException e)
    					{
    						//done with this direction.
    						break;
    					}
    				step1+=2;
    				step2+=1;
    	    	}
    	     }
    	    
    	    /*
    	     *
    	     * Kraken
    	     *
    	     */
    	     if(type == 8)
    	     {
    	     	for(int y = 1; y < 17; y++)
    	     		for(int x = 1; x < 17; x++)
    	     			legalMoves[x][y] = true;
    	     }
    	}
    	public String moves()
    	{
    		String ret = "";
    		for(int y = 1; y < 17; y++)
    		{
    			for(int x = 1; x < 17; x++)
    				ret += (legalMoves[y][x]?"X":"O");
    			ret += "\n";
    		}
    		return ret;	
    	}
    	public static String board()
    	{
    		String ret = "";
    		for(int y = 1; y < 17; y++)
    		{
    			for(int x = 1; x < 17; x++)
    				ret += (globalBoard[y][x]?"X":"O");
    			ret += "\n";
    		}
    		return ret;	
    	}
    }
    
    
}