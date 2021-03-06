package MapDesingComponents;

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Components.Map;
import Resources.FileManager;

/**
 * 
 * @author Michael J. Alvarado
 * Date - 03/March/2020
 *	This class let the user set a name, size and image to the map its going to create
 */
public class AddMapBox extends JPanel{

	private Plane plane;
	private JTextField name, imageURL, mapWidth, mapHeight;
	private static BufferedImage image;
	private JFileChooser browserImage, browserMap;
	private JLabel nameLabel, imageLabel, mapSize;
	private JButton enter, exit, browseButton, load;
	private Map map;

	public AddMapBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.plane = plane;
		map = new Map();
		map.setWidth(plane.getWidth());
		map.setHeight(plane.getHeight());
		setLayout(null); //This make it possible to set position to components
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Map");
		setBackground(new Color(190,190,190));

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
		browserImage = new JFileChooser();
		browseButton = new JButton("Browse");
		browseButton.setBounds((imageURL.getX()+imageURL.getWidth()), imageURL.getY(), 100, 25);
		browserImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browserImage.getSelectedFile();
				try {						
					imageURL.setText(imageFile.getPath());
					map.setImageURL(imageFile.getPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				browserImage.showOpenDialog(AddMapBox.this);
			}
		});

		//Set the map size (default is the Plane Size)
		mapSize = new JLabel("Map Size (in meters):");
		mapSize.setBounds(10, 100, 140, 25);
		mapWidth = new JTextField(String.valueOf(plane.getWidth()));
		mapWidth.setBounds(mapSize.getX()+mapSize.getWidth(), mapSize.getY(), 50, 25);
		mapHeight = new JTextField(String.valueOf(plane.getHeight()));
		mapHeight.setBounds(mapWidth.getX()+mapWidth.getWidth(), mapWidth.getY(), 50, 25);

		//Enter button
		enter = new JButton("Enter");
		enter.setBounds(width/2-50, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Creates the Map
				int width = Integer.valueOf(mapWidth.getText());
				int height = Integer.valueOf(mapHeight.getText());
				if(mapWidth.getText().toString().length() > 4 || 
						mapHeight.getText().toString().length() > 4) {
					JOptionPane.showMessageDialog(plane, "Map width and map height must be 4 digits or under.");
				}
				else {
					try {
						map.setWidth(width);
						map.setHeight(height);
					}catch(NumberFormatException n) {
						JOptionPane.showMessageDialog(plane, "Invalid map size.\nIt should be Integers");
					}
					map.setMapName(name.getText());
					plane.setMap(map);
					exit(); 
				}
			}
		});

		//Exit button
		exit = new JButton("Exit");
		exit.setVisible(false);
		exit.setBorder(BorderFactory.createEtchedBorder(Color.RED, Color.BLACK));
		exit.setBounds(width-55, 5, 50, 30);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		//LOAD existing map
		browserMap = new JFileChooser();
		browserMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//This should create a Map from the chosen txt file and place it on Place
				try {
					System.out.println("Loading Map");
					map = FileManager.generateMap(browserMap.getSelectedFile());
					System.out.println("Map loaded");
					plane.setMap(map);
					autofill();
					exit();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(plane, "Invalid Map File\nThis map file must be a .txt generated by the App");
				}
			}

		});
		load = new JButton("Open Existing Map");
		load.setBounds(width-190, height-50, 180, 30);
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				browserMap.showOpenDialog(AddMapBox.this);
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
		add(load);
	}
	/**
	 * @author Michael J. Alvarado
	 * Date - 03/March/2020
	 * this method let you edit the map and load the info of the map to this JPanel
	 */
	public void edit() {
		exit.setVisible(true);
		plane.disable();
		autofill();
		setVisible(true);
		setFocusable(true);

	}

	private void exit() {
		plane.enable();
		setVisible(false);
		setFocusable(false);
		plane.repaint();
	}

	private void autofill() {
		name.setText(map.getMapName());
		mapWidth.setText(String.valueOf(map.getWidth()));
		mapHeight.setText(String.valueOf(map.getHeight()));
		imageURL.setText(map.getImageURL());
	}


}
