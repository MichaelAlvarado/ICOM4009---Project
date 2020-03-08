package Components;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import testers.WriteFile;

public class Map {

	private LinkedList<Building> buildingList;
	private String mapName;
	private int height;
	private int width;
	private BufferedImage picture;

	//piso no se que va aqui


	public Map(LinkedList<Building> buildingList, String buildingName, int height, int width) {
		super();
		this.buildingList = buildingList;
		this.mapName = buildingName;
		this.height = height;
		this.width = width;
		this.picture = null;
	}
	public Map() {
		buildingList = new LinkedList<Building>();
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


	public String getBuildingName() {
		return mapName;
	}


	public void setBuildingName(String buildingName) {
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

		WriteFile data = new WriteFile("ConfigurationFile.txt", true);
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		int counter = 1;
		
		for(Building buildings: buildingList){
			hmap.put(counter, "Name: " + buildings.getName() + ", Building Image: null"
					+ ", Walls: " + 
					buildings.getWallInfo() 
					  + ", Questions: " + 
					buildings.getQuestions()
					+ " Found: " + buildings.getFound());
			counter++;

		}

		for(Entry<Integer, String> entry : hmap.entrySet()) {

			try {
				data.writeToFile("Building #" + entry.getKey() + ":\n" + entry.getValue());
			} catch (IOException e) {
				System.out.println("Something went wrong!");
			}	

		}

	}
	
	public static  void main(String[] args) throws IOException {
		BufferedImage image = new BufferedImage(5,5, BufferedImage.TYPE_INT_RGB);
		File file = new File("myimage.png");
        ImageIO.write(image, "png", file);
		
		LinkedList<Building> buildings = new LinkedList<Building>();
		LinkedList<Question> qs = new LinkedList<Question>();
		LinkedList<Question> qs2 = new LinkedList<Question>();
		LinkedList<Wall> ws = new LinkedList<Wall>();
		LinkedList<Wall> ws2 = new LinkedList<Wall>();
		
		Question q1 = new Question("Who are you?", "Me" , "You" , "We" , "Us");
		Question q2 = new Question("Who are you?", "Me" , "You" , "We" , "Us");
		Question q3 = new Question("How are you?", "Colgao", "Bien", "Mal", "Super");
		Question q4 = new Question("How are you?", "Colgao", "Bien", "Mal", "Super");
		qs.add(q1);
		qs.add(q2);
		qs2.add(q3);
		qs2.add(q4);
		
		Wall w1 = new Wall("w1", new Point(619, 435), new Point(744, 450));
		Wall w2 = new Wall("w2", new Point(545, 136), new Point(607, 128));
		ws.add(w1);
		ws.add(w2);
		buildings.add(new Building("b1", image, qs, ws, true));
		
		Wall w3 = new Wall("w3", new Point(601, 92), new Point(435, 619));
		Wall w4 = new Wall("w4", new Point(493, 89), new Point(450, 744));
		ws2.add(w3);
		ws2.add(w4);
		buildings.add(new Building("b2", null, qs2, ws2, true));
		
		generateTextFile(buildings);
	}

}



