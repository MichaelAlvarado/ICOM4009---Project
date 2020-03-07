package GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Components.Building;
import Components.Wall;

public class AddBuildingBox extends JPanel{

	Building building;
	Wall wall;
	Plane plane;
	Point p1, p2;
	JTextField name, picURL, wallHeight;
	JButton enter, exit, browseButton;
	JLabel buildingName, picLabel, wallHeightLabel, p1Label, p2Label, p3Label, p4Label;
	JFileChooser browser;
	static BufferedImage picture;
	

	public AddBuildingBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.setLayout(null);
		this.plane = plane;
		building = new Building(plane.currentBuilding.getName(),plane.currentBuilding.getPicture(), plane.currentBuilding.getQuestionPool(), plane.currentBuilding.getWalls(),plane.currentBuilding.getFound());
		wall = new Wall(plane.currentBuilding.getName(), plane.currentBuilding.getWalls(), p1, p2);
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Building");
		this.setBackground(new Color(190,190,190));

		//name = new JTextField("Building");
		buildingName = new JLabel("Building name: "+ building.getName()); 
		buildingName.setBounds(200, 40, 300, 25);
	
		picLabel = new JLabel("Building Texture");
		picLabel.setBounds(10, 70, 100, 25);
		picURL = new JTextField();
		picURL.setBounds(picLabel.getX()+picLabel.getWidth(), picLabel.getY(), (width/2), 25);
		browser = new JFileChooser();
		browseButton = new  JButton("Browse");
		browseButton.setBounds((picURL.getX()+picURL.getWidth())+10, picURL.getY(), 100, 20);
		browser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browser.getSelectedFile();
				try {						
					System.out.println("loading file...");
					picture = ImageIO.read(imageFile);
					System.out.println("load file");
					picURL.setText(imageFile.getPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				browser.showOpenDialog(AddBuildingBox.this);
			}
		});
		
		
		wallHeightLabel = new JLabel("Building height");
		wallHeightLabel.setBounds(10, 100, 100, 25);
		wallHeight = new JTextField();
		wallHeight.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), wallHeightLabel.getY(), (width/2), 25);
		
		
		
		
		
		// manually insert coordinates first 2
		p1Label = new JLabel("First Point of 1st Wall");
		p1Label.setBounds(10, 130, 100, 25);
		MaskFormatter formatP1X = new MaskFormatter();
		try {
			formatP1X.setMask("(###,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP1X.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP1X = new JFormattedTextField(formatP1X);
		formattedTextP1X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP1X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 130, 40, 25);
		
		MaskFormatter formatP1Y = new MaskFormatter();
		try {
			formatP1Y.setMask(" ###)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP1Y.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP1Y = new JFormattedTextField(formatP1Y);
		formattedTextP1Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP1Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 130, 40, 25);
		
		p2Label = new JLabel("End Point of 1st Wall");
		p2Label.setBounds(10,  160,  100,  25);
		MaskFormatter formatP2X = new MaskFormatter();
		try {
			formatP2X.setMask("(###,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP2X.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP2X = new JFormattedTextField(formatP2X);
		formattedTextP2X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP2X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 160, 40, 25);
		MaskFormatter formatP2Y = new MaskFormatter();
		try {
			formatP2Y.setMask(" ###)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP2Y.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP2Y = new JFormattedTextField(formatP2Y);
		formattedTextP2Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP2Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 160, 40, 25);
		
		
		
		
		// manually insert coordinates second 2
		p3Label = new JLabel("First point of 2nd Wall");
		p3Label.setBounds(10, 190, 100, 25);
		MaskFormatter formatP3X = new MaskFormatter();
		try {
			formatP3X.setMask("(###,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP3X.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP3X = new JFormattedTextField(formatP3X);
		formattedTextP3X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP3X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 190, 40, 25);
		
		MaskFormatter formatP3Y = new MaskFormatter();
		try {
			formatP3Y.setMask(" ###)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP3Y.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP3Y = new JFormattedTextField(formatP3Y);
		formattedTextP3Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP3Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 190, 40, 25);
		
		p4Label = new JLabel("End Point of 2nd Wall");
		p4Label.setBounds(10,  220,  100,  25);
		MaskFormatter formatP4X = new MaskFormatter();
		try {
			formatP4X.setMask("(###,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP4X.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP4X = new JFormattedTextField(formatP4X);
		formattedTextP4X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP4X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 220, 40, 25);
		MaskFormatter formatP4Y = new MaskFormatter();
		try {
			formatP4Y.setMask(" ###)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
	        System.exit(-1);
		}
		formatP4Y.setPlaceholderCharacter('_');
		JFormattedTextField formattedTextP4Y = new JFormattedTextField(formatP4Y);
		formattedTextP4Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP4Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 220, 40, 25);
		
		
		
		
		
		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(building != null) {
					building.setPicture(picture);
				}
				else {
					p1.setLocation(Integer.parseInt(formattedTextP1X.getValue().toString().substring(1,4)) , plane.getHeight() - Integer.parseInt(formattedTextP1Y.getValue().toString().substring(1,4)));
					p2.setLocation(Integer.parseInt(formattedTextP2X.getValue().toString().substring(1,4)) , plane.getHeight() - Integer.parseInt(formattedTextP2Y.getValue().toString().substring(1,4)));
					AddBuildingBox.this.plane.addBuilding(building);
					setVisible(false);
					plane.enable();
					AddBuildingBox.this.plane.repaint();
				}
				setVisible(false);
				plane.enable();
			}
		});

		exit = new JButton("Exit");
		exit.setBounds(width/2, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				building = null;
				plane.enable();
			}
		});

		add(buildingName);

		add(browseButton);
		add(wallHeight);
		add(picURL);
		add(picLabel);
		add(wallHeightLabel);
		add(formattedTextP1X);
		add(formattedTextP1Y);
		add(formattedTextP2X);
		add(formattedTextP2Y);
		add(formattedTextP3X);
		add(formattedTextP3Y);
		add(formattedTextP4X);
		add(formattedTextP4Y);
		add(p1Label);
		add(p2Label);
		add(p3Label);
		add(p4Label);
		add(enter);
		add(exit);

	}
	public void edit(Building building) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		name.setText(building.getName());
		this.building = building;
		setVisible(true);

	}
}
/*
 * Need to add method to create new building Object
 * input values (name, Color, Image, etc)
 * close and enter button
 */
