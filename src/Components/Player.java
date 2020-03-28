package Components;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import main.Handler;

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
	Building building; 
	Wall walls;

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


	//Tick for player must include, movement on each direction (UP, DOWN, LEFT, RIGHT) and the interaction with the Buildings once close.
	//If decide to interact then make tick for the Questions found in Question yet to be implemented.
	public void tick(Handler handler) {
		if(handler.getKeyListener().up || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_UP)) {
			this.moveOnY(position.y - 1);
		}
		if(handler.getKeyListener().left || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_LEFT)) {
			this.moveOnX(position.x - 1);
		}
		if(handler.getKeyListener().right || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_RIGHT)) {
			this.moveOnX(position.x + 1);
		}
		if(handler.getKeyListener().down || handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_DOWN)) {
			this.moveOnY(position.y + 1);
		}
	}
		
		//Temporary place where the player must be
		//		if(position.x >= walls.getP1().x && position.x <= walls.getP2().x
		//				&&	position.y >= walls.getP1().y - 100 && position.y <= walls.getP2().y + 100
		//				&& handler.getKeyListener().keyJustPressed(KeyEvent.VK_X)) {
		//				//Must make Question box appear, however this question box must be with the questions and answers already
		//				AddQuestionsBox addQuestionBox = new AddQuestionsBox((width/2)-250, 200,500,500, plane);
		//				addQuestionBox.setVisible(false);
		//				display.getContentPane().add(addQuestionBox);	
		//				}			
		//			}
		//		}
	public void render(Graphics g) {
		if(this.getAvatar() != null)
		g.drawImage(this.getAvatar(),this.getPosition().x, this.getPosition().y, 40, 40,null); //this should be in player 
	}

}


