package Components;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Font;

import GUI.Display;
import GameSetUp.Handler;
import MapDesingComponents.AddQuestionsBox;
import MapDesingComponents.Plane;
import Resources.Animation;
import Resources.Images;
import States.QuestionState;

import java.awt.Rectangle;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


/**
 * 
 * @author Fabiola Badillo
 * Date - Feb 29, 2020
 * This class describes the Building instances with all its attributes and methods  
 *
 */

public class Building {

	Question question;
	Display display;
	LinkedList<Wall> walls; // = new LinkedList<Wall>();
	private String buildingName; 
	private BufferedImage picture;
	private String pictureURL;
	private LinkedList<Question> questionPool;
	private int buildingHeight; //this is default height of walls
	private int width, height;
	//GameRelated
	private boolean playerCloseBy;
	private boolean found;
	private boolean debuggingMode;
	private BuildingAnimation animation;

	// constructor
	public Building(String buildingName) {
		this.buildingName = buildingName;
		walls = new LinkedList<Wall>();
		questionPool = new LinkedList<Question>();
		animation = new BuildingAnimation();
	}

	/**
	 * @author jorgecalderon
	 * Objective - Generate a string containing the questions with their answers
	 * Date - 03/08/2020
	 * @param - N/A
	 * @param - N/A
	 * @return - N/A 
	 */
	public String getQuestions() {
		String result = "";
		for (Question q: this.questionPool) {
			if (q.getQuestion().length() < 45) {
				result += "Question: " + q.getQuestion() + "\n"
						+ "Right: " + q.getAnswer_1() + "\n"
						+"Wrong: " + q.getAnswer_2() + "\n"
						+"Wrong: " + q.getAnswer_3() + "\n"
						+"Wrong: " + q.getAnswer_4() + "\n";
			}
			else {
				String split = q.getQuestion().substring(45);
				result += "Question: " + q.getQuestion().substring(0, 44) + "\n"
						+ split + "\n"
						+ "Right: " + q.getAnswer_1() + "\n"
						+"Wrong: " + q.getAnswer_2() + "\n"
						+"Wrong: " + q.getAnswer_3() + "\n"
						+"Wrong: " + q.getAnswer_4() + "\n";
			}
		}
		return result;
	}

	/**
	 * @author jorgecalderon
	 * Objective - Generate a string the walls information
	 * Date - 03/08/2020
	 * @param - N/A
	 * @param - N/A
	 * @return - N/A 
	 */
	public String getWallInfo() {
		String result = "";
		for (Wall w: this.walls) {
			result += "ID: " + w.getID() + ", Height: " + w.getHeight() + ", First Point: (" + w.getP1().x
					+ ", " + w.getP1().y + "), Second Point: (" + w.getP2().x + ", " + w.getP2().y +
					")\nWall Image: " + w.getTextureURL() + "\n";
		}

		return result;
	}
	// getters 
	public LinkedList<Wall> getWalls(){
		return this.walls;
	}
	public String getName() {
		return this.buildingName;
	}
	public BufferedImage getPicture() {
		return this.picture;
	}
	public LinkedList<Question> getQuestionPool(){
		return this.questionPool;
	}
	public boolean getFound() {
		return this.found;
	}

	// setters
	public void setWalls(LinkedList<Wall> walls) {
		this.walls = walls;
	}
	public void setName(String name) {
		this.buildingName = name;
	}
	public void setPicture(BufferedImage pic) {
		this.picture = pic;
	}
	public void setQuestions(LinkedList<Question> questionPool) {
		this.questionPool = questionPool;
	}
	public void setFound(boolean found) {
		this.found = found;
	}

	/**
	 * @author Fabiola Badillo
	 * Date - Feb 29, 2020
	 * Objective - This method will add a new wall to an already existing building
	 * @param w - a wall instance with all its attributes
	 */
	public void addWalls(Wall w) {
		this.walls.add(w);		
	}

	public void removeWall(Wall wall) {
		this.walls.remove(wall);
	}

	public void removeAllWalls() {
		this.walls.clear();
	}
	/**
	 * @author Fabiola Badillo
	 * Date - Feb 29, 2020
	 * Objective - This method will add a new question to an already existing question pool
	 * @param q - a Question instance with all its attributes
	 */
	public void addQuestion(Question q) {
		this.questionPool.add(q);
	}

	public int getBuildingHeight() {
		return this.buildingHeight;
	}

	public void setBuildingHeight(int buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	/**
	 * @return the pictureURL
	 */
	public String getPictureURL() {
		return pictureURL;
	}

	/**
	 * @param pictureURL the pictureURL to set
	 * @throws IOException 
	 */
	public void setPictureURL(String pictureURL) throws IOException {
		this.pictureURL = pictureURL;
		this.setPicture(ImageIO.read(new File(pictureURL)));

	}

	/**
	 * @author Fabiola Badillo
	 * Objective - helper method for defining the perimeter
	 * Date - March 28, 2020
	 * @return - list containing all points for the building
	 */
	private List<Point> allPoints(){
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < this.walls.size(); i++) {
			points.add(this.walls.get(i).getP1());
			points.add(this.walls.get(i).getP2());
		}
		return points;
	}

	public Rectangle perimeter() {
		Rectangle r = new Rectangle();
		if(this.walls.size() == 0) {
			return r;
		}
		double smallX, bigX, smallY, bigY;
		ArrayList<Point> points = (ArrayList<Point>) allPoints();
		smallX = points.get(0).getX();
		bigX = points.get(0).getX();
		smallY = points.get(0).getY();
		bigY = points.get(0).getY();
		for (int i = 1; i < points.size(); i++) {
			if (points.get(i).getX() < smallX)
				smallX = points.get(i).getX();
			if (points.get(i).getX() > bigX)
				bigX = points.get(i).getX();
			if (points.get(i).getY() < smallY)
				smallY = points.get(i).getY();
			if (points.get(i).getY() > bigY)
				bigY = points.get(i).getY();
		}
		r.setBounds((int)smallX, (int)smallY, (int)(bigX-smallX), (int)(bigY-smallY));
		return r;
	}

	/**
	 * @author Michael J. Alvarado
	 * Objective - it returns a rectangle bigger than the building to know if Player is close to the building
	 * @date Apr 1, 2020
	 */
	public Rectangle bound() {
		Rectangle perimeter = perimeter();
		int widther = 10;
		int heigher = 10;
		return new Rectangle(perimeter.x-widther,perimeter.y-heigher,perimeter.width+widther*2, perimeter.height+heigher*2);
	}

	/**
	 * Description - This method has the building actions in Game
	 * Precondition - This method should only be use in Game
	 * @author - Michael J. Alvarado
	 * @date March 23, 2020
	 */
	public void tick(Player player) {
		//Place where the player must be to answer a Question
		playerCloseBy=false;
		for (Wall w : this.getWalls()) {
			Rectangle bound = bound();
			if(bound.intersects(player.getBound())) {
				this.playerCloseBy = true;
				if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
					Handler.getQuestionState().setBuilding(this);	//this add the building to the Question State so it knows its questions
					Handler.setCurrentState(Handler.getQuestionState()); //change the state to Question State
				}
			}
		}
		if(Handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
			debuggingMode = !debuggingMode;
		}
	}

	/**
	 * Description - This method paints the building of Game in Canvas
	 * Precondition - This method should only be use in Game
	 * @author - Michael J. Alvarado
	 * @date March 23, 2020
	 */
	public void render(Graphics g) {
		Rectangle bound = bound();
		Rectangle perimeter = perimeter();

		if(found) {
			g.drawImage(getPicture(),perimeter.x,perimeter.y, perimeter.width, perimeter.height,null);
		}
		else {
			//Once the player is close, make wall visible
			if(playerCloseBy) {
				animation.stars.render(g);
				Rectangle rec = this.perimeter();
				g.setColor(Color.BLACK);
				g.setFont(new Font("SansSerif", Font.PLAIN, 20));
				g.drawString("Press F to try to discover the building", rec.x+10, rec.y+(rec.height/2));
			}
			//Need a fix (what if i got huge building, and this only take into account from the center point distance)
			Point centerPoint = new Point((int)bound.getCenterX(), (int)bound.getCenterY());
			if(centerPoint.distance(new Point((int)Handler.getGameState().getPlayer().getBound().getCenterX(),(int)Handler.getGameState().getPlayer().getBound().getCenterY()))<180) {
				for(Wall w: getWalls()) {
					int blackness = 255-(int)(1.3*centerPoint.distance(new Point((int)Handler.getGameState().getPlayer().getBound().getCenterX(),(int)Handler.getGameState().getPlayer().getBound().getCenterY())));
					g.setColor(new Color(0, 0, 0, blackness));
					g.drawLine(w.getP1().x, w.getP1().y, w.getP2().x, w.getP2().y);
				}
			}
		}
		/*
		 * Testing Only
		 */
		if(debuggingMode) {
			g.setColor(Color.RED);
			g.drawRect(bound.x, bound.y, bound.width, bound.height);
		}
	}

	private class BuildingAnimation {
		public Animation stars;
		public BuildingAnimation() {
			stars = new Animation(Images.starEffect,0.6) {
				@Override 
				public void render(Graphics g) {
					Rectangle perimeter = perimeter();
					if(!stars.isAnimating()) {
						Random rand = new Random();
						stars.setBound(rand.nextInt(perimeter.width)+perimeter.x, rand.nextInt(perimeter.height)+perimeter.y, 20, 20);
					}
					stars.startAnimation();
					super.render(g);
				}
			};
		}
	}
}