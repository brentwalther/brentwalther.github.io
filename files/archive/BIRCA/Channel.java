/**
 * @(#)Channel.java
 *
 *
 * @author 
 * @version 1.00 2010/9/6
 */
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;

public class Channel extends DefaultMutableTreeNode{

	Vector messageBuffer;
	private ArrayList<String> nickList;
	private final String name;
	
    public Channel(String name) {
    	super(name);
    	this.name = name;
    	messageBuffer = new Vector();
    }
    public void populateNickList(String data) {
    	
    }
    
    public void addLine(String data) {
    	if(messageBuffer.size() > 500)
    		messageBuffer.remove(0);
    	messageBuffer.add(data);
    }
    
    public String toString() {
    	return name;
    }
    
    
}