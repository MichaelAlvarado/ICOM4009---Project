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
	private static LinkedList<Trees> trees;
	private static String mapName;
	private static int height;
	private static int width;
	private static BufferedImage picture;
	private static String imageURL;

	public Map(LinkedList<Building> buildingList, LinkedList<Trees> trees, String mapName, int height, int width) {
		super();
		this.buildingList = buildingList;
		this.trees = trees;
		Map.mapName = mapName;
		Map.height = height;
		Map.width = width;
		Map.picture = null;
	}
	public Map() {
		buildingList = new LinkedList<Building>();
		trees = new LinkedList<Trees>();
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

	public LinkedList<Trees> getTrees(){
		return trees;
	}

	public void setTrees(LinkedList<Trees> trees) {
		this.trees = trees;
	}

	public static String getMapName() {
		return mapName;
	}


	public void setMapName(String buildingName) {
		Map.mapName = buildingName;
	}


	public static int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		Map.height = height;
	}


	public static int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		Map.width = width;
	}

	public static String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) throws IOException {
		Map.imageURL = imageURL;
		this.setPicture(ImageIO.read(new File(imageURL)));

	}

	public void addBuilding(Building b) {
		this.buildingList.add(b);
	}

	public void removeBuilding(Building b) {
		this.buildingList.remove(b);
	}

	public void addTree(Trees t) {
		this.trees.add(t);
	}

	public void removeTree(Trees t) {
		this.trees.remove(t);
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
		String file = "Map: " + Map.getMapName() + "\n"
				+ "Size: (" + Map.getWidth() + ", " + Map.getHeight() + ") " + "\n"
				+ "Image: " + Map.getImageURL() + "\n";

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
		for(Trees trees: trees) {
			file += trees.getTreeInfo();
		}
		//Write String file to the Configuration File
		try {
			data.writeToFile(file);
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}	
	}
	
	//Para cada building creado en un mapa tendra su tick y render
	public void tick(Handler handler, Player player) {
		for (Building building : buildingList) {
			building.tick(handler, player);
		}
	}
	
	public void render(Graphics g) {
		for (Building building : buildingList) {
			building.render(g);
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
					int index = questionLine.indexOf(',');
					//Get the question
					name = questionLine.substring(10,index);
					index = questionLine.indexOf(',', index + 1);
					//First Answer
					answ1 = questionLine.substring((questionLine.indexOf("Answers:") + 9), index);
					index = questionLine.indexOf(',', index + 1);
					//Second Answer
					answ2 = questionLine.substring((questionLine.indexOf("Answers:") + 12), index);
					index = questionLine.indexOf(',', index + 1);
					//Third Answer
					answ3 = questionLine.substring((questionLine.indexOf("Answers:") + 15), index);
					//Fourth Answer
					answ4 = questionLine.substring(questionLine.indexOf("Answers:") + 18);

					//					System.out.println(name);
					//					System.out.println(answ1);
					//					System.out.println(answ2);
					//					System.out.println(answ3);
					//					System.out.println(answ4);

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
					Trees t = new Trees();
					index = treeLine.indexOf(',');
					if(treeLine.contains("ID:")) {
						//ID of Tree
						String treeID = treeLine.substring(treeLine.indexOf("ID:") + 4, index);
						System.out.println(treeID);
					
						index = treeLine.indexOf(',', index + 1);
						//Species of Tree
						String treeSpecies = treeLine.substring(treeLine.indexOf("Species:") + 9);
						System.out.println(treeSpecies);
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
						System.out.println(p1X + ", " + p1Y);
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




