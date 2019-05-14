package game;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
	private String answer;
	private Position position;
	private Size size;
	
	//Java Swing Components
	private JLabel QuestionLabel;
	private ButtonGroup ResponsesGroup;
	private List<JRadioButton> response_buttons;

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
	public QuestionWindow(Position p, Size s, String question, String answer, List<String> responses) {
		this.question =question;
		this.answer = answer;
		this.position = p;
		this.size = s;
		//this.gotAnswerCorrect = false; //Did not get a correct answer yet
	
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
		this.response_buttons = new ArrayList<>();
		this.createResponses(responses);
		this.setAlwaysOnTop(true); //sets the JFrame always on top of the main GameScreen
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
//			response_button.addActionListener(new ActionListener() {
//		        @Override
//		        public void actionPerformed(ActionEvent e) {
//		            System.out.println("SELECTED:"+response_button.getText());
//		            
//		            if(!response_button.getText().equalsIgnoreCase(answer)) {
//		            	System.exit(0);
//		            	
//		            }
//		            else {
//		            	System.out.println("CORRECT");
//		            	dispose(); //destroys the JFrame Question window
//		            }
//
//		        }
//		    });
			this.response_buttons.add(response_button);
			this.ResponsesGroup.add(response_button);
			this.add(response_button);
		}
		
	}
	
	
	/**@author Miguel
	 * @param AL
	 * -Iterates through all of the response JRadioButtons and gives them
	 * the same actionlistener (useful for telling all the buttons that if player
	 * gets the correct response, increment their score within RedKnotView)
	 */
	public void setActionListeners(ActionListener AL) {
		for(JRadioButton rb:this.response_buttons) {
			rb.addActionListener(AL);
		}
	}
	

	/*Getters, Setters--------------------------*/
	
	
	/**
	 * @return String
	 */
	public String getQuestion() {
		return question;
	}
	

	public String getAnswer() {
		return answer;
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

	public List<JRadioButton> getResponse_buttons() {
		return response_buttons;
	}

	
	

	
}
