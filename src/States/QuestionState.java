package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.Building;
import main.Handler;

public class QuestionState implements State{
	
	private Handler handler;
	private Building building;
	
	public QuestionState(Handler handler) {
		this.handler = handler;
		building = new Building("Question Building"); //its a dummy building 
	}
	
	@Override
	public void tick() {
		if(handler.getKeyListener().keyJustPressed(KeyEvent.VK_ESCAPE)) {
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
		g.fillRoundRect((handler.getWidth()/2)-50, (handler.getHeight()/2), 100, 30, 20, 20);
		g.fillRoundRect((handler.getWidth()/2)-50, (handler.getHeight()/2)+40, 100, 30, 20, 20);
		g.setColor(Color.BLACK);
		g.drawString("Yes",(handler.getWidth()/2)-50, (handler.getHeight()/2)+25);
		g.drawString("No",(handler.getWidth()/2)-50, (handler.getHeight()/2)+65);

	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}

}
