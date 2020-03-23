/**
 * 
 */
package States;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Components.Map;

/**
 * @author Michael J. Alvarado
 * @date Mar 14, 2020
 */
public class GameState {

	Map map;
	int width, height;
	Handler handler;

	public GameState(Map map, Handler handler) {
		this.map = map;
		this.handler = handler;
		this.width = handler.getWidth();
		this.height = handler.getHeight();
	}

	public void tick() {
		//map.tick();
		if(handler.getKeyListener().keyJustPressed(KeyEvent.VK_SPACE)) {
			System.out.println("working");
		}
		if(handler.getKeyListener().up) {
			System.out.println("Moving Up");
		}
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
		//map.render(g);
	}
}
