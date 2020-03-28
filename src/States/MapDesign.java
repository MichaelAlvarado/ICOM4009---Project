package States;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.ParseException;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Canvas;
import javax.swing.JFormattedTextField;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.MaskFormatter;

import Components.Building;
import Components.Trees;
import Components.Wall;
import GUI.AddBuildingBox;
import GUI.AddMapBox;
import GUI.AddQuestionsBox;
import GUI.AddTreeBox;
import GUI.AddWallBox;
import GUI.Plane;

import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;

import javax.swing.JSlider;
import java.awt.Button;

/**
 * This was made using Window Builder version 1.9.3 plug-in in Eclipse IDE
 */

public class MapDesign{

	private JFrame display;
	private Plane plane;
	private int width, height;
	protected boolean toolOpened;

	/**
	 * @author Michael J. Alvarado
	 * The MapDesign Manage the Buttons and Plane
	 * @param display is a JFrame that Its initialize in Launch method as GUI.Display instance
	 * Date - 28/Feb/2020
	 * @throws ParseException 
	 */
	public MapDesign(JFrame display) throws ParseException {
		this.display = display;
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight();
		int canvasY = 85; //this is the position in Y where the division is between plane and menu

		display.getContentPane().setBackground(Color.WHITE);
		display.getContentPane().setLayout(null);

		//This initialize plane which is what draw points and has the mouse and key listeners
		plane = new Plane();
		plane.setBackground(Color.WHITE);
		plane.setLocation(0, canvasY);
		plane.setBounds(0, canvasY, width, height-(canvasY));

		//Buttons Panels
		JPanel panel = new JPanel();
		panel.setBackground(new Color(190,190,190));
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		panel.setBounds(0, 0, width, canvasY);
		display.getContentPane().add(panel);
		panel.setLayout(null);

		//Buttons
		JButton help = new JButton("Help");
		help.setBounds(width-170, 15, 150, 25);
		panel.add(help);

		JButton setting = new JButton("Setting");
		setting.setBounds(width-170, canvasY-35, 150, 25);
		panel.add(setting);

		JButton addWall = new JButton("Add Wall");
		addWall.setBounds((panel.getWidth()/2)+5, 15, 150, 25);
		panel.add(addWall);

		JButton addTree = new JButton("Add Tree");
		addTree.setBounds((panel.getWidth()/2)+165, 15, 150, 25);
		panel.add(addTree);

		JButton addQuestion = new JButton("Add Question");
		addQuestion.setBounds((panel.getWidth()/2)+5, 50, 150, 25);
		panel.add(addQuestion);	

		JButton editMap = new JButton("Edit Map");
		editMap.setBounds(165, 15, 150, 25);
		panel.add(editMap);

		JButton addBuilding = new JButton("Add Building");
		addBuilding.setBounds(10, 15, 150, 25);
		panel.add(addBuilding);

		JButton wallList = new JButton("Current Walls");
		wallList.setBounds(10,50,150,25);
		panel.add(wallList);

		JButton buildingList = new JButton("Building List");
		buildingList.setBounds(165,50,150,25);
		panel.add(buildingList);

		JButton treeList = new JButton("Tree List");
		treeList.setBounds((panel.getWidth()/2)+165, 50, 150, 25);
		panel.add(treeList);
		
		//Round Add Button in Plane
		BufferedImage buttonIcon = null;
		try {
		    buttonIcon = ImageIO.read(new File("res/addButton.png"));
		}
		catch(Exception ex) {
		}
		// Set the image icon here
		Image dimg = buttonIcon.getScaledInstance(60, 60,Image.SCALE_SMOOTH); //scale the image
		JButton tool = new JButton(new ImageIcon(dimg));
		tool.setBorderPainted(false);
		tool.setContentAreaFilled(false);
		tool.setFocusPainted(false);
		tool.setOpaque(false);
		tool.setBounds(plane.getWidth()-65, plane.getHeight()/2-60, 60, 60);
		plane.add(tool);

		//Boxes which are made to add or edit map,buildings,wall,questions, trees.
		AddBuildingBox addBuildingBox = new AddBuildingBox((width/2)-250, 200,500,200, plane, addBuilding);
		addBuildingBox.setVisible(false);
		display.getContentPane().add(addBuildingBox);

		AddWallBox addWallBox = new AddWallBox((width/2)-250, 200,500,300, plane);
		addWallBox.setVisible(false);
		display.getContentPane().add(addWallBox);

		AddQuestionsBox addQuestionBox = new AddQuestionsBox((width/2)-250, 200,500,500, plane);
		addQuestionBox.setVisible(false);
		display.getContentPane().add(addQuestionBox);

		AddMapBox addMapBox = new AddMapBox((width/2)-250, 200,500,200, plane);
		addMapBox.setVisible(true);
		plane.disable();
		display.getContentPane().add(addMapBox);

		AddTreeBox addTreeBox = new AddTreeBox((width/2)-250, 200,500,180, plane, addTree);
		addTreeBox.setVisible(false);
		display.getContentPane().add(addTreeBox);
		
		display.getContentPane().add(plane);//add at the end so its on the bottom

		/*
		 * Add Actions to Components
		 */
		setting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Opens a PopUp with settings options
				settingPopUp(setting.getX(), setting.getY()+setting.getHeight()*2);
			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Open a Message of the help Guide
				helpScreen();
			}
		});

		wallList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Make a PopUp to edit a specific wall in the currently editing Building
				wallsListPopUp(wallList.getX(), wallList.getY()+wallList.getHeight()*2, addWallBox);
			}
		});

		buildingList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Make a PopUp to edit a specific Building
				buildingListPopUp(buildingList.getX(),buildingList.getY()+buildingList.getHeight()*2, addBuildingBox);
			}
		});
		
		treeList.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				treeListPopUp(treeList.getX(), treeList.getY()+treeList.getHeight()*2, addTreeBox);
			}
		});

		addBuilding.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addMapBox.setVisible(false);
				addWallBox.setVisible(false);
				addQuestionBox.setVisible(false);

				if(addBuilding.getText().equals("Add Building")) {
					addBuildingBox.addBuilding();
				}
				else {
					plane.setCurrentBuilding(null);
					addBuilding.setText("Add Building");
				}
			}
		});

		addWall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addWallBox.addNewWall();
				addMapBox.setVisible(false);
				addBuildingBox.setVisible(false);
				addQuestionBox.setVisible(false);
			}
		});

		editMap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addMapBox.edit();
				addWallBox.setVisible(false);
				addBuildingBox.setVisible(false);
				addQuestionBox.setVisible(false);

			}
		});

		addQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addQuestionBox.addQuestion();
				addWallBox.setVisible(false);
				addBuildingBox.setVisible(false);
				addMapBox.setVisible(false);
			}
		});
		
		addTree.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addTreeBox.addTree();
				addQuestionBox.setVisible(false);
				addWallBox.setVisible(false);
				addBuildingBox.setVisible(false);
				addMapBox.setVisible(false);
			}
		});
		
		tool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!toolOpened) {
				plane.openTool();
				tool.setVisible(true);
				tool.setLocation(plane.getWidth()-65-100, tool.getLocation().y);
				toolOpened = true;
				}
				else {
					plane.closeTool();
					tool.setLocation(plane.getWidth()-65, tool.getLocation().y);
					toolOpened = false;
				}
			}
		});

	}//Last from Constructor

	/**
	 * @author Michael J. Alvarado 
	 * This method creates a PopUP with the walls in a currently editing Building.
	 * Date - 04/March/2020
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame)
	 * @param addWallBox - This must be given the addWallBox so that it can be displayed when clicked on a specifi Wall from the PopUp
	 */
	private void wallsListPopUp(int x, int y, AddWallBox addWallBox) {
		PopupMenu wallsPopUp = new PopupMenu("Walls on: " + plane.getCurrentBuilding().getName());
		wallsPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
		for(Wall wall: plane.getCurrentBuilding().getWalls()) {
			MenuItem wallOption = new MenuItem(wall.getID());
			wallOption.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					addWallBox.edit(wall);
					addWallBox.setVisible(true);
				}

			});
			wallsPopUp.add(wallOption);
		}
		display.add(wallsPopUp);
		wallsPopUp.show(display, x, y);

	}

	/**
	 * @author Michael J. Alvarado 
	 * This method creates a PopUP with the Buildings in the map being created.
	 * Date - 04/March/2020
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame)
	 * @param addBuildingBox - This must be given the addBuldingBox so that it can be edit when clicked on a specific Building from the PopUp
	 */
	private void buildingListPopUp(int x, int y, AddBuildingBox addBuildingBox) {
		PopupMenu buildingsPopUp = new PopupMenu("Buildings on: " +  plane.getMap().getMapName());
		buildingsPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
		for(Building building: plane.getMap().getBuildingList()) {
			MenuItem buildingOption = new MenuItem(building.getName());
			buildingOption.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					addBuildingBox.edit(building);
					addBuildingBox.setVisible(true);
				}

			});
			buildingsPopUp.add(buildingOption);
		}
		display.add(buildingsPopUp);
		buildingsPopUp.show(display, x, y);
	}
	
	/**
	 * @author Fabiola Badillo
	 * This method creates a PopUP with the trees in the map being created.
	 * Date - March 20, 2020
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame)
	 * @param addTreeBox - This must be given the addTreeBox so that it can be edit when clicked on a specific Building from the PopUp
	 */
	private void treeListPopUp(int x, int y, AddTreeBox addTreeBox) {
		PopupMenu treesPopUp = new PopupMenu("Trees on: " +  plane.getMap().getMapName());
		treesPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
		for(Trees tree: plane.getMap().getTrees()) {
			MenuItem treeOption = new MenuItem(tree.getID());
			treeOption.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					addTreeBox.edit(tree);
					addTreeBox.setVisible(true);
				}

			});
			treesPopUp.add(treeOption);
		}
		display.add(treesPopUp);
		treesPopUp.show(display, x, y);
	}
	
	/**
	 * @author Michael J. Alvarado
	 * This method creates the PopUp of the settings and actions when clicked.
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame) 
	 */
	private void settingPopUp(int x, int y) {
		PopupMenu setting = new PopupMenu("Settings");
		MenuItem undo = new MenuItem("Undo wall");
		MenuItem clear = new MenuItem("Clear All walls");
		MenuItem grid = new MenuItem("Grid");
		MenuItem buildingImage = new MenuItem("Put Buildings Images");
		MenuItem cpColor = new MenuItem("Change current point Color");
		MenuItem clColor = new MenuItem("Change current line Color");
		MenuItem ppColor = new MenuItem("Change previous point Color");
		MenuItem plColor = new MenuItem("Change previous line Color");

		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.undo();
			}
		});

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.clearAllWalls();
			}
		});

		grid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.setGrid();
				plane.repaint();
			}
		});

		cpColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"currentPoint");
			}
		});

		clColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"currentLine");
			}
		});

		ppColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"previousPoint");
			}
		});

		plColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				colorPopup(x,y,"previousLine");
			}
		});
		
		buildingImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.setBuildingImages();
			}	
		});

		setting.add(undo);
		setting.add(clear);
		setting.add(grid);
		setting.add(cpColor);
		setting.add(clColor);
		setting.add(ppColor);
		setting.add(plColor);
		setting.add(buildingImage);
		display.add(setting);
		setting.show(display, x, y);
	}
	/**
	 * @author Michael J. Alvarado 
	 * This method displays a PopUp of given Colors to paint specified traces or Points
	 * PreCondition - The settingsPopUP produces the component
	 * (currentPoint, currentLine, previousPoint, previousLine) 
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame)
	 * @param component - this is a String giving by the settings PopUP thats tells what Component to Paint
	 *	 
	 */
	private void colorPopup(int x, int y, String component) {
		PopupMenu ColorPopup = new PopupMenu("Choose color");
		MenuItem magenta = new MenuItem("Magenta");
		MenuItem green = new MenuItem("Green");
		MenuItem red = new MenuItem("Red");
		MenuItem blue = new MenuItem("Blue");
		MenuItem orange = new MenuItem("Orange");

		magenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.paintPlaneComponent(Color.MAGENTA, component);
			}
		});

		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color green = new Color(20,198,5); //Green
				plane.paintPlaneComponent(green, component);
			}
		});

		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.paintPlaneComponent(Color.RED, component);
			}
		});

		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.paintPlaneComponent(Color.BLUE, component);
			}
		});

		orange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				plane.paintPlaneComponent(Color.ORANGE, component);
			}
		});

		ColorPopup.add(orange);
		ColorPopup.add(blue);
		ColorPopup.add(red);
		ColorPopup.add(green);
		ColorPopup.add(magenta);
		display.add(ColorPopup);
		ColorPopup.show(display, x, y);
	}
	
	private void helpScreen() {
		String Instructions = "Help";
		JOptionPane.showMessageDialog(display, Instructions);
	}

//	private void setEnableButtons(JButton[] buttons, boolean enable) {
//		for(int i = 0; i < buttons.length; i++) {
//			buttons[i].setVisible(enable);
//		}
//	}


}
