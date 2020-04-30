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

import Components.Building;
import Components.Map;
import GUI.Display;
import GameSetUp.GameEngine;
import GameSetUp.Handler;
import Resources.Button;
import Resources.FileManager;
import Resources.Images;
import States.MenuState.MapSelect;
import States.MenuState.charSelect;

public class PauseState implements State {

	Button continueGame, saveMap, open3D, settings, help, help_exit, exitGame;
	Label helpLabel;
	boolean is_help = false;


	public PauseState(){



		continueGame = new Button("Continue", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2)- 120, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				Handler.setCurrentState(Handler.getGameState());				
			}	
		};


		open3D = new Button("Open 3D World", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) - 80, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				if (Handler.undiscoveredBuildings() == 0)
					FileManager.openFile(new File("MapVRML.wrl"));
				else {
					Handler.setCurrentState(Handler.getGameState());
				}
			}	
		};

		settings = new Button("Mute / Unmute", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) - 40, 110, 30, Color.CYAN) {

			@Override
			public void action() {				
				Handler.setCurrentState(Handler.getGameState());	
				Handler.getSoundManager().soundToggle();
			}
		};

		help = new Button("Help", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2), 110, 30, Color.CYAN) {

			@Override
			public void action() {
				is_help = true;
			}	
		};

		help_exit = new Button("Exit", 15, (Handler.getWidth()/2), (Handler.getHeight()/2) + 135, 110, 30, Color.CYAN) {

			@Override
			public void action() {
				is_help = false;
			}	
		};

		exitGame = new Button("Exit Game", 15, (Handler.getWidth()/2)-50, (Handler.getHeight()/2) + 40, 110, 30, Color.CYAN) {

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

			g.fillRoundRect((Handler.getWidth()/2)-(width/2)- 100, (Handler.getHeight()/2)-(height/2), width + 300, height + 70, 20, 20);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString("Help the character find all the buildings! Use arrow keys or 'WASD' to move the character around.", (Handler.getWidth()/2)-(width/2) - 50, (Handler.getHeight()/2)-(height/2)+30);
			g.drawString("The remaining number of buildings to find are shown in the upper right screen ", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+50);
			g.drawString("The closer you get your chacter to the building the more visible the building will become.", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+70);
			g.drawString("To interact with it press the F to discover the building!", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+90);
			g.drawString("You must answer correctly 3 out of the 4 possible questions to discover the building.", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+110);
			g.drawString("If you fail to answer correctly, you can try again as many times as you want!", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+130);
			g.drawString("Once all building are discovered you can view the Map in 3D!", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+150);
			g.drawString("In the Pause Menu, press continue to resume playing", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+170);
			g.drawString("If the conditions are met, you can view the 3D Map by pressing Open 3D World", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+190);	
			g.drawString("The mute/ unmute button will toggle sound on and off", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+210);
			g.drawString("Exit Game button will take you to the Main Menu Screen", (Handler.getWidth()/2)-(width/2) -50, (Handler.getHeight()/2)-(height/2)+230);
			

			help_exit.render(g);
		} else {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, Handler.getWidth(), Handler.getHeight());
			continueGame.render(g);
			open3D.render(g);
			settings.render(g);
			exitGame.render(g);
			help.render(g);		

		}

	}



}
