package GameSetUp;

import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import Components.Building;
import Components.Map;
import GUI.Display;
import Resources.KeyManager;
import Resources.MouseManager;
import Resources.SoundManager;
import States.GameState;
import States.MenuState;
import States.PauseState;
import States.QuestionState;
import States.State;

/**
 * 
 * @author Michael J. Alvarado
 * @date Mar 22, 2020
 * This class will be use to handle keyboard, mouse, music, and other IO
 * PreCondition - These methods should only be access by the Game
 *
 */
public class Handler {
	
	private static Map map;//Map being Player
	private static Display display;
	private static Canvas canvas;
	//States
	private static GameState gameState;
	private static QuestionState questionState;
	private static PauseState pauseState;
	private static State currentState;
	//Managers
	private static KeyManager keyManager;
	private static MouseManager mouseManager; 
	private static SoundManager soundManager;

	public Handler(Display display, Canvas canvas, Map map) {
		Handler.map = map;
		Handler.display = display;
		Handler.canvas = canvas;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		soundManager = new SoundManager();
		canvas.setFocusable(true);
		canvas.requestFocusInWindow();
		canvas.addKeyListener(keyManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		gameState = new GameState();
		questionState = new QuestionState();
		pauseState = new PauseState();
		currentState = gameState;
	}

	public static Map getMap() {
		return map;
	}

	public static void tick() {
		keyManager.tick();
		mouseManager.tick();
	}

	public static int getWidth() {
		return canvas.getWidth();
	}

	public static int getHeight() {
		return canvas.getHeight();
	}


	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		Handler.currentState = currentState;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static QuestionState getQuestionState() {
		return questionState;
	}

	public static KeyManager getKeyManager() {
		return keyManager;
	}

	public static MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public static SoundManager getSoundManager() {
		return soundManager;
	}

	public static PauseState getPauseState() {
		return pauseState;
	}
	
	public static void returnMenuState() {
		display.getContentPane().removeAll();	
		display.setLoadingScreen();
		MenuState menu = new MenuState(display);
	}

}
