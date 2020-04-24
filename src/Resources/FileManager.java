package Resources;

import java.awt.Desktop;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import Components.Building;
import Components.Map;
import Components.Question;
import Components.Tree;
import Components.Wall;
import testers.WriteFile;

public class FileManager {
	/**
	 * @author jorgecalderon
	 * Objective - Generate the text file needed for easier configuration in VRML
	 * Preconditions - map method will only be called after the user designing the map is done
	 * Postconditions - It will generate a text file containing information regarding the buildings and
	 * all it's components
	 * Date - 02/29/2020
	 * @param - N/A
	 * @param - N/A
	 * @return - N/A 
	 */
	public static void generateTextFile(Map map) {
		WriteFile data = new WriteFile(map.getMapName() +"ConfigurationFile.txt", false);
		//Write the map information first, no iteration needed for map.
		String file = "Map: " + map.getMapName() + "\n"
				+ "Size: (" + map.getWidth() + ", " + map.getHeight() + ") " + "\n"
				+ "Image: " + map.getImageURL() + "\n";

		//Iterate through list of buildings to store info on file
		for(Building b: map.getBuildingList()){
			file += "\nBuilding Name: " + b.getName() + "\n" 
					+ "Building Image: " + b.getPictureURL() + 
					", Height: " + b.getBuildingHeight() + "\n"
					+ b.getWallInfo(); //map method add printLine already
		}
		file += "\nTrees:";
		//Iterate through all the trees in the map
		for(Tree trees: map.getTrees()) {
			file += trees.getTreeInfo();
		}

		file += "\n\nQuestionsFile: " + map.getMapName() + "TriviaFile.txt";
		//Write String file to the Configuration File
		try {
			data.writeToFile(file);
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}	
	}

	/**
	 * 
	 * Description - Generate the questions text file
	 * @author - jorgecalderon
	 * @date Apr 9, 2020
	 */
	public static void generateQuestionFile(Map map) {
		WriteFile data = new WriteFile(map.getMapName() + "TriviaFile.txt", false);
		String file = "";
		for (Building b: map.getBuildingList()) {
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
	 * Post - It sets map map to the info in the file
	 * Date - 10/March/2020
	 * @param file - txt file with the map info
	 * @throws FileNotFoundException
	 */
	public static Map generateMap(File file) throws FileNotFoundException {
		Map map = new Map();
		Scanner sc = new Scanner(file);
		String filepath = "";
		String wallLine = "";
		String questionLine;
		String treeLine;
		String breakLine = "";


		//load Map info (name, size image)
		map.setMapName(sc.nextLine().substring(5)); 
		String size = sc.nextLine();
		map.setWidth(Integer.valueOf(size.substring(size.indexOf('(')+1, size.indexOf(','))));
		map.setHeight(Integer.valueOf(size.substring(size.indexOf(',')+2, size.indexOf(')'))));
		String imageURL = sc.nextLine();
		try {
			map.setImageURL(imageURL.substring(7));
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
				int index = 0;
				//name of Building
				String name = breakLine.substring(breakLine.indexOf("Name:")+6); //+6 to exclude "Name: "
				//System.out.println(name);
				building = new Building(name);
				//Building Image 
				String bLine = sc.nextLine(); 
				String bImage= "";
				index = bLine.indexOf(',');
				String bHeight = "";
				bImage = bLine.substring(bLine.indexOf("Building Image: ") + 16, index);
				bHeight = bLine.substring(index + 10);
				//System.out.println(bHeight);
				try {
					building.setPictureURL(bImage);
					building.setBuildingHeight(Integer.valueOf(bHeight));
				} catch (IOException e) {
					e.printStackTrace();
				}

				//Load Walls
				wallLine = sc.nextLine();
				while(wallLine.contains("ID")) {
					//name
					index = wallLine.indexOf(',');
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
				map.addBuilding(building);
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
						map.getTrees().add(t);
					}
					treeLine = sc.nextLine();
				}
			}
			questionLine = sc.nextLine();
			String q = questionLine.substring(questionLine.indexOf("QuestionsFile: ") + 15);
			//System.out.println(q);
			if(file.getParentFile() != null) {
				filepath = file.getParentFile().toString() + "/";
			}
			filepath = FileManager.OSCompability(filepath + map.getMapName() + "TriviaFile.txt");
			// System.out.println("aqui");

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
					//System.out.println("Building: " + bname);
				}
				nextLine = sc2.nextLine();

				while (nextLine.contains("Question:")) {
					qname = nextLine;
					qname = qname.substring(qname.indexOf("Question:") + 10);
					//System.out.println("Question: " + qname);
					rAns = sc2.nextLine();


					rAns = rAns.substring(rAns.indexOf("Right:") + 7);
					//System.out.println("Answer Correct: " + rAns);
					w1Ans = sc2.nextLine();


					w1Ans = w1Ans.substring(w1Ans.indexOf("Wrong:") + 7);
					//System.out.println("Answer Incorrect: " + w1Ans);
					w2Ans = sc2.nextLine();


					w2Ans = w2Ans.substring(w2Ans.indexOf("Wrong:") + 7);
					//System.out.println("Answer Incorrect: " + w2Ans);
					w3Ans = sc2.nextLine();


					w3Ans = w3Ans.substring(w3Ans.indexOf("Wrong:") + 7);
					//System.out.println("Answer Incorrect: " + w3Ans);

					Question question = new Question(qname, rAns, w1Ans, w2Ans, w3Ans);

					for (Building b: map.getBuildingList()){
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
		return map;
	}

	/**
	 * Description - This method opens a file in its corresponding File type.
	 * Precondition - To open certain file the user must have app to open those files (for .wrl must have a VRML viewer app)
	 * @author - Michael J. Alvarado
	 * @date Apr 14, 2020
	 * @param file - file to be opened
	 */
	public static void openFile(File file) {
		try {	       
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description -This method generate a .wrl file of the 3D map
	 * @author - Michael J. Alvarado
	 * @date Apr 14, 2020
	 * @param file - ConfigurationFile of the map to convert to 3D 
	 */
	public static void generateVRLM(File file) {
		try {
			Map map = FileManager.generateMap(file);
			generateVRML(map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description - This method generate a .wrl file of the map (VRML)
	 * @author - Michael J. Alvarado
	 * @date Apr 17, 2020
	 * @param map - map to be converted
	 */
	public static void generateVRML(Map map) {
		WriteFile wrl = new WriteFile("MapVRML.wrl", false);
		String data = "#VRML V2.0 utf8\n\n"; //VRML version
		data += VRMLFloor(map);
		for(Building building: map.getBuildingList()) {
			data += VRMLBuilding(building);
			for(Wall wall: building.getWalls()) {
				data += VRMLWall(wall);
			}
		}
		try {
			wrl.writeToFile(data);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Description - Make a VRML code of the building with the 2D Image in it (Testing Uses)
	 * @author - Michael J. Alvarado
	 * @date Apr 17, 2020
	 * @param build - building to be converted to VRML code
	 * @return A string with the VRML code of the building
	 */
	private static String VRMLTestBuilding(Building build) {
		String data = "DEF "+build.getName()+" Transform {\n"
				+ "\ttranslation "+(build.perimeter().x+(build.perimeter().width/2))+ " " +(build.getBuildingHeight()/2)+" " +(build.perimeter().y+(build.perimeter().height/2))+"\n"
				+ "\tscale "+build.perimeter().width+" "+build.getBuildingHeight()+" "+build.perimeter().height+"\n"
				+ "\tchildren [\n"
				+ "\t\tDEF Box Shape {\n"
				+ "appearance Appearance {\n"
				+ "material DEF White Material {\n"
				+ "ambientIntensity 0.200\n"
				+ "shininess 0.200\n"
				+ "diffuseColor 1 1 1\n" 
				+ "}\n"
				+ "texture ImageTexture {\n"
				+ "url "+'"'+build.getPictureURL()+'"'+"\n"
				+ "}\n"
				+ "}\n"
				+ "geometry DEF geoBox1 Box {\n"
				+ "size 1 1 1\n"
				+ "}\n}\n]\n}"
				+ "\n";
		return data;
	}

	private static String VRMLBuilding(Building building) {
		String data = VRMLBox(building.getName()+"Roof", (building.perimeter().x+(building.perimeter().width/2)), (building.perimeter().y+(building.perimeter().height/2)), building.getWalls().get(0).getHeight(),
				building.perimeter().width, 1, building.perimeter().height, " ");	
		for(Wall wall: building.getWalls()) {
			data += VRMLWall(wall);
		}
		return data;
	}
	
	private static String VRMLBox(String name, int x, int y, int z, int width, int heigth, int depth, String textureUrl) {
		String data = "DEF "+name+" Transform {\n"
				+ "\ttranslation "+x+ " " + z +" " + y +"\n"
				+ "\tscale "+ width +" "+ heigth +" "+ depth +"\n"
				+ "\tchildren [\n"
				+ "\t\tDEF Box Shape {\n"
				+ "\t\t\tappearance Appearance {\n"
				+ "\t\t\t\tmaterial DEF White Material {\n"
				+ "\t\t\t\t\tambientIntensity 0.200\n"
				+ "\t\t\t\t\tshininess 0.200\n"
				+ "\t\t\t\t\tdiffuseColor 1 1 1\n" 
				+ "\t\t\t\t}\n"
				+ "\t\t\ttexture ImageTexture {\n"
				+ "\t\t\t\t\turl "+'"'+textureUrl+'"'+"\n"
				+ "\t\t\t\t}\n"
				+ "\t\t\t}\n"
				+ "\t\t\tgeometry DEF geoBox1 Box {\n"
				+ "\t\t\t\tsize 1 1 1\n"
				+ "\t\t\t}\n"
				+ "\t\t}\n"
				+ "\t]\n"
				+ "}"
				+ "\n\n";
		return data;
	}

	/**
	 * Description - Make a VRML code of a wall
	 * @author - Michael J. Alvarado
	 * @date Apr 17, 2020
	 * @param wall - wall to be converted to VRML code
	 * @return A string with the VRML code of the wall
	 */
	private static String VRMLWall(Wall wall) {
		double x = (wall.getP1().getX() + wall.getP2().getX())/2; //center X position of wall
		double y = (wall.getP1().getY() + wall.getP2().getY())/2; //center Y position of wall
		double width = wall.getP1().distance(wall.getP2()); //width of the wall
		/*
		 * Rotation calculation using Cosine Law in a isosceles
		 */
		double a = width/2;
		double c = wall.getP2().y > wall.getP1().y? wall.getP1().distance(x+a, y): wall.getP2().distance(x+a, y);
		double cosine = 1-((c*c)/(2.00*a*a)); 
		double rotation = Math.acos(cosine);
		/*
		 * Conver the data of Wall to VRML
		 */
		String data = VRMLBox(wall.getID(), x, y, wall.getHeight()/2, width, wall.getHeight(), 1.00, 0, 0, 1, rotation, wall.getTextureURL());
		return data;
	}
	
	
	private static String VRMLBox(String name, double x, double y, double z, double width, double heigth, double depth, int rotateX, int rotateY, int rotateZ, double rotationAngle, String textureUrl) {
		String data = "DEF "+name+" Transform {\n"
				+ "\ttranslation "+ x + " " + z +" " + y +"\n"
				+ "\tscale "+ width +" "+ heigth +" "+ depth +"\n"
				+ "\trotation " + rotateX +" "+ rotateZ +" "+ rotateY +" "+ rotationAngle +"\n"
				+ "\tchildren [\n"
				+ "\t\tDEF Box Shape {\n"
				+ "\t\t\tappearance Appearance {\n"
				+ "\t\t\t\tmaterial DEF White Material {\n"
				+ "\t\t\t\t\tambientIntensity 0.200\n"
				+ "\t\t\t\t\tshininess 0.200\n"
				+ "\t\t\t\t\tdiffuseColor 1 1 1\n" 
				+ "\t\t\t\t}\n"
				+ "\t\t\ttexture ImageTexture {\n"
				+ "\t\t\t\t\turl "+'"'+textureUrl+'"'+"\n"
				+ "\t\t\t\t}\n"
				+ "\t\t\t}\n"
				+ "\t\t\tgeometry DEF geoBox1 Box {\n"
				+ "\t\t\t\tsize 1 1 1\n"
				+ "\t\t\t}\n"
				+ "\t\t}\n"
				+ "\t]\n"
				+ "}"
				+ "\n\n";
		return data;
	}

	/**
	 * Description - Make the floors the the map to place the components on top
	 * @author - Michael J. Alvarado
	 * @date Apr 17, 2020
	 * @param map - the map so it can make the floor with
	 * @return A string with the VRML code a the floor with map dimension and image
	 */
	private static String VRMLFloor(Map map) {
		String data = "DEF Floor Transform {\n"
				+ "\ttranslation " +map.getWidth()/2+" "+0+" "+map.getHeight()/2+"\n"
				+ "\tscale "+map.getWidth()+" "+1+" "+map.getHeight()+"\n"
				+ "\tchildren [\n"
				+ "\t\tDEF Box Shape {\n"
				+ "\t\t\tappearance Appearance {\n"
				+ "\t\t\t\tmaterial DEF White Material {\n"
				+ "\t\t\t\t\tambientIntensity 0.200\n"
				+ "\t\t\t\t\tshininess 0.200\n"
				+ "\t\t\t\t\tdiffuseColor 1 1 1\n" 
				+ "\t\t\t\t}\n"
				+ "\t\t\ttexture ImageTexture {\n"
				+ "\t\t\t\t\turl "+'"'+map.getImageURL()+'"'+"\n"
				+ "\t\t\t\t}\n"
				+ "\t\t\t}\n"
				+ "\t\t\tgeometry DEF geoBox1 Box {\n"
				+ "\t\t\t\tsize 1 1 1\n"
				+ "\t\t\t}\n"
				+ "\t\t}\n"
				+ "\t]\n"
				+ "}"
				+ "\n\n";
		return data;
	}

	/**
	 * 
	 * Description - This method returns the URL path with Operating System separator
	 * @author - Michael J. Alvarado
	 * @date Apr 9, 2020
	 */
	public static String OSCompability(String URL) {
		String OS = System.getProperty("os.name").toLowerCase();
		char separator = '/';
		if(OS.equals("win")) 
			separator = '\\';
		return URL.replace(separator, File.separatorChar);

	}
}
