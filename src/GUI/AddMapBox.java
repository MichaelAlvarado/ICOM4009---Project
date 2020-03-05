package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Components.Map;

/**
 * 
 * @author Michael J. Alvarado
 * Date - 03/March/2020
 *	This class let the user set a name, size and image to the map its going to create
 */
public class AddMapBox extends JPanel{

	JTextField name, imageURL, mapWidth, mapHeight;
	static BufferedImage image;
	JFileChooser browser;
	JLabel nameLabel, imageLabel, mapSize;
	JButton enter, exit, browseButton;
	Map map;
	Plane plane;

	public AddMapBox(int x, int y, int width, int height, Plane plane) {
		super();
		map = new Map();
		this.plane = plane;
		setLayout(null);
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Map");
		this.setBackground(new Color(190,190,190));
		
		//Choose a name (default is Map)
		nameLabel = new JLabel("Name of map");
		nameLabel.setBounds(10, 40, 100,25);
		name = new JTextField("Map");
		name.setBounds(nameLabel.getX()+nameLabel.getWidth(), nameLabel.getY(), (width/2), 25);
		
		//Lets you browse for an image
		imageLabel = new JLabel("Image of map");
		imageLabel.setBounds(10, 70, 100,25);
		imageURL = new JTextField();
		imageURL.setBounds(imageLabel.getX()+imageLabel.getWidth(), imageLabel.getY(), (width/2), 25);
		browser = new JFileChooser();
		browseButton = new JButton("Browse");
		browseButton.setBounds((imageURL.getX()+imageURL.getWidth()), imageURL.getY(), 100, 20);
		browser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browser.getSelectedFile();
				try {						
					System.out.println("loading file...");
					image = ImageIO.read(imageFile);
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
				browser.showOpenDialog(AddMapBox.this);
			}
		});
		
		//Set the map size (default is the Plane Size)
		mapSize = new JLabel("Map Size");
		mapSize.setBounds(10, 100, 100, 25);
		mapWidth = new JTextField(String.valueOf(plane.getWidth()));
		mapWidth.setBounds(mapSize.getX()+mapSize.getWidth(), mapSize.getY(), 50, 25);
		mapHeight = new JTextField(String.valueOf(plane.getHeight()));
		mapHeight.setBounds(mapWidth.getX()+mapWidth.getWidth(), mapWidth.getY(), 50, 25);

		//Enter button
		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Creates the Map
				if(image != null) {
					map.setPicture(image);
				}
				map.setBuildingName(name.getText());
				AddMapBox.this.plane.setMap(map);
				setVisible(false);
				plane.enable(); 
				AddMapBox.this.plane.repaint();
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

		//add all component to this JPanel 
		add(name);
		add(nameLabel);
		add(imageLabel);
		add(imageURL);
		add(mapSize);
		add(mapHeight);
		add(mapWidth);
		add(enter);
		add(exit);
		add(browseButton);
	}
	/**
	 * @author Michael J. Alvarado
	 * Date - 03/March/2020
	 * this method let you edit the map and load the info of the map to this JPanel
	 */
	public void edit() {
		setVisible(true);
	}


}
