package States;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * 
 * @author Michael J. Alvarado
 * @date Mar 22, 2020
 * This class will be use to handle keyboard, mouse, music, and other IO
 *
 */
public class Handler {
	
	GameState gameState;//this is the gameState where everything is being run at (tick and render)
	KeyListener keyListener;
	MouseListener mouseListener; 
	
	public Handler() {
		
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public KeyListener getKeyListener() {
		return keyListener;
	}

	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}

}
