package Resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import GameSetUp.Handler;

public abstract class Buttons {
	
	String message;
	Rectangle rec;
	Color color;
	
	public Buttons(String message, int x, int y, int width, int height, Color color) {
		this.message = message;
		rec = new Rectangle(x,y,width,height);
		this.color = color;
	}
		
	public void tick() {
		if(Handler.getMouseManager().clickedOn(rec)) {
			action();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRoundRect(rec.x, rec.y, rec.width, rec.height, 20, 20);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		g.drawString(message, rec.x, rec.y+25);
	}
	
	public abstract void action();
	
}
