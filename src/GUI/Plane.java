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
	LinkedList<Wall> walls; //add the wall at the first index (So it can access to last element added faster)
	LinkedList<Building> buildings;
	Building currentBuilding;
	private Point[] currentPointPair; //this is the current trace being drawn
	private Point drag; //this is use to interconnect points
	private int pointWidth; //points of coordinates
	private int xGap, yGap; //this is the distance of line to line
	private int xOrigin, yOrigin; //Pixel position on canvas origin
	Color cP, cL, pP, pL; //Color for c=current p=previous P=Point L=Line
	boolean gridIsOn; //draw the grid line in plane

	public Plane() {
		pointWidth = 10;
		cP = Color.BLUE;
		cL = Color.MAGENTA;
		pP = Color.RED;
		pL = new Color(20,198,5); //Green
		gridIsOn = true;
		walls = new LinkedList<Wall>();
		buildings = new LinkedList<Building>();
		currentBuilding = new Building("Test");
		map = new Map(buildings,"map",this.getWidth(), this.getHeight());
		currentPointPair = new Point[2];

		addMouseListener(new Mouse());
		addMouseMotionListener(new MouseMotion());
		addKeyListener(new Keyboard());
	}

	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		int gridLineQuantity = 16;
		xGap = this.getWidth()/gridLineQuantity; //wide of rectangles
		yGap = this.getHeight()/gridLineQuantity; //height of rectangles
		xOrigin = 0; //position in canvas of point x origin
		yOrigin = this.getHeight(); //position in canvas of point y origin

		//Draw background Image
		if(map.getPicture() != null) {
			g.drawImage(map.getPicture(), 0, 0, getWidth(), getHeight(), null);
		}

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
		for(Wall line: walls) {
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
	 * Date - 03/March/2020
	 * Objective - this method erase all the walls created in the currentBuilding
	 */
	public void clearAllWalls() {
		if(!walls.isEmpty()) {
			walls.clear();
			this.repaint();
		}
	}

	/**
	 * @author Michael Alvarado
	 * Date - 03/March/2020
	 * Objective - this method erase last wall added.
	 */
	public void undo() {
		if(!walls.isEmpty()) {
			walls.remove(); //remove the last lines added
			this.repaint();
		}
	}

	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * @author Michael Alvarado
	 *	this class is made for the mouse to be able to make points and line to create walls
	 *	Date - 03/March/2020
	 */
	private class Mouse extends MouseAdapter{

		public Mouse() {
			super();
		}

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
			if(currentPointPair[1] != null) {
				walls.addFirst(new Wall(currentBuilding.getName()+" Wall"+walls.size(), currentPointPair[0], currentPointPair[1]));
			}
			currentPointPair[0] = null;
			currentPointPair[1] = null;
			repaint();
		}
	}

	/** 
	 * @author Michael J. Alvarado
	 *	Date - 03/March/2020
	 *	This class was made to make the drag functionality so its easier to interconnect points
	 */
	private class MouseMotion extends MouseMotionAdapter{

		private Point drag(int x, int y) {
			for(Wall line: walls) {
				if(line.getP1().distance(x, y) < pointWidth) {
					return line.getP1();
				}
				else if(line.getP2().distance(x, y) < pointWidth) {
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

	}

	/**
	 * @author Michael J. Alvarado	 
	 *	Date - 03/March/2020
	 * This class was made so the user can do key shortcuts and for debugging
	 */
	private class Keyboard implements KeyListener{
		public Keyboard() {
			super();
		}

		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.isControlDown() && arg0.getKeyCode() == arg0.VK_Z) {
				undo();
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {	
			if(arg0.getKeyCode() == arg0.VK_K) { //Debugging Button
				System.out.println(map.getBuildingName());
				System.out.println(getWidth() + " , " + getHeight());
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

} //Last 

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
