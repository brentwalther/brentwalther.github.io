/**
 * @(#)InputInterpreter.java
 *
 *
 * @author 
 * @version 1.00 2010/9/7
 */
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InputInterpreter implements ActionListener{

	BIRCA source;
	
    public InputInterpreter(BIRCA source) {
    	this.source = source;
    }
    
    public void actionPerformed(ActionEvent e) {
    	String data = source.input.getText();
    	if(data.charAt(0) == '/') {
    		String[] args = data.split(" ");
    		if(args[0].equalsIgnoreCase("/join"))
    			//source.serverList.get(0).addChannel(args[1]);
    			//source.serverList.get(0).write("JOIN "+args[1]);
    			source.serv.addChannel(args[1]);
    			source.serv.write("JOIN "+args[1]);
    	}
    	else {
    		//source.serverList.get(0).write(data);
    		source.serv.write(data);
    	}
		source.input.setText("");
    }
}