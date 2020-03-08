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

	private Building building;
	private Plane plane;
	private JTextField name, picURL, wallHeight;
	private JButton enter, exit, browseButton;
	private JLabel buildingName, picLabel, wallHeightLabel, p1Label, p2Label, p3Label, p4Label;
	private JFileChooser browser;
	static BufferedImage picture;
	private boolean newBuilding;


	public AddBuildingBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.setLayout(null);
		this.plane = plane;
		//building = new Building(plane.currentBuilding.getName(),plane.currentBuilding.getPicture(), plane.currentBuilding.getQuestionPool(), plane.currentBuilding.getWalls(),plane.currentBuilding.getFound());
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Building");
		this.setBackground(new Color(190,190,190));

		//name = new JTextField("Building");
		buildingName = new JLabel("Building name: "); 
		buildingName.setBounds(10, 40, 100, 25);
		name = new JTextField();
		name.setBounds(buildingName.getX()+buildingName.getWidth(), buildingName.getY(), (width/2), 25);
		
		picLabel = new JLabel("Building Texture");
		picLabel.setBounds(10, 100, 100, 25);
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
		wallHeightLabel.setBounds(10, 70, 100, 25);
		wallHeight = new JTextField();
		wallHeight.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), wallHeightLabel.getY(), (width/2), 25);


		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				building.setName(name.getText());
				building.setBuildingHeight(Integer.valueOf(wallHeight.getText()));
				if(picture != null) {
					building.setPicture(picture);
				} 
				if(newBuilding) {
					plane.addBuilding(building);
				}
				exit();
			}
		});

		exit = new JButton("Exit");
		exit.setBounds(width/2, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

		add(name);	
		add(browseButton);
		add(wallHeight);
		add(picURL);
		add(picLabel);
		add(wallHeightLabel);
		add(buildingName);
		add(enter);
		add(exit);

	}
	public void edit(Building building) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		this.building = building;
		autofill(building);
		setVisible(true);
		newBuilding = false;
	}

	public void addBuilding() {
		building = new Building("");
		setVisible(true);
		plane.disable();
		newBuilding = true;

	}

	private void exit() {
		//Empty the textFields
		name.setText("");
		picURL.setText(""); 
		wallHeight.setText("");

		setVisible(false);
		building = null;
		plane.enable();
	}
	
	private void autofill(Building building) {
		name.setText(building.getName());
		wallHeight.setText(String.valueOf(building.getBuildingHeight()));
	}
}
/*
 * Need to add method to create new building Object
 * input values (name, Color, Image, etc)
 * close and enter button
 */
