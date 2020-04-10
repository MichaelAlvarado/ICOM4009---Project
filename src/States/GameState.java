package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Components.Building;
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

	public GameState() {
		map = Handler.getMap();
		map.scaleComponentTo(Handler.getWidth(), Handler.getHeight());
		player = new Player("Player" , new Point(100,100));
		Handler.getSoundManager().addAudio("background");
	}
	/**
	 * @author Michael J. Alvarado
	 * Objective - This method will make the game commands
	 * @date Mar 16, 2020
	 */
	public void tick() {
		Handler.getSoundManager().resumeAudio("background");
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			Handler.setCurrentState(Handler.getPauseState());
			Handler.getSoundManager().stopAudio("background");
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
		g.setColor(new Color(100,100,100,210));
		g.fillRect(Handler.getWidth()-180, 0, 180, 25);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString(undiscoveredBuildings() + " Building left to discover", Handler.getWidth()-180, 20);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	private int undiscoveredBuildings() {
		int count = map.getBuildingList().size();
		for(Building building:map.getBuildingList()) {
			if(building.getFound()) {
				count--;
			}
		}
		return count;
	}
}
