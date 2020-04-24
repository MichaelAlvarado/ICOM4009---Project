package testers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import Components.Building;
import Components.Question;
import Resources.FileManager;
import States.QuestionState;



public class writeToFileTest {

	public static  void main(String[] args) {
		/*
		 * Open File with predetermine app
		 */
			FileManager.generateVRLM(new File("TutorialMapConfigurationFile.txt"));
			FileManager.openFile(new File("MapVRML.wrl"));
	}
	//     
	//		 WriteFile data = new WriteFile("ConfigurationFile.txt", true);
	//		
	//		try {
	//			data.writeToFile("Hello World");
	//		} catch (IOException e) {
	//			System.out.println("Something went wrong!");
	//		}



	////////// To test randomizer
	//	LinkedList<Question> qList = new LinkedList<Question>();
	//
	//	Question q1 = new Question("A","1","2","3", "4");
	//	Question q2 = new Question("B","1","2","3", "4");
	//	Question q3 = new Question("C","1","2","3", "4");
	//	Question q4 = new Question("D","1","2","3", "4");
	//	Question q5 = new Question("E","1","2","3", "4");
	//	Question q6 = new Question("F","1","2","3", "4");
	//
	//	qList.add(q1);
	//	qList.add(q2);
	//	qList.add(q3);
	//	qList.add(q4);
	//	qList.add(q5);
	//	qList.add(q6);

	//	for(Question q: qList) {
	//		System.out.println(q.getQuestion());
	//		System.out.println(q.getAnswers());
	//	}
	//	System.out.println();
	//	QuestionState.questionRandomizer(qList);

	//	for(Question q: qList) {
	//		System.out.println(q.getQuestion());
	//		System.out.println();
	//		System.out.println(q.getAnswers());
	//	}

	//	public void generateTextFile(LinkedList<Building> buildingList) {
	//
	//		WriteFile data = new WriteFile("ConfigurationFile.txt", true);
	//		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	//	
	//
	//		for(Building buildings: buildingList){
	//			int counter = 1;
	//			hmap.put(counter, "Name:" + buildings.getName() + ", Wall Height:" + 
	//					buildings.getWalls().get(counter).getHeight() 
	//					+ ", Image:" + buildings.getPicture().toString() + ", Questions:" + 
	//					buildings.getQuestionPool()
	//					+ ", Found:" + buildings.getFound());
	//			counter++;
	//
	//		}
	//
	//		for(Entry<Integer, String> entry : hmap.entrySet()) {
	//
	//			try {
	//				data.writeToFile("Building #" + entry.getKey() + ": " + entry.getValue());
	//			} catch (IOException e) {
	//				System.out.println("Something went wrong!");
	//			}	
	//
	//		}
	//
	//}

}
