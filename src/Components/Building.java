package Components;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Graphics;
import java.awt.Point;
import GUI.AddQuestionsBox;
import GUI.Display;
import GUI.Plane;
import States.QuestionState;
import main.Handler;
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
	private LinkedList<Wall> walls; // = new LinkedList<Wall>();
	private String buildingName; 
	private BufferedImage picture;
	private String pictureURL;
	private LinkedList<Question> questionPool;
	private int buildingHeight; //this is default height of walls
	private int width, height;
	//GameRelated
	private boolean playerCloseBy;
	private boolean found;

	// constructor
	public Building(String buildingName) {
		this.buildingName = buildingName;
		walls = new LinkedList<Wall>();
		questionPool = new LinkedList<Question>();
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
			result += "Question: " + q.getQuestion()
			+ ", Correct Answer: " + q.getAnswer_1()
			+", Incorrect Answer1: " + q.getAnswer_2()
			+", Incorrect Answer2: " + q.getAnswer_3() 
			+", Incorrect Answer3: " + q.getAnswer_4() 
			+ " \n";
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
			result += "ID: " + w.getID() + ", Height: " + w.getHeight() + ", First Point: " + w.getP1().x
					+ ", " + w.getP1().y + ", Second Point: " + w.getP2().x + ", " + w.getP2().y +
					", Wall Image: " + w.getTextureURL() + "\n";
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
		return buildingHeight;
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


	public void tick(Player player, Handler handler) {
		//Place where the player must be to answer a Question
		playerCloseBy=false;
		for (Wall w : this.getWalls()) {
			//un acercamiento aproximado a cualquier pared
			if(player.getPosition().x >= w.getP1().x && player.getPosition().x <= w.getP2().x 
					&& player.getPosition().y >= walls.getFirst().getP1().y - 100 && player.getPosition().y <= walls.getFirst().getP2().y + 100) {
				this.playerCloseBy = true;
				if(handler.getKeyListener().keyJustPressed(KeyEvent.VK_X)) {
					handler.getQuestionState().setBuilding(this);	//this add the building to the Question State so it knows its questions
					handler.setCurrentState(handler.getQuestionState()); //change the state to Question State
				}
			}
		}
	}

	public void render(Graphics g) {
		//Once the player is close, make wall visible
		for (Wall w : this.getWalls()) {
			if(playerCloseBy) {
				g.drawLine(w.getP1().x, w.getP1().y, w.getP2().x, w.getP2().y);
			}
		}
		//	if(correct_answers == 3) {
		//Una vez contestado correcto 3 veces
		//	g.drawImage(this.getPicture(),this.perimeter().x,this.perimeter().y, width, height,null);
		//	}
	}


}
