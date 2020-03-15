/**
 * 
 */
package States;

import java.awt.Graphics;

import Components.Map;

/**
 * @author Michael J. Alvarado
 * @date Mar 14, 2020
 */
public class GameState {
	Map map;
	int width, height;
	public GameState(Map map, int width, int height) {
		this.map = map;
		this.width = width;
		this.height = height;
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(map.getPicture(),0,0,width, height,null);
	}
}