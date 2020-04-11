package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Components.Building;
import Components.Question;
import GameSetUp.Handler;
import Resources.Button;

public class QuestionState implements State{

	private static Building building;
	private static LinkedList<Question> randQList;
	private Button yes, no, opt1, opt2, opt3, opt4;
	private boolean answering; //if this is true then its on state where it ask the player if he wants to answer the questions of building
	private boolean isCorrect;
	Question currentQuestion;
	private String correctAnswer;
	private JLabel question;
	
	public QuestionState() {
		building = new Building("Question Building"); //its a dummy building 
		currentQuestion = new Question();
		
		yes = new Button("Yes", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 100, 30, Color.CYAN) {

			@Override
			public void action() {
				answering = true;
				questionRandomizer(); // this randomizes the questions each time the question state is created
			}
			
		};
		no = new Button("No", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)+40, 100, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());
			}
			
		};
		opt1 = new Button("Option1", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)-80, 100, 30, Color.CYAN) {
			@Override
			public void action() {
				if (opt1.getMessage().equals(correctAnswer)) {
					System.out.println("Correct");
				}
			}
		};
		opt2 = new Button("Option2", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)-40, 100, 30, Color.CYAN) {
			@Override
			public void action() {
				if (opt2.getMessage().equals(correctAnswer)) {
					System.out.println("Correct");
				}
			}
		};
		opt3 = new Button("Option3", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 100, 30, Color.CYAN) {
			@Override
			public void action() {
				if (opt3.getMessage().equals(correctAnswer)) {
					System.out.println("Correct");
				}
			}
		};
		opt4 = new Button("Option4", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)+40, 100, 30, Color.CYAN) {
			@Override
			public void action() {
				System.out.println("testing 4");
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
			for (int i = 0; i < 4; i++) {
				currentQuestion = randQList.get(i);
				correctAnswer = currentQuestion.getCorrectAnswer();
				opt1.setMessage(currentQuestion.getAnswer_1());
				opt2.setMessage(currentQuestion.getAnswer_2());
				opt3.setMessage(currentQuestion.getAnswer_3());
				opt4.setMessage(currentQuestion.getAnswer_4());
			}
			
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
		g.fillRoundRect((Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2), width, height, 20, 20);
		if(!answering) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString("Do you want to answer the questions of:", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+25);
			g.drawString(building.getName()+"?", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+50);
			//prints yes or no buttons
			yes.render(g);
			no.render(g);
			
		}
		else {
			//Displays the Question of the building(Not implemented)
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString(currentQuestion.getQuestion(), (Handler.getWidth()/2)-15, (Handler.getHeight()/2)-(height/2)+25);
			opt1.render(g);
			opt2.render(g);
			opt3.render(g);
			opt4.render(g);
		}
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
	

/**
 * 
 * @author jorgecalderon
 * Date - April 10, 2020
 * Method to randomize the order of the questions of a building
 *
 */
	public static void questionRandomizer() {
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
