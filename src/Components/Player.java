package Components;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import States.Handler;

/**
 * @author Fabiola Badillo Ramos
 * This class describes the Player instances] with all its attributes and methods
 *Date - March 20, 2020
 */

public class Player {

	private Point position;
	private BufferedImage avatar;
	private String name;
	Handler handler;
	private Building building; 
	
	public Player(String name, Point initialPosition) {
		this.name = name;
		position = initialPosition;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	public String getName() {
		return this.name;
	}
	public void setAvatar(BufferedImage avatar) {
		this.avatar = avatar;
	}
	public BufferedImage getAvatar() {
		return this.avatar;
	}
	public void moveTo(Point p) {
		this.position = p;
	}
	public Point getPosition() {
		return this.position;
	}
	public void moveOnX(int x) {
		position.x = x;
	}
	public void moveOnY(int y) {
		position.y = y;
	}
	
	public void tick() {
		if(handler.getKeyListener().up || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_UP)) {
			this.moveOnY(position.y + 1);
		}
		if(handler.getKeyListener().left || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_LEFT)) {
			this.moveOnX(position.x - 1);
		}
		if(handler.getKeyListener().right || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_RIGHT)) {
			this.moveOnX(position.x + 1);
		}
		if(handler.getKeyListener().down || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_DOWN)) {
			this.moveOnY(position.x -1);
		}

/*		if(handler.getKeyListener().keyJustPressed(KeyEvent.VK_X)) {
			//Temporary place where the player must be
			if(position.x >= walls.getFirst().getP1().x && position.x <=walls.getFirst().getP2().y 
				&& position.y >= walls.getFirst().getP1().y - 100 && position.y <= walls.getFirst().getP2().y + 100) {
				//Must make Question box appear, however this question box must be with the questions and answers already
//				AddQuestionsBox addQuestionBox = new AddQuestionsBox((width/2)-250, 200,500,500, plane);
//				addQuestionBox.setVisible(false);
//				display.getContentPane().add(addQuestionBox);	
				}
				
			}
			}
  */	
	}
	
}


