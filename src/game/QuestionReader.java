package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author MiguelZN
 *-Class to read in a formatted text file in order to read in quiz questions into our games
 *rather than hardcoding everything within the code
 *
 *
 *HOW TO FORMAT FOR A QUIZ QUESTION TEXTFILE: (BELOW) and look at text.txt for reference - Miguel
 *
 *question: <question being asked>
 *answer: <answer being asked>
 *response: <response to question>
 *response: <response to question>
 *response: <response to question>
 *											<- NOTE: leave a space BLANK in order for program to know that a new question is to be read
 question: <question being asked>     
 *answer: <answer being asked>
 *response: <response to question>
 *response: <response to question>
 *response: <response to question> 
 *
 *
 */
public class QuestionReader {
	private enum Words {
		ANSWER("answer:"), //south america (jungly background)
		QUESTION("question:"),
		RESPONSE("response:");

		private String asset_key = null;

		private Words(String s){
			asset_key=s;
		}
		
		public String getString(){
			return this.asset_key;
		}
	}
	
	String answer;
	String question;
	ArrayList<String> responses;
	
	String text_file_path;
	File text_file;
	
	BufferedReader br;
	
	ArrayList<QuizQuestion> quiz_questions;
	
	public QuestionReader(String text_file_path) {
		this.responses = new ArrayList<>();
		this.answer = "";
		this.question = "";
		
		this.quiz_questions = new ArrayList<>();
		
		
		this.text_file_path = text_file_path;
		
		//Takes in the text_file_path and reads it
		readInFile(this.text_file_path);
		
		System.out.println(this.question);
		System.out.println(this.answer);
		
		for(String s: responses) {
			System.out.println(s);
		}

	}
	
	
	/**@author Miguel
	 * @param file_path
	 * -Takes in a 'file_path' as a String, and reads through a text file
	 */
	public void readInFile(String file_path) {
		
		//Creates the File and BufferedReader which reads the text file
		this.text_file = new File(file_path);
		try {
			this.br = new BufferedReader(new FileReader(this.text_file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Reading in the text file Line by Line
		String st;
		try {
			while((st= this.br.readLine())!=null){
				System.out.println(st);
				int start =0;
				String rest_of_line = "";
				char[] char_arr = st.toCharArray();
				
				//if line starts with: 'answer:', 'question:', or 'response:'
				if(st.startsWith(Words.ANSWER.getString())) {
					start = Words.ANSWER.getString().length();
					
					
					for(int i=start;i<st.length();i++) {
						char c = char_arr[i]; //pulls the current character
						rest_of_line +=c; //adds it to the sentence
					}
					
					this.answer = rest_of_line; //pulls out the answer 
				}
				else if(st.startsWith(Words.QUESTION.getString())) {
					start = Words.QUESTION.getString().length();
					
					for(int i=start;i<st.length();i++) {
						char c = char_arr[i]; //pulls the current character
						rest_of_line +=c; //adds it to the sentence
					}
					
					this.question = rest_of_line; //pulls out the question
				}
				else if(st.startsWith(Words.RESPONSE.getString())) {
					start = Words.RESPONSE.getString().length();
					for(int i=start;i<st.length();i++) {
						char c = char_arr[i]; //pulls the current character
						rest_of_line +=c; //adds it to the sentence
					}
					
					String response = rest_of_line; //pulls out the response
					
					this.responses.add(response); //adds the response into ArrayList of responses
					
					
				}
				//Detects if there is a white space line (signifying the start of a new question)
				else if(st.isEmpty()) {
					//Creates a Quiz_Question
					this.quiz_questions.add(new QuizQuestion(this.question,this.answer, this.responses));

					
					//Makes blank answer and question
					//and refreshes the responses array to create a new QuizQuestion
					this.answer = "";
					this.question = "";
					ArrayList<String> new_arr = new ArrayList<>();
					this.responses = new_arr;
				}
				else {
					System.out.println("THIS LINE IS NOT BEING READ");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<QuizQuestion> getQuizQuestions(){
		return this.quiz_questions;
	}

}
