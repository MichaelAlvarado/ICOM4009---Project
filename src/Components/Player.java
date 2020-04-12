package Components;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import GameSetUp.GameEngine;
import GameSetUp.Handler;
import Resources.Animation;
import Resources.Images;
import States.MenuState;
import States.MenuState.charSelect;

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
	private boolean debuggingMode;
	private int speed;

	public Player(String name, Point initialPosition) {
		this.name = name;
		bound = new Rectangle(initialPosition.x, initialPosition.y, 40,40);
		animation = new PlayerAnimation();
		speed = 1;
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
		if(Handler.getKeyManager().runbutt) {
			speed = 2;
		}
		else {
			speed = 1;
		}
		if(Handler.getKeyManager().up && bound.y > 0) {
			this.moveOnY(bound.y - speed);
			walking = true;
		}
		if(Handler.getKeyManager().left && bound.x > 0) {
			this.moveOnX(bound.x - speed);
			walking = true;
			animation.setRight(false);
		}
		if(Handler.getKeyManager().right && bound.x+bound.width < Handler.getWidth()) {
			this.moveOnX(bound.x + speed);
			walking = true;
			animation.setRight(true);
		}
		if(Handler.getKeyManager().down && bound.y+bound.height < Handler.getHeight()) {
			this.moveOnY(bound.y + speed);
			walking = true;
		}
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
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

		private Animation animation;
		private boolean right; //if player looking right

		public PlayerAnimation(){
			Handler.getSoundManager().addAudio("footsteps");			
			animation = new Animation(Images.CharacterSpriteRight, 0.6);
			setRight(true);
		}

		/**
		 * Description - It animates walk images
		 * PreCondition - This method should be use in render(g)
		 * @author - Michael J. Alvarado
		 * @date Apr 9, 2020
		 */
		public BufferedImage getPlayerFrame() {
			if(!walking) {
				if(right)
					animation.setAnimation(Images.CharacterSpriteIdleRight);
				else
					animation.setAnimation(Images.CharacterSpriteIdleLeft);
				Handler.getSoundManager().stopAudio("footsteps");
				animation.stopAnimation();
			}
			else {
				if(right) 
					animation.setAnimation(Images.CharacterSpriteRight);

				else 
					animation.setAnimation(Images.CharacterSpriteLeft);
				Handler.getSoundManager().resumeAudio("footsteps");
				animation.startAnimation();
			}
			return animation.getCurrentFrame();
		}

		public Animation getAnimation() {
			return animation;
		}


		public boolean isRight() {
			return right;
		}


		public void setRight(boolean right) {
			this.right = right;
		}

	}

}


