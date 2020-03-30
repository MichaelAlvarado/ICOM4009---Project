package States;

import java.awt.Color;
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
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		int width = 250;
		int height = 150;
		g.setColor(new Color(100,100,100,Color.TRANSLUCENT));
		g.fillRoundRect((handler.getWidth()/2)-width, (handler.getHeight()/2)-height, width, height, 20, 20);
	}
	
	public void setBuilding(Building building) {
		this.building = building;
	}

}
