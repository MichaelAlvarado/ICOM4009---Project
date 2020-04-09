package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Components.Building;
import Main.Handler;

public class QuestionState implements State{

	private Building building;
	private Rectangle yes, no;
	private boolean answering; //if this is true then its on state where it ask the player if he wants to answer the questions of building

	public QuestionState() {
		building = new Building("Question Building"); //its a dummy building 
		yes = new Rectangle((Handler.getWidth()/2)-50, (Handler.getHeight()/2), 100, 30);
		no = new Rectangle ((Handler.getWidth()/2)-50, (Handler.getHeight()/2)+40, 100, 30);
	}

	@Override
	public void tick() {
		if(Handler.getKeyListener().keyJustPressed(KeyEvent.VK_ESCAPE)) { //Shorcut Escape key to exit the Question State
			this.answering = false;
			Handler.setCurrentState(Handler.getGameState());
		}
		
		if(!answering) {
			if(Handler.getMouseListener().clickedOn(yes)) {
				answering = true;
			}
			if(Handler.getMouseListener().clickedOn(no) || Handler.getKeyListener().keyJustPressed(KeyEvent.VK_ESCAPE)) {
				Handler.setCurrentState(Handler.getGameState());
			}
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
			//prints yes or no buttons
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 25));
			g.drawString("Do you want to answer the questions of:", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+25);
			g.drawString(building.getName()+"?", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+50);
			g.setColor(Color.CYAN);
			g.fillRoundRect(yes.x, yes.y, yes.width, yes.height, 20, 20);
			g.fillRoundRect(no.x, no.y, no.width, no.height, 20, 20);
			g.setColor(Color.BLACK);
			g.drawString("Yes",(Handler.getWidth()/2)-50, (Handler.getHeight()/2)+25);
			g.drawString("No",(Handler.getWidth()/2)-50, (Handler.getHeight()/2)+65);
		}
		else {
			//Displays the Question of the building(Not implemented)
		}
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

}
