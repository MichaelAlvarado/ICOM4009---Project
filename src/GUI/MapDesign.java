package GUI;

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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.ButtonGroup;
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
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.MaskFormatter;

import Components.Building;
import Components.Wall;

import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import javax.swing.JSlider;
import java.awt.Button;

/**
 * This was made using Window Builder's plug in in Eclipse IDE
 */

public class MapDesign{

	private JFrame display;
	private Plane plane;
	private int width, height;

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public MapDesign(JFrame display) throws ParseException {
		this.display = display;
		this.width = display.getContentPane().getWidth();
		this.height = display.getContentPane().getHeight()-15;
		int canvasY = 85; //this is the position in Y where the division is between plane and menu

		display.getContentPane().setBackground(Color.WHITE);
		display.getContentPane().setLayout(null);

		plane = new Plane();
		plane.setBackground(Color.WHITE);
		plane.setLocation(0, canvasY);
		plane.setBounds(0, canvasY, width, height-(canvasY));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(190,190,190));
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		panel.setBounds(0, 0, width, canvasY);
		display.getContentPane().add(panel);
		panel.setLayout(null);

		JButton help = new JButton("Help");
		help.setBounds(width-110, 15, 100, 25);
		panel.add(help);

		JButton setting = new JButton("Setting");
		setting.setBounds(width-110, canvasY-35, 100, 25);
		panel.add(setting);

		JButton addBuilding = new JButton("Add Building");
		addBuilding.setBounds((panel.getWidth()/2)+5, 15, 125, 25);
		panel.add(addBuilding);

		JButton addWall = new JButton("Add Wall");
		addWall.setBounds((panel.getWidth()/2)+130, 15, 125, 25);
		panel.add(addWall);

		JButton editMap = new JButton("Edit Map");
		editMap.setBounds((panel.getWidth()/2)+130, 50, 125, 25);
		panel.add(editMap);
		
		JButton addQuestion = new JButton("Add Question");
		addQuestion.setBounds((panel.getWidth()/2)+5, 50, 125, 25);
		panel.add(addQuestion);	

		JButton wallList = new JButton("Current Walls");
		wallList.setBounds(5,15,150,25);
		panel.add(wallList);

		JButton buildingList = new JButton("Building List");
		buildingList.setBounds(155,15,150,25);
		panel.add(buildingList);

		AddBuildingBox addBuildingBox = new AddBuildingBox((width/2)-250, 200,500,500, plane);
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

		display.getContentPane().add(plane);//add at the end so its on the bottom


		/*
		 * Add Actions to Components
		 */
		setting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				settingPopUp(setting.getX(), setting.getY()+setting.getHeight()*2);
			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				helpScreen();
			}
		});

		wallList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wallsListPopUp(wallList.getX(), wallList.getY()+wallList.getHeight()*2, addWallBox);
			}
		});

		buildingList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildingListPopUp(buildingList.getX(),buildingList.getY()+buildingList.getHeight()*2, addBuildingBox);
			}
		});

		addBuilding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBuildingBox.setVisible(true);
				plane.disable();
			}
		});

		addWall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addWallBox.setVisible(true);
				plane.disable();
			}
		});

		editMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addMapBox.edit();
				plane.disable();
			}
		});
		
		addQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addQuestionBox.setVisible(true);
				plane.disable();
			}
		});
		
	}//Last from Constructor

	private void wallsListPopUp(int x, int y, AddWallBox addWallBox) {
		PopupMenu wallsPopUp = new PopupMenu("Walls on: " + plane.currentBuilding.getName());
		wallsPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
		for(Wall wall: plane.walls) {
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

	private void buildingListPopUp(int x, int y, AddBuildingBox addBuildingBox) {
		PopupMenu buildingsPopUp = new PopupMenu("Buildings on: " +  plane.map.getBuildingName());
		buildingsPopUp.setFont(new Font("Arial", Font.PLAIN, 15));
		for(Building building: plane.buildings) {
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

	private void settingPopUp(int x, int y) {
		PopupMenu setting = new PopupMenu("Settings");

		MenuItem undo = new MenuItem("Undo wall");
		MenuItem clear = new MenuItem("Clear All walls");
		MenuItem grid = new MenuItem("Grid");
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
				plane.gridIsOn = !plane.gridIsOn;
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

		setting.add(undo);
		setting.add(clear);
		setting.add(grid);
		setting.add(cpColor);
		setting.add(clColor);
		setting.add(ppColor);
		setting.add(plColor);

		display.add(setting);
		setting.show(display, x, y);
	}

	private void colorPopup(int x, int y, String component) {
		PopupMenu ColorPopup = new PopupMenu();

		MenuItem magenta = new MenuItem("Magenta");
		MenuItem green = new MenuItem("Green");
		MenuItem red = new MenuItem("Red");
		MenuItem blue = new MenuItem("Blue");
		MenuItem orange = new MenuItem("Orange");

		magenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.MAGENTA); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.MAGENTA);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.MAGENTA);
				else if(component.equals("previousLine"))
					plane.pL = (Color.MAGENTA);				
				plane.repaint();
			}
		});

		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color green = new Color(20,198,5); //Green
				if(component.equals("currentPoint"))
					plane.cP = (green); 
				else if(component.equals("currentLine"))
					plane.cL = (green);
				else if(component.equals("previousPoint"))
					plane.pP = (green);
				else if(component.equals("previousLine"))
					plane.pL = (green);
				plane.repaint();
			}
		});

		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.RED); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.RED);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.RED);
				else if(component.equals("previousLine"))
					plane.pL = (Color.RED);
				plane.repaint();
			}
		});

		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.BLUE); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.BLUE);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.BLUE);
				else if(component.equals("previousLine"))
					plane.pL = (Color.BLUE);
				plane.repaint();
			}
		});

		orange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(component.equals("currentPoint"))
					plane.cP = (Color.ORANGE); 
				else if(component.equals("currentLine"))
					plane.cL = (Color.ORANGE);
				else if(component.equals("previousPoint"))
					plane.pP = (Color.ORANGE);
				else if(component.equals("previousLine"))
					plane.pL = (Color.ORANGE);
				plane.repaint();
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

}
