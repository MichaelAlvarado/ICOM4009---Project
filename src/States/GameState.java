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
import Resources.Animation;
import Resources.Button;
import Resources.FileManager;
import Resources.Images;
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
	private Animation youWin;
	private Animation congratulation;
	private Button yes, no;
	private boolean goBackToGame;

	public GameState() {
		goBackToGame = false;
		map = Handler.getMap();
		map.scaleComponentTo(Handler.getWidth(), Handler.getHeight());
		player = new Player("Player" , new Point(160,Handler.getHeight()-100));
		Handler.getSoundManager().addAudio("background");
		youWin = new Animation(Images.youWin, (Handler.getWidth()/2)-500, 50, 900, 900, 1.2);
		congratulation = new Animation(Images.yay, (Handler.getWidth()/2)-(Handler.getWidth()/8), (Handler.getHeight()/2)-(Handler.getHeight()/4), 400, 400, 1.2);
		
		yes = new Button("Yes", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 100, 30, Color.YELLOW) {
			@Override
			public void action() {
				FileManager.openFile(new File("MapVRML.wrl"));
				goBackToGame = true;
			}
			
		};
		
		no = new Button("No", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)+40, 100, 30, Color.YELLOW) {
			@Override
			public void action() {
				goBackToGame = true;
			}
			
		};
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
		if (Handler.undiscoveredBuildings() == 0) {
			//youWin.startAnimation();
			yes.tick();
			no.tick();
		}
	}

	/**
	 * 
	 * @author Michael J. Alvarado
	 * Objective - This method will paint all components of the Game to the Canvas
	 * @date Mar 16, 2020
	 * @param g - from the Canvas in Game Engine
	 */
	public void render(Graphics g) {
		int width = 500;
		int height = 250;
		map.render(g);
		player.render(g); 
		g.setColor(new Color(100,100,100,210));
		g.fillRect(Handler.getWidth()-180, 0, 180, 25);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString(Handler.undiscoveredBuildings() + " Building left to discover", Handler.getWidth()-180, 20);
		if (Handler.undiscoveredBuildings() == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, Handler.getWidth(), Handler.getHeight());
			youWin.render(g);
			g.fillRoundRect((Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2), width, height, 20, 20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString("Do you want to see the 3D map?", (Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2)+25);
			yes.render(g);
			no.render(g);
			if (goBackToGame) {
				goToDiscoveredMap(g);
			}
		}
		congratulation.render(g);
	}
	
	private void goToDiscoveredMap(Graphics g) {
		map.render(g);
		player.render(g); 
		g.setColor(new Color(100,100,100,210));
		g.fillRect(Handler.getWidth()-180, 0, 180, 25);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString(Handler.undiscoveredBuildings() + " Building left to discover", Handler.getWidth()-180, 20);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	

	public Animation getCongratulation() {
		return congratulation;
	}

}
