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

	public GameState(Handler handler) {
		this.handler = handler;
		width = handler.getWidth();
		height = handler.getHeight();
		map = handler.getMap();
		map.scaleComponentTo(width, height);

		player = new Player("Player" , new Point(100,100));
	}

	public void tick() {
		map.tick(player,handler);
		player.tick(handler);
	}

	/**
	 * 
	 * @author Michael J. Alvarado
	 * @date Mar 16, 2020
	 * @param g - from the Canvas in Game Engine
	 */
	public void render(Graphics g) {
		map.render(g,handler);
		player.render(g); 
	}
}
