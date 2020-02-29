package testers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

	/**
	 * @author jorgecalderon
	 * Objective 
	 * Preconditions
	 * Postconditions 
	 * Date - 02/29/2020
	 * @param 
	 * @param 
	 * @return  
	 */
	
	public class WriteFile{
		
		private String path;
		private boolean appendToFile = false;
		
		//Constructor for overwriting 
		public WriteFile(String fileToPath) {
			path = fileToPath;
		}
		
		//Constructor for appending at the end
		public WriteFile(String fileToPath, boolean appendValue) {
			path = fileToPath;
			appendToFile = appendValue;
		}
		
		public void writeToFile(String text) throws IOException{ 
			FileWriter write = new FileWriter(path, appendToFile);
			PrintWriter printLine = new PrintWriter(write);
			
			printLine.printf("%s" + "%n", text);
			printLine.close();
		}
		
}