package main;

import java.awt.EventQueue;

import GUI.Display;
import GUI.MapDesign;

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
					Display frame = new Display("Project",1000,800);
					MapDesign designMap = new MapDesign (frame);
					System.out.println("App Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
