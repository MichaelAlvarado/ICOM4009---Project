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
import javax.swing.JList;
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
 * @author Fabiola Badillo Ramos & Anel Martinez Gomez
 * Date - March 11, 2020
 *	This class lets the user set tree type 
 */

public class AddTreeBox extends JPanel{

	private Trees tree;
	private Plane plane;
	private JButton enter, exit, activationButton, browseButton;
	private JFileChooser browser;
	private JLabel treeSpecies, treeHeight, p1Label, p2Label, picLabel;
	private JList treeSpeciesField;
	private JTextField picBrowser, heightSel;
	private JFormattedTextField formattedTextP1X, formattedTextP1Y, formattedTextP2X, formattedTextP2Y;
	private boolean newTree;
	static BufferedImage picture;
	
	//Activation Button: this is the button used to active this Window


	public AddTreeBox(int x, int y, int width, int height, Plane plane, JButton activationButton) {
		super();
		this.setLayout(null);
		this.plane = plane;
		//this.activationButton = activationButton;
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		setName("New Tree");
		this.setBackground(new Color(190,190,190));

		treeSpecies = new JLabel("Tree species:"); 
		treeSpecies.setBounds(10, 10, 100, 25);
		String num[]= { "Type 1","Type 2","Type 3"};
		treeSpeciesField = new JList(num);
		treeSpeciesField.setBounds(treeSpecies.getX()+treeSpecies.getWidth(), treeSpecies.getY(), (width/2), 55);

		//Label for the user to know he can set the texture image
		picLabel = new JLabel("Tree Image:");
		picLabel.setBounds(10, 100, 100, 25);
		picBrowser = new JTextField();
		picBrowser.setBounds(picLabel.getX()+picLabel.getWidth(), picLabel.getY(), (width/2), 25);

		
		// display tree picture for selected tree type
		browser = new JFileChooser();
		browseButton = new  JButton("Browse");
		browseButton.setBounds((picBrowser.getX()+picBrowser.getWidth())+10, picBrowser.getY(), 100, 20);
		browser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browser.getSelectedFile();
				try {						
					System.out.println("loading file...");
					picture = ImageIO.read(imageFile);
					System.out.println("load file");
					picBrowser.setText(imageFile.getPath());
					tree.setPictureURL(imageFile.getPath());
					tree.setTreeImage(picture);;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		browseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				browser.showOpenDialog(AddTreeBox.this);
			}
		});

		//Height Label for the height of tree to be set if decided not to establish one in specific 
		treeHeight = new JLabel("Tree height:");


		//Height Label for the height of building to be set
		treeHeight = new JLabel("Tree height");
		treeHeight.setBounds(10, 70, 100, 25);
		heightSel = new JTextField();
		heightSel.setBounds(treeHeight.getX()+treeHeight.getWidth(), treeHeight.getY(), (width/2), 25);


		// Set beginning and End of Tree
		p1Label = new JLabel("Starting point");
		p1Label.setBounds(10, 130, 100, 25);
		MaskFormatter formatP1X = new MaskFormatter();
		try {
			formatP1X.setMask("(###,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
			System.exit(-1);
		}
		formatP1X.setPlaceholderCharacter('_');
		formattedTextP1X = new JFormattedTextField(formatP1X);
		formattedTextP1X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP1X.setBounds(treeHeight.getX()+ treeHeight.getWidth(), 130, 40, 25);

		MaskFormatter formatP1Y = new MaskFormatter();
		try {
			formatP1Y.setMask(" ###)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
			System.exit(-1);
		}
		formatP1Y.setPlaceholderCharacter('_');
		formattedTextP1Y = new JFormattedTextField(formatP1Y);
		formattedTextP1Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP1Y.setBounds(treeHeight.getX()+treeHeight.getWidth()+40, 130, 40, 25);

		p2Label = new JLabel("End point");
		p2Label.setBounds(10,  160,  100,  25);
		MaskFormatter formatP2X = new MaskFormatter();
		try {
			formatP2X.setMask("(###,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
			System.exit(-1);
		}
		formatP2X.setPlaceholderCharacter('_');
		formattedTextP2X = new JFormattedTextField(formatP2X);
		formattedTextP2X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP2X.setBounds(treeHeight.getX()+treeHeight.getWidth(), 160, 40, 25);
		MaskFormatter formatP2Y = new MaskFormatter();
		try {
			formatP2Y.setMask(" ###)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
			System.exit(-1);
		}
		formatP2Y.setPlaceholderCharacter('_');
		formattedTextP2Y = new JFormattedTextField(formatP2Y);
		formattedTextP2Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP2Y.setBounds(treeHeight.getX()+treeHeight.getWidth()+40, 160, 40, 25);

		//Enter will be valid if all text boxes are correspondingly valid
		enter = new JButton("Enter");
		enter.setBounds(width/2-100, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.setTreeSpecies(Integer.valueOf(treeSpeciesField.getSelectedIndex()));
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
				if(plane.currentTree == null) { 
					activationButton.setText("Add Tree");				
				}
				exit();
			}
		});

		//Add to Box PopUp
		add(browseButton);
		add(browser);
		add(picLabel);
		add(enter);
		add(exit);
		add(treeSpeciesField);
		add(treeSpecies);
		add(treeHeight);
		add(picBrowser);
		add(heightSel);
		add(p1Label);
		add(p2Label); 
		add(formattedTextP1X);
		add(formattedTextP1Y);
		add(formattedTextP2X);
		add(formattedTextP2Y);
		//		add(p1Label);
		//		add(p2Label); 

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
