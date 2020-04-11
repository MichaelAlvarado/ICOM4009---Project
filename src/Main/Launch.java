package Main;

import java.awt.EventQueue;

import GUI.Display;
import States.MapDesignState;
import States.MenuState;

public class Launch {
	/*
	 * Launch Application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Loading App...");
					long start = System.nanoTime();
					Display frame = new Display("Project");
					MenuState menu = new MenuState(frame);
					System.out.println("App Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
