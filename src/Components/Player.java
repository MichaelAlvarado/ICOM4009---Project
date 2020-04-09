package Components;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GameEngine;
import Main.Handler;
import Resources.Animation;

/**
 * @author Fabiola Badillo Ramos
 * This class describes the Player instances] with all its attributes and methods
 *Date - March 20, 2020
 */

public class Player {

	private Rectangle bound;
	private BufferedImage avatar;
	private PlayerAnimation animation;
	private boolean walking;
	private String name;
	Building building; 
	Wall walls;
	private boolean debuggingMode;

	public Player(String name, Point initialPosition) {
		this.name = name;
		bound = new Rectangle(initialPosition.x, initialPosition.y, 40,40);
		animation = new PlayerAnimation();
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
	public Point getPosition() {
		return bound.getLocation();
	}
	public void moveOnX(int x) {
		bound.setLocation(x, bound.y);
	}
	public void moveOnY(int y) {
		bound.setLocation(bound.x, y);
	}
	public Rectangle getBound() {
		return bound;
	}


	/**
	 * Description - This method has player movement on each direction (UP, DOWN, LEFT, RIGHT).
	 * Precondition - This method should only be use in Game
	 * @author - Anel Martinez 
	 * @date March 23, 2020
	 */
	public void tick() {
		walking = false;
		if(Handler.getKeyListener().up || Handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_UP)) {
			this.moveOnY(bound.y - 1);
			walking = true;
		}
		if(Handler.getKeyListener().left || Handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_LEFT)) {
			this.moveOnX(bound.x - 1);
			walking = true;
		}
		if(Handler.getKeyListener().right || Handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_RIGHT)) {
			this.moveOnX(bound.x + 1);
			walking = true;
		}
		if(Handler.getKeyListener().down || Handler.getKeyListener().keyJustPressed(KeyEvent.VK_KP_DOWN)) {
			this.moveOnY(bound.y + 1);
			walking = true;
		}
		if(Handler.getKeyListener().keyJustPressed(KeyEvent.VK_C)) {
			debuggingMode = !debuggingMode;
		}
	}

	/**
	 * Description - This method paints the player of Game in Canvas
	 * Precondition - This method should only be use in Game
	 * @author - Anel Martinez
	 * @date March 23, 2020
	 */
	public void render(Graphics g) {
		if(walking)
			animation.getAnimation().startAnimation();
		g.drawImage(animation.getPlayerFrame(),getPosition().x, getPosition().y, bound.width, bound.height,null); 
		if(debuggingMode)
			g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}

	/**
	 * Description - This class has the player images and animates it when walking
	 * @author - Michael J. Alvarado
	 * @date March 29, 2020
	 */
	private class PlayerAnimation {

		private BufferedImage idle;
		private BufferedImage[] walk;
		Animation animation;

		public PlayerAnimation(){
			walk = new BufferedImage[5];
			try {
				idle = ImageIO.read(new File("res/animation_Images/Idle (1).png"));
				walk[0] = idle;
				walk[1] =  ImageIO.read(new File("res/animation_Images/Walk (1).png"));
				walk[2] =  ImageIO.read(new File("res/animation_Images/Walk (3).png"));
				walk[3] =  ImageIO.read(new File("res/animation_Images/Walk (5).png"));
				walk[4] =  ImageIO.read(new File("res/animation_Images/Walk (7).png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			animation = new Animation(walk, 0.6);
		}


		/**
		 * 
		 * Description - It animates walk images
		 * PreCondition - This method should be use in render(g)
		 * @author - Michael J. Alvarado
		 * @date Apr 9, 2020
		 */
		public BufferedImage getPlayerFrame() {
			return animation.getCurrentFrame();
		}
		
		public Animation getAnimation() {
			return animation;
		}

	}

}


