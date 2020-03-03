package GUI;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.sound.sampled.Line;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Components.Building;
import Components.Map;
import Components.Wall;

/**
 * 
 * @author Michael Alvarado
 * Date - 28/Feb/2020  
 * This class is a Canvas UI with the building maps
 */
public class Plane extends Canvas{
	Map map;
	LinkedList<Wall> lines; //add the wall at the first index (So it can access to last element added faster)
	LinkedList<Building> buildings;
	Point[] currentPointPair; //this is the current trace being drawn
	Point drag; //this is use to interconnect points
	String currentBuilding;
	int pointWidth; //points of coordinates
	int xGap, yGap; //this is the distance of line to line
	int xOrigin, yOrigin; //Pixel position on canvas origin
	Color cP, cL, pP, pL; //Color for c=current p=previous P=Point L=Line
	boolean gridIsOn; //draw the grid line in plane

	public Plane() {
		pointWidth = 10;
		cP = Color.BLUE;
		cL = Color.MAGENTA;
		pP = Color.RED;
		pL = new Color(20,198,5); //Green
		gridIsOn = true;
		lines = new LinkedList<Wall>();
		buildings = new LinkedList<Building>();
		map = new Map(buildings,"map",this.getWidth(), this.getHeight());
		currentPointPair = new Point[2];


		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent arg0) {
				System.out.println("Pressed / "+"x: " + arg0.getX() + "  y:" + (getHeight() -arg0.getY()));
				if(drag == null) {
					currentPointPair[0] = new Point(arg0.getX(), arg0.getY());
				}
				else {
					currentPointPair[0] = drag;
				}
				currentPointPair[1] = null;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("Released / "+"x: " + arg0.getX() + "  y:" + (getHeight() - arg0.getY()));
				//add to line only if there was a mouse displacement
				if(currentPointPair[0].x != currentPointPair[1].x
						&& currentPointPair[0].y != currentPointPair[1].y) {
					lines.addFirst(new Wall(currentBuilding, currentPointPair[0], currentPointPair[1]));
				}
				currentPointPair[0] = null;
				currentPointPair[1] = null;
				repaint();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter(){
			//this method put the point on top of another if close enough
			private Point drag(int x, int y) {
				for(Wall line: lines) {
					if(line.getP1().distance(x, y) < pointWidth) {
						System.out.println("Near Point");
						return line.getP1();
					}
					else if(line.getP2().distance(x, y) < pointWidth) {
						System.out.println("Near Point");
						return line.getP2();
					}
				}
				return null;
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				drag = drag(arg0.getX(), arg0.getY());
				if(drag == null) {
					currentPointPair[1] = new Point(arg0.getX(), arg0.getY());
				}
				else {
					currentPointPair[1] = drag;
				}
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				//This will be use to drag mouse to the near point
				drag = drag(arg0.getX(), arg0.getY());
			}
		});
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.isControlDown() && arg0.getKeyCode() == arg0.VK_Z) {
					undo();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
	}

	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		int gridLineQuantity = 16;
		xGap = this.getWidth()/gridLineQuantity; //wide of rectangles
		yGap = this.getHeight()/gridLineQuantity; //height of rectangles
		xOrigin = 0; //position in canvas of point x origin
		yOrigin = this.getHeight(); //position in canvas of point y origin

		//draw Grid Lines
		if(gridIsOn) {
			g.setColor(Color.LIGHT_GRAY);
			//It draws negative and positive axis separately to make sure they are aligned with axis X and Y
			for(int i = 0; i < gridLineQuantity; i++) {
				g.drawLine((xOrigin + i*xGap), 0, (xOrigin + i*xGap), this.getHeight()); //draw positive x lines
				g.drawLine(0, (yOrigin - i*yGap), this.getWidth(), (yOrigin - i*yGap)); //draw positive y lines
			}
			//draw coordinates Number
			g.setColor(Color.GRAY);
			g.setFont(new Font("Arial", Font.PLAIN, 14));
			for(int s = 0; s < gridLineQuantity; s++) {
				//If is polar plane only draw one number per magnitude
				g.drawString(String.valueOf(s)+" m", (xOrigin + s*xGap), yOrigin); //draw positive X coordinate 
				g.drawString(String.valueOf(s)+" m", xOrigin, (yOrigin - s*yGap)); //draw positive Y coordinate 
			}
		}

		//Draw lines from point to point
		for(Wall line: lines) {
			g.setColor(pP);//Color of Points
			g.fillOval((int)(line.getP1().getX()-(pointWidth/2)), (int)(line.getP1().getY()-(pointWidth/2)), pointWidth, pointWidth);
			g.fillOval((int)(line.getP2().getX()-(pointWidth/2)), (int)(line.getP2().getY()-(pointWidth/2)), pointWidth, pointWidth);
			g.setColor(pL);//Color of Lines
			g.drawLine((int)line.getP1().getX(), (int)line.getP1().getY(), (int)line.getP2().getX(), (int)line.getP2().getY());
		}

		//Draw current Points
		g.setColor(cP);
		if(currentPointPair[0] != null) {
			g.fillOval((int)(currentPointPair[0].getX()-(pointWidth/2)), (int)(currentPointPair[0].getY()-(pointWidth/2)), pointWidth, pointWidth);
		}
		if(currentPointPair[1] != null) {
			g.fillOval((int)(currentPointPair[1].getX()-(pointWidth/2)), (int)(currentPointPair[1].getY()-(pointWidth/2)), pointWidth, pointWidth);
		}

		//Draw current Line
		g.setColor(cL);
		if(currentPointPair[0] != null && currentPointPair[1] != null) {
			g.drawLine((int)currentPointPair[0].getX(), (int)currentPointPair[0].getY(), (int)currentPointPair[1].getX(), (int)currentPointPair[1].getY());
		}

		//		//Draw panel with coordinates
		//		g.setColor(new Color(0,0,0,100));
		//		g.fillRect(this.getWidth()-200, 0, 200, 30);
		//		//Draw coordinates point on panel
		//		g.setFont(new Font("Arial", Font.PLAIN, 20));
		//		g.setColor(cP);
		//		g.drawString("( " + currentPoint.getX() + " , " + currentPoint.getY() + " )", this.getWidth()-200, 20);

	}



		/**
		 * @author Michael Alvarado
		 * Date - 13/Feb/2020
		 * Objective - this method erase all the lines and create a new line
		 */
		public void clearAll() {
			lines.clear();
			this.repaint();
		}
		
		/**
		 * @author Michael Alvarado
		 * Date - 13/Feb/2020
		 * Objective - this method erase last wall added.
		 */
		public void undo() {
			lines.remove(); //remove the last lines added
			this.repaint();
		}
	
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 13/Feb/2020
	//	 * Objective - this method create a new list of coordinates(Line), adds point at the origin, adds that line to the list of lines and make this new line the current line. 
	//	 * It repaints the canvas so that the change can be seen
	//	 */
	//	public void newLine() {
	//		ArrayList<Coordinates> line = new ArrayList<Coordinates>();
	//		line.add(new CartesianCoordinates(0,0));
	//		lines.add(line);
	//		currentLine = line; 
	//		this.repaint();
	//	}
	//	
	//	
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 20/Feb/2020
	//	 * Objective - this method will change the color of the current point
	//	 */
	//	public void currentPointColor(Color cP) {
	//		this.cP = cP;
	//		this.repaint();
	//	}
	//	
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 20/Feb/2020
	//	 * Objective - this method will change the color of the current line
	//	 */
	//	public void currentLineColor(Color cL) {
	//		this.cL = cL;
	//		this.repaint();
	//	}
	//	
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 20/Feb/2020
	//	 * Objective - this method will change the color of the points previous to the current point
	//	 */
	//	public void previousPointColor(Color pP) {
	//		this.pP = pP;
	//		this.repaint();
	//	}
	//	
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 20/Feb/2020
	//	 * Objective - this method will change the color of the lines previous to the current line
	//	 */
	//	public void previousLineColor(Color pL) {
	//		this.pL = pL;
	//		this.repaint();
	//	}
	//
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 13/Feb/2020
	//	 * @param coor - give the coordinate it want to draw on the plane
	//	 * @return - The position in x (in pixels) the coordinate should be painted
	//	 */
	//	private int printCoordinatesX(Coordinates coor) {
	//		return ((int)(coor.getX()/this.scale*xGap)+xOrigin);
	//	}
	//
	//	/**
	//	 * @author Michael Alvarado
	//	 * Date - 13/Feb/2020
	//	 * @param coor - give the coordinate it want to draw on the plane
	//	 * @return - The position in y (in pixels) the coordinate should be painted
	//	 */
	//	private int printCoordinatesY(Coordinates coor) {
	//		return ((int)(-coor.getY()/this.scale*yGap)+yOrigin);
	//	}

}
