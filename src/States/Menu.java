package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.MapDesign;

/**
 * 
 * @author Michael J. Alvarado
 *	This is class is the Menu state. Its responsible of changing to the DesignMap or to the game
 */
public class Menu{

	JFrame display;
	int width, height;

	public Menu(JFrame display) throws IOException {
		this.display = display;
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight();
		display.setLayout(null);

		JPanel panel = new JPanel(); 
		panel.setLayout(null);
		panel.setBounds(0, 0, width, height);
		
		JButton createMap = new JButton("Create new Map");
		createMap.setBounds(width/2-100, height/2, 200, 50);
		panel.add(createMap);
		
		JButton playGame = new JButton("Play Game");
		playGame.setBounds(width/2-100, height/2 + 60, 200, 50);
		panel.add(playGame);
		
		JLabel title = new JLabel(display.getTitle(), SwingConstants.CENTER);
		title.setFont(new Font("Comic Sans MS", Font.BOLD, 90));	
		title.setBounds(width/2-300, 150, 600, 100);
		panel.add(title);
		
		BufferedImage img = ImageIO.read(new File("res/backgroundMenu.jpg")); 
		Image dimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH); //scale the image to fit JFrame
		JLabel picLabel = new JLabel(new ImageIcon(dimg)); //add the image to a picLabel to display on the component
		picLabel.setLayout(null);
		picLabel.setBounds(0,0,width,height);
		panel.add(picLabel);
		
		display.add(panel);
		/*
		 * Actions
		 */
		createMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Loading MapDesign App...");
					long start = System.nanoTime();
					display.getContentPane().getGraphics().drawImage(ImageIO.read(new File("res/loadingscreen.png")), 0, 0, width, height, null); //loading screen 
					display.getContentPane().removeAll();
					MapDesign designMap = new MapDesign (display);
					System.out.println("Map Design App Loaded in: " + ((System.nanoTime()-start)/1000000000.0) + " seconds");
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		playGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// The game engine should start here
				System.out.println("Game should start");
			}
			
		});
	}

}
