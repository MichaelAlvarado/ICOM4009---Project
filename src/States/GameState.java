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
import GameSetUp.Handler;

/**
 * @author Michael J. Alvarado
 * @date Mar 14, 2020
 */
public class GameState implements State{

	private Map map;
	private Player player;
	private int width, height;

	public GameState() {
		width = Handler.getWidth();
		height = Handler.getHeight();
		map = Handler.getMap();
		map.scaleComponentTo(width, height);

		player = new Player("Player" , new Point(100,100));
	}
	/**
	 * @author Michael J. Alvarado
	 * Objective - This method will make the game commands
	 * @date Mar 16, 2020
	 */
	public void tick() {
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
			System.out.println("Pause music");
			Handler.getSoundManager().pauseBackground();
		}
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)) {
			Handler.getSoundManager().resumeBackground();
		}
		map.tick(player);
		player.tick();
	}

	/**
	 * 
	 * @author Michael J. Alvarado
	 * Objective - This method will paint all components of the Game to the Canvas
	 * @date Mar 16, 2020
	 * @param g - from the Canvas in Game Engine
	 */
	public void render(Graphics g) {
		map.render(g);
		player.render(g); 
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
