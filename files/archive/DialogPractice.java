/*
 *Brent Walther
 *10.07.09
 *Description: joptionpane practice
 */

import javax.swing.*;

public class DialogPractice {

    public static void main(String args[]) {
    	
    	int one=0, two=0, three=0, four=0, five=0, six=0;
    	
    	for(int x=0;true;x++)
    	{
    		switch((int)(Math.random()*6+1))
    		{
    			case 1: one++;
    					break;
    			case 2: two++;
    					break;
    			case 3: three++;
    					break;
    			case 4: four++;
    					break;
    			case 5: five++;
    					break;
    			case 6: six++;
    		}
    		System.out.println(one+" "+two+" "+three+" "+four+" "+five+" "+six);
    		if (one == two && one == three && one == four && one == five && one == six) break;
    	}
    	
    	/*
    	JTextArea area = new JTextArea();
    	area.setText("Face\tOccurences\n1\t"+one+"\n2\t"+two+"\n3\t"+three+"\n4\t"+four+"\n5\t"+five+"\n6\t"+six);
    	
    	JOptionPane.showMessageDialog(null, area, "Dice", JOptionPane.INFORMATION_MESSAGE);
    	*/
    	
    	
    	/*
    	String fName = JOptionPane.showInputDialog("Enter first name: ");
    	
    	String lName = JOptionPane.showInputDialog("Enter last name: ");
    	
    	JOptionPane.showMessageDialog(null, "Your name is "+fName+
    		" "+lName, "Name", JOptionPane.INFORMATION_MESSAGE);
    	*/
    }
    
    
}