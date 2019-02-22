/**
 * @|BIRCA.java
 * @|->Server.java
 * @||->Networking.java
 *
 *
 * @Author: Brent Walther 
 * @Version 1.00 2010/9/6
 */
 
import java.net.*;
import java.io.*;

public class Networking {
	
	private int port;
	private String host;
	private InputStream stream;
    private Socket sock;
    private BufferedReader reader;
    private PrintWriter writer;

    public Networking (String host, int port) {
    	
    	this.port = port;
    	this.host = host;
    	
    	try {
    		start();
    	}
    	catch(UnknownHostException e) {
    		System.out.println("ERROR: Unknown Host. Could not connect!");
    	}
    	catch(ConnectException e) {
    		System.out.println("ERROR: Could not connect!");
    	}
    	catch(Exception e) {
    		System.out.println("ERROR: Unknown I/O error.");
    	}
    }
    
    private void start() throws Exception {
    	sock = new Socket(host, port);

        reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        writer = new PrintWriter(sock.getOutputStream());
    }
    
    public void write(String data) {
    	writer.print(data+"\n");
    	writer.flush();
    }
    
    public String read() {
    	try {
    		return reader.readLine();
    	}
    	catch (IOException e) {
    		return null;
    	}
    	
    }
}