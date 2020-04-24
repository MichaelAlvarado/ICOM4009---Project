package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Components.Building;
import Components.Question;
import GameSetUp.Handler;
import Resources.Animation;
import Resources.Button;
import Resources.Images;

public class QuestionState implements State{ 

	private Building building;
	private LinkedList<Question> randQList;
	private Button yes, no;
	private Button opt1, opt2, opt3, opt4;
	private boolean answering; //if this is true then its on state where it ask the player if he wants to answer the questions of building
	private Question currentQuestion;
	private String correctAnswer;
	private int questionNumber = 0;
	private int correctlyAnsweredQuestions = 0;
	private int incorrectlyAnsweredQuestions = 0;
	private Animation correct, incorrect, playAgain;
		
	public QuestionState() {
		building = new Building("Question Building"); //its a dummy building 
		currentQuestion = new Question();
		Handler.getSoundManager().addAudio("correct");
		Handler.getSoundManager().addAudio("wrong");
		correct = new Animation(Images.correct,Handler.getWidth()/2,150,100,100,0.5);
		incorrect = new Animation(Images.incorrect, (Handler.getWidth()/2)-60, 150, 200, 100, 0.5);
		playAgain = new Animation(Images.playAgain, (Handler.getWidth()/2)-60, 150, 200, 100, 0.5);
		
		yes = new Button("Yes", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 100, 30, Color.YELLOW) {

			@Override
			public void action() {
				answering = true;
				questionRandomizer(); // this randomizes the questions each time the player wants to answer a set of questions
				getNextQuestion();
			}
			
		};
		no = new Button("No", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)+40, 100, 30, Color.YELLOW) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());
			}
			
		};
		opt1 = new Button("Option1", 15, (Handler.getWidth()/2)-200, (Handler.getHeight()/2)-80, 300, 30, Color.YELLOW) {
			@Override
			public void action() {
				displayNextQuestion(opt1, correctAnswer);	
			}
		};
		opt2 = new Button("Option2", 15, (Handler.getWidth()/2)-200, (Handler.getHeight()/2)-40, 300, 30, Color.YELLOW) {
			@Override
			public void action() {
				displayNextQuestion(opt2, correctAnswer);
			}
		};
		opt3 = new Button("Option3", 15, (Handler.getWidth()/2)-200, (Handler.getHeight()/2), 300, 30, Color.YELLOW) {
			@Override
			public void action() {
				displayNextQuestion(opt3, correctAnswer);
			}
		};
		opt4 = new Button("Option4", 15, (Handler.getWidth()/2)-200, (Handler.getHeight()/2)+40, 300, 30, Color.YELLOW) {
			@Override
			public void action() {
				displayNextQuestion(opt4, correctAnswer);
			}
		};
	}

	@Override
	public void tick() {
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) { //Shorcut Escape key to exit the Question State
			this.answering = false;
			Handler.setCurrentState(Handler.getGameState());
		}
		
		if(!answering) {
			yes.tick();
			no.tick();
		}
		else {
			opt1.tick();
			opt2.tick();
			opt3.tick();
			opt4.tick();
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		//this print background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Handler.getWidth(), Handler.getHeight());
		//this prints the box in the middle of the question
		int width = 500;
		int height = 250;
		g.setColor(new Color(100,100,100));
		if(!answering) {
			g.fillRoundRect((Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2), width, height, 20, 20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString("Do you want to answer the questions of: ", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+25);
			g.drawString(building.getName()+"?", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+50);
			//prints yes or no buttons
			yes.render(g);
			no.render(g);
			
		}
		else {
			g.setColor(Color.RED);
			g.draw3DRect((Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)-50, width+100, height+100, true);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString(currentQuestion.getQuestion(), (Handler.getWidth()/2)-200, (Handler.getHeight()/2)-(height/2)); 
			opt1.render(g);
			opt2.render(g);
			opt3.render(g);
			opt4.render(g);
			
			correct.render(g);
			incorrect.render(g);
			playAgain.render(g);
		}
		

	}

	public void setBuilding(Building building) {
		this.building = building;
		
	}
	
	
	/**
	 * 
	 * @author Fabiola Badillo
	 * Date - April 11, 2020
	 * Method to set the buttons for the next question to be answered
	 *
	 */
	public void getNextQuestion() {
		if (questionNumber<4 && correctlyAnsweredQuestions<3 && incorrectlyAnsweredQuestions<2) {
			currentQuestion = randQList.get(questionNumber);
			correctAnswer = currentQuestion.getCorrectAnswer();
			opt1.setMessage(currentQuestion.getAnswer_1());
			opt2.setMessage(currentQuestion.getAnswer_2());
			opt3.setMessage(currentQuestion.getAnswer_3());
			opt4.setMessage(currentQuestion.getAnswer_4());
			questionNumber++;			
		}
		if (incorrectlyAnsweredQuestions >= 2) {
			playAgain.startAnimation();
			if(!playAgain.isAnimating())
				Handler.setCurrentState(Handler.getGameState());
			resetQuestionState();
		}
		if (correctlyAnsweredQuestions == 3) {
			building.setFound(true);
			resetQuestionState();
			Handler.getGameState().getCongratulation().startAnimation();
			Handler.setCurrentState(Handler.getGameState());
		}
	}
	
	/**
	 * 
	 * @author Fabiola Badillo
	 * Date - April 11, 2020
	 * Method to check if the question was correctly answered
	 *
	 */
	public void displayNextQuestion(Button b, String str) {
		if (b.getMessage().equals(str)) {
			correctlyAnsweredQuestions++;
			correct.startAnimation();
			Handler.getSoundManager().resumeAudio("correct");
		}
		else {
			incorrectlyAnsweredQuestions++;
			incorrect.startAnimation();
			Handler.getSoundManager().resumeAudio("wrong");
		}
		getNextQuestion();
	}
	
	/**
	 * 
	 * @author Fabiola Badillo
	 * Date - April 11, 2020
	 * Method to reset the question state for the next building
	 *
	 */
	public void resetQuestionState() {
		correctlyAnsweredQuestions = 0;
		incorrectlyAnsweredQuestions = 0;
		questionNumber = 0;
		answering = false;
	}
	

/**
 * 
 * @author jorgecalderon
 * Date - April 10, 2020
 * Method to randomize the order of the questions of a building
 *
 */
	public void questionRandomizer() {
		randQList = new LinkedList<Question>();
		for(Question q: building.getQuestionPool()) {
			randQList.add(q);
		}
		Collections.shuffle(randQList);	
		for(Question q1: randQList) {
			q1.randonmizeAnswers();
		}
	}
	

}