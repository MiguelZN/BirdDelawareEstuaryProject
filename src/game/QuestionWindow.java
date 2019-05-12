package game;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;


public class QuestionWindow extends JFrame{
	private String question;
	private Position position;
	private Size size;
	
	//Java Swing Components
	private JLabel QuestionLabel;
	private ButtonGroup RadioResponses;

	final int LABEL_HEIGHT = 30;
	static final int QUESTIONBOX_WIDTH = 300;
	static final int QUESTIONBOX_HEIGHT = 150;
	
	
	
	
	
	public QuestionWindow(Position p, Size s, String question, List<String> responses) {
		this.question =question;
		this.position = p;
		this.size = s;
	
		this.setSize(QUESTIONBOX_WIDTH,QUESTIONBOX_HEIGHT);
		this.setLocation(p.getX(),p.getY());
		this.setTitle("Quiz Question!");
		
		//Label
		this.QuestionLabel = new JLabel();
		this.QuestionLabel.setText(question);
		this.QuestionLabel.setBounds(0, 0, this.getWindowSize().getWidth(), LABEL_HEIGHT);
		this.add(this.QuestionLabel);
		
		//Radio Button Responses
		this.RadioResponses = new ButtonGroup();
		this.createResponses(responses);
		this.setVisible(true);
	}
	
	public void createResponses(List<String> responses) {
		
		int current_y = LABEL_HEIGHT;
		int current_x = 0;
		for(String r: responses) {
			int radio_height = this.getWindowSize().getHeight()/responses.size()/2;
			int radio_width = this.getWindowSize().getWidth();
			JRadioButton response_button = new JRadioButton(r);

			response_button.setBounds(current_x, current_y, radio_width,radio_height);
			current_y+=radio_height;
			System.out.println("CREATING");
			this.add(response_button);
			this.RadioResponses.add(response_button);
		}
	}


	public String getQuestion() {
		return question;
	}

	public Position getPosition() {
		return position;
	}

	public Size getWindowSize() {
		return size;
	}

	public JLabel getQuestionLabel() {
		return QuestionLabel;
	}

	
}
