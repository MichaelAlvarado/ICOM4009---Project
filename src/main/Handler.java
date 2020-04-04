package main;

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
import States.GameState;
import States.QuestionState;
import States.State;

/**
 * 
 * @author Michael J. Alvarado
 * @date Mar 22, 2020
 * This class will be use to handle keyboard, mouse, music, and other IO
 *
 */
public class Handler {

	private Map map;//Map being Player
	private GameState gameState;//this is the gameState where everything is being run at (tick and render)
	private QuestionState questionState;
	private State currentState;
	private KeyManager keyListener;
	private MouseManager mouseListener; 
	private int width, height;

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
		gameState = new GameState(this);
		questionState = new QuestionState(this);
		currentState = gameState;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void tick() {
		keyListener.tick();
		mouseListener.tick();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public QuestionState getQuestionState() {
		return questionState;
	}

	public void setQuestionState(QuestionState questionState) {
		this.questionState = questionState;
	}

	public KeyManager getKeyListener() {
		return keyListener;
	}

	public void setKeyListener(KeyManager keyListener) {
		this.keyListener = keyListener;
	}

	public MouseManager getMouseListener() {
		return mouseListener;
	}

	public void setMouseListener(MouseManager mouseListener) {
		this.mouseListener = mouseListener;
	}

	/**
	 * 
	 * @author Michael
	 *
	 */
	public class KeyManager implements KeyListener {

		private boolean[] keys,justPressed,cantPress;
		public boolean up=false, down=false, left=false, right=false,pbutt=false,runbutt=false;

		public KeyManager(){
			keys = new boolean[256];
			justPressed = new boolean[keys.length];
			cantPress = new boolean[keys.length];
		}

		public void tick(){
			for(int i =0; i < keys.length;i++){
				if(cantPress[i] && !keys[i]){
					cantPress[i]=false;
				}else if(justPressed[i]){
					cantPress[i]=true;
					justPressed[i] =false;
				}
				if(!cantPress[i] && keys[i]){
					justPressed[i]=true;
				}
			}
			up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
			down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]; 
			left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
			right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
			runbutt = keys[KeyEvent.VK_SHIFT];
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
				return;
			keys[e.getKeyCode()] = true;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
				return;
			keys[e.getKeyCode()] = false;
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
		/**
		 * 
		 * @author Michael J. Alvarado
		 * @date Mar 23, 2020
		 *
		 */
		public boolean keyJustPressed(int keyCode){
			if(keyCode < 0 || keyCode >= keys.length)
				return false;
			return justPressed[keyCode];
		}
	}
	/**
	 * 
	 * @author Michael
	 *
	 */
	public class MouseManager implements MouseListener, MouseMotionListener{

		int x,y;//Position of the mouse
		boolean pressed, released;
		int timer = 0;

		public MouseManager(){
			pressed = false;
			released = false;
		}

		public void tick() {
			if(pressed || released) {
				if(timer > 1) {
					pressed = false;
					released = false;
					timer = 0;
				}
				timer++;
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			x = arg0.getX();
			y = arg0.getY();
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			x = arg0.getX();
			y = arg0.getY();
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			pressed = true;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			released = true;
		}
		/**
		 * 
		 * @author Michael J. Alvarado
		 * @date Mar 30, 2020
		 *
		 */
		public boolean clickedOn(Rectangle rec) {
			return released && rec.contains(x,y);
		}
		public boolean pressedOn(Rectangle rec) {
			return pressed && rec.contains(x,y);
		}
	}

}
