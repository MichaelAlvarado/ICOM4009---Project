package Main;

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
import States.GameState;
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
	private static GameState gameState;//this is the gameState where everything is being run at (tick and render)
	private static QuestionState questionState;
	private static State currentState;
	private static KeyManager keyListener;
	private static MouseManager mouseListener; 
	private static int width, height;

	public Handler(Canvas canvas, Map map) {
		this.map = map;
		width = canvas.getWidth();
		height = canvas.getHeight();
		keyListener = new KeyManager();
		mouseListener = new MouseManager();
		canvas.setFocusable(true);
		canvas.requestFocusInWindow();
		canvas.addKeyListener(keyListener);
		canvas.addMouseMotionListener(mouseListener);
		canvas.addMouseListener(mouseListener);
		gameState = new GameState();
		questionState = new QuestionState();
		currentState = gameState;
	}

	public static Map getMap() {
		return map;
	}

	public static void tick() {
		keyListener.tick();
		mouseListener.tick();
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
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

	public static KeyManager getKeyListener() {
		return keyListener;
	}

	public static MouseManager getMouseListener() {
		return mouseListener;
	}

}
