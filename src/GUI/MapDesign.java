package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

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
		this.width = display.getWidth() - (display.getInsets().left + display.getInsets().right); //this takes the width size inside the app
		this.height = display.getHeight() - (display.getInsets().top + display.getInsets().bottom); //this takes the height size inside the app
		int canvasY = 85; //this is the position in Y where the division is between plane and menu

		display.getContentPane().setBackground(Color.WHITE);
		display.getContentPane().setLayout(null);

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
		
		JButton addMap = new JButton("Add Map");
		addMap.setBounds((panel.getWidth()/2)+130, 50, 125, 25);
		panel.add(addMap);
		
		AddBuildingBox addBuildingBox = new AddBuildingBox((width/2)-250, 200,500,200);
		addBuildingBox.setVisible(false);
		display.getContentPane().add(addBuildingBox);
		
		AddWallBox addWallBox = new AddWallBox((width/2)-250, 200,500,200);
		addWallBox.setVisible(false);
		display.getContentPane().add(addWallBox);
		
		AddMapBox addMapBox = new AddMapBox((width/2)-250, 200,500,200);
		addMapBox.setVisible(true);
		display.getContentPane().add(addMapBox);

		this.plane = new Plane();
		this.plane.setBackground(Color.WHITE);
		this.plane.setLocation(0, canvasY);
		this.plane.setBounds(0, canvasY, width, height-(canvasY));
		display.getContentPane().add(this.plane);


		/*
		 * Add Actions to Components
		 */
		setting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				settingPopUp(setting.getX(), setting.getY()+setting.getHeight());
			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				helpScreen();
			}
		});
		
		addBuilding.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBuildingBox.setVisible(true);
			}
		});
		
		addWall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addWallBox.setVisible(true);
			}
		});
	}

	private void settingPopUp(int x, int y) {
		PopupMenu setting = new PopupMenu();

		MenuItem clear = new MenuItem("Clear last line");
		MenuItem grid = new MenuItem("Grid");
		MenuItem cpColor = new MenuItem("Change current point Color");
		MenuItem clColor = new MenuItem("Change current line Color");
		MenuItem ppColor = new MenuItem("Change previous point Color");
		MenuItem plColor = new MenuItem("Change previous line Color");

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
