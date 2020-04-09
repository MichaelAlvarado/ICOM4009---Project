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

import main.Handler;
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
		b.removeWalls();
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
				wall.getP1().setLocation((int)(wall.getP1().getX()/scaleX), (int)(wall.getP1().getY()/scaleY));
				wall.getP2().setLocation((int)(wall.getP2().getX()/scaleX), (int)(wall.getP2().getY()/scaleY));
			}
		}
		for(Tree tree: getTrees()) {
			tree.getP1().setLocation((int)(tree.getP1().getX()/scaleX), (int)(tree.getP1().getY()/scaleY));
		}
	}
	
	//Para cada building creado en un mapa tendra su tick y render
	public void tick(Player player, Handler handler) {
		for (Building building : buildingList) {
			building.tick(player, handler);
		}
	}
	
	public void render(Graphics g, Handler handler) {
		if(getPicture() != null)
			g.drawImage(getPicture(),0,0, handler.getWidth(), handler.getHeight(),null);
		for (Building building : buildingList) {
			building.render(g, handler);
		}
		for(Tree tree: getTrees()) {
			tree.render(g, handler);
		}
	}

	/**
	 * @author jorgecalderon
	 * Objective - Generate the text file needed for easier configuration in VRML
	 * Preconditions - This method will only be called after the user designing the map is done
	 * Postconditions - It will generate a text file containing information regarding the buildings and
	 * all it's components
	 * Date - 02/29/2020
	 * @param - N/A
	 * @param - N/A
	 * @return - N/A 
	 */
	public void generateTextFile() {

		WriteFile data = new WriteFile(mapName +"ConfigurationFile.txt", false);
		int counter = 1;

		//Write the map information first, no iteration needed for this.
		String file = "Map: " + this.getMapName() + "\n"
				+ "Size: (" + this.getWidth() + ", " + this.getHeight() + ") " + "\n"
				+ "Image: " + this.getImageURL() + "\n";

		//Iterate through list of buildings to store info on file
		for(Building buildings: buildingList){
			file += "\nBuilding #" + counter + ":\n" 
					+ "Name: " + buildings.getName() + "\n" 
					+ "Building Image: " + buildings.getPictureURL() + "\n"
					+ buildings.getWallInfo() //this method add printLine already
					+ buildings.getQuestions() 
					+ "Found: " + buildings.getFound() + "\n";
			counter++;
		}
		file += "\nTrees:";
		//Iterate through all the trees in the map
		for(Tree trees: trees) {
			file += trees.getTreeInfo();
		}
		//Write String file to the Configuration File
		try {
			data.writeToFile(file);
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}	
	}

	/**
	 * @author Michael J. Alvarado
	 * Objective - load map
	 * Precondition - a txt file generated from generateTextFile must be giving
	 * Post - It sets this map to the info in the file
	 * Date - 10/March/2020
	 * @param file - txt file with the map info
	 * @throws FileNotFoundException
	 */
	public void generateMap(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String wallLine;
		String questionLine;
		String foundLine = "";
		String treeLine;
		String breakLine = "";

		//load Map info (name, size image)
		this.setMapName(sc.nextLine().substring(5)); 
		String size = sc.nextLine();
		this.setWidth(Integer.valueOf(size.substring(size.indexOf('(')+1, size.indexOf(','))));
		this.setHeight(Integer.valueOf(size.substring(size.indexOf(',')+2, size.indexOf(')'))));
		String imageURL = sc.nextLine();
		try {
			this.setImageURL(imageURL.substring(7));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Building building; 
		breakLine = sc.nextLine();
		breakLine = sc.nextLine();

		//Load Buildings
		while (sc.hasNextLine()) {
			//create a building
			if(breakLine.contains("Building #")) {
				//name of Building
				String name = sc.nextLine();
				name = name.substring(name.indexOf("Name:")+6); //+6 to exclude "Name: "
				building = new Building(name);
				//Building Image 
				String bImage = sc.nextLine(); 
				bImage = bImage.substring(bImage.indexOf("Building Image: ") + 16);
				try {
					building.setPictureURL(bImage);
				} catch (IOException e) {
					e.printStackTrace();
				}

				//Load Walls
				wallLine = sc.nextLine();
				while(wallLine.contains("ID")) {
					//name
					int index = wallLine.indexOf(',');
					name = wallLine.substring(wallLine.indexOf("ID:")+4, index);
					//height
					index = wallLine.indexOf(',',index+1);
					String height = wallLine.substring(wallLine.indexOf("Height:")+8, index);
					//PX1
					index = wallLine.indexOf(',',index+1);
					String pointX1 = wallLine.substring(wallLine.indexOf("First Point:")+13, index);
					//PY1
					String pointY1 = wallLine.substring(index+2, index = wallLine.indexOf(',',index+1));
					//PX2
					index = wallLine.indexOf(',',index+1);
					String pointX2 = wallLine.substring(wallLine.indexOf("Second Point:")+14, index);
					//PY2
					String pointY2 = wallLine.substring(index+2, index = wallLine.indexOf(',',index+1));
					//Image
					String wImage = wallLine.substring(wallLine.indexOf("Wall Image:" ) + 12);

					//					System.out.println(name);
					//					System.out.println(height);
					//					System.out.println(pointX1);
					//					System.out.println(pointY1);
					//					System.out.println(pointX2);
					//					System.out.println(pointY2);
					//					System.out.println(wImage);

					Wall wall = new Wall(name, Double.valueOf(height), 
							new Point(Integer.valueOf(pointX1), Integer.valueOf(pointY1)),
							new Point(Integer.valueOf(pointX2), Integer.valueOf(pointY2)));
					wall.setTextureURL(wImage);
					building.addWalls(wall);
					wallLine=sc.nextLine();

				}
				//Load Questions 
				questionLine=wallLine;
				String answ1, answ2, answ3, answ4;
				while(questionLine.contains("Question:")){
					int index = questionLine.indexOf("Correct Answer:") - 2;
					//Get the question
					name = questionLine.substring(questionLine.indexOf("Question:")+10,index);
					index = questionLine.indexOf("Incorrect Answer1:")-2;
					//First Answer (Correct Answer)
					answ1 = questionLine.substring((questionLine.indexOf("Correct Answer:") + 16), index);
					index = questionLine.indexOf("Incorrect Answer2:") - 2;
					//Second Answer
					answ2 = questionLine.substring((questionLine.indexOf("Incorrect Answer1:") + 19), index);
					index = questionLine.indexOf("Incorrect Answer3:") - 2;
					//Third Answer
					answ3 = questionLine.substring((questionLine.indexOf("Incorrect Answer2:") + 19), index);
					//Fourth Answer
					answ4 = questionLine.substring(questionLine.indexOf("Incorrect Answer3: ") + 19);

//										System.out.println(name);
//										System.out.println(answ1);
//										System.out.println(answ2);
//										System.out.println(answ3);
//										System.out.println(answ4);

					Question question = new Question(name, answ1, answ2, answ3, answ4);
					building.addQuestion(question);
					questionLine=sc.nextLine();	
				}

				//Load Found 
				foundLine = questionLine;
				name = foundLine.substring(foundLine.indexOf("Found:") + 7);
				boolean found = Boolean.valueOf(name);
				building.setFound(found);
				//System.out.println(name);
				foundLine = sc.nextLine();
				foundLine = breakLine;
				foundLine = sc.nextLine();
				
				//Add Building
				this.addBuilding(building);
				//System.out.println("Load Done");
			}
			////// Load Trees
			treeLine = foundLine;
			if(treeLine.contains("Trees:")) {
				if (sc.hasNext())
						treeLine = sc.nextLine();
				int index = 0;
				while(sc.hasNextLine()) {
					Tree t = new Tree();
					index = treeLine.indexOf(',');
					if(treeLine.contains("ID:")) {
						//ID of Tree
						String treeID = treeLine.substring(treeLine.indexOf("ID:") + 4, index);
						//System.out.println(treeID);
					
						index = treeLine.indexOf(',', index + 1);
						//Species of Tree
						String treeSpecies = treeLine.substring(treeLine.indexOf("Species:") + 9);
						//System.out.println(treeSpecies);
//						index = treeLine.indexOf(',', index + 1);
//						//Height of Tree
//						String treeHeight = treeLine.substring(treeLine.indexOf("Height:") + 8);
//						System.out.println(treeHeight);
						t.setID(treeID);
						t.setTreeSpecies(Integer.valueOf(treeSpecies));
//						t.setTreeHeight(Integer.valueOf(treeHeight));
					}
					
					treeLine = sc.nextLine();
					
					if(treeLine.contains("Position:")) {
						index = treeLine.indexOf(',');
						//Coordinates of First Point
						String p1X = treeLine.substring(treeLine.indexOf("Position:") + 10, index);
						String p1Y = treeLine.substring(index + 2);
						//System.out.println(p1X + ", " + p1Y);
						t.setP1(new Point(Integer.valueOf(p1X), Integer.valueOf(p1Y))); 
					}
					
					
					//treeLine = sc.nextLine();
					if(!(t.getID() == null)) {
						trees.add(t);
					}
					
				}
			}
			
		}

	}

}




