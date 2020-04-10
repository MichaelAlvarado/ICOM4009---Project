package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.LinkedList;

import Components.Building;
import Components.Question;
import GameSetUp.Handler;
import Resources.Button;

public class QuestionState implements State{

	private static Building building;
	private static LinkedList<Question> qList;
	private static LinkedList<Question> randQList;
	private Button yes, no;
	private boolean answering; //if this is true then its on state where it ask the player if he wants to answer the questions of building

	public QuestionState() {
		building = new Building("Question Building"); //its a dummy building 
		yes = new Button("Yes", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 100, 30, Color.CYAN) {

			@Override
			public void action() {
				answering = true;				
			}
			
		};
		no = new Button("No", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)+40, 100, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());
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
			//make the mouselistener know is a rectangle option was choosen(Not Implemented)
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
		qList = new LinkedList<Question>();
		randQList = new LinkedList<Question>();
		for(Question q: building.getQuestionPool()) {
			qList.add(q);
		}
		Collections.shuffle(qList);	
		for(Question q1: qList) {
			q1.randonmizeAnswers();
			randQList.add(q1);
//			System.out.println(q1.getQuestion());
//			System.out.println(q1.getAnswers());
		}
	}
	

}
