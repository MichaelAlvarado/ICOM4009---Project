package Components;

import java.util.LinkedList;

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
	
}
