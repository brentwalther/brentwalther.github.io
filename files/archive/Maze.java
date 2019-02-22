/*
 *Brent Walther
 *02.09.10
 *Desc: CSCI UIL Regionals 2005 - Problem 8
 */

import java.util.Scanner;
import java.io.*;
import java.awt.*;

public class Maze {

    public static void main(String args[]) throws IOException {
    	Scanner input = new Scanner(new File("./Java UIL Material/Regionals/2006/reg_judgedata_06/Judges' Test Data/maze.in"));
    	
		int sets = input.nextInt(), width, height;
    	String set = input.nextLine(), currentLine = "", temp = "";
    	char[][] maze = new char[50][50];
    	Point[][] solution = new Point[4][100];
    	Point start = new Point(0,0), end = new Point(0,0);
    	boolean found;
    	
    	for(int ds = 0; ds < 1; ds++) {
    		
    	//RESET THE SOLUTION ARRAY
    		for(int d = 0; d < 4; d++)
    			for(int e = 0; e < 100; e++)
    				solution[d][e] = new Point(-1,-1);
    				
    	//START -> DATA PARSE
    		width = input.nextInt();
    		height = input.nextInt();
    		temp = input.nextLine();
    		
    		
    		
    		for(int a = 0; a < height; a++)
    			maze[a] = input.nextLine().toCharArray();
    	//END -> DATA PARSE
    	
    	found = false;
    	for(int b = 0; b < height; b++) {
    		for(int c = 0; c < width; c++) {
    			System.out.print(maze[b][c]);
    			if (maze[b][c] == '@') {
    				if (!found) {    					
    					start = new Point(c,b);
    					found = true;   					
    				}
    				else
    					end = new Point(c,b);
    			}
    		}
    		System.out.println();
    	}
    		
    		solution[0] = findRoute(maze, width, height, start, end, true, false);
    		//solution[1] = findRoute(maze, width, height, start, end, -1, 1);
    		//solution[2] = findRoute(maze, width, height, start, end, 1, -1);
    		//solution[3] = findRoute(maze, width, height, start, end, 1, 1);
    	}
    }
    public static Point[] findRoute(char maze[][], int width, int height, Point start, Point end, boolean goUp, boolean goDown) {
    	Point[] route = new Point[100];
    	int step = 0;
    	int x = start.x, y = start.y, tempX, tempY;
    	
    	int choice, totalChoices = 0, lastChoice = -1;    	
    	boolean[] ch = new boolean[9];
    	
    	boolean goBack;
    	
    	String[] decision = new String[100];
    	
    	String temp = "";
    	System.out.println("("+x+","+y+") ("+end.x+","+end.y+") "+step);
    	
    	while(step <= 100) {
    		if (x == end.x && y == end.y)
    			break;
    			
    		for(int a = 0; a < height; a++) {
    			for(int b = 0; b < width; b++)
    				if(x == b && y == a)
    					System.out.print("O");
    				else
    					System.out.print(maze[a][b]);
    			System.out.println();
    		}
    		
    		//ANALYZE CHOICES
    		ch[0] = (y-1 > -1) && maze[y-1][x] == '.' ? true : false;
    		ch[1] = (y+1 < height) && maze[y+1][x] == '.' ? true : false;
    		ch[2] = (x-1 > -1) && maze[y][x-1] == '.' ? true : false;
    		ch[3] = (x+1 < width) && maze[y][x+1] == '.' ? true : false;
    		
    		System.out.println("Step: "+step);    		
    		System.out.println(ch[0]+" "+ch[1]+" "+ch[2]+" "+ch[3]);
    		
    		//FIND NUMBER OF CHOICES
    		totalChoices = 0;
    		for(int a = 0; a < 4; a++)
    			if(ch[a])
    				totalChoices++;
    				
    		//USE DEFINED LOGIC TO MAKE A CHOICE
    		choice = 0;
    		goBack = false;
    		
    		 do {    			
    			if (lastChoice == 0 && choice == 1)
    				choice = 2;
    			if (lastChoice == 1 && choice == 0)
    				choice = 2;
    			if (lastChoice == 2 && choice == 3)
    				choice = 0;
    			if (lastChoice == 3 && choice == 2)
    				choice = 0;
    			choice++;
    			try {
    				temp += ch[choice];
    			} 
    			catch (ArrayIndexOutOfBoundsException e) {
    				goBack = true;
    				step--;
    				while(decision[step].length() < 1) {
    					step--; 
    					System.out.println(step);   					
    				}
    				break;
    			}
    		} while (!ch[choice]);
    		
    		if(goBack)
    			continue;
    		
    		lastChoice = choice;   		
    		decision[step] += choice;
    		
    		while(true) {
    			if (x == end.x && y == end.y)
    				break;
    				
    			tempX = x;
    			tempY = y;
    			step++;
    			switch (choice) {
    				case 0:
    					y--;
    					break;
    				case 1:
    					y++;
    					break;
    				case 2:
    					x--;
    					break;
    				case 3:
    					x++;
    					break;
    			}
    			
    			if(maze[y][x] == '#') {
    				x = tempX;
    				y = tempY;
    				step--;
    				break;
    			}
    			
    		}
    		temp = new Scanner(System.in).nextLine();
    	}
    	
    	return route;
    }
}
