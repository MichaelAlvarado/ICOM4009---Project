package GUI;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Components.Building;
import Components.Wall;



/**
 * 
 * @author Anel Martinez Gomez
 * Date - March 6, 2020
 *	This class lets the user set building parameters like image, height, and name
 */

public class AddBuildingBox extends JPanel{

	private Building building;
	private Plane plane;
	private JTextField name, picURL, wallHeight;
	private JButton enter, exit, browseButton, remove;
	private JLabel buildingName, picLabel, wallHeightLabel;
	private JFileChooser browser;
	static BufferedImage picture;
	private boolean newBuilding;
	private JButton activationButton; //this is the button use to active this Window



	public AddBuildingBox(int x, int y, int width, int height, Plane plane, JButton activationButton) {
		super();
		this.setLayout(null);
		this.plane = plane;
		this.activationButton = activationButton;
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
		name.setFocusable(true);

		//Label for the user to know he can set the texture image
		picLabel = new JLabel("Building Texture:");
		picLabel.setBounds(10, 100, 100, 25);
		picURL = new JTextField();
		picURL.setBounds(picLabel.getX()+picLabel.getWidth(), picLabel.getY(), (width/2), 25);

		//Search for Picture
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
					building.setPictureURL(imageFile.getPath());
					building.setPicture(picture);
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

		//Height Label for the height of building to be set
		wallHeightLabel = new JLabel("Building height");
		wallHeightLabel.setBounds(10, 70, 100, 25);
		wallHeight = new JTextField();
		wallHeight.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), wallHeightLabel.getY(), (width/2), 25);
		wallHeight.setFocusable(true);

		//Enter will be valid if all text boxes are correspondingly valid
		enter = new JButton("Enter");
		enter.setBounds(width/2-150, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(name.getText().isEmpty()) {
					JOptionPane.showMessageDialog(plane, "Name of building cannot be blank. "
							+ "Please enter a valid name for building.");
				}
				else {
					if(nameExistInList(plane.getMap().getBuildingList())) {
						JOptionPane.showMessageDialog(plane, "Building name already in use. Please choose another.");
					}
					else {
						building.setName(name.getText());
						try {
							building.setBuildingHeight(Integer.valueOf(wallHeight.getText()));
							if(picture != null) {
								building.setPicture(picture);
							} 
							if(newBuilding) {
								plane.addBuilding(building);
								activationButton.setText("Finish Building");
							}
							exit();
						}catch(NumberFormatException n){
							JOptionPane.showMessageDialog(plane, "Invalid Building Height.\nIt must be an Integer");
						}
					}
				}
			}
		});

		//Exit without new updates on building
		exit = new JButton("Exit");
		exit.setBounds(width/2 - 50, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
				if(plane.currentBuilding == null) {
					activationButton.setText("Add Building");
				}
			}
		});

		remove = new JButton("Remove Building");
		remove.setBounds(width/2 + 50, height-60, 130, 50);
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newBuilding)
					JOptionPane.showMessageDialog(plane, "Cannot remove a new building.");
				else {
					plane.getMap().removeBuilding(plane.currentBuilding);
					activationButton.setText("Add Building");
					exit();
				}
			}
		});

		//Add to Box PopUp
		add(name);	
		add(browseButton);
		add(wallHeight);
		add(picURL);
		add(picLabel);
		add(wallHeightLabel);
		add(buildingName);
		add(enter);
		add(exit);
		add(remove);

	}

	//This is a method to fill the box the the information of a Wall so you can edit it
	public void edit(Building building) {
		this.building = building;
		autofill(building);
		setVisible(true);
		activationButton.setText("Finish Building");
		newBuilding = false;
		plane.disable();
		plane.setCurrentBuilding(building);
	}


	public void addBuilding() {
		building = new Building("");
		edit(building); //here error in exit without creating BUilding
		newBuilding = true;

	}

	//Empty the textFields
	private void exit() {
		name.setText("");
		picURL.setText(""); 
		wallHeight.setText("");
		setVisible(false);
		building = null;
		plane.repaint();
		plane.enable();
	}

	private void autofill(Building building) {
		name.setText(building.getName());
		picURL.setText(building.getPictureURL());
		wallHeight.setText(String.valueOf(building.getBuildingHeight()));
	}

	private boolean nameExistInList(LinkedList<Building> buildings) {
		for(Building b: buildings) {
			if (newBuilding && b.getName().equals(name.getText()))
				return true;	
		}
		return false;
	}
}
/*
 * Need to add method to create new building Object
 * input values (name, Color, Image, etc)
 * close and enter button
 */
