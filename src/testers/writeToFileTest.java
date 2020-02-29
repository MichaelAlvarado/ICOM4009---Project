package testers;

import java.io.IOException;



public class writeToFileTest {

	public static  void main(String[] args) {
     
		 WriteFile data = new WriteFile("ConfigurationFile.txt", true);
		
		try {
			data.writeToFile("Hello World");
		} catch (IOException e) {
			System.out.println("Something went wrong!");
		}
		
	}

}
