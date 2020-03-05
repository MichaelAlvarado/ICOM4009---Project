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
					Display frame = new Display("Project",1000,800);
					MapDesign designMap = new MapDesign (frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
