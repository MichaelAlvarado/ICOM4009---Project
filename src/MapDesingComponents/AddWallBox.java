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
		wallHeightLabel = new JLabel("Wall height (m):");
		wallHeightLabel.setBounds(10, 100, 100, 25);
		wallHeight = new JTextField();
		wallHeight.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), wallHeightLabel.getY(), (width/2), 25);


		// manually insert coordinates
		p1Label = new JLabel("Starting point");
		p1Label.setBounds(10, 130, 100, 25);
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
		formattedTextP1X.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth(), 130, 40, 25);

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
		formattedTextP1Y.setBounds(wallHeightLabel.getX()+wallHeightLabel.getWidth()+40, 130, 40, 25);

		p2Label = new JLabel("End point");
		p2Label.setBounds(10,  160,  100,  25);
		MaskFormatter formatP2X = new MaskFormatter();
		try {
			formatP2X.setMask("(####,");
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
			formatP2Y.setMask("####)");
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
				if(newWall && isValidTextField() && isValidCoordinates()) {
					int x1 = getX1Coordinate();
					System.out.println(x1);
					int y1 = plane.getHeight() - getY1Coordinate();
					System.out.println(y1);
					int x2 = getX2Coordinate();
					System.out.println(x2);
					int y2 = plane.getHeight() - getY2Coordinate();
					System.out.println(y2);


					wall.setTexture(texture); 
					wall.getP1().setLocation(x1,y1);
					wall.getP2().setLocation(x2,y2);
				}
				else {
					JOptionPane.showMessageDialog(plane, "Coordinates exceed the size of the map."
							+ "Please enter valid coordinates.");
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
					plane.getCurrentBuilding().removeWall(wall);
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
		this.wall = wall;
		newWall = !plane.getCurrentBuilding().getWalls().contains(wall);
		autofill();
		plane.disable();
		setVisible(true);
	}

	public void addNewWall() {
		wall = new Wall(plane.getWallAutoGeneratedName(),new Point(),new Point());
		wall.setHeight(plane.getCurrentBuilding().getBuildingHeight());
		edit(wall);
	}

	private void exit() {
		setVisible(false);
		//make sure the info don't stay in the box
		wall = null; 
		plane.enable();
		plane.repaint();
	}

	private void autofill() {
		if(!newWall) {
			this.nameLabel.setText("Wall name: " + wall.getID());
			wallHeight.setText(String.valueOf(wall.getHeight()));
			String x1 = String.valueOf(wall.getP1().x);
			String y1 = String.valueOf(plane.getHeight() - wall.getP1().y);
			String x2 = String.valueOf(wall.getP2().x);
			String y2 = String.valueOf(plane.getHeight() - wall.getP2().y);
			while(x1.length()<3) {
				formattedTextP1X.setText(x1 = "0"+x1);
			}
			while(y1.length()<3) {
				formattedTextP1X.setText(y1 = "0"+y1);
			}
			while(x2.length()<3) {
				formattedTextP1X.setText(x2 = "0"+x2);
			}
			while(y2.length()<3) {
				formattedTextP1X.setText(y2 = "0"+y2);
			}
			formattedTextP1X.setText(x1);
			formattedTextP1Y.setText(y1);
			formattedTextP2X.setText(x2);
			formattedTextP2Y.setText(y2);
			imageURL.setText(wall.getTextureURL());
		}
	}

	private boolean isValidTextField() {
		return 	formattedTextP1X.isValid() && 
				formattedTextP1Y.isValid() &&
				formattedTextP2X.isValid() &&
				formattedTextP2Y.isValid();
	}

	private boolean isValidCoordinates() {
		int x1Value, y1Value, x2Value, y2Value;
		x1Value = getX1Coordinate();
		y1Value = getY1Coordinate();
		x2Value = getX2Coordinate();
		y2Value = getY2Coordinate();
		if(x1Value <= plane.getMap().getWidth() && y1Value <= plane.getMap().getHeight()
				&& x2Value <= plane.getMap().getWidth() && y2Value <= plane.getMap().getHeight())
			return true;
		else
			return false;
	}

	private int getX1Coordinate() {
		int index;
		int x;
		String delim = "";
		delim = formattedTextP1X.getValue().toString();
		index = delim.indexOf(',');
		x = Integer.parseInt(formattedTextP1X.getValue().toString().substring(1, index));
		return x;
	}

	private int getY1Coordinate() {
		int index;
		int y;
		String delim = "";
		delim = formattedTextP1Y.getValue().toString();
		index = delim.indexOf(')');
		y = Integer.parseInt(formattedTextP1Y.getValue().toString().substring(1,index));

		return y;
	}
	private int getX2Coordinate() {
		int index;
		int x;
		String delim = "";
		delim = formattedTextP1X.getValue().toString();
		index = delim.indexOf(',');
		x = Integer.parseInt(formattedTextP2X.getValue().toString().substring(1, index));
		return x;
	}

	private int getY2Coordinate() {
		int index;
		int y;
		String delim = "";
		delim = formattedTextP1Y.getValue().toString();
		index = delim.indexOf(')');
		y = Integer.parseInt(formattedTextP2Y.getValue().toString().substring(1,index));

		return y;
	}
}

