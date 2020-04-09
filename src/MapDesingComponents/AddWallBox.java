package MapDesingComponents;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Components.Building;
import Components.Wall;
import javax.swing.text.MaskFormatter;


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
	JButton enter, exit, browseButton, remove;
	Plane plane;
	JLabel nameLabel, imageLabel, wallHeightLabel, p1Label, p2Label;
	JFormattedTextField formattedTextP1X, formattedTextP1Y, formattedTextP2X, formattedTextP2Y;
	boolean newWall;

	public AddWallBox(int x, int y, int width, int height, Plane plane) {
		super();
		this.setLayout(null);
		this.plane = plane;
		setBounds(x, y, width, height);
		setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		this.setBackground(new Color(190,190,190));

		// set the default wall name derived from the building
		nameLabel = new JLabel("Wall name: "); 
		nameLabel.setBounds(200, 40, 300, 25);

		// browse for a wall texture
		imageLabel = new JLabel("Wall texture");
		imageLabel.setBounds(10, 70, 100, 25);
		imageURL = new JTextField();
		imageURL.setBounds(imageLabel.getX()+imageLabel.getWidth(), imageLabel.getY(), (width/2), 25);
		browser = new JFileChooser();
		browseButton = new  JButton("Browse");
		browseButton.setBounds((imageURL.getX()+imageURL.getWidth())+10, imageURL.getY(), 100, 20);
		browser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File imageFile = browser.getSelectedFile();
				try {						
					System.out.println("loading file...");
					texture = ImageIO.read(imageFile);
					System.out.println("load file");
					imageURL.setText(imageFile.getPath());
					wall.setTextureURL(imageFile.getPath());
					wall.setTexture(texture);
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
		wallHeight = new JTextField();
		wallHeight.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), wallHeightLabel.getY(), (width/2), 25);


		// manually insert coordinates
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
		formattedTextP1X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 130, 40, 25);

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
		formattedTextP1Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 130, 40, 25);

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
		formattedTextP2X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 160, 40, 25);
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
		formattedTextP2Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 160, 40, 25);


		// enter button
		enter = new JButton("Enter");
		enter.setBounds(width/2-150, height-60, 100, 50);
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isValidTextField()) {
					int x1 = Integer.parseInt(formattedTextP1X.getValue().toString().substring(1,4));
					int y1 = plane.getHeight() - Integer.parseInt(formattedTextP1Y.getValue().toString().substring(1,4));
					int x2 = Integer.parseInt(formattedTextP2X.getValue().toString().substring(1,4));
					int y2 = plane.getHeight() - Integer.parseInt(formattedTextP2Y.getValue().toString().substring(1,4));

					wall.setTexture(texture); 
					wall.getP1().setLocation(x1,y1);
					wall.getP2().setLocation(x2,y2);
				}
				else {
					JOptionPane.showMessageDialog(plane, "Invalid Coordinates");
				}
				if(newWall) {
					plane.addWall(wall);
				}
				exit();
			}
		});

		//Exit button
		exit = new JButton("Exit");
		exit.setBounds(width/2 - 50, height-60, 100, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		remove = new JButton("Remove Wall");
		remove.setBounds(width/2 + 50, height-60, 130, 50);
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newWall)
					JOptionPane.showMessageDialog(plane, "Cannot remove a new wall.");
				else {
					plane.currentBuilding.removeWall(wall);
					exit();
				}
			}
		});


		add(enter);
		add(exit);
		add(remove);
		add(browseButton);
		add(wallHeight);
		add(imageURL);
		add(nameLabel);
		add(imageLabel);
		add(wallHeightLabel);
		add(formattedTextP1X);
		add(formattedTextP1Y);
		add(formattedTextP2X);
		add(formattedTextP2Y);
		add(p1Label);
		add(p2Label);
	}

	public void edit(Wall wall) {
		//This is a method to fill the box the the information of a Wall so you can edit it
		// name.setText(wall.getID());
		this.wall = wall;
		this.newWall = false;
		autofill();
		plane.disable();
		setVisible(true);
	}

	public void addNewWall() {
		this.wall = new Wall(plane.getWallAutoGeneratedName(),new Point(),new Point());
		this.newWall = true;
		plane.disable();
		setVisible(true);
	}

	private void exit() {
		setVisible(false);
		//make sure the info don't stay in the box
		wall = null; 
		this.formattedTextP1X.setText("000");
		this.formattedTextP1Y.setText("000");
		this.formattedTextP2X.setText("000");
		this.formattedTextP2Y.setText("000");
		plane.enable();
		plane.repaint();
	}

	private void autofill() {
		this.nameLabel.setText("Wall name: " + wall.getID());
		if(!newWall) {
			this.formattedTextP1X.setText(String.valueOf(wall.getP1().x));
			this.formattedTextP1Y.setText(String.valueOf(plane.getHeight() - wall.getP1().y));
			this.formattedTextP2X.setText(String.valueOf(wall.getP2().x));
			this.formattedTextP2Y.setText(String.valueOf(plane.getHeight() - wall.getP2().y));
			this.wallHeight.setText(String.valueOf(wall.getHeight()));
			imageURL.setText(wall.getTextureURL());
		}
	}

	private boolean isValidTextField() {
		return formattedTextP1X.getValue() != null &&
				formattedTextP1Y.getValue() != null &&
				formattedTextP2X.getValue() != null &&
				formattedTextP2Y.getValue() != null;
	}

}

