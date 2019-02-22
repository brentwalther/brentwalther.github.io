/**
 * @(#)ServerInterpreter.java
 *
 *
 * @author 
 * @version 1.00 2010/9/6
 */


public class ServerInterpreter extends Thread {

	private Server source;
	
    public ServerInterpreter(Server source) {
    	this.source = source;
    }
    
    public void init() {
    }
    
    public void run() {
    	String line, args[];    	
    	while((line = source.read()) != null) {
    		System.out.println(line);
    		//Splits the line into arguments
    		args = line.split(" ");
    		
    		//Starts interpreting
    		if(args[0].equalsIgnoreCase("PING"))
    			source.write("PONG "+args[1]);
    			
    		else if (args[1].equals("004"))
    			source.setName(args[3]);
    			
    		else if (args[0].matches(source.getHost().replaceAll(".","\\.").replaceAll("irc",".")))    			
    			source.print(args[0].substring(1), line);
    			
    		else if (args[1].equalsIgnoreCase("NOTICE") && !args[2].equalsIgnoreCase("AUTH"))
    			source.print("active", credentials(args[0])[1] + "\t" + args[3].substring(1));
    		
    		else if (args[1].equalsIgnoreCase("PRIVMSG")) {
    			source.print(args[3], credentials(args[0])[1] + "\t" + args[3].substring(1));
    		}
    		
    		else
    			source.print(args[0].substring(1), line);
    	}
    }
    
    public String[] credentials(String split) {
    	System.out.println(split);
    	String[] returnData = new String[3];
    	split = split.substring(1);
    	returnData[0] = split.substring(0,split.indexOf('!'));
    	returnData[1] = split.substring(split.indexOf('!') + 1,split.indexOf('@'));
    	returnData[2] = split.substring(split.indexOf('@') + 1);
    	return returnData;
    }
    
    
}