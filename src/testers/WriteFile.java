package testers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

	/**
	 * @author jorgecalderon
	 * Objective - Class to allow writing on text file
	 * Preconditions - N/A
	 * Postconditions - Permits to write on text file after creating a WriteFile object
	 * Date - 02/29/2020
	 * @param - N/A
	 * @param - N/A
	 * @return - N/A 
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