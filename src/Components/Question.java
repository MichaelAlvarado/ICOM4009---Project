package Components;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import Main.Handler;

/**
 * 
 * @author Anel Martinez
 * Date - March 1, 2020
 * This class generates the structure of the questions the designer will input for the player user.
 * The user needs to answer at least 3 correctly in order to proceed.  
 *
 */


public class Question {
	
	private String question, answer_1, answer_2, answer_3, answer_4;
	private boolean correct;
	
	//Creates Question Constructor to use as source for Question Pools for the buildings
	public Question(String question, String answer_1, String answer_2, String answer_3, String answer_4, boolean theCorrect) {
		this.question = question;
		this.answer_1 = answer_1;
		this.answer_2 = answer_2;
		this.answer_3 = answer_3;
		this.answer_4 = answer_4;		
		this.correct = theCorrect;
	}
	public Question(String question, String answer_1, String answer_2, String answer_3, String answer_4) {
	this.question = question;
	this.answer_1 = answer_1; //Correct one
	this.answer_2 = answer_2;
	this.answer_3 = answer_3;
	this.answer_4 = answer_4;
	}
	public String getQuestion() {
		return question;
	}
	
	public String getAnswers() {
		return this.answer_1 + ", " + this.answer_2 + ", " + this.answer_3 + ", " + this.answer_4;
	}

	public String getAnswer_1() {
		return answer_1;
	}

	public String getAnswer_2() {
		return answer_2;
	}

	public String getAnswer_3() {
		return answer_3;
	}

	public String getAnswer_4() {
		return answer_4;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	
	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswer_1(String answer_1) {
		this.answer_1 = answer_1;
	}

	public void setAnswer_2(String answer_2) {
		this.answer_2 = answer_2;
	}

	public void setAnswer_3(String answer_3) {
		this.answer_3 = answer_3;
	}

	public void setAnswer_4(String answer_4) {
		this.answer_4 = answer_4;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	//Navigate through the Question Pop Up, select next of previous answer (UP, DOWN) cancel (ESCAPE) or decide answer (ENTER)
	//This if not implemented with JButtons. 
	public void tick() {
		if(Handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_UP)) {
			//next answer up
		}
		if(Handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_DOWN)) {
			//next answer down
		}
		if(Handler.getKeyListener().keyJustPressed(KeyEvent.VK_ENTER)) {
			//choose answer
		}
		if(Handler.getKeyListener().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			//exit to try
		}
	}
	
}
