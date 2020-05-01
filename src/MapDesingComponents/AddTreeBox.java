package MapDesingComponents;

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

import Components.Tree;
import Components.Wall;

/**
 * 
 * @author Fabiola Badillo Ramos & Anel Martinez Gomez
 * Date - March 11, 2020
 *	This class lets the user set tree type 
 */

public class AddTreeBox extends JPanel{

	private Tree tree;
	private Plane plane;
	private JButton enter, exit, activationButton, remove;
	private JLabel treeSpecies, p1Label;
	private JList treeSpeciesField;
	private JFormattedTextField formattedTextP1X, formattedTextP1Y;
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
		String num[]= { "Type 1 (Small)","Type 2 (Medium)","Type 3 (Large)"};
		treeSpeciesField = new JList(num);
		treeSpeciesField.setBounds(treeSpecies.getX()+treeSpecies.getWidth(), treeSpecies.getY(), (width/2), 55);


		// Set beginning and End of Tree
		p1Label = new JLabel("Position");
		p1Label.setBounds(10, treeSpecies.getY()+70, 100, 25);
		MaskFormatter formatP1X = new MaskFormatter();
		try {
			formatP1X.setMask("(####,");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
			System.exit(-1);
		}
		formatP1X.setPlaceholderCharacter('_');
		formattedTextP1X = new JFormattedTextField(formatP1X);
		formattedTextP1X.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP1X.setBounds(treeSpecies.getX()+treeSpecies.getWidth(), treeSpecies.getY()+70, 40, 25);

		MaskFormatter formatP1Y = new MaskFormatter();
		try {
			formatP1Y.setMask("####)");
		} catch (ParseException e1) {
			System.err.println("number should be an integer");
			System.exit(-1);
		}
		formatP1Y.setPlaceholderCharacter('_');
		formattedTextP1Y = new JFormattedTextField(formatP1Y);
		formattedTextP1Y.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextP1Y.setBounds(treeSpecies.getX()+treeSpecies.getWidth()+40, treeSpecies.getY()+70, 40, 25);


		//Enter will be valid if all text boxes are correspondingly valid
		enter = new JButton("Enter");
		enter.setBounds(width/2-150, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tree.setTreeSpecies(Integer.valueOf(treeSpeciesField.getSelectedIndex()));
				System.out.println("especie escogida "+treeSpeciesField.getSelectedIndex());
				if(newTree && isValidCoordinates() && isValidTextField()) { 
					// tree.setTreeSpecies(treeSpeciesField.getAnchorSelectionIndex()+1);
					int x = getXCoordinate();
					System.out.println("Coordenada de árbol en x:"  + x);
					int y = getYCoordinate();
					System.out.println("Coordenada de árbol en y:" + y);
					y = plane.getHeight() - y;
					tree.setP1(new Point(x,y));
					plane.addTree(tree);
					exit();

				}
				else {
					JOptionPane.showMessageDialog(plane, "Coordinates exceed the size of the map. "
							+ "Please enter valid coordinates");
				}

			}

		});

		//Exit without new updates on building
		exit = new JButton("Exit");
		exit.setBounds(width/2 - 50, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tree == null) { 
					activationButton.setText("Add Tree");				
				}
				exit();
			}
		});

		remove = new JButton("Remove Tree");
		remove.setBounds(width/2 + 50, height-60, 130, 50);
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newTree)
					JOptionPane.showMessageDialog(plane, "Cannot remove a new tree.");
				else {
					plane.getMap().removeTree(tree);
					plane.repaint();
					exit();
				}
			}
		});

		//Add to Box PopUp
		add(enter);
		add(exit);
		add(remove);
		add(treeSpeciesField);
		add(treeSpecies);
		add(p1Label);
		add(formattedTextP1X);
		add(formattedTextP1Y);
	}

	//This is a method to fill the box the the information of a Wall so you can edit it
	public void edit(Tree tree) {
		this.tree = tree;
		autofill(tree);
		setVisible(true);
		newTree = false;
		plane.disable();
	}


	public void addTree() {
		tree = new Tree(plane.map, 1, new Point()); // TODO: implement
		setVisible(true);
		newTree = false;
		plane.disable();
		newTree = true;
	}

	//Empty the textFields
	private void exit() {
		tree = null;
		this.formattedTextP1X.setText("0000");
		this.formattedTextP1Y.setText("0000");
		treeSpeciesField.clearSelection();
		setVisible(false);
		plane.enable();
		plane.repaint();
	}

	private void autofill(Tree tree) {
		if(!newTree) {
			treeSpeciesField.setSelectedIndex(tree.getTreeSpecies());
			String x1 = String.valueOf(tree.getP1().x);
			//System.out.println(x1);
			String y1 = String.valueOf(plane.getHeight() - tree.getP1().y);
			//System.out.println(y1);
			if(x1.length()<4) {
				if(x1.length() == 3)
					formattedTextP1X.setText(x1 = "0"+x1);
				else if(x1.length() == 2)
					formattedTextP1X.setText(x1 = "00"+x1);
				else
					formattedTextP1X.setText(x1 = "0000"+x1);
			}
			else {
				this.formattedTextP1X.setText(String.valueOf(tree.getP1().x));
			}
			if(y1.length()<4) {
				if(y1.length() == 3)
					formattedTextP1Y.setText(y1 = "0"+y1);
				else if(y1.length() == 2)
					formattedTextP1Y.setText(y1 = "00"+y1);
				else
					formattedTextP1Y.setText(y1 = "0000"+y1);			
			}
			else {
				this.formattedTextP1Y.setText(String.valueOf(plane.getHeight() - tree.getP1().y));
			}
//			System.out.println(formattedTextP1X.getText());
//			System.out.println(formattedTextP1Y.getText());
		}
	}

	private boolean isValidTextField() {
		return 	formattedTextP1X.isValid() && 
				formattedTextP1Y.isValid();
	}

	private boolean isValidCoordinates() {
		int xValue, yValue;
		xValue = getXCoordinate();
		yValue = getYCoordinate();
		if(xValue <= plane.getMap().getWidth() && yValue <= plane.getMap().getHeight())
			return true;
		else
			return false;
	}
	private int getXCoordinate() {
		int index;
		int x;
		String delim = "";
		delim = formattedTextP1X.getValue().toString();
		index = delim.indexOf(',');
		x = Integer.parseInt(formattedTextP1X.getValue().toString().substring(1, index));
		return x;
	}
	private int getYCoordinate() {
		int index;
		int y;
		String delim = "";
		delim = formattedTextP1Y.getValue().toString();
		index = delim.indexOf(')');
		y = Integer.parseInt(formattedTextP1Y.getValue().toString().substring(1,index));

		return y;
	}
}

