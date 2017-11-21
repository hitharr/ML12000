import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * This class is used in order to create a Naive Bayes classifier
 * Calculations for conditional probability are done in this class and utilized
 * in order to create the classifier
 */
public class Processing {
	// the attribute list
		//private ArrayList<String> allAttribs = new ArrayList<>(); 
		
		//used to identify
		//private ArrayList<BinaryNumber> binNumSet = new ArrayList<>();
		
		//private List<String[]> dataSet = new ArrayList<String[]>();
		private ArrayList<Instance> dataSet = new ArrayList<Instance>();
		private int numInstances;
		
		/**
		 * Creates a Naive Bayes classifier given a file name input
		 * @param input the name of the file input (will be the training set)
		 * @throws IOException error with input/output
		 */
		public void runAlg(String input) throws IOException{
			
			Scanner scan = new Scanner(new File(input));
			// Read the data into an instance class first 3 = binary, next 139 is reals, next number is 1 of 6, 
			//next 3 is reals, next 26 are out of 10, next 4 are out of 100, then the class value
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner scanLine = new Scanner(line);
				//while there is another line, a new instance is made and added
				Instance inst = new Instance();
				if (!line.isEmpty()){
					// split the line
					/**
					String[] lineSplit = new String[176];
					for(int a= 0; a < lineSplit.length; a++){
						lineSplit[a]= scanLine.next();
					}
					scanLine.close();
					// go through the dataSet and add
					dataSet.add(lineSplit);
					*/
					//scan in the 3 binary numbers
					for(int i = 0; i < 3; i++){
						inst.addBinary(Integer.parseInt(scanLine.next()));
					}
					
					//scan in the following 139 real numbers
					for(int i = 0; i < 139; i++){
						inst.addReal(Double.parseDouble(scanLine.next()));
					}
					
					//scan in the following year
					inst.addYear(Integer.parseInt(scanLine.next()));
					
					//scan in the following 3 reals
					for(int i = 0; i < 3; i++){
						inst.addReal(Double.parseDouble(scanLine.next()));
					}
					
					//scan in the following tens
					for(int i = 0; i < 26; i++){
						inst.addTens(Integer.parseInt(scanLine.next()));
					}
					
					//scan in the following hundreds
					for(int i = 0; i< 4; i++){
						inst.addHundreds(Integer.parseInt(scanLine.next()));
					}
					
					//scan in the class value (The final value)
					inst.addClass(Integer.parseInt(scanLine.next()));
					
					scanLine.close();
					dataSet.add(inst);
					/**
					//go through the dataSet of the class attribute
					classAttribVals.add(lineSplit[classAttribute]);
					*/
				}
			}
			scan.close();
		}
		/** 
		 * @return the number of instances
		 */
		public int getNumInstances(){
			return numInstances;
		}
}