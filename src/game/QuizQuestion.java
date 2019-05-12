package game;

import java.util.ArrayList;

/**
 * @author MiguelZN
 *-Stores the question, answer and list of responses for a Quiz question
 */
public class QuizQuestion {
	private ArrayList<String> responses;
	private String question;
	private String answer;

	public QuizQuestion(String question, String answer, ArrayList<String> responses) {
		this.question = question;
		this.answer = answer;
		this.responses = responses;
	}

	public ArrayList<String> getResponses() {
		return responses;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public String toString() {
		String s = "Question:" + this.question+"\nAnswer:"+this.answer+"\n";
		for(String r:this.responses) {
			s+= "Response:"+r+"\n";
		}
		
		return s;
	}
	
	
}
