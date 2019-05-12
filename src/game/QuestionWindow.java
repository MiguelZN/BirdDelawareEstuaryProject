package game;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


/**
 * @author MiguelZN
 * -A JFrame pop-up window that spawns when a player touches a QuestionCloud in RedKnot
 * -Contains RadioButtons which allows the user to select their response to a question
 *
 */
public class QuestionWindow extends JFrame{
	private String question;
	private Position position;
	private Size size;
	
	//Java Swing Components
	private JLabel QuestionLabel;
	private ButtonGroup ResponsesGroup;

	final int LABEL_HEIGHT = 30;
	static final int QUESTIONBOX_WIDTH = 300;
	static final int QUESTIONBOX_HEIGHT = 200;
	
	/*GridLayout: in order to layout the radiobuttons neatly*/
	GridLayout gl;
	
	
	
	
	/**@author Miguel
	 * @param p
	 * @param s
	 * @param question
	 * @param responses
	 */
	public QuestionWindow(Position p, Size s, String question, List<String> responses) {
		this.question =question;
		this.position = p;
		this.size = s;
	
		//this.setSize(QUESTIONBOX_WIDTH,QUESTIONBOX_HEIGHT);
		this.setLocation(p.getX(),p.getY());
		this.setTitle("Quiz Question!");
		
		//Grid Layout
		this.gl = new GridLayout(1,1);
		
		this.setLayout(gl);
		
		//Label
		this.QuestionLabel = new JLabel();
		this.QuestionLabel.setText(question);
		this.QuestionLabel.setBounds(0, 0, this.getWindowSize().getWidth(), LABEL_HEIGHT);
		this.add(this.QuestionLabel);
		
		//Radio Button Responses
		this.ResponsesGroup = new ButtonGroup();
		this.createResponses(responses);
		this.pack();
		this.setVisible(true);
	}
	
	/**@author Miguel
	 * @param responses
	 * -Takes in a List of Strings and Generates and adds the RadioButtons 
	 * onto the radiogroup and onto the JFrame window
	 */
	public void createResponses(List<String> responses) {
		//Readjusts the grid layout to fit the amount of radio buttons + label
		this.gl.setRows(responses.size()+1);
		this.gl.setColumns(1);
		
		for(String r: responses) {
			JRadioButton response_button = new JRadioButton(r);
			response_button.setHorizontalAlignment(SwingConstants.CENTER); //Adjusts the radio buttons to be in the center
			//System.out.println("CREATING");
			this.ResponsesGroup.add(response_button);
			this.add(response_button);
		}
		
	}

	/*Getters, Setters--------------------------*/
	/**
	 * @return String
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @return Position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @return Size
	 */
	public Size getWindowSize() {
		return size;
	}

	/**
	 * @return JLabel
	 */
	public JLabel getQuestionLabel() {
		return QuestionLabel;
	}

	
}
