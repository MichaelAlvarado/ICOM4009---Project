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

		//Write the map information first, no iteration needed for this.
		String file = "Map: " + this.getMapName() + "\n"
				+ "Size: (" + this.getWidth() + ", " + this.getHeight() + ") " + "\n"
				+ "Image: " + this.getImageURL() + "\n";

		//Iterate through list of buildings to store info on file
		for(Building b: buildingList){
			file += "\nBuilding Name: " + b.getName() + "\n" 
					+ "Building Image: " + b.getPictureURL() + "\n"
					+ b.getWallInfo(); //this method add printLine already
		}
		file += "\nTrees:";
		//Iterate through all the trees in the map
		for(Tree trees: trees) {
			file += trees.getTreeInfo();
		}

		file += "\n\nQuestionsFile: " + mapName + "TriviaFile.txt";
		//Write String file to the Configuration File
		try {
			data.writeToFile(file);
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}	
	}

	public void generateQuestionFile() {
		WriteFile data = new WriteFile(mapName + "TriviaFile.txt", false);
		String file = "";
		for (Building b: buildingList) {
			file += "Building: " + b.getName() + "\n"
					+ b.getQuestions();
		}

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
		String filepath = "";
		String wallLine = "";
		String questionLine;
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
			while(breakLine.contains("Building Name")) {
				//name of Building
				String name = breakLine.substring(breakLine.indexOf("Name:")+6); //+6 to exclude "Name: "
				//System.out.println(name);
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
					String pointX1 = wallLine.substring(wallLine.indexOf("First Point:")+14, index);
					//PY1
					String pointY1 = wallLine.substring(index+2, index = wallLine.indexOf(')',index+1));
					//PX2
					index = wallLine.indexOf(',',index+1);
					index = wallLine.indexOf(',',index+1);
					String pointX2 = wallLine.substring(wallLine.indexOf("Second Point:")+15, index);
					//PY2
					String pointY2 = wallLine.substring(index+2, wallLine.indexOf(')', index));
					//Image
					wallLine=sc.nextLine();
					String wImage = wallLine.substring(wallLine.indexOf("Wall Image:" ) + 12);

					//										System.out.println(name);
					//									System.out.println(height);
					//										System.out.println(pointX1);
					//										System.out.println(pointY1);
					//										System.out.println(pointX2);
					//										System.out.println(pointY2);
					//										System.out.println(wImage);

					Wall wall = new Wall(name, 
							new Point(Integer.valueOf(pointX1), Integer.valueOf(pointY1)),
							new Point(Integer.valueOf(pointX2), Integer.valueOf(pointY2)));
					wall.setHeight(Integer.valueOf(height));
					wall.setTextureURL(wImage);
					building.addWalls(wall);
					wallLine=sc.nextLine();
					if (wallLine.isEmpty())
						breakLine = sc.nextLine();

				}

				//Add Building
				this.addBuilding(building);
				//System.out.println("Load Done");
			}
			////// Load Trees
			treeLine = breakLine;
			//System.out.println(treeLine);
			if(treeLine.contains("Trees:")) {
				if (sc.hasNext())
					treeLine = sc.nextLine();
				int index = 0;
				while(treeLine.contains("ID:")) {
					Tree t = new Tree();
					index = treeLine.indexOf(',');

					//ID of Tree
					String treeID = treeLine.substring(treeLine.indexOf("ID:") + 4, index);
					//System.out.println(treeID);

					index = treeLine.indexOf(',', index + 1);
					//Species of Tree
					String treeSpecies = treeLine.substring(treeLine.indexOf("Species:") + 9, index);
					//System.out.println(treeSpecies);

					index = treeLine.indexOf(',', index + 1);
					String p1X = treeLine.substring(treeLine.indexOf("Position:") + 11, index);
					String p1Y = treeLine.substring(index + 2, index = treeLine.indexOf(')', index + 1));
					t.setID(treeID);
					t.setTreeSpecies(Integer.valueOf(treeSpecies));
					t.setP1(new Point(Integer.valueOf(p1X), Integer.valueOf(p1Y))); 
					//System.out.println(p1X + ", " + p1Y);

					//treeLine = sc.nextLine();
					if(!(t.getID() == null)) {
						trees.add(t);
					}
					treeLine = sc.nextLine();
				}
			}
			questionLine = sc.nextLine();
			String q = questionLine.substring(questionLine.indexOf("QuestionsFile: ") + 15);
			//System.out.println(q);
			String OS = System.getProperty("os.name").toLowerCase();
			if(OS.indexOf("mac") >= 0) {
				filepath = file.getParentFile().toString() + "/" + mapName + "TriviaFile.txt";
				// System.out.println("aqui");
			}
			else {
				filepath = file.getParentFile().toString() + "\\" + mapName + "TriviaFile.txt";
				//System.out.println("aqui2");
			}
			//System.out.println(filepath);
			Scanner sc2 = new Scanner (new File(filepath));
			//System.out.println(sc2.nextLine());
			String bname = sc2.nextLine();
			String qname = "";
			String rAns = "";
			String w1Ans = "";
			String w2Ans = "";
			String w3Ans = "";
			String nextLine = "";
			while(sc2.hasNextLine()) {
				//System.out.println("llegamos");
				if(bname.contains("Building:")) {
					//System.out.println("building");

					bname = bname.substring(bname.indexOf("Building:") + 10);
					System.out.println("Building: " + bname);
				}
				nextLine = sc2.nextLine();

				while (nextLine.contains("Question:")) {
					qname = nextLine;
					qname = qname.substring(qname.indexOf("Question:") + 10);
					System.out.println("Question: " + qname);
					rAns = sc2.nextLine();


					rAns = rAns.substring(rAns.indexOf("Right:") + 7);
					System.out.println("Answer Correct: " + rAns);
					w1Ans = sc2.nextLine();


					w1Ans = w1Ans.substring(w1Ans.indexOf("Wrong:") + 7);
					System.out.println("Answer Incorrect: " + w1Ans);
					w2Ans = sc2.nextLine();


					w2Ans = w2Ans.substring(w2Ans.indexOf("Wrong:") + 7);
					System.out.println("Answer Incorrect: " + w2Ans);
					w3Ans = sc2.nextLine();


					w3Ans = w3Ans.substring(w3Ans.indexOf("Wrong:") + 7);
					System.out.println("Answer Incorrect: " + w3Ans);

					Question question = new Question(qname, rAns, w1Ans, w2Ans, w3Ans);

					for (Building b: buildingList){
						if (b.getName().equals(bname)) {
							//System.out.println("for loop");
							b.addQuestion(question);;
						}
					}
					nextLine = sc2.nextLine();
				}
				bname = nextLine;

			}
		}

	}

}
