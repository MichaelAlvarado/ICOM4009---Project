package Components;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import testers.WriteFile;

public class Map {
	
	private LinkedList<Building> buildingList;
	private String buildingName;
	private int height;
	private int width;
	//piso no se que va aqui
	
	
	public Map(LinkedList<Building> buildingList, String buildingName, int height, int width) {
		super();
		this.buildingList = buildingList;
		this.buildingName = buildingName;
		this.height = height;
		this.width = width;
	}


	public LinkedList<Building> getBuildingList() {
		return buildingList;
	}


	public void setBuildingList(LinkedList<Building> buildingList) {
		this.buildingList = buildingList;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
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
	
	 WriteFile data = new WriteFile("ConfigurationFile.txt", true);
	 HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	 
	 for(Building buildings: this.buildingList){
		 int counter = 1;
		 hmap.put(counter, "Name:" + buildings.getName() + ", Wall Height:" + 
				 buildings.getWalls().get(counter).getHeight() 
				 + ", Image:" + buildings.getPicture().toString() + ", Questions:" + 
				 buildings.getQuestionPool()
				 + ", Found:" + buildings.getFound());
		 counter++;
		 
	   }
	 
		for(Entry<Integer, String> entry : hmap.entrySet()) {
			
			try {
				data.writeToFile("Building #" + entry.getKey() + ": " + entry.getValue());
			} catch (IOException e) {
				System.out.println("Something went wrong!");
			}	
			
		}
		 
	}
	 
}

	

