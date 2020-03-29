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
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.KeyStroke;

import Components.Building;
import Components.Map;
import Components.Question;
import Components.Wall;
import Components.Trees;

/**
 * 
 * @author Michael Alvarado
 * Date - 28/Feb/2020  
 * This class is a Canvas UI with the building maps. 
 * It stores the map info in this class until you save it which generates a txt file
 * 
 */
public class Plane extends JPanel{

	Map map;
	Building currentBuilding; //the building currently being edit
	Trees currentTree; //the tree currently being added
	Color cP, cL, pP, pL; //Color for c=current p=previous P=Point L=Line
	boolean gridIsOn; //draw the grid line in plane
	boolean buildingImagesIsOn;
	private Point[] currentPointPair; //this is the current trace being drawn
	private Point drag; //this is use to interconnect points
	private int pointWidth; //points of coordinates
	private int xGap, yGap; //this is the distance of line to line
	private int xOrigin, yOrigin; //Pixel position on canvas origin
	private Mouse mouse; //use to create walls with mouse
	private MouseMotion mouseMotion; //use to drag points
	Keyboard keyboard; //use for shortcuts
	private double scaleX, scaleY; //This is to scale the map
	private boolean isOpenTool; //Testing

	public Plane() {
		isOpenTool = false;
		setLayout(null);
		pointWidth = 10;
		cP = Color.BLUE;
		cL = Color.MAGENTA;
		pP = Color.RED;
		pL = new Color(20,198,5); //Green
		gridIsOn = true;
		buildingImagesIsOn = false;
		map = new Map();
		currentPointPair = new Point[2];
		mouse = new Mouse();
		mouseMotion = new MouseMotion();
		keyboard = new Keyboard();
		addMouseListener(mouse);
		addMouseMotionListener(mouseMotion);
		addKeyListener(keyboard);
	}

	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		scaleX = (double)(map.getWidth())/(double)(getWidth());
		scaleY = (double)(map.getHeight())/(double)(getHeight());
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
				g.drawString(String.valueOf(Math.round(s*xGap*scaleX))+" m", (xOrigin + s*xGap), yOrigin); //draw positive X coordinate 
				g.drawString(String.valueOf(Math.round(s*yGap*scaleY))+" m", xOrigin, (yOrigin - s*yGap)); //draw positive Y coordinate 
			}
		}

		//Draw lines from point to point
		for(Building building: map.getBuildingList()) {
			for(Wall line: building.getWalls()) {
				//				g.setColor(pP);//Color of Points
				//				g.fillOval((int)(line.getP1().getX()-(pointWidth/2)), (int)(line.getP1().getY()-(pointWidth/2)), pointWidth, pointWidth);
				//				g.fillOval((int)(line.getP2().getX()-(pointWidth/2)), (int)(line.getP2().getY()-(pointWidth/2)), pointWidth, pointWidth);
				g.setColor(pL);//Color of Lines
				g.drawLine((int)(line.getP1().getX()*scaleX), (int)(line.getP1().getY()*scaleY), (int)(line.getP2().getX()*scaleX), (int)(line.getP2().getY()*scaleY));
			}
		}

		//Draw Current Building Walls
		if(currentBuilding != null) {
			for(Wall line: currentBuilding.getWalls()) {
				g.setColor(cP);//Color of Points
				g.fillOval((int)Math.round((line.getP1().getX()*scaleX-(pointWidth/2))), (int)Math.round((line.getP1().getY()*scaleY-(pointWidth/2))), pointWidth, pointWidth);
				g.fillOval((int)Math.round((line.getP2().getX()*scaleX-(pointWidth/2))), (int)Math.round((line.getP2().getY()*scaleY-(pointWidth/2))), pointWidth, pointWidth);
				g.setColor(cL);//Color of Lines
				g.drawLine((int)(line.getP1().getX()*scaleX), (int)(line.getP1().getY()*scaleY), (int)(line.getP2().getX()*scaleX), (int)(line.getP2().getY()*scaleY));
			}
		}

		//		if(currentTree != null) {
		//			g.drawImage(currentTree.getTreeImage(), 0, 0, getWidth(), getHeight(), null);       TODO: implement
		//		}

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

		//Draw Current Coordinates
		if(currentPointPair[0] != null && currentPointPair[1] != null) {
			//Draw panel with coordinates
			g.setColor(new Color(0,0,0,100));
			g.fillRect(this.getWidth()-300, 0, 300, 30);
			//Draw coordinates point on panel
			g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setColor(cP);
			g.drawString("( " + Math.round(currentPointPair[0].getX()*scaleX) + " , " + Math.round(currentPointPair[0].getY()*scaleY) + " )", this.getWidth()-300, 20);
			g.drawString("( " + Math.round(currentPointPair[1].getX()*scaleX) + " , " + Math.round(currentPointPair[1].getY()*scaleY) + " )", this.getWidth()-150, 20);

		}
		//Draw Building Image
		if(buildingImagesIsOn) {
			for(Building building: map.getBuildingList()) {
				if (building.getPicture()!=null) {
					Rectangle r = building.perimeter();
					g.drawImage(building.getPicture(), r.x, r.y, r.width, r.height, null);
				}
			}
		}
		
		//Draw Tree Image
		for(Trees tree: map.getTrees()) {
			g.drawImage(tree.getTreeImage(), tree.getP1().x, tree.getP1().y, tree.getTreeHeight(), tree.getTreeHeight(), null);
		}

		//Draw tool panel
		if(isOpenTool) {
			g.setColor(new Color(0,0,0,100));
			g.fillRect(this.getWidth()-100, 0, 200, getHeight());
		}
	} //Paint end

	/**
	 * @author Michael Alvarado
	 * Date - 03/March/2020
	 * Objective - this method erase all the walls created in the currentBuilding
	 */
	public void clearAllWalls() {
		if(!currentBuilding.getWalls().isEmpty()) {
			currentBuilding.getWalls().clear();
			this.repaint();
		}
	}

	/**
	 * @author Michael Alvarado
	 * Date - 03/March/2020
	 * Objective - this method erase last wall added.
	 */
	public void undo() {
		if(!currentBuilding.getWalls().isEmpty()) {
			currentBuilding.getWalls().remove();
			this.repaint();
		}
	}
	/**
	 * @author Michael J. Alvarado
	 * Date - 04/March/2020
	 * This method will disable mouse therefore you cannot draw walls
	 */
	public void disable() {
		mouse.disable();
		mouseMotion.disable();
		keyboard.disable();
	}

	/**
	 * @author Michael J. Alvarado
	 * Date - 04/March/2020
	 * This method will enable mouse therefore you can draw walls
	 */
	public void enable() {
		mouse.enable();
		mouseMotion.enable();
		keyboard.enable();
	}

	/**
	 * This method is use to add a wall with the click and drag method
	 *	This is assuming p1 and p2 are originated in upper left corner
	 *(Remember the display is actually on bottom left)
	 *	PreCondition: the MouseListener and MouseMotionListener in Plane must be initialize and there must be a CurrentBuilding
	 *	
	 * @param point p1 in wall
	 * @param point p2 in wall
	 */
	public void addWall(Point p1, Point p2) {
		Wall wall = new Wall(getWallAutoGeneratedName(), p1, p2);
		wall.setHeight(currentBuilding.getBuildingHeight());
		addWall(wall);
	}

	public void addWall(Wall wall) {
		currentBuilding.getWalls().addFirst(wall);
		this.repaint();
	}

	public void addBuilding(Building building) {
		map.addBuilding(building);
		setCurrentBuilding(building);
	}
	
	public void setGrid() {
		gridIsOn = !gridIsOn;
	}
	
	public boolean isBuildingImagesIsOn() {
		return buildingImagesIsOn;
	}

	public void setBuildingImages() {
		this.buildingImagesIsOn = !buildingImagesIsOn;
		this.repaint();
	}
	
	/**
	 * @author Michael J. Alvarado
	 * This Methods paints the components  of traces and points specified  on the plane.
	 * @param color - Color to paint components specified in @param compenent
	 * @param component - This Strings specified that to paint (giving by the Settings PopUP)
	 */
	public void paintPlaneComponent(Color color, String component) {
		if(component.equals("currentPoint"))
			cP = (color); 
		else if(component.equals("currentLine"))
			cL = (color);
		else if(component.equals("previousPoint"))
			pP = (color);
		else if(component.equals("previousLine"))
			pL = (color);				
		repaint();
	}

	public Map getMap() {
		return map;
	}

	/**
	 * 
	 * @author Michael J. Alvarado
	 * This methods prints the tool panel in plane
	 * @date Mar 12, 2020
	 */
	public void openTool() {
		this.isOpenTool = true;
		this.repaint();
	}
	
	/**
	 * 
	 * @author Michael J. Alvarado
	 * This methods ignore print tool panel 
	 * @date Mar 12, 2020
	 */
	public void closeTool() {
		this.isOpenTool = false;
		this.repaint();
	}

	public void addTree(Trees tree) {
		map.addTree(tree);
		setCurrentTree(tree);
	}

	public void setCurrentBuilding(Building building) {
		currentBuilding = building;
		this.repaint();
	}
	
	public Building getCurrentBuilding() {
		return currentBuilding;
	}

	public void setCurrentTree(Trees tree) {
		currentTree = tree;
		this.repaint();
	}

	public void setMap(Map map) {
		this.map = map;

	}
	/**
	 * @author Michael Alvarado
	 * PreCondition - The Plane must have a current Building editing
	 * Date - 03/March/2020
	 * @return A autoGenerated name for a wall given from the building its from and the quantity of walls
	 */
	public String getWallAutoGeneratedName() {
		return currentBuilding.getName()+"_w" + String.valueOf(currentBuilding.getWalls().size());
	}


	/**
	 * @author Michael Alvarado
	 *	this class is made for the mouse to be able to make points and line to create walls
	 *	Date - 03/March/2020
	 */
	private class Mouse extends MouseAdapter{

		private boolean enable;

		public Mouse() {
			super();
			enable = true;
		}

		public void enable() {
			enable = true;
		}

		public void disable() {
			enable = false;
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			if(enable && currentBuilding != null) {
				System.out.println("Pressed / "+"x: " + arg0.getX() + "  y:" + (getHeight()-arg0.getY()));
				if(drag == null) {
					currentPointPair[0] = new Point(arg0.getX(), arg0.getY());
				}
				else {
					currentPointPair[0] = drag;
				}
				currentPointPair[1] = null;
				repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			if(enable) {
				if(currentBuilding != null) {
					System.out.println("Released / "+"x: " + arg0.getX() + "  y:" + (getHeight() - arg0.getY()));
					if(currentPointPair[1] != null && currentPointPair[0] != null) {
						//add to wall only if there was a mouse displacement
						currentPointPair[0].setLocation(currentPointPair[0].x/scaleX, currentPointPair[0].y/scaleY); //remember the origin in the plane is bottom left
						currentPointPair[1].setLocation(currentPointPair[1].x/scaleX, currentPointPair[1].y/scaleY); //remember the origin in the plane is bottom left
						addWall(currentPointPair[0], currentPointPair[1]);
					}
					currentPointPair[0] = null;
					currentPointPair[1] = null;
					repaint();
				}
				//This means you can't create walls since you are not editing any building
				else {
					JOptionPane.showMessageDialog(Plane.this, "You must create a Building in order to create walls");
				}
			}
		}

	}

	/** 
	 * @author Michael J. Alvarado
	 *	Date - 03/March/2020
	 *	This class was made to make the drag functionality so its easier to interconnect points and to paint line while moving
	 */
	private class MouseMotion extends MouseMotionAdapter{
		private boolean enable;
		public MouseMotion(){
			super();
			enable = true;
		}
		public void enable() {
			enable = true;
		}
		public void disable() {
			enable = false;
		}
		private Point dragToPoint(double x, double y) {
			for(Wall line: currentBuilding.getWalls()) {
				if(line.getP1().distance(x/scaleX, y/scaleY) < pointWidth) {
					return  new Point((int)Math.round(line.getP1().x*scaleX), (int)Math.round(line.getP1().y*scaleY));
				}
				else if(line.getP2().distance(x/scaleX, y/scaleY) < pointWidth) {
					return  new Point((int)Math.round(line.getP2().x*scaleX), (int)Math.round(line.getP2().y*scaleY));
				}
			}
			return null;
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			//This is use to put the dragging point on near Point
			if(enable && currentBuilding != null) {
				drag = dragToPoint(arg0.getX(), arg0.getY());
				if(drag == null) {
					currentPointPair[1] = new Point(arg0.getX(), arg0.getY());
				}
				else {
					currentPointPair[1] = drag;
				}
				repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			//This is use to place initial point on a near Point
			setFocusable(true);
			requestFocus(); 
			if(enable && currentBuilding != null) {
				drag = dragToPoint(arg0.getX(), arg0.getY());
			}
		}

	}

	/**
	 * @author Michael J. Alvarado	 
	 *	Date - 03/March/2020
	 * This class was made so the user can do key shortcuts and for debugging
	 */
	private class Keyboard implements KeyListener{
		private boolean enable;
		public Keyboard() {
			enable = true;
		}
		public void enable() {
			enable = true;
		}
		public void disable() {
			enable = false;
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			/*
			 * Shorcuts
			 */
			if(enable) {
				if(arg0.isControlDown() && arg0.getKeyCode() == arg0.VK_Z) {
					undo();
				}
				if(arg0.isControlDown() && arg0.getKeyCode() == arg0.VK_S) {
					map.generateTextFile();
					System.out.println("Map saved");
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {	
			/*
			 * debugging Buttons
			 */
			if(enable) {
				//Print Map info and saves it
				if(arg0.getKeyCode() == arg0.VK_K) { 
					System.out.println(map.getMapName());
					System.out.println(getWidth() + " , " + getHeight());
				}
				//Print question on currentBuilding
				if(arg0.getKeyCode() == arg0.VK_Q) {
					for(Question question: currentBuilding.getQuestionPool()) {
						System.out.println("Question: " + question.getQuestion());
						System.out.println("Correct: " + question.getAnswer_1());
						System.out.println("Incorrect: " + question.getAnswer_2());
						System.out.println("Incorrect: " + question.getAnswer_3());
						System.out.println("Incorrect: " + question.getAnswer_4());
					}
				}
				//add a Test building
				if(arg0.getKeyCode() == arg0.VK_N) {
					addBuilding(new Building("Test"));
					System.out.println("Test Building Created");
				}
				//Print scale factors
				if(arg0.getKeyCode() == arg0.VK_F) {
					System.out.println("scaleX: "+scaleX+" , "+"scaleY: "+scaleY);
				}
				//Print CurrentBuilding Info
				if(arg0.getKeyCode() == arg0.VK_B) {
					if(currentBuilding != null) {
						System.out.println("Current Building Info:");
						System.out.println("Name: " + currentBuilding.getName());
						System.out.println("Building Height: " + currentBuilding.getBuildingHeight());
						System.out.println("Wall info:\n" + currentBuilding.getWallInfo());
					}
					else {
						System.out.println("No current building");
					}
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}



	/**
	 * 
	 */

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
