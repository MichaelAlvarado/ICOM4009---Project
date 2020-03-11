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
	private LinkedList<Trees> trees;
	private static String mapName;
	private static int height;
	private static int width;
	private static BufferedImage picture;
	private static String imageURL;
	
	public Map(LinkedList<Building> buildingList, String mapName, int height, int width) {
		super();
		this.buildingList = buildingList;
		Map.mapName = mapName;
		Map.height = height;
		Map.width = width;
		Map.picture = null;
	}
	public Map() {
		buildingList = new LinkedList<Building>();
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

	public LinkedList<Building> getList(){
		return this.buildingList;
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

		//Iterate through list of buildings to store info on hashmap
		for(Building buildings: buildingList){
			file += "\nBuilding #" + counter + ":\n" 
					+ "Name: " + buildings.getName() + "\n" 
					+ "Building Image: " + buildings.getPicture() + "\n"
					+ buildings.getWallInfo() //this method add printLine already
					+ "Questions: " + buildings.getQuestions() + "\n"
					+ "Found: " + buildings.getFound() + "\n";
			counter++;

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
				System.out.println(building.getName());
				//Building Image (Not Done)
				String image = sc.nextLine(); //Ignores for now
				//Load Walls
				String wallLine;
				while((wallLine = sc.nextLine()).contains("ID")) {
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

					System.out.println(name);
					System.out.println(height);
					System.out.println(pointX1);
					System.out.println(pointY1);
					System.out.println(pointX2);
					System.out.println(pointY2);
					Wall wall = new Wall(name, Double.valueOf(height), 
							new Point(Integer.valueOf(pointX1), Integer.valueOf(pointY1)),
							new Point(Integer.valueOf(pointX2), Integer.valueOf(pointY2)));
					building.addWalls(wall);
				}
				//Load Questions (Not Done)
				
				//Load Found (Not Done)
				
				//Add Building
				this.addBuilding(building);
			}
		} 
		/*
		 * Falta que cargue las preguntas, las imagenes y el found de buildings
		 */

	}

}


//	public static  void main(String[] args) throws IOException {
//		BufferedImage image = new BufferedImage(5,5, BufferedImage.TYPE_INT_RGB);
//		File file = new File("myimage.png");
//        ImageIO.write(image, "png", file);
//		
//		LinkedList<Building> buildings = new LinkedList<Building>();
//		LinkedList<Question> qs = new LinkedList<Question>();
//		LinkedList<Question> qs2 = new LinkedList<Question>();
//		LinkedList<Wall> ws = new LinkedList<Wall>();
//		LinkedList<Wall> ws2 = new LinkedList<Wall>();
//		
//		Question q1 = new Question("Who are you?", "Me" , "You" , "We" , "Us");
//		Question q2 = new Question("Who are you?", "Me" , "You" , "We" , "Us");
//		Question q3 = new Question("How are you?", "Colgao", "Bien", "Mal", "Super");
//		Question q4 = new Question("How are you?", "Colgao", "Bien", "Mal", "Super");
//		qs.add(q1);
//		qs.add(q2);
//		qs2.add(q3);
//		qs2.add(q4);
//		
//		Wall w1 = new Wall("w1", new Point(619, 435), new Point(744, 450));
//		Wall w2 = new Wall("w2", new Point(545, 136), new Point(607, 128));
//		ws.add(w1);
//		ws.add(w2);
//		buildings.add(new Building("b1", image, qs, ws, true));
//		
//		Wall w3 = new Wall("w3", new Point(601, 92), new Point(435, 619));
//		Wall w4 = new Wall("w4", new Point(493, 89), new Point(450, 744));
//		ws2.add(w3);
//		ws2.add(w4);
//		buildings.add(new Building("b2", null, qs2, ws2, true));
//		
//		generateTextFile(buildings);
//	}




