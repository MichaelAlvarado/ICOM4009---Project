package testers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class writeToFileTest {

	public static  void main(String[] args) {
		
		 WriteFile data = new WriteFile("ConfigurationFile.txt", true);
		
		 
//		 To be used when Building class and Wall Class are implemented 
//		 HashMap<Integer, String> hmap = new HashMap<Integer, String>();
//		 
//		 for each in Building List{
//			 int counter = 1;
//			 hmap.put(counter, "Name:" + building.getname + ", Wall Height:" + building.getwall.getheight 
//					 + ", Image:" + building.getimage + ", Questions:" + building.questionpool.getquestions
//					 + ", Found:" + building.isfound)
//			 counter++;
//			 
//		 }
//		
//		for(Map.Entry<Integer, String> entry : hmap.entrySet()) {
//			
//			try {
//				data.writeToFile("Building #" + entry.getKey() + ": " + entry.getValue());
//			} catch (IOException e) {
//				System.out.println("Something went wrong!");
//			}	
//			
//		}
		
		try {
			data.writeToFile("Hello World");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}
		
	}

}
