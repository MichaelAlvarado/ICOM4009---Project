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
import java.io.IOException;
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
import Components.Question;
import Components.Tree;
import Components.Wall;
import GUI.Display;
import MapDesingComponents.AddBuildingBox;
import MapDesingComponents.AddMapBox;
import MapDesingComponents.AddQuestionsBox;
import MapDesingComponents.AddTreeBox;
import MapDesingComponents.AddWallBox;
import MapDesingComponents.Plane;
import Resources.ConfigurationFile;

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

public class MapDesignState{

	private Display display;
	private Plane plane;
	private JPanel panel;
	private JButton help, setting, addWall, addTree, addQuestion, editMap, mapDone, addBuilding, wallList, buildingList, treeList, questionList;
	private int width, height;
	private int canvasY = 85; //this is the position in Y where the division is between plane and menu

	/**
	 * @author Michael J. Alvarado
	 * The MapDesign Manage the Buttons and Plane
	 * @param display is a JFrame that Its initialize in Launch method as GUI.Display instance
	 * Date - 28/Feb/2020
	 * @throws ParseException 
	 */
	public MapDesignState(Display display) throws ParseException {
		this.display = display;
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight();
		display.getContentPane().setBackground(Color.WHITE);
		display.getContentPane().setLayout(null);

		//This initialize plane which is what draw points and has the mouse and key listeners
		plane = new Plane(0, canvasY, width, height-(canvasY));
		plane.setBackground(Color.WHITE);

		//Buttons Panels
		panel = new JPanel() {
			@Override 
			public void paint(Graphics g) {
				super.paint(g);
				setComponentsBound();
			}
		};
		panel.setBackground(new Color(190,190,190));
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		display.getContentPane().add(panel);
		panel.setLayout(null);

		//Buttons
		help = new JButton("Help");
		panel.add(help);

		setting = new JButton("Settings");
		panel.add(setting);

		addWall = new JButton("Add Wall");
		panel.add(addWall);

		addTree = new JButton("Add Tree");
		panel.add(addTree);

		addQuestion = new JButton("Add Question");
		panel.add(addQuestion);	

		editMap = new JButton("Edit Map");
		panel.add(editMap);

		mapDone = new JButton("Map Done");
		panel.add(mapDone);

		addBuilding = new JButton("Add Building");
		panel.add(addBuilding);

		wallList = new JButton("Wall List");
		panel.add(wallList);

		buildingList = new JButton("Building List");
		panel.add(buildingList);

		treeList = new JButton("Tree List");
		panel.add(treeList);

		questionList = new JButton("Question List");
		panel.add(questionList);


		//Round Add Button in Plane

		setComponentsBound();
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

		questionList.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				questionListPopUp(questionList.getX(), questionList.getY()+questionList.getHeight()*2, addQuestionBox);
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
					if(plane.getCurrentBuilding().getQuestionPool().size() < 4 || plane.getCurrentBuilding().getWalls().size() < 1) {
						JOptionPane.showMessageDialog(plane, "Building must contain at least 1 wall and 4 questions.");
					}
					else {
						plane.setCurrentBuilding(null);
						addBuilding.setText("Add Building");
					}
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

		mapDone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Object[] options = { "YES", "NO" };
				int x = JOptionPane.showOptionDialog(null, "Do you wish to save the Map?", "Warning",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
				if (x == 0) {
					if(!(plane.getMap().getPicture() == null)) {
						if(plane.getCurrentBuilding().getWalls().size() < 1 || plane.getCurrentBuilding().getQuestionPool().size() < 4)
							plane.getMap().removeBuilding(plane.getCurrentBuilding());
						ConfigurationFile.generateTextFile(plane.getMap());
						ConfigurationFile.generateQuestionFile(plane.getMap());
						System.out.println("Map Saved");
						loadingScreen();
					}
					else
						JOptionPane.showMessageDialog(plane, "Map must contain an Image");
				}
				else if(x == 1) {
					loadingScreen();
				}

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

		display.repaint();
	}//Last from Constructor

	/**
	 * 
	 * Description - set all the components to there position
	 * @author - Michael J. Alvarado
	 * @date Apr 9, 2020
	 */
	private void setComponentsBound() {
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight();
		panel.setBounds(0, 0, this.width, canvasY);
		plane.setBounds(0, canvasY, this.width, this.height-(canvasY));
		//Buttons
		int y1 = 10;
		int y2 = 45;
		int width = 200;
		int height = 30;
		mapDone.setBounds(10,y1,width,height);
		editMap.setBounds(10, y2, width, height);
		addBuilding.setBounds((panel.getWidth()/2) - 400, y1, width, height);
		buildingList.setBounds((panel.getWidth()/2) - 400,y2,width, height);
		addWall.setBounds((panel.getWidth()/2) - 200, y1, width, height);
		wallList.setBounds((panel.getWidth()/2) - 200, y2, width, height);
		addQuestion.setBounds((panel.getWidth()/2), y1, width, height);
		questionList.setBounds((panel.getWidth()/2), y2, width, height);
		addTree.setBounds((panel.getWidth()/2)+200, y1, width, height);
		treeList.setBounds((panel.getWidth()/2)+200, y2, width, height);
		help.setBounds(this.width-220, y1, width, height);
		setting.setBounds(this.width-220, y2, width, height);
	}

	public void loadingScreen() {
		display.setLoadingScreen();
		display.getContentPane().removeAll();	
		MenuState menu = new MenuState(display);
	}

	/**
	 * @author Michael J. Alvarado 
	 * This method creates a PopUP with the walls in a currently editing Building.
	 * Date - 04/March/2020
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame)
	 * @param addWallBox - This must be given the addWallBox so that it can be displayed when clicked on a specifi Wall from the PopUp
	 */
	private void wallsListPopUp(int x, int y, AddWallBox addWallBox) {
		if(addBuilding.getText().contentEquals("Finish Building")) {
			PopupMenu wallsPopUp = new PopupMenu("Walls on: " + plane.getCurrentBuilding().getName());
			wallsPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
			if(!plane.getCurrentBuilding().getWalls().isEmpty()) {
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
			else
				JOptionPane.showMessageDialog(plane, "There are currently no walls in this building.");}
		else
			JOptionPane.showMessageDialog(plane, "You must first create or select a building"
					+ " to access its list of walls.");


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
		if(!plane.getMap().getBuildingList().isEmpty()) {
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
		else
			JOptionPane.showMessageDialog(plane, "There currently no buildings in this map.");

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
		if(!plane.getMap().getTrees().isEmpty()) {
			PopupMenu treesPopUp = new PopupMenu("Trees on: " +  plane.getMap().getMapName());
			treesPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
			for(Tree tree: plane.getMap().getTrees()) {
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
		else
			JOptionPane.showMessageDialog(plane, "There are currently no trees in this map.");

	}

	/**
	 * @author jorgecalderon
	 * This method creates a PopUP with the questions in the building being created.
	 * Date - April 10, 2020
	 * @param x - Position x in pixels to display PopUP (Origin is from the JFrame)
	 * @param y - Position y in pixels to display PopUP (Origin is from the JFrame)
	 * @param addQuestionBox - This must be given the addQuestionBox so that it can be edit when clicked on a specific Building from the PopUp
	 */
	private void questionListPopUp(int x, int y, AddQuestionsBox addQuestionBox) {
		if(addBuilding.getText().contentEquals("Finish Building")) {
			PopupMenu questionPopUp = new PopupMenu("Questions on: " +  plane.getMap().getMapName());
			questionPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
			if(!plane.getCurrentBuilding().getQuestionPool().isEmpty()) {
				for(Question q: plane.getCurrentBuilding().getQuestionPool()) {
					MenuItem questionOption = new MenuItem(q.getQuestion());
					questionOption.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							addQuestionBox.edit(q);
						}
					});
					questionPopUp.add(questionOption);
				}
				display.add(questionPopUp);
				questionPopUp.show(display, x, y);
			}
			else
				JOptionPane.showMessageDialog(plane, "There are currently no questions in this building.");
		}
		else
			JOptionPane.showMessageDialog(plane, "You must first create or select a building"
					+ " to access its list of questions.");

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
