/**
 * 
 */
package States;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Components.Map;
import Components.Player;
import main.Handler;

/**
 * @author Michael J. Alvarado
 * @date Mar 14, 2020
 */
public class GameState implements State{

	Map map;
	Player player;
	int width, height;
	Handler handler;

	public GameState(Map map, Handler handler) {
		this.map = map;
		this.handler = handler;
		this.width = handler.getWidth();
		this.height = handler.getHeight();
		player = new Player("Player" , new Point(100,100));

		try {
			player.setAvatar(ImageIO.read(new File("res/animation_Images/Idle (1).png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void tick() {
		//map.tick();
		player.tick(handler);
		/*
		 * This code makes the player moved but should be in Player tick method
		 */
//		if(handler.getKeyListener().keyJustPressed(KeyEvent.VK_SPACE)) {
//			System.out.println("Jump");
//		}
//		if(handler.getKeyListener().up) {
//			player.moveTo(new Point(player.getPosition().x, player.getPosition().y-1));
//		}
//		if(handler.getKeyListener().down) {
//			player.moveTo(new Point(player.getPosition().x, player.getPosition().y+1));
//		}
//		if(handler.getKeyListener().right) {
//			player.moveTo(new Point(player.getPosition().x+1, player.getPosition().y));
//		}
//		if(handler.getKeyListener().left) {
//			player.moveTo(new Point(player.getPosition().x-1, player.getPosition().y));
//		}
	}

	/**
	 * 
	 * @author Michael J. Alvarado
	 * @date Mar 16, 2020
	 * @param g - from the Canvas in Game Engine
	 */
	public void render(Graphics g) {
		if(map.getPicture() != null)
			g.drawImage(map.getPicture(),0,0,width, height,null);
		g.drawImage(player.getAvatar(),player.getPosition().x, player.getPosition().y, 40, 40,null); //this should be in player 
		//map.render(g);
		//player.render(g); 
	}
}
