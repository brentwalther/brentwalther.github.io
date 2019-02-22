/**
 * @|BIRCA.java
 * @|->Server.java
 *
 *
 * @Author: Brent Walther 
 * @Version 1.00 2010/9/6
 */
import java.util.Vector;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class Server {
	
	private String name;
	private String host;
	private Networking socket;
	Vector<Channel> channelList;
	private BIRCA app;

    public Server(String host, int port, BIRCA app) {
    	    	
    	this.host = host;
    	this.app = app;
    	
    	channelList = new Vector<Channel>();    	
    	addChannel(host);
    	
    	socket = new Networking(host, port);
    	new Thread(new ServerInterpreter(this)).start();
    	
        socket.write("NICK bmw13_java\r\n");
        socket.write("USER Brent Brent Brent :Brent\r\n");
    }
    
    public void print(String target, String data) {
    	Channel targetBuffer;
    	if(target.equalsIgnoreCase(this.name)) {
    		targetBuffer = channelList.get(0);
    		targetBuffer.addLine(data);
    	}
    	else if(target.equalsIgnoreCase("active")) {
    		targetBuffer = app.active;
    		targetBuffer.addLine(data);
    	}
    	else {
    		if(findChannel(target) != null)
    			targetBuffer = findChannel(target);
    		else
    			targetBuffer = addChannel(target);
    		targetBuffer.addLine(data);
    	}
    	if(targetBuffer.equals(app.active))
    		app.update();    		
    }
    
    public Channel addChannel(String name) {
    	Channel newChan = new Channel(name);
    	channelList.add(newChan);
    	if(!channelList.get(0).equals(newChan)) {   		
    		DefaultMutableTreeNode rootNode = channelList.get(0);
    		app.nav.add(newChan, rootNode, rootNode.getChildCount());
			app.active = newChan;
    	}
    	return newChan;
    }

    public Channel findChannel(Channel search) {
    	Channel returnValue = null;
    	Enumeration e = channelList.elements();
    	while(e.hasMoreElements()) {
    		returnValue = (Channel)e.nextElement();
			if(returnValue.equals(search))
				break;
    	}
    	return returnValue;
    }
    
    public Channel findChannel(String search) {
    	Channel returnValue = null;
    	Enumeration e = channelList.elements();
    	while(e.hasMoreElements()) {
    		returnValue = (Channel)e.nextElement();
			if(returnValue.toString().equalsIgnoreCase(search))
				break;
    	}
    	return returnValue;
    }
    
    public String read() {
    	return socket.read();
    }
    
    public void write(String data) {
    	socket.write(data);
    }
    
    public Channel matchNodeToChannel(DefaultMutableTreeNode node) {
    	Channel ret;
    	Enumeration channels = channelList.elements();
    	while (channels.hasMoreElements()) {
    		ret = (Channel)channels.nextElement();
    		if(ret.equals(node))
    			return ret;
    	}
    	return null;
    }
    
    public DefaultMutableTreeNode getTreeNode() {
    	Channel server = (Channel)(channelList.get(0));
    	return server;
    }
    
    public void setName(String newHost) {
    	name = newHost;
    }
    
    public String getHost() {
    	return host;
    }
    
    
}