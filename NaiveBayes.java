import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

/**
 * This class is used in order to create a Naive Bayes classifier
 * Calculations for conditional probability are done in this class and utilized
 * in order to create the classifier
 */
public class NaiveBayes {
	// the attribute list
		private ArrayList<String> allAttribs = new ArrayList<>(); 
		private ArrayList<BinaryNumber> binNumSet = new ArrayList<>();
		private List<String[]> dataSet = new ArrayList<String[]>();
		private int numInstances;
   		private double[] percent1Attrib;
		private double[] percent0Attrib;
		
		/**
		 * Creates a Naive Bayes classifier given a file name input
		 * @param input the name of the file input (will be the training set)
		 * @throws IOException error with input/output
		 */
		public void runAlg(String input) throws IOException{
			
			Scanner scan = new Scanner(new File(input));
			
			//read in the attributes (first line)
			while(allAttribs.isEmpty()){
	    		String line = scan.nextLine();
	    		//System.out.println("Attribute Line: " + line);
	        	if(line.length() > 0){
	            	Scanner scanLine = new Scanner(line);
	            	while(scanLine.hasNext()){
	            		allAttribs.add(scanLine.next());
	            	}
	            	scanLine.close();
	        	}
	    	}
			
			//System.out.println("Attributes: " + allAttribs);
			
			// Read the data into an arraylist of arrays
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner scanLine = new Scanner(line);
				if (!line.isEmpty()){
					// split the line
					String[] lineSplit = new String[allAttribs.size()];
					for(int a= 0; a < lineSplit.length; a++){
						lineSplit[a]= scanLine.next();
					}
					scanLine.close();
					// go through the dataSet and add
					dataSet.add(lineSplit);
					/**
					//go through the dataSet of the class attribute
					classAttribVals.add(lineSplit[classAttribute]);
					*/
				}
			}
			scan.close();
			//for testing
			/**
			for(String[] s: dataSet){
				System.out.println(Arrays.toString(s));
			}
			*/
			
			//Creates the binary number possibilities given the number of attributes minus the class attribute
			for(int i = 0; i< (int)Math.pow(2, allAttribs.size()-1); i++){
				binNumSet.add(new BinaryNumber(i, allAttribs.size()-1));
			}
			/**
			//testing to see if the binary numbers are done properly
			for(BinaryNumber bn: binaryNumberSet){
				System.out.println(bn.getDecimal() + ": " +bn.getBinary());
			}c
			*/
			
			//This code counts the number of 1's in each attribute
			double[] count1Attrib = new double[allAttribs.size()];
			double[] count0Attrib = new double[allAttribs.size()];
			double total = 0;
			for(String[] a: dataSet){
				for(int i = 0; i <a.length; i++){
					//System.out.print(a[i]);
					if(Integer.parseInt(a[i]) == 1){
						count1Attrib[i]++;
					}
				}
				total++;
				//System.out.println();
			}
			//System.out.println("Total #: " +total);
			
			//putting the count of 0's from the difference of total-count1
			for(int i = 0; i<count1Attrib.length; i++){
				count0Attrib[i] = total - count1Attrib[i];
			}
			
			percent1Attrib = new double[allAttribs.size()];
			percent0Attrib = new double[allAttribs.size()];
			for(int i = 0; i<count1Attrib.length; i++){
				percent1Attrib[i]=count1Attrib[i]/total;
				percent0Attrib[i]=count0Attrib[i]/total;
			}
			
			/**
			System.out.println("Percents given Attribs = 1");
			for(int i = 0; i<count1Attrib.length; i++){
				System.out.println(allAttribs.get(i) + ": " +percent1Attrib[i]);
			}
			
			System.out.println("Percents given Attribs = 0");
			for(int i = 0; i<count0Attrib.length; i++){
				System.out.println(allAttribs.get(i) + ": " +percent0Attrib[i]);
			}
			*/
			
			//this loops through through the binary numbers depending on whether or not the last digit(class)
			//is a 1 or 0
			
			for(BinaryNumber bn: binNumSet){	//loop through every binary number that is in the numSet
				int classifier = calcNaive(bn.getBinary(), percent0Attrib[percent0Attrib.length-1], percent1Attrib[percent1Attrib.length-1]);	//calculate the classifier
				bn.setClassifier(Integer.toString(classifier));	//set the classifier
			}
			/**
			//testing to see what the classifier classifies binary strings as
			for(BinaryNumber bn: binNumSet){
				System.out.println(bn.getBinary() + ": " + bn.getClassifier() + "\n");
			}
			*/
		}
		
		/**
		 * Calculates the accuracy of the test set based on the classifier created from the training set
		 * @param input the test file to check against the training set
		 * @throws IOException error with input/output
		 * @return the accuracy as a percentage
		 */
		public Double predict(String input) throws IOException{
			if(!dataSet.isEmpty()){
				dataSet = new ArrayList<String[]>();
			}
			Scanner scan = new Scanner(new File(input));
			double percent = 0.0;
			
			//System.out.println("Attributes: " + allAttribs);
			
			// Read the data into an arraylist of arrays
			scan.nextLine();
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner scanLine = new Scanner(line);
				if (!line.isEmpty()){
					// split the line
					String[] lineSplit = new String[allAttribs.size()];
					for(int a= 0; a < lineSplit.length; a++){
						lineSplit[a]= scanLine.next();
					}
					scanLine.close();
					// go through the dataSet and add
					dataSet.add(lineSplit);
				}
			}
			scan.close();
			
			double counter = 0.0;
			double total = 0.0;
			for(String[] s: dataSet){//loop through every row that has been input
				String str = "";
				for(int i = 0; i < s.length; i++){
					str = str + s[i];
				}
				String newBinNum = str.substring(0, str.length()-1);
				String newClass = str.substring(str.length()-1, str.length());
				//System.out.println(str);
				total++;	//add 1 to the total
				for(BinaryNumber bn : binNumSet){	//loop through each binary number
					//if the string (not classifier) == binary number && the string is classified the same
					if(newBinNum.equals(bn.getBinary()) && newClass.equals(bn.getClassifier())){
						counter++; // then the counter goes up
					}
				}
			}
			//System.out.println("Total: " + total + "\nCount: " + counter);
			//finding the total percentage and returning it
			percent = counter/total;
			numInstances = (int) total;
			return (percent)*100;
		}
		
		/**
		 * method to calculate the naive bayes probability given 
		 * @param binString the binary string of 1's and 0's to figure out which probabilities to use
		 * @param percArray the array of percentages that is taken in
		 * @return the naive bayesian probability
		 */
		private int calcNaive(String binString, double class0Perc, double class1Perc){
			//calculating the naive bayesian probability of the class being 0
			//the binary class probabilities of 1 and 0
			double class0 = LogBaseTwo(class0Perc);
			double class1 = LogBaseTwo(class1Perc);
			//System.out.println();
			//System.out.println(binString + ": ");
			for(int i = 0; i < binString.length(); i++){	//loop through every character in the binary string
				//System.out.println("Index: " + i);
				if(binString.charAt(i) == '0'){	//if the value in that spot is 0
					//System.out.println("Value0Class0: ");
					class0 += LogBaseTwo(calcConditional(i,0,0));	//count when class 0 and value is 0
					//System.out.println("Value0Class1: ");
					class1 += LogBaseTwo(calcConditional(i,0,1));	//count class 1 and value 0
				}
				else{	 //if the string is 1 rather than 0
					//System.out.println("Value1Class0: ");
					class0 += LogBaseTwo(calcConditional(i,1,0));	//count class 0 value 1
					//System.out.println("Value1Class1: ");
					class1 += LogBaseTwo(calcConditional(i,1,1));	//count class 1 value 1
				}
			}
			//System.out.println(binString + ": ");
			//System.out.println("\tClass = 0: " + class0);
			//System.out.println("\tClass = 1: " + class1);
			// if the 1 class percent for the binary string is bigger than the 0 class then return 1
			if(class0 < class1){
				return 1;
			}
			else if(class0 > class1){	//vice versa is 0
				return 0;
			}
			else{//when the values are equal
				return -1;
			}
		}
		
		/**
		 * calculate the conditional probability given the index(which attribute)
		 * @param index where the index is at
		 * @param indexVal what the index value should be
		 * @param classVal what the class value should be
		 * @return the conditional probability of (attribute @ index given the class value)
		 */
		private double calcConditional(int index, int indexVal, int classVal){
			double calc = 0.0;
			double counter = 0.0;
			double total = 0.0;
			//loop through data set
			for(String[] s: dataSet){
				//if the index of the data matches the index given and the class values also match then counter goes up
				String str = "";
				for(int i = 0; i < s.length; i++){
					str = str + s[i];
				}
				if(Integer.parseInt(str.substring(str.length()-1, str.length()))==classVal){
					total++;
					if(Integer.parseInt(str.substring(index, index+1)) == indexVal){
						counter++;
					}
				}
			}
			calc = counter/total;
			//System.out.println(calc);
			return calc;
		}
		
		
		/** 
		 * @return the number of instances
		 */
		public int getNumInstances(){
			return numInstances;
		}
		
		/**
		 * returns the log base two of a given value
		 * @param value the value to take the log of 
		 * @return log base two of a number
		 */
		private double LogBaseTwo(double value){
			return Math.log(value)/Math.log(2);
		}
		
		
		/**
		 * Prints the parameters of the classifier that were estimated from the training data
		 */		
		public void printAll(){
   		   	DecimalFormat df = new DecimalFormat("#.##");
   			//loop on the two possible class values
			for(int c=0; c<2; c++){
				System.out.print("P(class=" + c+ ")=");
		        if(c==1){
		          System.out.print(df.format(percent1Attrib[percent1Attrib.length-1])+ " ");
		        }
		        else{
		          System.out.print(df.format(percent0Attrib[percent0Attrib.length-1])+ " ");
		        }
				//loop for the difference attributes
				for(int attrIndex = 0; attrIndex<allAttribs.size()-1; attrIndex++){
					//loop for the difference values of attributes
					for(int val=0; val<2; val++){
						System.out.print("P(" + allAttribs.get(attrIndex)+ "=" + val+ "|"+c+ ")="+ df.format(calcConditional(attrIndex, val, c)) + " ");
					}
				}
				System.out.println();
			}
		}
}