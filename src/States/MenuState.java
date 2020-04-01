package States;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Components.Map;
import Components.Wall;
import GUI.AddWallBox;
import GUI.Display;
import GUI.Plane;
import main.GameEngine;

/**
 * 
 * @author Michael J. Alvarado
 * Date - 10/March/2020
 *	This is class is the Menu state.
 *  Its responsible of changing to the DesignMap or to the game
 */
public class MenuState{

	JFrame display;
	JButton createMap, playGame, help;
	int width, height;

	public MenuState(JFrame display) throws IOException {
		this.display = display;
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight();
		display.getContentPane().setLayout(null);

		JPanel panel = new JPanel(); 
		panel.setLayout(null);
		panel.setBounds(0, 0, width, height);

		MapSelect selection = new MapSelect(width/2-250, height/2-100,500,200, this);
		selection.setVisible(false);
		panel.add(selection);

		createMap = new JButton("Create new Map");
		createMap.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		createMap.setBounds(width/2+100, height/2-30, 250, 90);
		panel.add(createMap);

		playGame = new JButton("Play Game");
		playGame.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		playGame.setBounds(width/2+100, height/2 + 100, 250, 90);
		panel.add(playGame);

		help = new JButton("Help");
		help.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		help.setBounds(width/2+100, height/2 + 230, 250, 90);
		panel.add(help);

		JLabel title = new JLabel(display.getTitle(), SwingConstants.CENTER);
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 90));	
		title.setBounds(width/2, 150, 400, 140);
		panel.add(title);

		BufferedImage img = ImageIO.read(new File("res/MenuSky1.png")); 
		Image dimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH); //scale the image to fit JFrame
		JLabel picLabel = new JLabel(new ImageIcon(dimg)); //add the image to a picLabel to display on the component
		picLabel.setLayout(null);
		picLabel.setBounds(0,0,width,height);
		panel.add(picLabel);

		display.getContentPane().add(panel);
		/*
		 * Actions
		 */
		createMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Loading MapDesign App...");
					long start = System.nanoTime();
					loadingScreen();
					MapDesign designMap = new MapDesign(display);
					System.out.println("Map Design App Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		playGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The game engine should start here
				selection.setVisible(true);
				setButtonsEnable(false);
			}
		});

		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String help = "Create Map buttons allows you to create your own map to play.\n"+
						"Play Game buttons can get you playing with map already made.\n"+
						"Have fun XD"+
						"\n\n" +
						"This app was made by: \n"+
						"	Michael Alvarado\r\n" + 
						"	Fabiola Badillo\r\n" + 
						"	Anel Martinez\r\n" + 
						"	Jorge Calderon\n\n";
				JOptionPane.showMessageDialog(display, help);
			}
		});
		display.repaint();
	}
	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * This methods draw the Loading Screen Image and remove all Content in the display
	 */
	public void loadingScreen() {
		try {
			display.getContentPane().getGraphics().drawImage(ImageIO.read(new File("res/loadingscreen.png")), 0, 0, width, height, null);
			display.getContentPane().removeAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author Michael J. Alvarado
	 * Date - 12/March/2020
	 * @param arg - true if enable buttons, false if disable button
	 */
	public void setButtonsEnable(boolean arg) {
		createMap.setEnabled(arg);
		playGame.setEnabled(arg);
		help.setEnabled(arg);
	}

	/**
	 * 
	 * @author Michael J. Alvarado
	 * Date - 10/March/2020
	 *
	 */
	public class MapSelect extends JPanel{

		Map map;
		JTextField mapURL;
		JFileChooser browser;
		JButton enter, exit, browseButton, defaultMap;
		JLabel mapLabel;
		MenuState menu;

		public MapSelect(int x, int y, int width, int height, MenuState menu) {
			super();
			this.menu = menu;
			map = new Map();
			setLayout(null);
			setBounds(x, y, width, height);
			setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
			setBackground(new Color(190,190,190));


			// browse for a wall texture
			mapLabel = new JLabel("Map File URL");
			mapLabel.setBounds(10, 70, 100, 25);
			mapURL = new JTextField();
			mapURL.setBounds(mapLabel.getX()+mapLabel.getWidth(), mapLabel.getY(), (width/2), 25);
			browser = new JFileChooser();
			browseButton = new  JButton("Browse");
			browseButton.setBounds((mapURL.getX()+mapURL.getWidth())+10, mapURL.getY(), 100, 20);
			browser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						map.generateMap(browser.getSelectedFile());
					} catch (IOException e) {
						e.printStackTrace();
					}
					mapURL.setText(browser.getSelectedFile().getPath());

				}
			});
			browseButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					browser.showOpenDialog(MapSelect.this);
				}
			});

			// enter button
			enter = new JButton("Start Game");
			enter.setBounds(width/2-100, height-60, 200, 50);
			enter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//Start
					System.out.println("Loading GameEngine...");
					long start = System.nanoTime();
					menu.loadingScreen();
					GameEngine gameEngine = new GameEngine(display, map);
					gameEngine.start();
					System.out.println("GameEngine Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
					exit();
				}
			});

			exit = new JButton("Exit");
			exit.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.BLACK));
			exit.setBounds(width-55, 5, 50, 30);
			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					exit();
				}
			});
			
			//Tutorial Default Map
			defaultMap = new JButton("Default Map");
			defaultMap.setBounds(10, 10, 150, 30);
			defaultMap.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						map.generateMap(new File("TutorialMapConfigurationFile.txt"));
						enter.getActionListeners()[0].actionPerformed(arg0);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			
			add(enter);
			add(exit);
			add(browseButton);
			add(mapURL);
			add(mapLabel);
			add(defaultMap);
		}

		private void exit() {
			setVisible(false);
			menu.setButtonsEnable(true);
		}


	}
}
