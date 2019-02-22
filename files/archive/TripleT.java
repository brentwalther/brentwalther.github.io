/**
 * @(#)TripleT.java
 *
 *
 * @author 
 * @version 1.00 2011/5/23
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.JTextComponent;

public class TripleT extends JFrame{

	Container pane;
	JButton buttons[];
	String turn;
	String wins[];
	
    public TripleT() {
    	super("Tic Tac Toe!");
    	
    	pane = getContentPane();
    	pane.setLayout(new GridLayout(3,3));
    	
    	ClickHandler waiter = new ClickHandler(this);
    	
    	buttons = new JButton[9];
    	for(int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].addActionListener(waiter);
			pane.add(buttons[i]);
		}
    	newGame();
    	
    	setSize(300,300);
    	setVisible(true);
    }
    
    public void newGame() {
    	turn = "X";
    	wins = new String[] {
			"XXX......",
			"...XXX...",
			"......XXX",
			"X..X..X..",
			".X..X..X.",
			"..X..X..X",
			"X...X...X",
			"..X.X.X.."
		};
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setText("");
		}
    }
    
    class ClickHandler implements ActionListener {
    	JFrame parent;
    	public ClickHandler(JFrame parent) {
    		this.parent = parent;
    	}
    	public void actionPerformed(ActionEvent e) {
    		JButton pressed = (JButton)(e.getSource());
    		if(pressed.getText().equals("")) {
    			pressed.setText(turn);
    			turn = (turn.equals("X") ? "O" : "X");
    		}
    		else {
    			JOptionPane.showMessageDialog(parent,"That is not a valid move", "Try again", JOptionPane.INFORMATION_MESSAGE);
    		}
    		String board = getBoard();
    		for(int i = 0; i < wins.length; i++) {
    			String winner = wins[i];
    			if(board.matches(winner)) {
    				JOptionPane.showMessageDialog(parent,"The X's Won! :)", "Winner!", JOptionPane.INFORMATION_MESSAGE);
    				newGame();
    				break;
    			}
    			else if (board.matches(winner.replaceAll("X","O"))) {
    				JOptionPane.showMessageDialog(parent,"The O's Won! :)", "Winner!", JOptionPane.INFORMATION_MESSAGE);
    				newGame();
    				break;
    			}
    			else if (board.indexOf(' ') == -1) {
    				JOptionPane.showMessageDialog(parent,"It was a tie! :(", "Oh noooo!", JOptionPane.INFORMATION_MESSAGE);
    				newGame();
    				break;
    			}
    		}
    	}
    }
    
    public String getBoard() {
    	String ret = "";
    	for(int i = 0; i < buttons.length; i++) {
    		ret += (buttons[i].getText().equals("") ? " " : buttons[i].getText());
    	}
    	return ret;
    }
    public static void main(String args[]) {
    	TripleT game = new TripleT();
    }
    
}