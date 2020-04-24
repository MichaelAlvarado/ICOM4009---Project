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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Components.Map;
import Components.Wall;
import GUI.Display;
import GameSetUp.GameEngine;
import MapDesingComponents.AddWallBox;
import MapDesingComponents.Plane;
import Resources.FileManager;
import Resources.Images;

/**
 * 
 * @author Michael J. Alvarado
 * Date - 10/March/2020
 *	This is class is the Menu state.
 *  Its responsible of changing to the DesignMap or to the game
 */
public class MenuState{

	private Display display;
	private JButton createMap, playGame, help;
	private MapSelect mapSelection;
	private charSelect charSelection;
	private JLabel  title;
	private int width, height;

	public MenuState(Display display){
		this.display = display;
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight();
		display.getContentPane().setLayout(null);

		JPanel panel = new JPanel(){
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				setSize(width,height);
				responsiveScreen();
			}
		};
		panel.setLayout(null);
		panel.setBounds(0, 0, width, height);

		charSelection = new charSelect(width/2-250, height/2-100,500,200, this);
		charSelection.setVisible(false);
		panel.add(charSelection);

		mapSelection = new MapSelect(width/2-250, height/2-100,500,200, this);
		mapSelection.setVisible(false);
		panel.add(mapSelection);

		createMap = new JButton("Map Design");
		createMap.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(createMap);

		playGame = new JButton("Play Game");
		playGame.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(playGame);

		help = new JButton("Help");
		help.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(help);

		title = new JLabel(display.getTitle(), SwingConstants.CENTER);
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 90));	
		panel.add(title);

		BufferedImage img;
		try {
			img = ImageIO.read(new File("res/backgrounds/MenuSky1.png"));
			JLabel picLabel = new JLabel() {
				@Override
				public void paint(Graphics g) {
					g.drawImage(img, 0, 0, display.getContentPane().getWidth(), display.getContentPane().getHeight(), null);
					setBounds(0,0,display.getContentPane().getWidth(),display.getContentPane().getHeight());
				} 
			}; 
			picLabel.setBounds(0,0,display.getContentPane().getWidth(),display.getContentPane().getHeight());
			panel.add(picLabel);

		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		responsiveScreen();

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
					MapDesignState designMap = new MapDesignState(display);
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
				setButtonsEnable(false);
				charSelection.setVisible(true);
				charSelection.requestFocus();
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
		display.setLoadingScreen();
		display.getContentPane().removeAll();
	}

	/**
	 * 
	 * Description - This method set the position of all responsive Element
	 * Responsive means its ajust then resizing display
	 * @author - Michael J. Alvarado
	 * @date Apr 9, 2020
	 */
	private void responsiveScreen() {
		width = display.getContentPane().getWidth();
		height = display.getContentPane().getHeight();
		createMap.setBounds(width/2+100, height/2-30, 250, 90);
		playGame.setBounds(width/2+100, height/2 + 100, 250, 90);
		help.setBounds(width/2+100, height/2 + 230, 250, 90);
		mapSelection.setBounds(width/2-250, height/2-100,500,200);
		charSelection.setBounds(width/2-250, height/2-100,500,200);
		title.setBounds(width/2, 150, 400, 140);
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
			map = null;
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
					if(!mapURL.getText().isEmpty()) {		
						loadMap();
					}
					else {
						JOptionPane.showMessageDialog(display, "Please Choose Your Map");
					}
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
					mapURL.setText("TutorialMapConfigurationFile.txt");
					loadMap();
				}
			});

			add(enter);
			add(exit);
			add(browseButton);
			add(mapURL);
			add(mapLabel);
			add(defaultMap);
		}

		private void loadMap() {
			try {
				menu.loadingScreen();
				System.out.println("Loading GameEngine...");
				long start = System.nanoTime();
				new Images(charSelection.getSelectedIndex());
				map = FileManager.generateMap(new File(mapURL.getText()));
				GameEngine gameEngine = new GameEngine(display, map);
				gameEngine.start();
				System.out.println("GameEngine Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		private void exit() {
			setVisible(false);
			menu.setButtonsEnable(true);
		}

	}

	public class charSelect extends JPanel{


		JButton enter, exit;
		JList characterSel;
		JLabel charLabel;
		MenuState menu;

		public charSelect(int x, int y, int width, int height, MenuState menu) {
			super();
			this.menu = menu;
			setLayout(null);
			setBounds(x, y, width, height);
			setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
			setBackground(new Color(190,190,190));

			// browse for a wall texture
			charLabel = new JLabel("Choose Your Character");
			charLabel.setBounds(10, 70, 150, 25);


			String num[]= { "Boy","Girl","Adventure Boy","Adventure Girl","Cat","Dog"};
			characterSel = new JList(num);
			characterSel.setSelectedIndex(0);
			characterSel.setBounds(charLabel.getX()+charLabel.getWidth(), charLabel.getY()-50, (width/2)-75, 110);


			// enter button
			enter = new JButton("OK");
			enter.setBounds(width/2-100, height-60, 200, 50);
			enter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					//Start
					// The game engine should start here
					mapSelection.setVisible(true);
					charSelection.setVisible(false);
					setButtonsEnable(false);
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

			add(enter);
			add(exit);
			add(charLabel);
			add(characterSel);
		}
		public int getSelectedIndex() {
			return characterSel.getSelectedIndex();
		}

		private void exit() {
			setVisible(false);
			menu.setButtonsEnable(true);
		}

	}

}
