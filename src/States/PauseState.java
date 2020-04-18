package States;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.Display;
import GameSetUp.Handler;
import States.MenuState.MapSelect;
import States.MenuState.charSelect;

public class PauseState implements State {
	
	private Display display;
	private JButton saveMap, exitGame, continueGame,settings, open3D, help;
	private MapSelect mapSelection;
	private charSelect charSelection;
	private JLabel  title;
	private int width, height;
	
	
	public PauseState(){
//		this.display = display;
//		this.width = display.getContentPane().getWidth();
//		this.height = display.getContentPane().getHeight();
//		display.getContentPane().setLayout(null);

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
		
		continueGame = new JButton("Continue");
		continueGame.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(continueGame);
		
		saveMap = new JButton("Save");
		saveMap.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(saveMap);	
		
		open3D = new JButton("Open in 3D");
		open3D.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(open3D);
				
		settings = new JButton("Settings");
		settings.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(settings);
		
		help = new JButton("Help");
		help.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(help);
		
		exitGame = new JButton("Exit to Main Menu");
		exitGame.setFont(new Font("Comic Sans MS", Font.BOLD, 20));	
		panel.add(exitGame);
//		
//		title = new JLabel(display.getTitle(), SwingConstants.CENTER);
//		title.setFont(new Font("Comic Sans MS", Font.BOLD, 90));	
//		panel.add(title);

		responsiveScreen();
//
//		display.getContentPane().add(panel);

		continueGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Loading MapDesign App...");
					long start = System.nanoTime();
					//loadingScreen();
					MapDesignState designMap = new MapDesignState(display);
					System.out.println("Map Design App Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		saveMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The game engine should start here
				setButtonsEnable(false);
				charSelection.setVisible(true);
				charSelection.requestFocus();
			}
		});
		
		
		open3D.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The game engine should start here
				setButtonsEnable(false);
				charSelection.setVisible(true);
				charSelection.requestFocus();
			}
		});
		
		
		settings.addActionListener(new ActionListener() {
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
		
		
		exitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The game engine should start here
				setButtonsEnable(false);
				charSelection.setVisible(true);
				charSelection.requestFocus();
			}
		});
		
//		
//		display.repaint();

	}

	private void responsiveScreen() {
//		width = display.getContentPane().getWidth();
//		height = display.getContentPane().getHeight();
		continueGame.setBounds(width/2+100, height/2-30, 250, 90);
		saveMap.setBounds(width/2+100, height/2-30, 250, 90);
		open3D.setBounds(width/2+100, height/2 + 100, 250, 90);
		settings.setBounds(width/2+100, height/2-30, 250, 90);
		help.setBounds(width/2+100, height/2 + 230, 250, 90);
		exitGame.setBounds(width/2+100, height/2-30, 250, 90);

		
	}
	
	public void setButtonsEnable(boolean arg) {
		continueGame.setEnabled(arg);
		saveMap.setEnabled(arg);
		open3D.setEnabled(arg);
		settings.setEnabled(arg);
		help.setEnabled(arg);
		exitGame.setEnabled(arg);

	}

	@Override
	public void tick() {
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			Handler.setCurrentState(Handler.getGameState());
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
