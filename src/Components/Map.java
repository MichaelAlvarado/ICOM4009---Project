package Components;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.imageio.ImageIO;

import GameSetUp.Handler;
import testers.WriteFile;


/**
 * @author jorgecalderon
 * This class describes the Map instances with all its attributes and methods
 *Date - 02/29/2020
 */
public class Map {

	private LinkedList<Building> buildingList;
	private LinkedList<Tree> trees;
	private String mapName;
	private int height;
	private int width;
	private BufferedImage picture;
	private String imageURL;

	public Map(LinkedList<Building> buildingList, LinkedList<Tree> trees, String mapName, int height, int width) {
		super();
		this.buildingList = buildingList;
		this.trees = trees;
		this.mapName = mapName;
		this.height = height;
		this.width = width;
		this.picture = null;
		this.imageURL = "";
	}
	public Map() {
		buildingList = new LinkedList<Building>();
		trees = new LinkedList<Tree>();
	}


	public BufferedImage getPicture() {
		return picture;
	}


	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}


	public LinkedList<Building> getBuildingList() {
		return buildingList;
	}


	public void setBuildingList(LinkedList<Building> buildingList) {
		this.buildingList = buildingList;
	}

	public LinkedList<Tree> getTrees(){
		return trees;
	}

	public void setTrees(LinkedList<Tree> trees) {
		this.trees = trees;
	}

	public String getMapName() {
		return mapName;
	}


	public void setMapName(String buildingName) {
		this.mapName = buildingName;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}

	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) throws IOException {
		this.imageURL = imageURL;
		this.setPicture(ImageIO.read(new File(imageURL)));

	}

	public void addBuilding(Building b) {
		this.buildingList.add(b);
	}

	public void removeBuilding(Building b) {
		b.removeAllWalls();
		this.buildingList.remove(b);
	}

	public void addTree(Tree t) {
		this.trees.add(t);
	}

	public void removeTree(Tree t) {
		this.trees.remove(t);
	}
	/**
	 * 
	 * @author Michael J. Alvarado
	 * Objective - This method scale Buildings and map to fit on a dimension of width, height
	 * Precondition - Map should have a non 0 dimension
	 * PosCondition - The Trees and Buildings would be scaled and positioned in the correct position according to width and height given
	 * @date Mar 31, 2020
	 * @param width - The width of where you would like to fit the map components (in Game its given the Canvas Width)
	 * @param height - The height of where you would like to fit the map components (in Game its given the Canvas Height)
	 */
	public void scaleComponentTo(int width, int height) {
		double scaleX = (double)getWidth()/(double)width;
		double scaleY = (double)getHeight()/(double)height;
		for(Building building: getBuildingList()) {
			for(Wall wall: building.getWalls()) {
				wall.getP1().setLocation((int)((double)(wall.getP1().getX())/scaleX), (int)((double)(wall.getP1().getY())/scaleY));
				wall.getP2().setLocation((int)((double)(wall.getP2().getX())/scaleX), (int)((double)(wall.getP2().getY())/scaleY));
			}
		}
		for(Tree tree: getTrees()) {
			tree.getP1().setLocation((int)(tree.getP1().getX()/scaleX), (int)(tree.getP1().getY()/scaleY));
		}
		//		this.width = width;
		//		this.height = height;
	}


	public void tick(Player player) {
		for (Building building : buildingList) {
			building.tick(player);
		}
	}

	/**
	 * Description - This method paints all components in map of Game in Canvas
	 * Precondition - This method should only be use in Game
	 * @author - Joerge Calderon
	 * @date March 28, 2020
	 */
	public void render(Graphics g) {
		if(getPicture() != null)
			g.drawImage(getPicture(),0,0, Handler.getWidth(), Handler.getHeight(),null);
		for (Building building : buildingList) {
			building.render(g);
		}
		for(Tree tree: getTrees()) {
			tree.render(g);
		}
		//		scaleComponentTo(Handler.getWidth(), Handler.getHeight()); //To make screen responsive when resizing screen
	}


}
