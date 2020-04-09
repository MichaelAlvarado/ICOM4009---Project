package GameSetUp;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Components.Map;
import GUI.Display;
import States.GameState;


/**
 * @author Michael J. Alvarado
 * Description: This class runs the game loop.
 * It initialize the keyboard and mouse settings and make the printable canvas for render.
 * 60FPS both render and tick.
 * Date - 11/March/2020
 */

public class GameEngine implements Runnable {
	//Running Game
	private JFrame display;
	private boolean running = false;
	private Thread thread;
	//Paint Game
	public static boolean threadB;
	private BufferStrategy bs;
	private Graphics g;
	private Canvas canvas;
	private static final int FPS = 60;
	private Map map;
	//Handler will have listeners
	private Handler handler;

	public GameEngine(JFrame display, Map map) {
		this.display = display;
		this.map = map;
		threadB = false;
		canvas = new Canvas();
		canvas.setBounds(0, 0, display.getContentPane().getWidth(), display.getContentPane().getHeight());
		canvas.setFocusable(true);
		display.getContentPane().add(canvas);
		handler = new Handler(canvas, map);
	}
	
	public static int getFPS() {
		return FPS;
	}
	
	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This method starts the game engine. 
	 * If this method is not run the game loop will not start.
	 */
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();		//this runs the run method in this  class
	}

	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This method run the Game loop in fps constant
	 * Runs render and tick at same fps
	 */
	public void run(){
		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			//makes sure the games runs smoothly at 60 FPS
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				//re-renders and ticks the game around 60 times per second
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}
		stop();

	}


	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This method is will run the game code 
	 */
	private void tick(){
		handler.tick();
		if(handler.getCurrentState() != null) {
			handler.getCurrentState().tick();
		}
	}

	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This method will draw the game to the canvas	and make a bufferStrategy (preLoad draws)
	 */
	private void render(){
		bs = canvas.getBufferStrategy();

		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		//Clear Screen
		g.clearRect(0, 0,  display.getWidth(), display.getHeight());

		//Draw Here!
		if(handler.getCurrentState() != null) {
			handler.getCurrentState().render(g);
		}
		//		g.setColor(Color.WHITE);
		//		g.drawString("Game Engine Prints", 10, 10);
		//		g.drawRect(200, 0, 50, 50);
		//		g.drawRect(200, canvas.getHeight()-50, 50, 50);
		//End Drawing!
		bs.show();
		g.dispose();
	}

	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This methods stops the game loop (it could be use to exit the game)
	 */
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}