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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import Components.Trees;
import Components.Wall;



/**
 * 
 * @author Fabiola Badillo Ramos
 * Date - March 11, 2020
 *	This class lets the user set tree type 
 */

public class AddTreeBox extends JPanel{

	private Trees tree;
	private Plane plane;
	private JTextField treeSpeciesField;
	private JButton enter, exit;
	private JLabel treeSpecies, treeHeight, p1Label, p2Label, picLabel;
	static BufferedImage picture;
	private boolean newTree;
	private JButton activationButton; //this is the button use to active this Window


	public AddTreeBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.setLayout(null);
		this.plane = plane;
		this.activationButton = activationButton;
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Tree");
		this.setBackground(new Color(190,190,190));

		treeSpecies = new JLabel("Tree species (1, 2 or 3): "); 
		treeSpecies.setBounds(10, 40, 100, 25);
		treeSpeciesField = new JTextField();
		treeSpeciesField.setBounds(treeSpecies.getX()+treeSpecies.getWidth(), treeSpecies.getY(), (width/2), 25);

		//Label for the user to know he can set the texture image
		picLabel = new JLabel("Tree Image");
		picLabel.setBounds(10, 100, 100, 25);
		// display tree picture for selected tree type
		
		//Height Label for the height of building to be set
		treeHeight = new JLabel("Tree height" + Integer.toString(this.tree.getTreeHeight()));
		treeHeight.setBounds(10, 70, 100, 25);
		

		//Enter will be valid if all text boxes are correspondingly valid
		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.setTreeSpecies(Integer.valueOf(treeSpeciesField.getText()));
				try {
					if(picture != null) {
						tree.setTreeImage(picture);
					} 
					if(newTree) {
						plane.addTree(tree); 
					}
					exit();
				}catch(NumberFormatException n){
					// TODO: 
				}

			}
		});

		//Exit without new updates on building
		exit = new JButton("Exit");
		exit.setBounds(width/2, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
				if(plane.currentTree == null) { 
					activationButton.setText("Add Tree");				
				}
			}
		});

		//Add to Box PopUp
		add(picLabel);
		add(enter);
		add(exit);
		add(treeSpeciesField);
		add(treeSpecies);
		add(treeHeight);
		add(p1Label);
		add(p2Label); 

	}
	
	//This is a method to fill the box the the information of a Wall so you can edit it
	public void edit(Trees tree) {
		this.tree = tree;
		autofill(tree);
		setVisible(true);
		newTree = false;
		plane.setCurrentTree(tree); 
	}

	
	public void addTree() {
		tree = new Trees(1, new Point(), new Point()); // TODO: implement
		setVisible(true);
		plane.disable();
		newTree = true;

	}

	//Empty the textFields
	private void exit() {
		treeSpecies.setText("");
		setVisible(false);
		tree = null;
		plane.enable();
	}

	private void autofill(Trees tree) {
		treeSpecies.setText(Integer.toString(tree.getTreeSpecies()));
	}
}
/*
 * Need to add method to create new building Object
 * input values (name, Color, Image, etc)
 * close and enter button
 */
