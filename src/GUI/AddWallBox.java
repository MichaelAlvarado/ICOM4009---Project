package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import Components.Building;
import Components.Wall;


/**
 * 
 * @author Fabiola Badillo Ramos
 * Date - March 5, 2020
 *	This class lets the user create a wall and set all its parameters
 */

public class AddWallBox extends JPanel{
	Wall wall;
	JTextField wallHeight, imageURL;
	static BufferedImage texture;
	JFileChooser browser;
	JButton enter, exit, browseButton;
	Plane plane;
	JLabel nameLabel, imageLabel, wallHeightLabel;
	Point p1, p2;

	public AddWallBox(int x, int y, int width, int height, Plane plane) {
		super();
		p1 = new Point(0, 0);
		p2 = new Point(1, 1);
		wall = new Wall(plane.currentBuilding.getName(), plane.currentBuilding.getWalls(), p1, p2);
		this.plane = plane;
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Wall");
		this.setBackground(new Color(190,190,190));
		
		// set the default wall name derived from the building
		nameLabel = new JLabel(wall.getID()); 
		nameLabel.setBounds(10,  40,  100, 25);
		
		// browse for a wall texture
		imageLabel = new JLabel("Wall texture");
		imageLabel.setBounds(10, 70, 100, 25);
		imageURL = new JTextField();
		imageURL.setBounds(imageLabel.getX()+imageLabel.getWidth(), imageLabel.getY(), (width/2), 25);
		browser = new JFileChooser();
		browseButton = new  JButton("Browse");
		browseButton.setBounds((imageURL.getX()+imageURL.getWidth()), imageURL.getY(), 100, 20);
		browser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browser.getSelectedFile();
				try {						
					System.out.println("loading file...");
					texture = ImageIO.read(imageFile);
					System.out.println("load file");
					imageURL.setText(imageFile.getPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				browser.showOpenDialog(AddWallBox.this);
			}
		});
		
		// set the wall height for the 3D representation
		wallHeightLabel = new JLabel("Wall height");
		wallHeightLabel.setBounds(10, 100, 100, 25);
		wallHeight = new JTextField("height");
		wallHeight.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), wallHeightLabel.getY(), (width/2), 25);
		
		// enter button
		
		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(wall != null) {
					wall.setTexture(texture); 
				}
				AddWallBox.this.plane.addWall(wall);
				setVisible(false);
				plane.enable();
				AddWallBox.this.plane.repaint();
			}
		});
		
		//Exit button
				exit = new JButton("Exit");
				exit.setBounds(width/2, height-60, 100, 50);
				exit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						plane.enable();
					}
				});

		
		add(enter);
		add(exit);
		add(browseButton);
		add(wallHeight);
		add(imageURL);
		add(nameLabel);
		add(imageLabel);
		add(wallHeightLabel);
	}
	
	public void edit(Wall wall) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		// name.setText(wall.getID());
		this.wall = wall;
		setVisible(true);
	}
}

