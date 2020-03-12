package States;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import GUI.Display;


/**
 * @author Michael J. Alvarado
 * Description: This class runs the game loop.
 * It initialize the keyboard and mouse settings and make the printable canvas for render.
 * 60FPS both render and tick.
 * Date - 11/March/2020
 */

public class GameEngine implements Runnable {
	
	private JFrame display;
	private boolean running = false;
	private Thread thread;
	public static boolean threadB;
	private BufferStrategy bs;
	private Graphics g;
	private Canvas canvas;
	private final int fps = 60;
	
	public GameEngine(JFrame display) {
		this.display = display;
		threadB = false;

        canvas = new Canvas();
        canvas.setBounds(0, 0, display.getWidth(), display.getHeight());
        canvas.setFocusable(false);
        canvas.setBackground(Color.black); //Testing

        display.add(canvas);
        display.pack();
     
        init();
	}
	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This method will load files needed to play and add all the Mouse and Key Listener to play the game
	 */
	private void init(){

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

		//initiallizes everything in order to run without breaking
		init();

		double timePerTick = 1000000000 / fps;
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
		g.setColor(Color.WHITE);
		g.drawString("Game Engine Prints", 10, 200);
		
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