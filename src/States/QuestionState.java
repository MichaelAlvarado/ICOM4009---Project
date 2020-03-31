package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Components.Building;
import main.Handler;

public class QuestionState implements State{
	
	private Handler handler;
	private Building building;
	private Rectangle yes, no;
	
	public QuestionState(Handler handler) {
		this.handler = handler;
		building = new Building("Question Building"); //its a dummy building 
		yes = new Rectangle((handler.getWidth()/2)-50, (handler.getHeight()/2), 100, 30);
		no = new Rectangle ((handler.getWidth()/2)-50, (handler.getHeight()/2)+40, 100, 30);
	}
	
	@Override
	public void tick() {
		if(handler.getMouseListener().clickedOn(yes)) {
			System.out.println("Yes");
		}
		if(handler.getMouseListener().clickedOn(no) || handler.getKeyListener().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			handler.setCurrentState(handler.getGameState());
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		int width = 500;
		int height = 250;
		g.setColor(new Color(100,100,100));
		g.fillRoundRect((handler.getWidth()/2)-(width/2), (handler.getHeight()/2)-(height/2), width, height, 20, 20);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		g.drawString("Do you want to answer the questions of:", (handler.getWidth()/2)-(width/2), (handler.getHeight()/2)-(height/2)+25);
		g.drawString(building.getName()+"?", (handler.getWidth()/2)-(width/2), (handler.getHeight()/2)-(height/2)+50);
		g.setColor(Color.CYAN);
		g.fillRoundRect(yes.x, yes.y, yes.width, yes.height, 20, 20);
		g.fillRoundRect(no.x, no.y, no.width, no.height, 20, 20);
		g.setColor(Color.BLACK);
		g.drawString("Yes",(handler.getWidth()/2)-50, (handler.getHeight()/2)+25);
		g.drawString("No",(handler.getWidth()/2)-50, (handler.getHeight()/2)+65);

	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}

}
