package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Components.Map;
import GUI.Display;
import GameSetUp.GameEngine;
import GameSetUp.Handler;
import Resources.Button;
import Resources.ConfigurationFile;
import Resources.Images;
import States.MenuState.MapSelect;
import States.MenuState.charSelect;

public class PauseState implements State {

	Button continueGame, saveMap, open3D, settings, help, help_exit, exitGame;
	Label helpLabel;
	GameState gameS;
	boolean is_help = false;


	public PauseState(){



		continueGame = new Button("Continue", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)- 120, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());				
			}	
		};


		saveMap = new Button("Save Map", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) - 80, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());				
			}	
		};


		open3D = new Button("Open 3D World", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) - 40, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				ConfigurationFile.openFile(new File("MapVRML.wrl"));
			}	
		};

		settings = new Button("Settings", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 110, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());
				Handler.getSoundManager().stopAudio("background");


			}
		};

		help = new Button("Help", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) + 40, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				is_help = true;
			}	
		};

		help_exit = new Button("Exit", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) + 40, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				is_help = false;
			}	
		};

		exitGame = new Button("Exit Game", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) + 80, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.returnMenuState();
			}		
		};

	}




	@Override
	public void tick() {
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			Handler.setCurrentState(Handler.getGameState());
		}
		if(is_help==true) {
			help_exit.tick();
		} else {
			continueGame.tick();
			saveMap.tick();
			open3D.tick();
			settings.tick();
			help.tick();
			exitGame.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		int width = 500;
		int height = 250;
		if(is_help == true) {

			g.fillRoundRect((Handler.getWidth()/2)-(width/2), (Handler.getHeight()/2)-(height/2), width, height, 20, 20);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.drawString("Insert Help Info Here:", (Handler.getWidth()/2)-(width/2) + 10, (Handler.getHeight()/2)-(height/2)+25);
			help_exit.render(g);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, Handler.getWidth(), Handler.getHeight());
			continueGame.render(g);
			saveMap.render(g);
			open3D.render(g);
			settings.render(g);
			exitGame.render(g);
			help.render(g);		

		}

	}



}
