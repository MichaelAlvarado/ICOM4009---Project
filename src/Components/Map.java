package Components;

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


	public static BufferedImage getPicture() {
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
	public void setImageURL(String imageURL) {
		Map.imageURL = imageURL;
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
	public static void generateTextFile(LinkedList<Building> buildingList) {

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
					+ "Building Image: " + buildings.getPicture() + "\n"
					+ buildings.getWallInfo() //this method add printLine already
					+ buildings.getQuestions() 
					+ "Found: " + buildings.getFound() + "\n";
			counter++;
		//Iterate through all the trees in the map
		for(Trees trees: trees) {
			file += trees.getTreeInfo();
		}

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

		//load Map info (name, size image)
		this.setMapName(sc.nextLine().substring(5)); 
		String size = sc.nextLine();
		this.setWidth(Integer.valueOf(size.substring(size.indexOf('(')+1, size.indexOf(','))));
		this.setHeight(Integer.valueOf(size.substring(size.indexOf(',')+2, size.indexOf(')'))));
		String imageURL = sc.nextLine();
		this.setImageURL(imageURL.substring(7));
		System.out.println(this.getImageURL());
		try {
			this.setPicture(ImageIO.read(new File(this.getImageURL())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Building building; 

		//Load Buildings
		while (sc.hasNextLine()) {
			//create a building
			if(sc.nextLine().contains("Building #")) {
				//name of Building
				String name = sc.nextLine();
				name = name.substring(name.indexOf("Name:")+6); //+6 to exclude "Name: "
				building = new Building(name);
				//System.out.println(building.getName());
				//Building Image (Not Done)
				String image = sc.nextLine(); //Ignores for now
				
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

//					System.out.println(name);
//					System.out.println(height);
//					System.out.println(pointX1);
//					System.out.println(pointY1);
//					System.out.println(pointX2);
//					System.out.println(pointY2);
					
					Wall wall = new Wall(name, Double.valueOf(height), 
							new Point(Integer.valueOf(pointX1), Integer.valueOf(pointY1)),
							new Point(Integer.valueOf(pointX2), Integer.valueOf(pointY2)));
					building.addWalls(wall);
					wallLine=sc.nextLine();
					
				}
				//Load Questions (Not Done)
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
				while(foundLine.contains("Found:")) {
					name = foundLine.substring(foundLine.indexOf("Found:") + 7);
					boolean found = Boolean.valueOf(name);
					building.setFound(found);
					System.out.println(name);
					foundLine = sc.nextLine();
				}
				//Add Building
				this.addBuilding(building);
				//System.out.println("Load Done");
			}
			treeLine = foundLine;
			if(treeLine.contains("Trees:")) {
				//TO-DO once Trees are fully functional
			}
			else {
				treeLine = sc.nextLine();
			}
		} 
		/*
		 * Falta que cargue las imagenes
		 */

	}

}




