/**
 * @(#)Evaluate.java
 *
 *
 * @author 
 * @version 1.00 2010/11/19
 */


public class Evaluate {
    
    public static double eval(String eval, double at) 
    {
    	
    	eval = eval.replaceAll("x",""+at);
    	
    	/*
    	 * This portion of code recusively 
    	 * resolves all the parantheses
    	 * by seperating them into simpler 
    	 * evalutations to solve then replacing
    	 * them in the origninal equation.
    	 */
    	while(true) 
    	{
	    	int start = eval.lastIndexOf('(');
	    	if(start > -1) 
	    	{
	    		int finish = eval.substring(start).indexOf(')');
	    		String newEval = eval.substring(start+1,start+finish);
	    		double result = eval(newEval,0);
	    		eval = eval.replace(eval.substring(start,start+finish+1),""+result);
	    	}
	    	else
	    		break;	
    	}   	
    	
    	
    	// Evaluates all of the exponents (^)
    	while(true) 
    	{
    		int start = eval.lastIndexOf("^");
    		if(start > -1)
    		{
    			String temp = "";
    			for(int i = start+1; i < eval.length(); i++)
    				if(Character.isDigit(eval.charAt(i)) || eval.charAt(i) == '.' || eval.charAt(start+1) == '-')
    					temp += eval.charAt(i);
    				else
    					break;
    			double after = Double.parseDouble(temp);
    			int end = start+temp.length()+1;
    			temp = "";
    			for(int i = start-1; i >= 0; i--)
    				if(Character.isDigit(eval.charAt(i)) || eval.charAt(i) == '.' || eval.charAt(i) == '-')
    					temp = eval.charAt(i) + temp;
    				else
    					break;
    			double before = Double.parseDouble(temp);
    			start -= temp.length();
    			eval = eval.replace(eval.substring(start,end),""+Math.pow(before,after));
    		}
    		else
    			break;
    	}
    	
    	// Evaluates the multiplication and division
    	while(true)
    	{
    		int start = -1;
    		for(int i = 0; i < eval.length(); i++)
    			if("*/".contains(""+eval.charAt(i)))
    			{
    				start = i;
    				break;
    			}
    		
    		if(start > -1)
    		{
    			String temp = "";
    			for(int i = start+1; i < eval.length(); i++)
    				if(Character.isDigit(eval.charAt(i)) || eval.charAt(i) == '.' || eval.charAt(start+1) == '-')
    					temp += eval.charAt(i);
    				else
    					break;
    			double after = Double.parseDouble(temp);
    			int end = start+temp.length()+1;
    			temp = "";
    			for(int i = start-1; i >= 0; i--)
    				if(Character.isDigit(eval.charAt(i)) || eval.charAt(i) == '.' || eval.charAt(i) == '-')
    					temp = eval.charAt(i) + temp;
    				else
    					break;
    			double before = Double.parseDouble(temp);
    			int begin = start-temp.length();
    			eval = eval.replace(eval.substring(begin,end),""+(before*(eval.charAt(start)=='*'?after:1/after)));
    		}
    		else
    			break;
    	}
    	
    	// Evaluates addition and subtraction
    	while(true)
    	{
    		int start = -1;
    		for(int i = 0; i < eval.length(); i++)
    			if("+-".contains(""+eval.charAt(i)))
    			{
    				start = i;
    				break;
    			}
    		
    		if(start > -1)
    		{
    			String temp = "";
    			for(int i = start+1; i < eval.length(); i++)
    				if(Character.isDigit(eval.charAt(i)) || eval.charAt(i) == '.' || eval.charAt(start+1) == '-')
    					temp += eval.charAt(i);
    				else
    					break;
    			double after = Double.parseDouble(temp);
    			int end = start+temp.length()+1;
    			temp = "";
    			for(int i = start-1; i >= 0; i--)
    				if(Character.isDigit(eval.charAt(i)) || eval.charAt(i) == '.' || eval.charAt(i) == '-')
    					temp = eval.charAt(i) + temp;
    				else
    					break;
    			if(temp.equals(""))
    				break;
    			double before = Double.parseDouble(temp);
    			int begin = start-temp.length();
    			eval = eval.replace(eval.substring(begin,end),""+(before+(eval.charAt(start)=='+'?after:-after)));
    		}
    		else
    			break;
    	}
    	return Double.parseDouble(eval);
    }
}