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
		this.width = display.getWidth();
		this.height = display.getHeight();
		int canvasY = 85; //this is the position in Y where the division is between plane and menu

		display.getContentPane().setBackground(Color.WHITE);
		display.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(190,190,190));
		panel.setBorder(new LineBorder(UIManager.getColor("Button.darkShadow"), 3, true));
		panel.setBounds(0, 0, width, canvasY);
		display.getContentPane().add(panel);
		panel.setLayout(null);
	
		this.plane = new Plane();
		this.plane.setBackground(Color.WHITE);
		this.plane.setBounds(0, canvasY, width, height-(canvasY*2));
		display.getContentPane().add(this.plane);


		/*
		 * Add Actions to Components
		 */
		
	}

	private void settingScreen(int x, int y) {
		PopupMenu setting = new PopupMenu();

		MenuItem clear = new MenuItem("clear current line");
		MenuItem cpColor = new MenuItem("change current point Color");
		MenuItem clColor = new MenuItem("change current line Color");
		MenuItem ppColor = new MenuItem("change previous point Color");
		MenuItem plColor = new MenuItem("change previous line Color");

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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

		setting.add(cpColor);
		setting.add(clColor);
		setting.add(ppColor);
		setting.add(plColor);
		setting.add(clear);

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
//				if(component.equals("currentPoint"))
//					plane.currentPointColor(Color.MAGENTA); 
//				else if(component.equals("currentLine"))
//					plane.currentLineColor(Color.MAGENTA);
//				else if(component.equals("previousPoint"))
//					plane.previousPointColor(Color.MAGENTA);
//				else if(component.equals("previousLine"))
//					plane.previousLineColor(Color.MAGENTA);
			}
		});

		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Color green = new Color(20,198,5); //Green
//				if(component.equals("currentPoint"))
//					plane.currentPointColor(green); 
//				else if(component.equals("currentLine"))
//					plane.currentLineColor(green);
//				else if(component.equals("previousPoint"))
//					plane.previousPointColor(green);
//				else if(component.equals("previousLine"))
//					plane.previousLineColor(green);
			}
		});

		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				if(component.equals("currentPoint"))
//					plane.currentPointColor(Color.RED); 
//				else if(component.equals("currentLine"))
//					plane.currentLineColor(Color.RED);
//				else if(component.equals("previousPoint"))
//					plane.previousPointColor(Color.RED);
//				else if(component.equals("previousLine"))
//					plane.previousLineColor(Color.RED);
			}
		});

		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				if(component.equals("currentPoint"))
//					plane.currentPointColor(Color.BLUE); 
//				else if(component.equals("currentLine"))
//					plane.currentLineColor(Color.BLUE);
//				else if(component.equals("previousPoint"))
//					plane.previousPointColor(Color.BLUE);
//				else if(component.equals("previousLine"))
//					plane.previousLineColor(Color.BLUE);
			}
		});
		
		orange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				if(component.equals("currentPoint"))
//					plane.currentPointColor(Color.ORANGE); 
//				else if(component.equals("currentLine"))
//					plane.currentLineColor(Color.ORANGE);
//				else if(component.equals("previousPoint"))
//					plane.previousPointColor(Color.ORANGE);
//				else if(component.equals("previousLine"))
//					plane.previousLineColor(Color.ORANGE);
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
